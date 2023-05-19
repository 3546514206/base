#include "nsmvertexdata.h"
#include "common.h"
NSMVertexData::NSMVertexData()
{
    params=new QList<NSMVertexParamData*>();
}

NSMVertexData::~NSMVertexData()
{
    params->clear();
    delete params;
}

int NSMVertexData::getB() const
{
    return b;
}

void NSMVertexData::setB(int value)
{
    b = value;
}

int NSMVertexData::getPi() const
{
    return pi;
}

void NSMVertexData::setPi(int value)
{
    pi = value;
}

QList<NSMVertexParamData *> *NSMVertexData::getParams() const
{
    return params;
}
