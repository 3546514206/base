#include "gameview.h"
#include <QDebug>
GameView::GameView(QWidget *parent) :
    QWidget(parent)
{
    this->setFixedSize(430, 550);   //设置固定尺寸
    setupUi();  //构建UI
    this->setFocusPolicy(Qt::ClickFocus);   //定义焦点策略 只能通过点击获得焦点
    connect(reset,SIGNAL(clicked()),this, SLOT(slot_restart()));
    this->setFocus();   //获取焦点
    this->isActive = true;  //表示当前能够进行操作
}

void GameView::setupUi(){
    this->setStyleSheet("background: #faf8ef");
    //添加标题
    title = new QLabel(this);
    title->setFont(QFont("Clear Sans", 50));
    title->setStyleSheet("color:#776e65");
    title->setText("2048");
    title->setGeometry(30,10,200,100);
    //分数板
    scoreb = new ScoreBoard(this);
    scoreb->setGeometry(200,30,200,65);
    //提示信息
    info = new QLabel(this);
    info->setFont(QFont("黑体", 20));
    info->setStyleSheet("color:#776e65");
    info->setText("累加数字得到<b>2048</b>!");
    info->setGeometry(30,90,300,50);
    //重新开始的按钮
    reset = new QPushButton(this);
    reset->setText("开始新游戏");
    reset->setGeometry(300,100,100,30);
    reset->setStyleSheet("background-color:#8f7a66; "
                         "border:none; "
                         "border-radius:5px;"
                         "color: #fff");
    //数字格子
    ng = new NumberGrid(this);
    ng->setGeometry(30,150,370,370);
    //输赢的蒙版
    mask = new Mask(this);
    mask->setGeometry(30,150,370,370);
}

void GameView::display(ViewData *data){
    scoreb->setScore(data->score);
    ng->mapFromMatrix(data->matrix);
}

void GameView::slot_restart(){
    this->setFocus();
    hideMsg();
    emit restart();
}

void GameView::win(){
    showMsg("   胜利!");
}

void GameView::gameover(){
    showMsg("  游戏结束");
}

void GameView::showMsg(QString s){
    mask->setMessage(s);
    mask->show();
    this->isActive = false;
}

void GameView::hideMsg(){
    mask->hide();
    this->isActive = true;
}
//键盘操作
void GameView::keyPressEvent(QKeyEvent *e){
    if(!isActive)
        return;
    switch(e->key()){
    case Qt::Key_Left:
        emit key_op(LEFT);
        break;
    case Qt::Key_Right:
        emit key_op(RIGHT);
        break;
    case Qt::Key_Up:
        emit key_op(UP);
        break;
    case Qt::Key_Down:
        emit key_op(DOWN);
        break;
    default:
        return;
    }
}
