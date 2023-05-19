#include "spvertexparam.h"

SPVertexParam::SPVertexParam()
{
    p=0;
    e=0;
    x=0;
    y=0;
    width=0;
    hover=false;
    curve=false;
    moveFlag=false;
}

SPVertexParam::SPVertexParam(int p, double e)
{
    this->p=p;
    this->e=e;
    x=0;
    y=0;
    width=0;
    hover=false;
    curve=false;
    moveFlag=false;
}

int SPVertexParam::getP() const
{
    return p;
}

void SPVertexParam::setP(int value)
{
    p = value;
}

double SPVertexParam::getE() const
{
    return e;
}

void SPVertexParam::setE(double value)
{
    e = value;
}



bool SPVertexParam::getCurve() const
{
    return curve;
}

void SPVertexParam::setCurve(bool value)
{
    curve = value;
}



bool SPVertexParam::getMoveFlag() const
{
    return moveFlag;
}

void SPVertexParam::setMoveFlag(bool value)
{
    moveFlag = value;
}

int SPVertexParam::getY() const
{
    return y;
}

void SPVertexParam::setY(int value)
{
    y = value;
}

int SPVertexParam::getX() const
{
    return x;
}

void SPVertexParam::setX(int value)
{
    x = value;
}

int SPVertexParam::getOriy() const
{
    return oriy;
}

void SPVertexParam::setOriy(int value)
{
    oriy = value;
}

int SPVertexParam::getOrix() const
{
    return orix;
}

void SPVertexParam::setOrix(int value)
{
    orix = value;
}

void SPVertexParam::saveXY()
{
    orix=x;
    oriy=y;
}

bool SPVertexParam::getHover() const
{
    return hover;
}

void SPVertexParam::setHover(bool value)
{
    hover = value;
}

double SPVertexParam::getDis() const
{
    return dis;
}

void SPVertexParam::setDis(double value)
{
    dis = value;
}

double SPVertexParam::getDeg() const
{
    return deg;
}

void SPVertexParam::setDeg(double value)
{
    deg = value;
}

int SPVertexParam::getWidth() const
{
    return width;
}

void SPVertexParam::setWidth(int value)
{
    width = value;
}




