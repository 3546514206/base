#include "nsmvertexparamdata.h"
#include "common.h"
NSMVertexParamData::NSMVertexParamData()
{

}

int NSMVertexParamData::getP() const
{
    return p;
}

void NSMVertexParamData::setP(int value)
{
    p = value;
}

int NSMVertexParamData::getCapacity() const
{
    return capacity;
}

void NSMVertexParamData::setCapacity(int value)
{
    capacity = value;
}

int NSMVertexParamData::getC() const
{
    return c;
}

void NSMVertexParamData::setC(int value)
{
    c = value;
}

int NSMVertexParamData::getRc() const
{
    return rc;
}

void NSMVertexParamData::setRc(int value)
{
    rc = value;
}

int NSMVertexParamData::getCost() const
{
    return cost;
}

void NSMVertexParamData::setCost(int value)
{
    cost = value;
}

int NSMVertexParamData::getFlow() const
{
    return flow;
}

void NSMVertexParamData::setFlow(int value)
{
    flow = value;
}
