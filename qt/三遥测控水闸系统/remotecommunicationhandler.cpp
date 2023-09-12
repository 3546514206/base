#include "remotecommunicationhandler.h"
#include "communicationmanager.h"
#include <QDebug>

RemoteCommunicationHandler::RemoteCommunicationHandler(QObject *parent) :
    QObject(parent)
{
}

void RemoteCommunicationHandler::NewDataArrived(QByteArray& datagram)
{
    qDebug() << "新的远程主机数据!";
    if( datagram.startsWith(0X01) )
    {
        qDebug() << "远程设备请求帧";
        datagram.remove(0, 1);
    }
    else if( datagram.startsWith(0X02) )
    {
        qDebug() << "远程设备数据帧";
        datagram.remove(0, 1);
    }
}
