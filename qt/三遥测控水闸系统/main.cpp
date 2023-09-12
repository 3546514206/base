#include "arc.h"
#include "connection.h"
#include "databasemanager.h"
#include "communicationmanager.h"
#include "localcommunicationhandler.h"
#include <QGuiApplication>
//#include <QQmlContext>
#include <QQmlApplicationEngine>

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);
    if (!createConnection())
        return 1;

    qmlRegisterType<Arc>("Arcs", 1, 0, "Arc");
    qmlRegisterType<DatabaseManager>("Database", 1, 0, "DatabaseManager");
    qmlRegisterType<LocalCommunicationHandler>("Communication", 1, 0, "LocalCommunicationHandler");
    qmlRegisterType<CommunicationManager>("Communication", 1, 0, "RemoteCommunicationHandler");

    QQmlApplicationEngine engine;
    engine.load(QUrl(QStringLiteral("qrc:///qml/SluiceUI.qml")));

    return app.exec();
}
