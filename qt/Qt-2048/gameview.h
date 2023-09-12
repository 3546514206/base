#ifndef GAMEVIEW_H
#define GAMEVIEW_H

#include <QWidget>
#include <QLabel>
#include <QPushButton>
#include <QKeyEvent>
#include "scoreboard.h"
#include "numbergrid.h"
#include "gamedatadef.h"
#include "mask.h"

class GameView : public QWidget
{
    Q_OBJECT
public:
    explicit GameView(QWidget *parent = 0);
public slots:
    void display(ViewData *data);   //将模型发过来的数据呈现的函数
    void slot_restart();    //重新开始
    void win(); //展示赢得函数
    void gameover();    //展示结束的函数
signals:
    void restart(); //重置的信号 发给模型要求重置
    void key_op(GameOP);    //操作信号
private:
    QLabel *title, *info;   //提示文字
    ScoreBoard *scoreb;     //分数板
    QPushButton *reset;     //重新开始按钮
    NumberGrid *ng;         //数值格子
    Mask *mask; //展示输赢的蒙版
    bool isActive;  //表示当前是否接受操作
    void showMsg(QString);  //显示蒙版
    void hideMsg(); //隐藏蒙版
    void setupUi();
    void keyPressEvent(QKeyEvent *e);
};

#endif // GAMEVIEW_H
