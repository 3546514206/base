/************************************************************
 * 用户交互类,系统所有来自用户的指令均由该类进行分发
 * 是Qml界面与C++底层交互的桥梁
 *
 */
#ifndef USERINTERFACE_H
#define USERINTERFACE_H

#include <QObject>
class CommunicationHandler;
class LocalCommunicationHandler;

class UserInterface : public QObject
{
    Q_OBJECT
public:
    explicit UserInterface(QObject *parent = 0);

    Q_INVOKABLE void setNewOpeningvalue(int sluiceId, float value);
    Q_INVOKABLE void setControlMethod(int method);
    Q_INVOKABLE void requestRelayState(void);
    Q_INVOKABLE void requestSensorState(void);
    Q_INVOKABLE QString requestLocalCommunicationSendData();
    Q_INVOKABLE QString requestLocalCommunicationReceivedData();
    Q_INVOKABLE QString requestRemoteCommunicationSendData();
    Q_INVOKABLE QString requestRemoteCommunicationReceivedData();

signals:
    void simulationNewDataArrived();

public slots:

private:
    CommunicationHandler *comHandler;
    //LocalCommunicationHandler *localComHandler;
};

#endif // USERINTERFACE_H
