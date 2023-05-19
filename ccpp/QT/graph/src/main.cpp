#include <QApplication>
#include "launchdialog.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    LaunchDialog d;
    d.show();
    return a.exec();
}
