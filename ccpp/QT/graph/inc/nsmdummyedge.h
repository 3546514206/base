#ifndef NSMDUMMYEDGE_H
#define NSMDUMMYEDGE_H

#include <QFontMetrics>
class NSMDummyEdge
{
public:
    NSMDummyEdge(int p,bool dirIn);
    int getP() const;
    void setP(int value);


    int getFWidth() const;
    void setFWidth(int value);

    int getCWidth() const;
    void setCWidth(int value);
    void saveCXY();
    void saveFXY();
    int getFX() const;
    void setFX(int value);

    int getFY() const;
    void setFY(int value);

    int getCX() const;
    void setCX(int value);

    int getCY() const;
    void setCY(int value);

    bool getHover() const;
    void setHover(bool value);

    bool getMoveCFlag() const;
    void setMoveCFlag(bool value);

    bool getMoveFFlag() const;
    void setMoveFFlag(bool value);

    bool getDirIn() const;
    void setDirIn(bool value);

    int getRc() const;
    void setRc(int value);

    int getFlow() const;
    void setFlow(int value);

    ~NSMDummyEdge();
    double getFDeg() const;
    void setFDeg(double value);

    double getFDis() const;
    void setFDis(double value);

    double getCDeg() const;
    void setCDeg(double value);

    double getCDis() const;
    void setCDis(double value);

private:
    int p;
    bool dirIn;
    int rc;
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
    bool hover;

    QFontMetrics* metrics;

};

#endif // NSMDUMMYEDGE_H
