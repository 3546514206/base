#ifndef NSMGRAPH_H
#define NSMGRAPH_H
#include <QList>
#include <QObject>
class NSMVertex;
class BaseMatrix;
class NSMGraphData;
class NSMDummyEdge;
class BaseVector;
class NSMGraph:public QObject
{
    Q_OBJECT
public:
    NSMGraph();
    ~NSMGraph();
    void addVertex(NSMVertex* v);
    void clearVertexs();
    NSMVertex *getVertexAt(int pos) const;
    void removeVertexAt(int pos);
    int getCount() const;
    int ctsma();
    int getLastX();
    int getLastY();
    QList<NSMGraphData *> *getGraphData() const;
    void clearFlow();
    void setCost();
    BaseMatrix *getBaseMatrix() const;
    void clearVerticesStates();
    int getDummyEdgeCnt() const;
    void setDummyEdgeCnt(int value);

    QList<NSMDummyEdge *> *getDummyEdge() const;

private:
    QList<NSMVertex*> *vertexs;
    BaseMatrix* baseMatrix;
    NSMVertex* dummy;
    int count;
    void calcPi();
    int changeBaseVector(int phase);
    QList<NSMGraphData*> *graphData;
    QList<NSMDummyEdge *> *dummyEdge;
    int dummyEdgeCnt;

    void addGraphData(int phase, BaseVector in, BaseVector out);
};

#endif // NSMGRAPH_H
