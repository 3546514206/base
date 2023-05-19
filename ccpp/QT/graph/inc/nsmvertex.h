#ifndef NSMVERTEX_H
#define NSMVERTEX_H
#include <QList>
#include <QFontMetrics>
class NSMVertexParam;
class NSMVertex
{
public:
    NSMVertex();
    NSMVertex(int b);
    ~NSMVertex();
    QList<NSMVertexParam *> *getParams() const;
    void addVertexParams(NSMVertexParam *vp);
    void removeVertexParamsAt(int pos);
    int getB() const;
    void setB(int value);

    int getPi() const;
    void setPi(int value);

    int getCenterX() const;
    void setCenterX(int value);

    int getCenterY() const;
    void setCenterY(int value);

    bool getSelected() const;
    void setSelected(bool value);

    int getOriCenterX() const;
    void setOriCenterX(int value);

    int getOriCenterY() const;
    void setOriCenterY(int value);

    void saveCenter();
    void saveBCenter();
    void savePiCenter();
    int getBCenterX() const;
    void setBCenterX(int value);

    int getBCenterY() const;
    void setBCenterY(int value);

    int getBWidth() const;
    void setBWidth(int value);

    int getBOriCenterX() const;
    void setBOriCenterX(int value);

    int getBOriCenterY() const;
    void setBOriCenterY(int value);

    int getPiCenterX() const;
    void setPiCenterX(int value);

    int getPiCenterY() const;
    void setPiCenterY(int value);

    int getPiOriCenterX() const;
    void setPiOriCenterX(int value);

    int getPiOriCenterY() const;
    void setPiOriCenterY(int value);

    int getPiWidth() const;
    void setPiWidth(int value);

    int getLabelPos() const;
    void setLabelPos(int value);

    bool getBClicked() const;
    void setBClicked(bool value);

private:
    QList<NSMVertexParam*> *params;
    int b;
    int pi;
    int labelPos;
    int centerX;
    int centerY;
    bool selected;
    int oriCenterX;
    int oriCenterY;
    int bCenterX;
    int bCenterY;
    int bOriCenterX;
    int bOriCenterY;
    int bWidth;
    int piCenterX;
    int piCenterY;
    int piOriCenterX;
    int piOriCenterY;
    int piWidth;
    bool bClicked;
    QFontMetrics* metrics;
};

#endif // NSMVERTEX_H
