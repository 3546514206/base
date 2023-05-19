#include "common.h"

NSMVertex::NSMVertex()
{
    b=0;
    labelPos=0;
    pi=POS_INFINITY;
    selected=false;
    params=new QList<NSMVertexParam*>();
    metrics=new QFontMetrics(QFont("微软雅黑",15));
    bClicked=false;

}

NSMVertex::NSMVertex(int b)
{
    this->b=b;
    labelPos=0;
    pi=POS_INFINITY;
    selected=false;
    params=new QList<NSMVertexParam*>();
    metrics=new QFontMetrics(QFont("微软雅黑",15));
    bClicked=false;
}

NSMVertex::~NSMVertex()
{
    params->clear();
    delete params;
    delete metrics;
}

QList<NSMVertexParam *> *NSMVertex::getParams() const
{
    return params;
}

void NSMVertex::addVertexParams(NSMVertexParam *vp)
{
    for(int i=0;i<params->count();i++){
        if(params->at(i)->getP()==vp->getP()){
            params->removeAt(i);
            break;
        }
    }
    params->push_back(vp);
}

void NSMVertex::removeVertexParamsAt(int pos)
{
    params->removeAt(pos);
}

int NSMVertex::getB() const
{
    return b;
}

void NSMVertex::setB(int value)
{
    b = value;
    bWidth=metrics->horizontalAdvance(QString::number(b))+30;
}

int NSMVertex::getPi() const
{
    return pi;
}

void NSMVertex::setPi(int value)
{
    pi = value;
    if(pi==POS_INFINITY)
        piWidth=metrics->horizontalAdvance("∞")+30;
    else
        piWidth=metrics->horizontalAdvance(QString::number(pi))+30;

}

int NSMVertex::getCenterX() const
{
    return centerX;
}

void NSMVertex::setCenterX(int value)
{
    centerX = value;
}

int NSMVertex::getCenterY() const
{
    return centerY;
}

void NSMVertex::setCenterY(int value)
{
    centerY = value;
}

bool NSMVertex::getSelected() const
{
    return selected;
}

void NSMVertex::setSelected(bool value)
{
    selected = value;
}

int NSMVertex::getOriCenterX() const
{
    return oriCenterX;
}

void NSMVertex::setOriCenterX(int value)
{
    oriCenterX = value;
}

int NSMVertex::getOriCenterY() const
{
    return oriCenterY;
}

void NSMVertex::setOriCenterY(int value)
{
    oriCenterY = value;
}

void NSMVertex::saveCenter()
{
    oriCenterX=centerX;
    oriCenterY=centerY;
}

void NSMVertex::saveBCenter()
{
    bOriCenterX=bCenterX;
    bOriCenterY=bCenterY;
}

void NSMVertex::savePiCenter()
{
    piOriCenterX=piCenterX;
    piOriCenterY=piCenterY;
}

int NSMVertex::getBCenterX() const
{
    return bCenterX;
}

void NSMVertex::setBCenterX(int value)
{
    bCenterX = value;
}

int NSMVertex::getBCenterY() const
{
    return bCenterY;
}

void NSMVertex::setBCenterY(int value)
{
    bCenterY = value;
}

int NSMVertex::getBWidth() const
{
    return bWidth;
}

void NSMVertex::setBWidth(int value)
{
    bWidth = value;
}

int NSMVertex::getBOriCenterX() const
{
    return bOriCenterX;
}

void NSMVertex::setBOriCenterX(int value)
{
    bOriCenterX = value;
}

int NSMVertex::getBOriCenterY() const
{
    return bOriCenterY;
}

void NSMVertex::setBOriCenterY(int value)
{
    bOriCenterY = value;
}

int NSMVertex::getPiCenterX() const
{
    return piCenterX;
}

void NSMVertex::setPiCenterX(int value)
{
    piCenterX = value;
}

int NSMVertex::getPiCenterY() const
{
    return piCenterY;
}

void NSMVertex::setPiCenterY(int value)
{
    piCenterY = value;
}

int NSMVertex::getPiOriCenterX() const
{
    return piOriCenterX;
}

void NSMVertex::setPiOriCenterX(int value)
{
    piOriCenterX = value;
}

int NSMVertex::getPiOriCenterY() const
{
    return piOriCenterY;
}

void NSMVertex::setPiOriCenterY(int value)
{
    piOriCenterY = value;
}

int NSMVertex::getPiWidth() const
{
    return piWidth;
}

void NSMVertex::setPiWidth(int value)
{
    piWidth = value;
}

int NSMVertex::getLabelPos() const
{
    return labelPos;
}

void NSMVertex::setLabelPos(int value)
{
    labelPos = value;
}

bool NSMVertex::getBClicked() const
{
    return bClicked;
}

void NSMVertex::setBClicked(bool value)
{
    bClicked = value;
}
