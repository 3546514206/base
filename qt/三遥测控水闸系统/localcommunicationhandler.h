/************************************************************
 * 本地通讯数据处理者,用来进一步对通信管理类接收到的数据进行解析
 * 并将需要发送的数据打包成合适的格式交给通信管理类进行发送
 *
 */
#ifndef LOCALCOMMUNICATIONHANDLER_H
#define LOCALCOMMUNICATIONHANDLER_H

#include "databasemanager.h"
#include <QObject>

class QTimer;
class QSerialPort;
class DatabaseManager;

class LocalCommunicationHandler : public QObject
{
    Q_OBJECT
public:
    explicit LocalCommunicationHandler(QObject *parent = 0);
    Q_INVOKABLE void init();
    Q_INVOKABLE void sluiceOpenControl(int id, float openingvalue);
    Q_INVOKABLE void sluiceCloseControl(int id, float openingvalue);

    Q_INVOKABLE void setPortName(QString str);
    Q_INVOKABLE void setInterval(QString str);
    Q_INVOKABLE void setBaudRate(QString str);
    Q_INVOKABLE void setDataBits(QString str);
    Q_INVOKABLE void setParity(QString str);
    Q_INVOKABLE void setFlowControl(QString str);
    Q_INVOKABLE void setStopBits(QString str);

    Q_PROPERTY(QString newSendData READ newSendData WRITE setNewSendData NOTIFY newSendDataChanged)
    Q_PROPERTY(QString newReceiveData READ newReceiveData WRITE setNewReceiveData NOTIFY newReceiveDataChanged)

    QString newSendData();
    void setNewSendData(QString&);
    QString newReceiveData();
    void setNewReceiveData(QString&);

    void sendSerialData(QByteArray&);
    void newDataArrived(QByteArray& requestData);

    void sluiceDataCollection();
    void sluiceSensorStateCollection();
    void environmentalDataCollection();
    void environmentalSensorStateCollection();

signals:
    void newSendDataChanged();
    void newReceiveDataChanged();

public slots:

private slots:
    void slotTimeoutHandler();

private:
    QTimer *timer;
    QSerialPort *serial;
    DatabaseManager *manager;
    QString newestUpperlimit;
    QString newestLowerlimit;

    QString m_newSendData;
    QString m_newReceiveData;
};

#endif // LOCALCOMMUNICATIONHANDLER_H
