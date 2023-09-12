#include <QApplication>
#include <QtCore>
#include "mainwindow.h"

int main(int argc, char *argv[])
{
    QTextCodec::setCodecForTr(QTextCodec::codecForName("utf-8"));
    QApplication a(argc, argv);
    MainWindow w;
    w.resize(800,480);
    w.show();
    return a.exec();
}
