#ifndef NSMVERTEXDATA_H
#define NSMVERTEXDATA_H

#include <QList>
class NSMVertexParamData;
class NSMVertexData
{
public:
    NSMVertexData();
    ~NSMVertexData();

    int getB() const;
    void setB(int value);

    int getPi() const;
    void setPi(int value);

    QList<NSMVertexParamData *> *getParams() const;

private:
    QList<NSMVertexParamData*> *params;
    int b;
    int pi;

};

#endif // NSMVERTEXDATA_H
