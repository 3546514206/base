#ifndef SPVERTEX_H
#define SPVERTEX_H
#include <QList>
class SPVertexParam;
class SPVertex
{
public:
    SPVertex();
    QList<SPVertexParam*> *getParams() const;
    ~SPVertex();
    void addVertexParams(SPVertexParam *vp);
    void removeVertexParamsAt(int pos);

    int getCenterY() const;
    void setCenterY(int value);

    int getCenterX() const;
    void setCenterX(int value);

    bool getSelected() const;
    void setSelected(bool value);
    void saveCenter();

    int getOriCenterX() const;

    int getOriCenterY() const;

private:
    QList<SPVertexParam*> *params;
    int centerX;
    int centerY;
    bool selected;
    int oriCenterX;
    int oriCenterY;

};

#endif // SPVERTEX_H
