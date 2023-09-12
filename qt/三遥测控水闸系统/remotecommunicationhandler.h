/************************************************************
 * 本地通讯数据处理者,用来进一步对通信管理类接收到的数据进行解析
 * 并将需要发送的数据打包成合适的格式交给通信管理类进行发送
 *
 */
#ifndef REMOTECOMMUNICATIONHANDLER_H
#define REMOTECOMMUNICATIONHANDLER_H

#include <QObject>
class CommunicationManager;

class RemoteCommunicationHandler : public QObject
{
    Q_OBJECT
public:
    explicit RemoteCommunicationHandler(QObject *parent);
    void NewDataArrived(QByteArray&);

signals:

public slots:

};

#endif // REMOTECOMMUNICATIONHANDLER_H
