#ifndef TILE_H
#define TILE_H

#include <QPushButton>
#include <QPropertyAnimation>
#include <QMap>
#include "gamedatadef.h"
#include <QDebug>
#include <qmath.h>
#include <QRect>

class Tile : public QPushButton
{
    Q_OBJECT
    //定义Qt属性 用于完成动画
    Q_PROPERTY(int value READ getValue WRITE setValue)
public:
    explicit Tile(QWidget *parent = 0);
    int getValue();
    void setValue(int value);
    void animateTo(int i);
signals:

public slots:
private:
    int value;
    QRect c_rect;

};

#endif // TILE_H
