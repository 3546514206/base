#ifndef RESTARTHANDLER_H
#define RESTARTHANDLER_H

#include <QObject>

class RestartHandler : public QObject
{
    Q_OBJECT
public:
    explicit RestartHandler(QObject *parent = 0);
    Q_INVOKABLE void restart();

signals:

public slots:

};

#endif // RESTARTHANDLER_H
