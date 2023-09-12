#include <QApplication>
#include <QIcon>
#include <QFile>
#include <QString>

#include "login.h"

int main(int argc, char *argv[]){
    QApplication a(argc, argv);

    a.setWindowIcon(QIcon(":/app.ico"));

    QString stylesheet;
    QFile file(":/blue.qss");
    file.open(QIODevice::ReadOnly);
    stylesheet = QLatin1String(file.readAll());
    qApp->setStyleSheet(stylesheet);
    file.close();

    Login l;
    l.show();

    return a.exec();
}
