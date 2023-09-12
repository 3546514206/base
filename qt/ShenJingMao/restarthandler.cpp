#include "restarthandler.h"
#include <QCoreApplication>

RestartHandler::RestartHandler(QObject *parent) :
    QObject(parent)
{
}

void RestartHandler::restart()
{
    QCoreApplication::instance()->exit(773);
}
