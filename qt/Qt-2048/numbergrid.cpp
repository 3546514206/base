#include "numbergrid.h"

NumberGrid::NumberGrid(QWidget *parent) :
    QWidget(parent)
{
    this->setStyleSheet("background-color:#bbada0;"
                               "border-radius:5px;");
    for(int i =0; i<4; i++)
        for(int j=0; j<4; j++){
            Tile *_tile = new Tile(this);
            _tile->setGeometry(10+90*i,10+90*j,80,80);
            tiles[i][j] =_tile;
        }
}

void NumberGrid::paintEvent(QPaintEvent *){
    QStyleOption opt;
    opt.init(this);
    QPainter p(this);
    style()->drawPrimitive(QStyle::PE_Widget, &opt, &p, this);
}

void NumberGrid::mapFromMatrix(int (*matrix)[4]){
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++){
            tiles[i][j]->animateTo(matrix[i][j]);
        }
}
