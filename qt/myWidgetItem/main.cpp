#include "mainwindow.h"
#include <QApplication>
#include <QGraphicsScene>
#include <QGraphicsView>
#include <QGraphicsWidget>
#include <QTextEdit>
#include <QPushButton>
#include <QGraphicsProxyWidget>
#include <QGraphicsLinearLayout>
#include <QObject>
#include "QTime"
int main(int argc, char *argv[])
{
    void sleep(unsigned int msec);
    QApplication a(argc, argv);
    /*MainWindow w;
    w.show();*/
    QGraphicsScene scene;
       // 创建部件，并关联它们的信号和槽
       QTextEdit *edit = new QTextEdit;
       QPushButton *button = new QPushButton("clear");
       QObject::connect(button, SIGNAL(clicked()), edit, SLOT(clear()));
       // 将部件添加到场景中
       QGraphicsWidget *textEdit = scene.addWidget(edit);
       QGraphicsWidget *pushButton = scene.addWidget(button);
       // 将部件添加到布局管理器中
       QGraphicsLinearLayout *layout = new QGraphicsLinearLayout;
       layout->addItem(textEdit);
       layout->addItem(pushButton);
       // 创建图形部件，设置其为一个顶层窗口，然后在其上应用布局
       QGraphicsWidget *form = new QGraphicsWidget;
       form->setWindowFlags(Qt::Window);
       form->setWindowTitle("Widget Item");
       form->setLayout(layout);
       scene.addItem(form);
       QGraphicsView view(&scene);
       view.resize(1440,768);
       view.show();
       form->resize(500,500);
       QRectF r = form->boundingRect();
       for(int i=1;i<=360;i++)
       {
           form->setTransform(QTransform().rotate(i - 360 * 1, Qt::YAxis));
           sleep(20);
           view.update();
       }
    return a.exec();
}

void sleep(unsigned int msec)
{
        QTime dieTime = QTime::currentTime().addMSecs(msec);
        while( QTime::currentTime() < dieTime )
        QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
}

