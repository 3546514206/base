/************************************************************
 * 通讯管理类,用来接收和发送数据
 * 通讯协议基于Udp
 * sendData()可以被外部调用以发送数据
 * 对接收到的数据进行分析后转交给对应的Handler类进行处理
 * 提供两个便利的new数据成员来提取最新的 发送/接收 数据报
 */

#ifndef COMMUNICATIONMANAGER_H
#define COMMUNICATIONMANAGER_H

#include <QObject>
#include <QHostAddress>

class QUdpSocket;
class DatabaseManager;

class CommunicationManager : public QObject
{
    Q_OBJECT
public:
    explicit CommunicationManager(QObject *parent = 0);
    Q_PROPERTY(QString newSendByteData READ newSendByteData WRITE setNewSendByteData NOTIFY newSendByteDataChanged)
    Q_PROPERTY(QString newReceivedByteData READ newReceivedByteData WRITE setNewReceivedByteData NOTIFY newReceivedByteDataChanged)

    Q_INVOKABLE void startMonitor();
    void sendData(QByteArray &);

    void setNewSendByteData(QString);
    void setNewReceivedByteData(QString);
    QString newSendByteData();
    QString newReceivedByteData();

signals:
    void newSendByteDataChanged();
    void newReceivedByteDataChanged();

private slots:
    void receiveData();

private:
    QString m_newSendByteData;
    QString m_newReceivedByteData;

    DatabaseManager *manager;
    QUdpSocket *sendUdpSocket;
    QHostAddress groupAddress;
    QUdpSocket *recvUdpSocket;
};

#endif // COMMUNICATIONMANAGER_H
