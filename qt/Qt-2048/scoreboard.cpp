#include "scoreboard.h"

ScoreBoard::ScoreBoard(QWidget *parent) :
    QWidget(parent)
{
    QLabel *score_wrap = new QLabel(this);
    score_wrap->setStyleSheet(
                "border-radius: 5px; "
                "text-align:center;"
                "background-color:#bbada0;"
                "color:#fff");
    QLabel *best_wrap = new QLabel(this);
    best_wrap->setStyleSheet(
                "border-radius: 5px; "
                "text-align:center;"
                "background-color:#bbada0;"
                "color:#fff");
    score_wrap->setGeometry(0,0,90,50);
    best_wrap->setGeometry(100,0,90,50);
    i_score = i_best = 0;
    QLabel *t1 = new QLabel("分数", score_wrap);
    QLabel *t2 = new QLabel("最高分", best_wrap);
    t1->setFont(QFont("黑体", 10));
    t2->setFont(QFont("黑体", 10));
    t1->setGeometry(10,1,30,20);
    t2->setGeometry(10,1,40,20);
    score = new QLabel(score_wrap);
    best = new QLabel(best_wrap);
    score->setText(QString::number(i_score));
    best->setText(QString::number(i_best));
    score->setFont(QFont("Arial",20));
    best->setFont(QFont("Arial",20));
    score->setGeometry(10,20,75,20);
    best->setGeometry(10,20,75,20);
}

void ScoreBoard::setScore(int c_score){
    if(c_score > this->i_best){
        i_best = c_score;
        best->setText(QString::number(i_best));
    }
    i_score = c_score;
    score->setText(QString::number(i_score));
}

void ScoreBoard::initScore(){
    i_score = 0;
    score->setText("0");
}
