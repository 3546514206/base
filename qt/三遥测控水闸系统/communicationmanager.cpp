#include "communicationmanager.h"
#include "databasemanager.h"
#include "addcrc.h"
#include <QUdpSocket>
#include <QDebug>

CommunicationManager::CommunicationManager(QObject *parent) :
    QObject(parent)
{
    manager = new DatabaseManager(this);
}

// 开始监听
void CommunicationManager::startMonitor()
{
    groupAddress = QHostAddress("239.255.43.21");
    sendUdpSocket = new QUdpSocket(this);

    recvUdpSocket = new QUdpSocket(this);
    recvUdpSocket->bind(QHostAddress::AnyIPv4, 45454, QUdpSocket::ShareAddress);
    recvUdpSocket->joinMulticastGroup(groupAddress);
    connect(recvUdpSocket, SIGNAL(readyRead()), this, SLOT(receiveData()));
}

QString CommunicationManager::newSendByteData()
{
    return m_newSendByteData;
}

void CommunicationManager::setNewSendByteData(QString data)
{
    m_newSendByteData = data;
    emit newSendByteData();
}

QString CommunicationManager::newReceivedByteData()
{
    return m_newReceivedByteData;
}

void CommunicationManager::setNewReceivedByteData(QString data)
{
    m_newReceivedByteData = data;
    emit newReceivedByteDataChanged();
}

void CommunicationManager::receiveData()
{
    while ( recvUdpSocket->hasPendingDatagrams() )
    {
        QByteArray datagram;
        datagram.resize(recvUdpSocket->pendingDatagramSize());
        recvUdpSocket->readDatagram(datagram.data(), datagram.size());

        // 接收到新的数据写入属性值
        QString str = datagram.toHex().toUpper();
        for ( int i = 2; i < str.length(); i += 3 )
        {
            str = str.insert(i, " ");
        }
        setNewReceivedByteData(str);

        QByteArray fixedHead;
        fixedHead.append(0xEB);
        fixedHead.append(0x19);
        if( !datagram.startsWith(fixedHead) )
        {
            qDebug() << "远端数据帧头不合法!";
            return;
        }

        unsigned int crc8 = datagram.at(datagram.length() - 3);
        crc8 &= 0x000000FF;
        datagram[datagram.length() - 3] = 0x00;
        if ( crc8 != CRC8_Tab((unsigned char*)datagram.data(), datagram.at(5) + 9))
        {
            qDebug() << "CRC校验不匹配! 应该是 " << CRC8_Tab((unsigned char*)datagram.data(), datagram.at(5) + 9)
                        << "提取到的CRC8为 " << crc8;
            return;
        }

        if( datagram.at(4) == 0X51 )
        {
            qDebug() << "远端请求闸门数据 " << datagram.toHex();
            if ( datagram.length() != 9 )
                return;

            unsigned char answerData[15] = {0XEB, 0X19, 0XFF, 0XFF, 0X51, 0X06, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X03, 0X0D};
            QStringList dataList = manager->requestSluiceData(1, datagram[2]).split(",");

            // float转Char,保留2位小数
            answerData[6] = ((signed short)(dataList.at(2).toFloat()*100)) >> 8;
            answerData[7] = ((signed short)(dataList.at(2).toFloat()*100)) & 0xFF;
            answerData[8] = ((signed short)(dataList.at(3).toFloat()*100)) >> 8;
            answerData[9] = ((signed short)(dataList.at(3).toFloat()*100)) & 0xFF;
            answerData[10] = ((signed short)(dataList.at(4).toFloat()*100)) >> 8;
            answerData[11] = ((signed short)(dataList.at(4).toFloat()*100)) & 0xFF;

            answerData[12] = CRC8_Tab(answerData, 15);

            QByteArray array;
            array.append((const char*)answerData, 15);
            sendData(array);
        }

        if( datagram.at(4) == 0X52 )
        {
            qDebug() << "远端请求闸门传感器状态 " << datagram.toHex();
            if ( datagram.length() != 9 )
                return;

            unsigned char answerData[12] = {0XEB, 0X19, 0XFF, 0XFF, 0X52, 0X03, 0X00, 0X00, 0X00, 0X00, 0X03, 0X0D};
            QStringList dataList = manager->requestSluiceSensor(1, datagram[2]).split(",");

            // int转Char
            answerData[6] = dataList.at(1).toInt() & 0x000000FF;
            answerData[7] = dataList.at(2).toInt() & 0x000000FF;
            answerData[8] = dataList.at(3).toInt() & 0x000000FF;

            answerData[9] = CRC8_Tab(answerData, 12);

            QByteArray array;
            array.append((const char*)answerData, 12);
            sendData(array);
        }

        if( datagram.at(4) == 0X53 )
        {
            qDebug() << "远端请求环境数据 " << datagram.toHex();
            if ( datagram.length() != 9 )
                return;

            unsigned char answerData[19] = {0XEB, 0X19, 0XFF, 0XFF, 0X53, 0X0a, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X03, 0X0D};
            QStringList dataList = manager->requestEnvironmentalData(1).split(",");

            // float转Char,保留2位小数
            answerData[6] = ((signed short)(dataList.at(2).toFloat()*100)) >> 8;
            answerData[7] = ((signed short)(dataList.at(2).toFloat()*100)) & 0xFF;
            answerData[8] = ((signed short)(dataList.at(3).toFloat()*100)) >> 8;
            answerData[9] = ((signed short)(dataList.at(3).toFloat()*100)) & 0xFF;
            answerData[10] = ((signed short)(dataList.at(4).toFloat()*100)) >> 8;
            answerData[11] = ((signed short)(dataList.at(4).toFloat()*100)) & 0xFF;
            answerData[12] = ((signed short)(dataList.at(5).toFloat()*100)) >> 8;
            answerData[13] = ((signed short)(dataList.at(5).toFloat()*100)) & 0xFF;
            answerData[14] = ((signed short)(dataList.at(6).toFloat()*100)) >> 8;
            answerData[15] = ((signed short)(dataList.at(6).toFloat()*100)) & 0xFF;

            answerData[16] = CRC8_Tab(answerData, 19);

            QByteArray array;
            array.append((const char*)answerData, 19);
            sendData(array);
        }

        if( datagram.at(4) == 0X54 )
        {
            qDebug() << "远端请求环境传感器状态 " << datagram.toHex();
            if ( datagram.length() != 9 )
                return;

            unsigned char answerData[14] = {0XEB, 0X19, 0XFF, 0XFF, 0X54, 0X05, 0X00, 0X00, 0X00, 0X00, 0X00, 0X00, 0X03, 0X0D};
            QStringList dataList = manager->requestEnvironmentalSensor(1).split(",");

            // int转Char
            answerData[6] = dataList.at(1).toInt() & 0x000000FF;
            answerData[7] = dataList.at(2).toInt() & 0x000000FF;
            answerData[8] = dataList.at(3).toInt() & 0x000000FF;
            answerData[9] = dataList.at(4).toInt() & 0x000000FF;
            answerData[10] = dataList.at(5).toInt() & 0x000000FF;

            answerData[11] = CRC8_Tab(answerData, 14);

            QByteArray array;
            array.append((const char*)answerData, 14);
            sendData(array);
        }

        if( datagram.at(4) == 0X61 )
        {
            qDebug() << "远端请求控制闸门开启 " << datagram.toHex();
            if ( datagram.length() != 11 )
                return;

            unsigned char answerData[11] = {0XEB, 0X19, 0XFF, 0XFF, 0X61, 0X02, 0X00, 0X00, 0X00, 0X03, 0X0D};
            QStringList dataList = manager->requestSluiceData(1, datagram[2]).split(",");

            // float转Char,保留2位小数
            answerData[6] = ((signed short)(dataList.at(4).toFloat()*100)) >> 8;
            answerData[7] = ((signed short)(dataList.at(4).toFloat()*100)) & 0xFF;

            answerData[8] = CRC8_Tab(answerData, 11);

            QByteArray array;
            array.append((const char*)answerData, 11);
            sendData(array);
        }

        if( datagram.at(4) == 0X62 )
        {
            qDebug() << "远端请求控制闸门关闭 " << datagram.toHex();
            if ( datagram.length() != 11 )
                return;

            unsigned char answerData[11] = {0XEB, 0X19, 0XFF, 0XFF, 0X62, 0X02, 0X00, 0X00, 0X00, 0X03, 0X0D};
            QStringList dataList = manager->requestSluiceData(1, datagram[2]).split(",");

            // float转Char,保留2位小数
            answerData[6] = ((signed short)(dataList.at(4).toFloat()*100)) >> 8;
            answerData[7] = ((signed short)(dataList.at(4).toFloat()*100)) & 0xFF;

            answerData[8] = CRC8_Tab(answerData, 11);

            QByteArray array;
            array.append((const char*)answerData, 11);
            sendData(array);
        }
    }
}

// 发送数据调用函数
void CommunicationManager::sendData(QByteArray &datagram)
{
    sendUdpSocket->writeDatagram(datagram.data(), datagram.size(),  groupAddress, 45455);
    // 发送新的数据写入属性值
    QString str = datagram.toHex().toUpper();
    for ( int i = 2; i < str.length(); i += 3 )
    {
        str = str.insert(i, " ");
    }
    setNewSendByteData(str);
}
