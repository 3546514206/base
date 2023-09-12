#ifndef NUMBERGRID_H
#define NUMBERGRID_H

#include <QWidget>
#include <QStyleOption>
#include <QPainter>
#include "tile.h"

class NumberGrid : public QWidget
{
    Q_OBJECT
public:
    explicit NumberGrid(QWidget *parent = 0);
    void mapFromMatrix(int (*matrix)[4]); //从二位数组映射到数字网格界面
signals:

public slots:
private:
    void paintEvent(QPaintEvent*);
    Tile *tiles[4][4];
};

#endif // NUMBERGRID_H
