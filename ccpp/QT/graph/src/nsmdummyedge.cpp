#include "nsmdummyedge.h"
#include "common.h"
NSMDummyEdge::NSMDummyEdge(int p,bool dirIn)
{
    this->p=p;
    this->dirIn=dirIn;
    metrics=new QFontMetrics(QFont("微软雅黑",15));
}
NSMDummyEdge::~NSMDummyEdge(){
    delete metrics;
}

double NSMDummyEdge::getFDeg() const
{
    return fDeg;
}

void NSMDummyEdge::setFDeg(double value)
{
    fDeg = value;
}

double NSMDummyEdge::getFDis() const
{
    return fDis;
}

void NSMDummyEdge::setFDis(double value)
{
    fDis = value;
}

double NSMDummyEdge::getCDeg() const
{
    return cDeg;
}

void NSMDummyEdge::setCDeg(double value)
{
    cDeg = value;
}

double NSMDummyEdge::getCDis() const
{
    return cDis;
}

void NSMDummyEdge::setCDis(double value)
{
    cDis = value;
}
int NSMDummyEdge::getP() const
{
    return p;
}

void NSMDummyEdge::setP(int value)
{
    p = value;
}


int NSMDummyEdge::getFWidth() const
{
    return fWidth;
}

void NSMDummyEdge::setFWidth(int value)
{
    fWidth = value;
}

int NSMDummyEdge::getCWidth() const
{
    return cWidth;
}

void NSMDummyEdge::setCWidth(int value)
{
    cWidth = value;
}

void NSMDummyEdge::saveCXY()
{
    oricX=cX;
    oricY=cY;
}

void NSMDummyEdge::saveFXY()
{
    orifX=fX;
    orifY=fY;
}

int NSMDummyEdge::getFX() const
{
    return fX;
}

void NSMDummyEdge::setFX(int value)
{
    fX = value;
}

int NSMDummyEdge::getFY() const
{
    return fY;
}

void NSMDummyEdge::setFY(int value)
{
    fY = value;
}

int NSMDummyEdge::getCX() const
{
    return cX;
}

void NSMDummyEdge::setCX(int value)
{
    cX = value;
}

int NSMDummyEdge::getCY() const
{
    return cY;
}

void NSMDummyEdge::setCY(int value)
{
    cY = value;
}

bool NSMDummyEdge::getHover() const
{
    return hover;
}

void NSMDummyEdge::setHover(bool value)
{
    hover = value;
}

bool NSMDummyEdge::getMoveCFlag() const
{
    return moveCFlag;
}

void NSMDummyEdge::setMoveCFlag(bool value)
{
    moveCFlag = value;
}

bool NSMDummyEdge::getMoveFFlag() const
{
    return moveFFlag;
}

void NSMDummyEdge::setMoveFFlag(bool value)
{
    moveFFlag = value;
}

bool NSMDummyEdge::getDirIn() const
{
    return dirIn;
}

void NSMDummyEdge::setDirIn(bool value)
{
    dirIn = value;
}

int NSMDummyEdge::getRc() const
{
    return rc;
}

void NSMDummyEdge::setRc(int value)
{
    rc = value;
    cWidth=metrics->horizontalAdvance(QString("[%2]").arg(getRc()));
}

int NSMDummyEdge::getFlow() const
{
    return flow;
}

void NSMDummyEdge::setFlow(int value)
{
    flow = value;
    fWidth= metrics->horizontalAdvance(QString("%1").arg(getFlow()));

}
