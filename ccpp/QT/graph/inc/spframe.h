#ifndef SP_FRAME
#define SP_FRAME
#include <QFrame>
#include <QObject>
#include "spgraph.h"

class SPFrame : public QFrame
{
    Q_OBJECT

public:
    enum Method{
       Bellman,Floyd
   };
    explicit SPFrame(QWidget* parent=0);

    SPGraph *getGraph() const;

    ~SPFrame();
    double getWinScale() const;

    int getWinOffsetX() const;

    void reset();
    bool getEditable() const;
    void setEditable(bool value);

    int getWinOffsetY() const;

    void setWinOffsetX(int value);

    void setWinOffsetY(int value);

    void setWinScale(double value);

    void clearState();
    bool getMethod() const;
    void setMethod(bool value);
    void saveWinOffset();
    static QPoint calcEdgeCenter(SPVertex *v1, SPVertex *v2);
signals:
    void hintChanged(QString);
protected:
    void paintEvent(QPaintEvent *event);
    void mousePressEvent(QMouseEvent *event);
    void mouseMoveEvent(QMouseEvent *event);
    void mouseReleaseEvent(QMouseEvent *event);
    void wheelEvent(QWheelEvent *event);
    void keyPressEvent(QKeyEvent *event);
    void keyReleaseEvent(QKeyEvent *event);
private:
    SPGraph* graph;
    double winScale;
    int winOffsetX;
    int winOffsetY;
    int winStartOffsetX;
    int winStartOffsetY;
    int winOriOffsetX;
    int winOriOffsetY;
    int realX;
    int realY;
    bool winStartMove;
    bool refresh;
    bool keyCtrlDown;
    bool maybeMultiSelect;
    bool multiSelect;
    bool readyMultiMove;
    bool createEdge;
    bool findEdgeTail;
    bool moveEdgeLabel;
    bool editable;
    bool method;
    int moveVertexPos;
    int currentLMouseX;
    int currentLMouseY;
    int moveVertexCenterX;
    int moveVertexCenterY;
    int maybeMultiSelectMouseX;
    int maybeMultiSelectMouseY;
    int maybeMultiSelectMouseX2;
    int maybeMultiSelectMouseY2;
    int createEdgeMouseX;
    int createEdgeMouseY;
    int createEdgeVertexHead;
    int createEdgeVertexTail;
    QString strHint;
    QWidget *mParent;

    void drawVertexs(QPainter *painter);
    int checkLBtnDownVertex();
    void drawTest(QPainter *painter);
    void drawSelects(QPainter *painter);
    void drawEdge(QPainter *painter);
    QPoint mouseToReal(int x, int y);
    QPoint realToMouse(int x, int y);
    void init();
    void drawStraightEdge(QPainter *painter, SPVertex *v1, SPVertex *v2);
    void drawStraightMaybeEdge(QPainter *painter, SPVertex *v1, QPoint p);
    void drawCurveEdge(QPainter *painter, SPVertex *v1, SPVertex *v2);
    void drawHint(QPainter *painter);
    int checkMouseMoveVertex(QPoint pos);
};

#endif // SP_FRAME
