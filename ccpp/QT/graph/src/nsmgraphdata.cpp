#include "nsmgraphdata.h"
#include "common.h"

NSMGraphData::NSMGraphData(int phase)
{
    this->phase=phase;
    vertexDatas=new QList<NSMVertexData*>();
    baseMatrix=new BaseMatrix();
    inVector=new BaseVector();
    outVector=new BaseVector();
}

NSMGraphData::~NSMGraphData()
{
    delete baseMatrix;
    vertexDatas->clear();
    delete vertexDatas;
    delete inVector;
    delete outVector;
}

int NSMGraphData::getPhase() const
{
    return phase;
}

void NSMGraphData::setPhase(int value)
{
    phase = value;
}

QList<NSMVertexData *> *NSMGraphData::getVertexDatas() const
{
    return vertexDatas;
}

BaseMatrix *NSMGraphData::getBaseMatrix() const
{
    return baseMatrix;
}

void NSMGraphData::setBaseMatrix(BaseMatrix *value)
{
    baseMatrix = value;
}

BaseVector *NSMGraphData::getInVector() const
{
    return inVector;
}

BaseVector *NSMGraphData::getOutVector() const
{
    return outVector;
}

