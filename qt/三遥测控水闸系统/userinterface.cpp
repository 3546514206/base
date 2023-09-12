#include "userinterface.h"
#include "localcommunicationhandler.h"
#include <QTimer>
#include <QDebug>

UserInterface::UserInterface(QObject *parent) :
    QObject(parent)
{
    QTimer *timer = new QTimer(this);
    timer->setInterval(2000);
    timer->start();
    connect(timer, SIGNAL(timeout()), this, SIGNAL(simulationNewDataArrived()));

    //localComHandler = new LocalCommunicationHandler(this);
}

/************************************************************
 * 开度设置响应函数
 */
void UserInterface::setNewOpeningvalue(int sluiceId, float value)
{
    qDebug() << "设置" << sluiceId << "号闸机的开度: " << value;
    //localComHandler->sluiceOpenControl(value);
}

/************************************************************
 * 控制方式响应函数
 */
void UserInterface::setControlMethod(int method)
{
    qDebug() << "设置控制方式 " << method;
}

/************************************************************
 * 继电器状态响应函数
 */
void UserInterface::requestRelayState(void)
{
    qDebug() << "请求继电器状态";
}

/************************************************************
 * 传感器响应函数
 */
void UserInterface::requestSensorState(void)
{
    qDebug() << "请求传感器状态";
    //localComHandler->sluiceSensorStateCollection();
    //localComHandler->environmentalSensorStateCollection();
}

// TODO: 这里的QByteArray应该从通信类中取得
/************************************************************
 * 获取现地设备接收数据响应函数
 */
QString UserInterface::requestLocalCommunicationReceivedData()
{
    QByteArray byteArray;
    byteArray[0] = 0X51;
    byteArray[1] = 0X92;
    byteArray[2] = 0X2C;
    byteArray[3] = 0X4F;
    byteArray[4] = 0XA1;

    QString retString = byteArray.toHex().toUpper();
    int i = retString.size();
    while(i > 0)
    {
        retString.insert(i, " ");
        i -= 2;
    }

    qDebug() << "请求获取接收自现地设备的通信数据" << retString;
    return retString;
}

/************************************************************
 * 获取现地设备发送数据响应函数
 */
QString UserInterface::requestLocalCommunicationSendData()
{
    QByteArray byteArray;
    byteArray[0] = 0X41;
    byteArray[1] = 0X42;
    byteArray[2] = 0X2C;
    byteArray[3] = 0X48;
    byteArray[4] = 0XA2;

    QString retString = byteArray.toHex().toUpper();
    int i = retString.size();
    while(i > 0)
    {
        retString.insert(i, " ");
        i -= 2;
    }

    qDebug() << "请求获取向现地设备发送的通信数据" << retString;
    return retString;
}

/************************************************************
 * 获取远程主机接收数据响应函数
 */
QString UserInterface::requestRemoteCommunicationReceivedData()
{
    QByteArray byteArray;
    byteArray[0] = 0XAA;
    byteArray[1] = 0X01;
    byteArray[2] = 0X2C;
    byteArray[3] = 0X02;
    byteArray[4] = 0X40;

    QString retString = byteArray.toHex().toUpper();
    int i = retString.size() - 2;
    while(i > 0)
    {
        retString.insert(i, " ");
        i -= 2;
    }

    qDebug() << "请求获取接收自远程主机的通信数据" << retString;
    return retString;
}

/************************************************************
 * 获取远程主机发送数据响应函数
 */
QString UserInterface::requestRemoteCommunicationSendData()
{
    QByteArray byteArray;
    byteArray[0] = 0X28;
    byteArray[1] = 0XFC;
    byteArray[2] = 0X2C;
    byteArray[3] = 0XA5;
    byteArray[4] = 0X07;

    QString retString = byteArray.toHex().toUpper();
    int i = retString.size();
    while(i > 0)
    {
        retString.insert(i, " ");
        i -= 2;
    }

    qDebug() << "请求获取向远程主机发送的通信数据" << retString;
    return retString;
}
