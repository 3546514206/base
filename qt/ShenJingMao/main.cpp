#include <QGuiApplication>
#include <QProcess>
#include <QQmlApplicationEngine>
#include <QQmlComponent>
#include "restarthandler.h"

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    qmlRegisterType<RestartHandler>("RestartHandler", 1, 0, "RestartHandler");

    QQmlApplicationEngine engine;
    engine.load(QUrl(QStringLiteral("qrc:///main.qml")));

    int ret = app.exec();
    if (ret == 773) {
        QProcess::startDetached(qApp->applicationFilePath(), QStringList());
        return 0;
    }
    return ret;
}
