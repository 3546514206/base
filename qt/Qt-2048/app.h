#ifndef APP_H
#define APP_H

#include <QApplication>
#include "gameview.h"
#include "gamemodel.h"

class App : public QObject
{
    Q_OBJECT
public:
    App(int argc, char *argv[]);
    ~App();
    void start();   //启动游戏
    void stop();
private:
    QApplication *_app; //QApplication绑定主循环
    GameView *view; //游戏场景
    GameModel *model; //游戏模型
};

#endif // APP_H
