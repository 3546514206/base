#ifndef GAMEMODEL_H
#define GAMEMODEL_H
#include <QObject>
#include <QVector>
#include "gamedatadef.h"

class GameModel : public QObject
{
    Q_OBJECT
public:
    GameModel();
public slots:
    void init();    //重置并产生两个数字
    void op(GameOP);    //操作入口
signals:
    void finishProcess(ViewData *data); //处理完成后更新界面的信号
    void gameover();    //游戏结束信号
    void win();     //游戏胜利信号
private:
    int (*matrix)[4];   //对游戏数据data的引用指针，方便操作
    ViewData data;  //游戏数据
    void clear();   //清零数据
    void rotate();  //旋转矩阵
    void process(); //按游戏规则对矩阵进行处理
    void addRamNum();   //添加随机数
};

#endif // GAMEMODEL_H
