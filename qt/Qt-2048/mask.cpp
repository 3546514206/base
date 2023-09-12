#include "mask.h"

Mask::Mask(QWidget *parent) :
    QWidget(parent)
{
    this->setStyleSheet("background-color : rgba(238, 228, 218, 0.73)");
    info = new QLabel(this);
    info->setFont(QFont("黑体",30));
    info->setGeometry(20,100,320,100);
    info->setStyleSheet("background-color : rgba(0,0,0,0);"
                        "color : #776e65;"
                        "margin: 0 auto;");
    this->hide();
}

void Mask::setMessage(QString s){
    info->setText(s);
    this->show();
}

void Mask::paintEvent(QPaintEvent *){
    QStyleOption opt;
    opt.init(this);
    QPainter p(this);
    style()->drawPrimitive(QStyle::PE_Widget, &opt, &p, this);
}
