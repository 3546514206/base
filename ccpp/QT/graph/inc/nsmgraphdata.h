#ifndef NSMGRAPHDATA_H
#define NSMGRAPHDATA_H
#include <QList>
class NSMVertexData;
class BaseMatrix;
class BaseVector;
class NSMGraphData
{
public:
    NSMGraphData(int phase);
    ~NSMGraphData();
    int getPhase() const;
    void setPhase(int value);

    QList<NSMVertexData *> *getVertexDatas() const;

    BaseMatrix *getBaseMatrix() const;
    void setBaseMatrix(BaseMatrix *value);

    BaseVector *getInVector() const;

    BaseVector *getOutVector() const;

private:
    QList<NSMVertexData*> *vertexDatas;
    BaseMatrix* baseMatrix;
    BaseVector* inVector;
    BaseVector* outVector;
    int phase;
};

#endif // NSMGRAPHDATA_H
