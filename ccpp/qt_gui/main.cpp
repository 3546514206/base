
#include <QtGui>
#include <QWindow>

int main(int argc, char *argv[]) {
    QGuiApplication application(argc, argv);
    QWindow window;

    window.show();
    application.dumpObjectInfo();

    qGuiApp -> dumpObjectTree();
    qGuiApp ->dumpObjectInfo();

    return QGuiApplication::exec();
}
