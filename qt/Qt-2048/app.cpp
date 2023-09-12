#include "app.h"
#include <QString>
/*
 * 这个程序采用MVC架构
 * 模型做逻辑判断和数据操作 然后发送到界面进行呈现
 * App是控制接口
*/
App::App(int argc, char *argv[])
{
    _app = new QApplication(argc, argv);
    view = new GameView();
    model = new GameModel();
    //finishProcess用于更新界面上的显示数据 传递ViewData结构体
    connect(model, SIGNAL(finishProcess(ViewData*)), view, SLOT(display(ViewData*)));
    //绑定重新开始按钮 重置游戏模型数据
    connect(view, SIGNAL(restart()), model, SLOT(init()));
    //操作 界面向模型传递操作
    connect(view, SIGNAL(key_op(GameOP)), model, SLOT(op(GameOP)));
    //模型判断输赢 界面响应
    connect(model, SIGNAL(win()), view, SLOT(win()));
    connect(model, SIGNAL(gameover()), view, SLOT(gameover()));
}

App::~App(){
    delete view;
    delete model;
    delete _app;
}

void App::start(){
    view->show();
    model->init();
    _app->exec();
}

void App::stop(){
    _app->exit();
}
