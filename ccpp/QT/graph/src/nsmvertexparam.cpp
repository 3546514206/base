#include "nsmvertexparam.h"

NSMVertexParam::NSMVertexParam()
{
    p=0;
    capacity=POS_INFINITY;
    flow=0;
    cost=0;
    oriCost=0;
    rc=0;
    metrics=new QFontMetrics(QFont("微软雅黑",15));

}

NSMVertexParam::~NSMVertexParam()
{
    delete metrics;
}

NSMVertexParam::NSMVertexParam(int p, int c, int capacity)
{
    this->p=p;
    this->cost=c;
    this->capacity=capacity;
    oriCost=cost;
    flow=0;
    rc=0;
    metrics=new QFontMetrics(QFont("微软雅黑",15));


}

int NSMVertexParam::getP() const
{
    return p;
}

void NSMVertexParam::setP(int value)
{
    p = value;
}

int NSMVertexParam::getCost() const
{
    return cost;
}

void NSMVertexParam::setCost(int value)
{
    cost = value;
    cWidth=metrics->horizontalAdvance(QString("%1 [%2]").arg(getCost()).arg(getRc()));
}

bool NSMVertexParam::getCurve() const
{
    return curve;
}

void NSMVertexParam::setCurve(bool value)
{
    curve = value;
}

bool NSMVertexParam::getHover() const
{
    return hover;
}

void NSMVertexParam::setHover(bool value)
{
    hover = value;
}


int NSMVertexParam::getFlow() const
{
    return flow;
}

void NSMVertexParam::setFlow(int value)
{
    flow = value;
    if(getCapacity()==POS_INFINITY){
        fWidth= metrics->horizontalAdvance(QString("%1").arg(getFlow()));
    }else{
        fWidth= metrics->horizontalAdvance(QString("%1 (%2)").arg(getFlow()).arg(getCapacity()));
    }
}

bool NSMVertexParam::isDummy() const
{
    return bDummy;
}

void NSMVertexParam::setDummy(bool value)
{
    bDummy = value;
}

int NSMVertexParam::getCapacity() const
{
    return capacity;
}

void NSMVertexParam::setCapacity(int value)
{
    capacity = value;
    if(getCapacity()==POS_INFINITY){
        fWidth= metrics->horizontalAdvance(QString("%1").arg(getFlow()));
    }else{
        fWidth= metrics->horizontalAdvance(QString("%1 (%2)").arg(getFlow()).arg(getCapacity()));
    }
}

int NSMVertexParam::getC() const
{
    return c;
}

void NSMVertexParam::setC(int value)
{
    c = value;
}


int NSMVertexParam::getFWidth() const
{
    return fWidth;
}

void NSMVertexParam::setFWidth(int value)
{
    fWidth = value;
}

int NSMVertexParam::getCWidth() const
{
    return cWidth;
}

void NSMVertexParam::setCWidth(int value)
{
    cWidth = value;
}

double NSMVertexParam::getFDeg() const
{
    return fDeg;
}

void NSMVertexParam::setFDeg(double value)
{
    fDeg = value;
}

double NSMVertexParam::getFDis() const
{
    return fDis;
}

void NSMVertexParam::setFDis(double value)
{
    fDis = value;
}

double NSMVertexParam::getCDeg() const
{
    return cDeg;
}

void NSMVertexParam::setCDeg(double value)
{
    cDeg = value;
}

double NSMVertexParam::getCDis() const
{
    return cDis;
}

void NSMVertexParam::setCDis(double value)
{
    cDis = value;
}

int NSMVertexParam::getFX() const
{
    return fX;
}

void NSMVertexParam::setFX(int value)
{
    fX = value;
}

int NSMVertexParam::getFY() const
{
    return fY;
}

void NSMVertexParam::setFY(int value)
{
    fY = value;
}

int NSMVertexParam::getCX() const
{
    return cX;
}

void NSMVertexParam::setCX(int value)
{
    cX = value;
}

int NSMVertexParam::getCY() const
{
    return cY;
}

void NSMVertexParam::setCY(int value)
{
    cY = value;
}

bool NSMVertexParam::getMoveCFlag() const
{
    return moveCFlag;
}

void NSMVertexParam::setMoveCFlag(bool value)
{
    moveCFlag = value;
}

bool NSMVertexParam::getMoveFFlag() const
{
    return moveFFlag;
}

void NSMVertexParam::setMoveFFlag(bool value)
{
    moveFFlag = value;
}

void NSMVertexParam::saveCXY()
{
    oricX=cX;
    oricY=cY;
}

void NSMVertexParam::saveFXY()
{
    orifX=fX;
    orifY=fY;
}

int NSMVertexParam::getOrifX() const
{
    return orifX;
}

void NSMVertexParam::setOrifX(int value)
{
    orifX = value;
}

int NSMVertexParam::getOrifY() const
{
    return orifY;
}

void NSMVertexParam::setOrifY(int value)
{
    orifY = value;
}

int NSMVertexParam::getOricX() const
{
    return oricX;
}

void NSMVertexParam::setOricX(int value)
{
    oricX = value;
}

int NSMVertexParam::getOricY() const
{
    return oricY;
}

void NSMVertexParam::setOricY(int value)
{
    oricY = value;
}

int NSMVertexParam::getRc() const
{
    return rc;
}

void NSMVertexParam::setRc(int value)
{
    rc = value;
}

int NSMVertexParam::getOriCost() const
{
    return oriCost;
}

void NSMVertexParam::setOriCost(int value)
{
    oriCost = value;
}

