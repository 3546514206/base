#include "spvertex.h"
#include "spvertexparam.h"
#include "common.h"
SPVertex::SPVertex()
{
    params=new QList<SPVertexParam*>();
    selected=false;
}

QList<SPVertexParam*>* SPVertex::getParams() const
{
    return params;
}


SPVertex::~SPVertex(){
    delete params;
}

void SPVertex::addVertexParams(SPVertexParam* vp){
    for(int i=0;i<params->count();i++){
        if(params->at(i)->getP()==vp->getP()){
            params->removeAt(i);
            break;
        }
    }
    params->push_back(vp);
}
void SPVertex::removeVertexParamsAt(int pos){
    params->removeAt(pos);
}

int SPVertex::getCenterY() const
{
    return centerY;
}

void SPVertex::setCenterY(int value)
{
    centerY = value;
}

int SPVertex::getCenterX() const
{
    return centerX;
}

void SPVertex::setCenterX(int value)
{
    centerX = value;
}

bool SPVertex::getSelected() const
{
    return selected;
}

void SPVertex::setSelected(bool value)
{
    selected = value;

}

void SPVertex::saveCenter()
{
    oriCenterX=centerX;
    oriCenterY=centerY;
}

int SPVertex::getOriCenterX() const
{
    return oriCenterX;
}

int SPVertex::getOriCenterY() const
{
    return oriCenterY;
}



