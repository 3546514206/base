#ifndef NSMFRAME_H
#define NSMFRAME_H

#include <QFrame>
class NSMGraph;
class NSMVertex;
class NSMVertexParam;
class NSMGraphData;
class NSMFrame : public QFrame
{
    Q_OBJECT
public:
    enum Type{
        DF,CV
    };
    explicit NSMFrame(QWidget* parent=0);
    ~NSMFrame();
    double getWinScale() const;
    void setWinScale(double value);
    void clearState();
    int getWinOffsetX() const;
    void setWinOffsetX(int value);

    int getWinOffsetY() const;
    void setWinOffsetY(int value);
    NSMGraph* getGraph();
    void moveVertexLabel(NSMVertex *v);

    void reset();
    bool getEditable() const;
    void setEditable(bool value);
    void saveWinOffset();
    void setGraphData(int num);
    void clearCurrentGraphData();
    void setCurrentGraphData(NSMGraphData *data);
    QPoint mouseToReal(int x, int y);
    QPoint mouseToReal2(int x, int y);
    QPoint realToMouse(int x, int y);
    NSMVertex *getDummyVertex() const;
    QPoint calcEdgeCenter(NSMVertex *v1, NSMVertex *v2);
    QRect getPainterRect() const;
    void moveDummyLabel();
protected:
    void paintEvent(QPaintEvent *event);
    void mousePressEvent(QMouseEvent *event);
    void mouseMoveEvent(QMouseEvent *event);
    void mouseReleaseEvent(QMouseEvent *event);
    void wheelEvent(QWheelEvent *event);
    void keyPressEvent(QKeyEvent *event);
    void keyReleaseEvent(QKeyEvent *event);
private:
    NSMGraph* graph;
    NSMVertex* dummyVertex;
    double winScale;
    int winOffsetX;
    int winOffsetY;
    int winStartOffsetX;
    int winStartOffsetY;
    int winOriOffsetX;
    int winOriOffsetY;
    int realX;
    int realY;
    int moveVertexPos;
    bool winStartMove;
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
    bool refresh;
    bool keyCtrlDown;
    bool maybeMultiSelect;
    bool multiSelect;
    bool readyMultiMove;
    bool createEdge;
    bool findEdgeTail;
    bool moveEdgeLabel;
    bool moveDummyVertex;
    bool editable;
    QWidget* mParent;
    QRect painterRect;
    NSMGraphData* currentGraphData;
    void drawDemandsAndArcFlows(QPainter *painter);
    void drawCostAndDualVariables(QPainter *painter);
    void init();
    void drawDemandsAndArcFlowsFrame(QPainter *painter);
    void drawCostAndDualVariablesFrame(QPainter *painter);
    void drawVertexs(QPainter *painter, Type type);
    void drawVerticesSelf(QPainter *painter);
    void drawSelects(QPainter *painter);
    int checkLBtnDownVertex();
    void drawStraightMaybeEdge(QPainter *painter, NSMVertex *v1, QPoint p);
    void drawEdges(QPainter *painter, Type type);
    void drawStraightEdge(QPainter *painter, NSMVertex *v1, NSMVertex *v2);
    void drawDemand(QPainter *painter);
    void drawDualVariable(QPainter *painter);
    void setDegAndDisByVertexMove(NSMVertex *v1, NSMVertex *v2, NSMVertexParam *vp);
    void drawFlowAndCapacity(QPainter *painter, NSMVertexParam *param);
    void drawCost(QPainter *painter, NSMVertexParam *param);
    void drawCurveEdge(QPainter *painter, NSMVertex *v1, NSMVertex *v2);
    void drawCurveDummyEdge(QPainter *painter, NSMVertex *v1, NSMVertex *v2, bool base, bool out);
    void drawStraightDummyEdge(QPainter *painter, NSMVertex *v1, NSMVertex *v2, bool base, bool out);
};

#endif // NSMFRAME_H
