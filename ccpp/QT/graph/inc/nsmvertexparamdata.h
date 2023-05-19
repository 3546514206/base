#ifndef NSMVERTEXPARAMDATA_H
#define NSMVERTEXPARAMDATA_H


class NSMVertexParamData
{
public:
    NSMVertexParamData();
    int getP() const;
    void setP(int value);

    int getCapacity() const;
    void setCapacity(int value);

    int getC() const;
    void setC(int value);

    int getRc() const;
    void setRc(int value);

    int getCost() const;
    void setCost(int value);

    int getFlow() const;
    void setFlow(int value);

private:
    int p;
    int capacity;
    int c;
    int rc;
    int cost;
    int flow;
};

#endif // NSMVERTEXPARAMDATA_H
