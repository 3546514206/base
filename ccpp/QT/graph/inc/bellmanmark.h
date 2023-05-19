#ifndef BELLMANMARK_H
#define BELLMANMARK_H

#include <QList>
class BellmanMark
{
public:
    BellmanMark(int count);
    ~BellmanMark();

    double getD(int pos);
    int getP(int pos);
    void setD(int pos, double val);
    void setP(int pos, int val);
    int getCount() const;

    void addVertex(int i);
    bool findVertex(int i);
    bool getNegaCircuit() const;
    void setNegaCircuit(bool value);
    void reset();

    QList<int> *getVertex() const;

    int getNega() const;
    void setNega(int value);

private:
    double *d;
    int *p;
    int count;
    QList<int>* vertex;
    bool negaCircuit;
    int nega;
};

#endif // BELLMANMARK_H
