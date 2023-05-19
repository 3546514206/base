#ifndef NSMVERTEXPARAM_H
#define NSMVERTEXPARAM_H

#include "common.h"
#include <QFontMetrics>
class NSMVertexParam
{
public:
    NSMVertexParam();
    ~NSMVertexParam();
    NSMVertexParam(int p, int c=0,int capacity=POS_INFINITY);
    int getP() const;
    void setP(int value);

    int getCost() const;
    void setCost(int value);

    bool getCurve() const;
    void setCurve(bool value);

    bool getHover() const;
    void setHover(bool value);



    int getFlow() const;
    void setFlow(int value);

    bool isDummy() const;
    void setDummy(bool value);

    int getCapacity() const;
    void setCapacity(int value);

    int getC() const;
    void setC(int value);

    int getFWidth() const;
    void setFWidth(int value);

    int getCWidth() const;
    void setCWidth(int value);

    double getFDeg() const;
    void setFDeg(double value);

    double getFDis() const;
    void setFDis(double value);

    double getCDeg() const;
    void setCDeg(double value);

    double getCDis() const;
    void setCDis(double value);

    int getFX() const;
    void setFX(int value);

    int getFY() const;
    void setFY(int value);

    int getCX() const;
    void setCX(int value);

    int getCY() const;
    void setCY(int value);

    bool getMoveCFlag() const;
    void setMoveCFlag(bool value);

    bool getMoveFFlag() const;
    void setMoveFFlag(bool value);

    void saveCXY();
    void saveFXY();

    int getOrifX() const;
    void setOrifX(int value);

    int getOrifY() const;
    void setOrifY(int value);

    int getOricX() const;
    void setOricX(int value);

    int getOricY() const;
    void setOricY(int value);

    int getRc() const;
    void setRc(int value);

    int getOriCost() const;
    void setOriCost(int value);

private:
    int p;
    int capacity;
    int c;
    int rc;
    int cost;
    int oriCost;
    int flow;
    int fX;
    int fY;
    int cX;
    int cY;
    int fWidth;
    int cWidth;
    double fDeg;
    double fDis;
    double cDeg;
    double cDis;
    int orifX;
    int orifY;
    int oricX;
    int oricY;
    bool moveCFlag;
    bool moveFFlag;
    bool curve;
    bool hover;
    bool bDummy;
    QFontMetrics* metrics;

};

#endif // NSMVERTEXPARAM_H
