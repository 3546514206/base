#include "localcommunicationhandler.h"
#include "addcrc.h"
#include "databasemanager.h"

#include <QTimer>
#include <QtSerialPort/QSerialPort>
#include <QDebug>

LocalCommunicationHandler::LocalCommunicationHandler(QObject *parent) :
    QObject(parent)
{
    manager = new DatabaseManager(this);

    serial = new QSerialPort(this);
    serial->setPortName("COM1");
    serial->setBaudRate(QSerialPort::Baud115200);
    serial->setDataBits(QSerialPort::Data8);
    serial->setFlowControl(QSerialPort::NoFlowControl);
    serial->setParity(QSerialPort::NoParity);
    serial->setStopBits(QSerialPort::OneStop);

    timer = new QTimer(this);
    timer->setInterval(60000);
    connect(timer, SIGNAL(timeout()), this, SLOT(slotTimeoutHandler()));
}

void LocalCommunicationHandler::init()
{
    if (!serial->open(QIODevice::ReadWrite)) {
        qDebug() << QString("Can't open %1, error code %2")
                   .arg(serial->portName()).arg(serial->error());
        return;
    }
    timer->start();
}

void LocalCommunicationHandler::setPortName(QString str)
{
    serial->close();
    serial->setPortName(str);

    if (!serial->open(QIODevice::ReadWrite)) {
        qDebug() << QString("Can't open %1, error code %2")
                   .arg(serial->portName()).arg(serial->error());
        return;
    }
}

void LocalCommunicationHandler::setInterval(QString str)
{
    //30s,60s,90s
    str = str.remove("s");
    timer->setInterval(str.toInt() * 1000);
    qDebug() << str.toInt();
    timer->start();
}

void LocalCommunicationHandler::setBaudRate(QString str)
{
    serial->close();

    //4800,9600,19200,38400,57600,115200
    if ( str == "4800" )
        serial->setBaudRate(QSerialPort::Baud4800);
    else if ( str == "9600" )
        serial->setBaudRate(QSerialPort::Baud9600);
    else if ( str == "19200" )
        serial->setBaudRate(QSerialPort::Baud19200);
    else if ( str == "38400" )
        serial->setBaudRate(QSerialPort::Baud38400);
    else if ( str == "57600" )
        serial->setBaudRate(QSerialPort::Baud57600);
    else if ( str == "115200" )
        serial->setBaudRate(QSerialPort::Baud115200);

    if (!serial->open(QIODevice::ReadWrite)) {
        qDebug() << QString("Can't open %1, error code %2")
                   .arg(serial->portName()).arg(serial->error());
        return;
    }
}

void LocalCommunicationHandler::setDataBits(QString str)
{
    serial->close();

    // 5,6,7,8
    if ( str == "5" )
        serial->setDataBits(QSerialPort::Data5);
    else if ( str == "6" )
        serial->setDataBits(QSerialPort::Data6);
    else if ( str == "7" )
        serial->setDataBits(QSerialPort::Data7);
    else if ( str == "8" )
        serial->setDataBits(QSerialPort::Data8);

    if (!serial->open(QIODevice::ReadWrite)) {
        qDebug() << QString("Can't open %1, error code %2")
                   .arg(serial->portName()).arg(serial->error());
        return;
    }
}

void LocalCommunicationHandler::setParity(QString str)
{
    serial->close();

    // 无校验,偶校验,奇校验
    if ( str == "无校验" )
        serial->setParity(QSerialPort::NoParity);
    else if ( str == "偶校验" )
        serial->setParity(QSerialPort::EvenParity);
    else if ( str == "奇校验" )
        serial->setParity(QSerialPort::OddParity);

    if (!serial->open(QIODevice::ReadWrite)) {
        qDebug() << QString("Can't open %1, error code %2")
                   .arg(serial->portName()).arg(serial->error());
        return;
    }
}

void LocalCommunicationHandler::setFlowControl(QString str)
{
    serial->close();

    //无流控,硬流控,软流控
    if ( str == "无流控" )
        serial->setFlowControl(QSerialPort::NoFlowControl);
    else if ( str == "硬流控" )
        serial->setFlowControl(QSerialPort::HardwareControl);
    else if ( str == "软流控" )
        serial->setFlowControl(QSerialPort::SoftwareControl);

    if (!serial->open(QIODevice::ReadWrite)) {
        qDebug() << QString("Can't open %1, error code %2")
                   .arg(serial->portName()).arg(serial->error());
        return;
    }
}

void LocalCommunicationHandler::setStopBits(QString str)
{
    serial->close();

    //1,1.5,2
    if ( str == "1" )
        serial->setStopBits(QSerialPort::OneStop);
    else if ( str == "1.5" )
        serial->setStopBits(QSerialPort::OneAndHalfStop);
    else if ( str == "2" )
        serial->setStopBits(QSerialPort::TwoStop);

    if (!serial->open(QIODevice::ReadWrite)) {
        qDebug() << QString("Can't open %1, error code %2")
                   .arg(serial->portName()).arg(serial->error());
        return;
    }
}

void LocalCommunicationHandler::newDataArrived(QByteArray& datagram)
{
    qDebug() << "收到的数据帧是: " << datagram.toHex();
    // 将接收数据写入属性值
    QString str = datagram.toHex().toUpper();
    for ( int i = 2; i < str.length(); i += 3 )
    {
        str = str.insert(i, " ");
    }
    setNewReceiveData(str);

    QByteArray fixedHead;
    fixedHead.append(0xEB);
    fixedHead.append(0x19);
    if( !datagram.startsWith(fixedHead) )
    {
        qDebug() << "帧头不合法!";
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
        qDebug() << "闸门数据返回 " << datagram.toHex();
        if ( datagram.length() != 15 )
            return;
        // 0xeb 0x19 目标站编号 源站编号 0x51 0x06 闸门上限 闸门下限 闸门开度 校验码 0x03 0x0d
        QStringList list;
        list.append(QString::number((unsigned char)datagram.at(6) + (unsigned char)datagram.at(7)*0.01));
        list.append(QString::number((unsigned char)datagram.at(8) + (unsigned char)datagram.at(9)*0.01));
        list.append(QString::number((unsigned char)datagram.at(10) + (unsigned char)datagram.at(11)*0.01));

        manager->updateSluiceData((unsigned char)datagram.at(2), list);

        newestUpperlimit = list.at(0);
        newestLowerlimit = list.at(1);
    }
    if( datagram.at(4) == 0X52 )
    {
        qDebug() << "闸门传感器状态返回 " << datagram.toHex();
        if ( datagram.length() != 12 )
            return;
        // 0xeb 0x19 目标站编号 源站编号 0x52 0x03 上限传感器状态 下限传感器状态 开度传感器状态 校验码 0x03 0x0d
        QList<int> list;
        list.append((unsigned char)datagram.at(6));
        list.append((unsigned char)datagram.at(7));
        list.append((unsigned char)datagram.at(8));
        manager->updateSluiceSensor((unsigned char)datagram.at(2), list);
    }
    if( datagram.at(4) == 0X53 )
    {
        qDebug() << "环境数据返回 " << datagram.toHex();
        if ( datagram.length() != 19 )
            return;
        // 0xeb 0x19 目标站编号 源站编号 0x53 0x0a 上游水位 下游水位 流量 雨量 风向 校验码 0x03 0x0d
        QStringList list;
        list.append(QString::number((unsigned char)datagram.at(6) + (unsigned char)datagram.at(7)*0.01));
        list.append(QString::number((unsigned char)datagram.at(8) + (unsigned char)datagram.at(9)*0.01));
        list.append(QString::number((unsigned char)datagram.at(10) + (unsigned char)datagram.at(11)*0.01));
        list.append(QString::number((unsigned char)datagram.at(12) + (unsigned char)datagram.at(13)*0.01));
        list.append(QString::number((unsigned char)datagram.at(14) + (unsigned char)datagram.at(15)*0.01));

        manager->updateEnvironmentalData(list);
    }
    if( datagram.at(4) == 0X54 )
    {
        qDebug() << "环境传感器状态返回 " << datagram.toHex();
        if ( datagram.length() != 14 )
            return;
        // 0xeb 0x19 目标站编号 源站编号 0x54 0x05 上游水位传感器状态 下游水位传感器状态 流量传感器状态 雨量传感器状态 风向传感器状态 校验码 0x03 0x0d
        QList<int> list;
        list.append((unsigned char)datagram.at(6));
        list.append((unsigned char)datagram.at(7));
        list.append((unsigned char)datagram.at(8));
        list.append((unsigned char)datagram.at(9));
        list.append((unsigned char)datagram.at(10));

        manager->updateEnvironmentalSensor(list);
    }
    if( datagram.at(4) == 0X61 )
    {
        qDebug() << "闸门开启控制数据返回 " << datagram.toHex();
        if ( datagram.length() != 11 )
            return;
        // 0xeb	0x19 目标站编号 源站编号 0x61 0x02 当前开度值 校验码 0x03 0x0d
        QStringList list;
        list.append(newestUpperlimit);
        list.append(newestLowerlimit);
        list.append(QString::number((unsigned char)datagram.at(6) + (unsigned char)datagram.at(7)*0.01));

        manager->updateSluiceData((unsigned char)datagram.at(2), list);
    }
    if( datagram.at(4) == 0X62 )
    {
        qDebug() << "闸门关闭数据返回 " << datagram.toHex();
        if ( datagram.length() != 11 )
            return;
        // 0xeb 0x19 目标站编号 源站编号 0x62 0x02 当前开度值 校验码 0x03 0x0d
        QStringList list;
        list.append(newestUpperlimit);
        list.append(newestLowerlimit);
        list.append(QString::number((unsigned char)datagram.at(6) + (unsigned char)datagram.at(7)*0.01));

        manager->updateSluiceData((unsigned char)datagram.at(2), list);
    }
}

void LocalCommunicationHandler::sendSerialData(QByteArray& requestData)
{
    serial->write(requestData);

    // 将发送数据写入属性值
    QString str = requestData.toHex().toUpper();
    for ( int i = 2; i < str.length(); i += 3 )
    {
        str = str.insert(i, " ");
    }
    setNewSendData(str);

    if (serial->waitForBytesWritten(1000)) {
        // 读取响应
        if (serial->waitForReadyRead(1000)) {
            QByteArray responseData = serial->readAll();
            while (serial->waitForReadyRead(10))
            {
               responseData += serial->readAll();
            }
            newDataArrived(responseData);
        } else {
            qDebug() << QString("Wait read response timeout");
        }

    } else {
        qDebug() << QString("Wait write request timeout");
    }

}

void LocalCommunicationHandler::sluiceDataCollection()
{
    //0xeb 0x19 目标站编号 源站编号 0x51 0x00 校验码 0x03 0x0d
    unsigned char requestData[9] = {0XEB, 0X19, 0XFF, 0XFF, 0X51, 0X00, 0X00, 0X03, 0X0D};
    // 添加CRC8校验码
    requestData[6] = CRC8_Tab(requestData, 9);

    QByteArray array;
    array.append((const char*)requestData, 9);
    sendSerialData(array);
}

void LocalCommunicationHandler::sluiceSensorStateCollection()
{
    //0xeb 0x19 目标站编号 源站编号 0x52 0x00 校验码 0x03 0x0d
    unsigned char requestData[9] = {0XEB, 0X19, 0XFF, 0XFF, 0X52, 0X00, 0X00, 0X03, 0X0D};
    // 添加CRC8校验码
    requestData[6] = CRC8_Tab(requestData, 9);

    QByteArray array;
    array.append((const char*)requestData, 9);
    sendSerialData(array);
}

void LocalCommunicationHandler::environmentalDataCollection()
{
    //0xeb 0x19 目标站编号 源站编号 0x53 0x00 校验码 0x03 0x0d
    unsigned char requestData[9] = {0XEB, 0X19, 0XFF, 0XFF, 0X53, 0X00, 0X00, 0X03, 0X0D};
    // 添加CRC8校验码
    requestData[6] = CRC8_Tab(requestData, 9);

    QByteArray array;
    array.append((const char*)requestData, 9);
    sendSerialData(array);
}

void LocalCommunicationHandler::environmentalSensorStateCollection()
{
    //0xeb 0x19 目标站编号 源站编号 0x54 0x00 校验码 0x03 0x0d
    unsigned char requestData[9] = {0XEB, 0X19, 0XFF, 0XFF, 0X54, 0X00, 0X00, 0X03, 0X0D};
    // 添加CRC8校验码
    requestData[6] = CRC8_Tab(requestData, 9);

    QByteArray array;
    array.append((const char*)requestData, 9);
    sendSerialData(array);
}

void LocalCommunicationHandler::sluiceOpenControl(int id, float openingvalue)
{
    //0xeb 0x19 目标站编号 源站编号 0x61 0x02 开度值 校验码 0x03 0x0d
    unsigned char requestData[11] = {0XEB, 0X19, 0XFF, 0XFF, 0X61, 0X02, 0X00, 0X00, 0X00, 0X03, 0X0D};

    requestData[2] = id;
    // float转Char,保留2位小数
    requestData[6] = ((signed short)(openingvalue*100)) >> 8;
    requestData[7] = ((signed short)(openingvalue*100)) & 0xFF;
    // 添加CRC8校验码
    requestData[8] = CRC8_Tab(requestData, 11);

    QByteArray array;
    array.append((const char*)requestData, 11);
    sendSerialData(array);
}

void LocalCommunicationHandler::sluiceCloseControl(int id, float openingvalue)
{
    //0xeb 0x19 目标站编号 源站编号 0x61 0x02 开度值 校验码 0x03 0x0d
    unsigned char requestData[11] = {0XEB, 0X19, 0XFF, 0XFF, 0X61, 0X02, 0X00, 0X00, 0X00, 0X03, 0X0D};

    requestData[2] = id;
    // float转Char,保留2位小数
    requestData[6] = ((signed short)(openingvalue*100)) >> 8;
    requestData[7] = ((signed short)(openingvalue*100)) & 0xFF;
    // 添加CRC8校验码
    requestData[8] = CRC8_Tab(requestData, 11);

    QByteArray array;
    array.append((const char*)requestData, 11);
    sendSerialData(array);
}

void LocalCommunicationHandler::slotTimeoutHandler()
{
    sluiceDataCollection();
    environmentalDataCollection();

    sluiceSensorStateCollection();
    environmentalSensorStateCollection();
}

QString LocalCommunicationHandler::newSendData()
{
    return m_newSendData;
}

void LocalCommunicationHandler::setNewSendData(QString& data)
{
    m_newSendData = data;
    emit newSendDataChanged();
}

QString LocalCommunicationHandler::newReceiveData()
{
    return m_newReceiveData;
}

void LocalCommunicationHandler::setNewReceiveData(QString& data)
{
    m_newReceiveData = data;
    emit newReceiveDataChanged();
}
