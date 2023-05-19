#include "spframe.h"
#include "common.h"
#include "setdistancedialog.h"
#include <math.h>
#include <QPainter>
#include <QPaintEvent>
#include <QMouseEvent>
#include <QMessageBox>
#include <iostream>
using namespace std;
SPFrame::SPFrame(QWidget *parent):mParent(parent)
{
    graph=new SPGraph();
    refresh=false;
    init();
    setMouseTracking(true);

}
void SPFrame::init(){
    clearState();
    editable=true;
    winScale=1;
    winOffsetX=0;
    winOffsetY=0;
    winOriOffsetX=0;
    winOriOffsetY=0;
    realX=0;
    realY=0;
    moveVertexPos=0;
    createEdgeMouseX=0;
    createEdgeMouseY=0;
    createEdgeVertexHead=0;
    createEdgeVertexTail=0;

}
SPFrame::~SPFrame(){
    delete graph;
}
void SPFrame::paintEvent(QPaintEvent *event)
{
    QPainter painter(this);
    painter.setFont(QFont("微软雅黑",15));
    painter.setRenderHint(QPainter::Antialiasing);
    QRect rect=painter.window();
    if(!refresh){
        refresh=true;
        init();
        winOffsetX=rect.width()/4-VERTEX_SIZE/2;
        winOffsetY=rect.height()/4-VERTEX_SIZE/2;
        winOriOffsetX=winOffsetX;
        winOriOffsetY=winOffsetY;
    }
    if(editable)
        painter.fillRect(rect,Qt::white);
    else
        painter.fillRect(rect,QColor(248,248,248));

    painter.translate(winOffsetX,winOffsetY);
    painter.scale(winScale,winScale);
    drawEdge(&painter);
    drawVertexs(&painter);
    drawSelects(&painter);
    painter.scale(1/winScale,1/winScale);
    painter.translate(-winOffsetX,-winOffsetY);

    //drawHint(&painter);

    event->accept();
}
void SPFrame::drawHint(QPainter* painter){
    if(!editable){
        painter->setPen(QColor(Qt::blue));
        painter->drawText(QRect(50,50,painter->window().width()-50,painter->window().height()-50),strHint);
    }
}

void SPFrame::drawSelects(QPainter* painter){
    if(maybeMultiSelect){
        painter->setBrush(QBrush(QColor(128,128,128,24)));
        int xx=maybeMultiSelectMouseX;
        int xx2=maybeMultiSelectMouseX2;
        int yy=maybeMultiSelectMouseY;
        int yy2=maybeMultiSelectMouseY2;
        int x=MIN(xx,xx2);
        int y=MIN(yy,yy2);
        QPoint p=mouseToReal(x,y);
        QPoint p1=mouseToReal(xx,yy);
        QPoint p2=mouseToReal(xx2,yy2);
        int w=abs(p1.rx()-p2.rx());
        int h=abs(p1.ry()-p2.ry());
        painter->setPen(QPen(QBrush(Qt::black),0.5));
        painter->drawRect(p.rx(),p.ry(),w,h);
    }
}
void SPFrame::drawEdge(QPainter* painter){
    painter->setPen(QPen());
    for(int i=1;i<=graph->getCount();i++){
        SPVertex* v=graph->getVertexAt(i);
        for(int j=0;j<v->getParams()->count();j++){
            SPVertexParam* param=v->getParams()->at(j);
            SPVertex* v1=graph->getVertexAt(param->getP());
            SPVertex* v2=v;
            if(editable){
                if(param->getHover())
                    painter->setPen(QColor(Qt::red));
            }else{
                if(method==Bellman){
                    BellmanMark *m=graph->getBellmanMark();
                    if(m!=NULL)
                    {
                        bool b=false;
                        for(int k=0;k<m->getVertex()->count()-1;k++){
                            int t=m->getVertex()->at(k);
                            int s=m->getVertex()->at(k+1);
                            if(s==param->getP()&&t==i){
                                b=true;
                                break;
                            }
                            if(m->getNegaCircuit()&&s==i&&m->findVertex(param->getP()))
                            {
                                b=true;
                                break;
                            }
                        }


                        if(b){
                            if(m->getNegaCircuit())
                                painter->setPen(QPen(QColor(128,0,0),2));
                            else
                                painter->setPen(QPen(QColor(0,128,0),2));

                        }

                    }
                }
                else if(method==Floyd){
                    FloydMark *m=graph->getFloydMark();
                    if(m!=NULL)
                    {
                        bool b=false;
                        for(int k=0;k<m->getVertex()->count()-1;k++){
                            int t=m->getVertex()->at(k);
                            int s=m->getVertex()->at(k+1);
                            if(s==param->getP()&&t==i){
                                b=true;
                                break;
                            }
                            if(m->getNegaCircuit()&&s==i&&m->findVertex(param->getP()))
                            {
                                b=true;
                                break;
                            }
                        }


                        if(b){
                            if(m->getNegaCircuit())
                                painter->setPen(QPen(QColor(128,0,0),2));
                            else
                                painter->setPen(QPen(QColor(0,128,0),2));

                        }

                    }
                }
            }
            if(!param->getCurve()){
                drawStraightEdge(painter,v1,v2);
            }else{
                drawCurveEdge(painter,v1,v2);
            }

            painter->drawText(QRect(param->getX()-param->getWidth()/2,param->getY()-VERTEX_SIZE/2,param->getWidth(),VERTEX_SIZE)
                              ,QString::number(param->getE()),QTextOption(Qt::AlignCenter));
            painter->setPen(QPen());


        }
    }
    if(createEdge){
        if(!findEdgeTail){
            SPVertex* v=graph->getVertexAt(createEdgeVertexHead);
            QPoint realPoint=mouseToReal(createEdgeMouseX,createEdgeMouseY);
            drawStraightMaybeEdge(painter,v,realPoint);
        }
        else{
            SPVertex* v1=graph->getVertexAt(createEdgeVertexHead);
            SPVertex* v2=graph->getVertexAt(createEdgeVertexTail);
            drawStraightEdge(painter,v1,v2);
        }
    }


}
QPoint SPFrame::calcEdgeCenter(SPVertex* v1,SPVertex* v2){
    return  QPoint((v1->getCenterX()+v2->getCenterX())/2,(v1->getCenterY()+v2->getCenterY())/2);
}
void SPFrame::drawCurveEdge(QPainter* painter,SPVertex* v1,SPVertex* v2)
{
    QPoint vCenter=QPoint(v1->getCenterX(),v1->getCenterY());
    int deg=calcDeg(v2->getCenterX(),v2->getCenterY(),v1->getCenterX(),v1->getCenterY());
    QPoint realPoint=calcTail(v2->getCenterX(),v2->getCenterY(),deg,VERTEX_SIZE/2);
    QPoint startPoint;
    QPoint middlePoint;
    int length=sqrt((realPoint.x()-vCenter.x())*(realPoint.x()-vCenter.x())+
                    (realPoint.y()-vCenter.y())*(realPoint.y()-vCenter.y()));
    int c=VERTEX_SIZE/2+1;
    if(length>c){
        startPoint.setX(c*(realPoint.x()-vCenter.x())/length+vCenter.x());
        startPoint.setY(c*(realPoint.y()-vCenter.y())/length+vCenter.y());

        double deg=calcDeg(startPoint.x(),startPoint.y(),realPoint.x(),realPoint.y());
        int degoffset=30;
        deg-=degoffset;
        middlePoint=calcTail(startPoint.x(),startPoint.y(),deg,length/2/cos(degoffset*PI/180));
        QPainterPath path(QPointF(startPoint.x(),startPoint.y()));
        path.quadTo(QPointF(middlePoint.x(),middlePoint.y()),QPointF(realPoint.x(),realPoint.y()));
        painter->setBrush(QBrush(Qt::transparent));
        painter->drawPath(path);
        deg=calcDeg(realPoint.x(),realPoint.y(),startPoint.x(),startPoint.y());
        deg+=degoffset;
        for(int j=-30;j<=30;j++)
        {
            QPoint p=calcTail(realPoint.x(),realPoint.y(),deg+j,VERTEX_SIZE/8/cos(j*PI/180));
            painter->drawLine(realPoint,p);
        }

    }
}
void SPFrame::drawStraightEdge(QPainter* painter,SPVertex* v1,SPVertex* v2)
{
    QPoint vCenter=QPoint(v1->getCenterX(),v1->getCenterY());
    int deg=calcDeg(v2->getCenterX(),v2->getCenterY(),v1->getCenterX(),v1->getCenterY());
    QPoint realPoint=calcTail(v2->getCenterX(),v2->getCenterY(),deg,VERTEX_SIZE/2);
    QPoint startPoint;
    int length=sqrt((realPoint.x()-vCenter.x())*(realPoint.x()-vCenter.x())+
                    (realPoint.y()-vCenter.y())*(realPoint.y()-vCenter.y()));
    int c=VERTEX_SIZE/2+1;
    if(length>c){
        startPoint.setX(c*(realPoint.x()-vCenter.x())/length+vCenter.x());
        startPoint.setY(c*(realPoint.y()-vCenter.y())/length+vCenter.y());
        painter->drawLine(startPoint,realPoint);
        double deg=calcDeg(realPoint.x(),realPoint.y(),startPoint.x(),startPoint.y());
        for(int j=-30;j<=30;j++)
        {
            QPoint p=calcTail(realPoint.x(),realPoint.y(),deg+j,VERTEX_SIZE/8/cos(j*PI/180));
            painter->drawLine(realPoint,p);
        }

    }
}
void SPFrame::drawStraightMaybeEdge(QPainter* painter,SPVertex* v1,QPoint p){
    QPoint vCenter=QPoint(v1->getCenterX(),v1->getCenterY());
    QPoint realPoint=p;
    QPoint startPoint;
    int length=sqrt((realPoint.x()-vCenter.x())*(realPoint.x()-vCenter.x())+
                    (realPoint.y()-vCenter.y())*(realPoint.y()-vCenter.y()));
    int c=VERTEX_SIZE/2+1;
    if(length>c)
    {
        startPoint.setX(c*(realPoint.x()-vCenter.x())/length+vCenter.x());
        startPoint.setY(c*(realPoint.y()-vCenter.y())/length+vCenter.y());
        painter->drawLine(startPoint,realPoint);
        double deg=calcDeg(realPoint.x(),realPoint.y(),startPoint.x(),startPoint.y());
        for(int j=-30;j<=30;j++)
        {
            QPoint p=calcTail(realPoint.x(),realPoint.y(),deg+j,VERTEX_SIZE/8/cos(j*PI/180));
            painter->drawLine(realPoint,p);
        }

    }
}
void SPFrame::drawVertexs(QPainter* painter){
    painter->setPen(QPen());
    for(int i=1;i<=graph->getCount();i++){
        SPVertex* v=graph->getVertexAt(i);
        QRect rect;
        rect.setLeft(v->getCenterX()-VERTEX_SIZE/2);
        rect.setTop(v->getCenterY()-VERTEX_SIZE/2);
        rect.setWidth(VERTEX_SIZE);
        rect.setHeight(VERTEX_SIZE);
        if(editable){
            if(v->getSelected()){
                painter->setBrush(QBrush(QColor(234,234,234)));
            }else{
                painter->setBrush(QBrush(QColor(Qt::white)));
            }if(keyCtrlDown){
                painter->setPen(QPen(Qt::black,2));
            }else
                painter->setPen(QPen());
        }else{
            if(graph->getCount()>1)
            {
                if(method==Bellman){
                    BellmanMark* m=graph->getBellmanMark();
                    if(m!=NULL)
                    {
                        painter->setPen(QPen());
                        if(m->findVertex(i)){
                            if(m->getNegaCircuit()){
                                painter->setBrush(QBrush(QColor(255,128,128)));
                            }else{
                                painter->setBrush(QBrush(QColor(128,255,128)));
                            }
                        }else{
                            painter->setBrush(QBrush(QColor(246,246,246)));

                        }
                    }
                }
                if(method==Floyd){
                    painter->setBrush(QBrush(QColor(246,246,246)));
                    FloydMark* m=graph->getFloydMark();
                    painter->setPen(QPen());
                    if(m!=NULL)
                    {
                        if(m->getFloydStart()>0){
                            if(m->findVertex(i)){
                                if(m->getNegaCircuit()){
                                    painter->setBrush(QBrush(QColor(255,128,128)));
                                }else{
                                    if(m->getD(m->getFloydStart(),i)==POS_INFINITY)
                                        painter->setBrush(QBrush(QColor(255,255,128)));
                                    else
                                        painter->setBrush(QBrush(QColor(128,255,128)));
                                }
                            }else{
                                painter->setBrush(QBrush(QColor(246,246,246)));

                            }
                        }

                        if(m->getFloydStart()==i){
                            painter->setBrush(QBrush(QColor(128,128,255)));
                        }
                    }
                }

            }

        }
        painter->drawEllipse(rect);
        painter->drawText(rect,QString::number(i),QTextOption(Qt::AlignCenter));
    }
}
int SPFrame::checkLBtnDownVertex(){

    int ret=0;
    for(int i=graph->getCount();i>=1;i--){
        SPVertex* v=graph->getVertexAt(i);
        double dis=sqrt((realX-v->getCenterX())*(realX-v->getCenterX())+(realY-v->getCenterY())*(realY-v->getCenterY()));
        if(dis<VERTEX_SIZE/2){
            ret=i;
            break;
        }
    }
    return ret;
}
int SPFrame::checkMouseMoveVertex(QPoint pos){

    int ret=0;
    QPoint realPoint=mouseToReal(pos.x(),pos.y());
    for(int i=graph->getCount();i>=1;i--){
        SPVertex* v=graph->getVertexAt(i);
        double dis=sqrt((realPoint.x()-v->getCenterX())*(realPoint.x()-v->getCenterX())
                        +(realPoint.y()-v->getCenterY())*(realPoint.y()-v->getCenterY()));
        if(dis<VERTEX_SIZE/2){
            ret=i;
            break;
        }
    }
    return ret;
}
void SPFrame::mousePressEvent(QMouseEvent *event){
    int x=event->x();
    int y=event->y();
    if(editable){
        if(event->button()==Qt::LeftButton){
            int pos=checkLBtnDownVertex();
            if(!keyCtrlDown){
                if(pos>0){
                    SPVertex* v=graph->getVertexAt(pos);
                    if(!multiSelect){
                        moveVertexPos=pos;
                        moveVertexCenterX=v->getCenterX();
                        moveVertexCenterY=v->getCenterY();
                        v->setSelected(true);
                        for(int i=0;i<v->getParams()->count();i++){
                            SPVertexParam* vp=v->getParams()->at(i);
                            SPVertex* v1=graph->getVertexAt(vp->getP());
                            vp->setDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),vp->getX(),vp->getY())
                                       -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                            vp->setDis(calcDis(v1->getCenterX(),v1->getCenterY(),vp->getX(),vp->getY()));
                        }
                        for(int i=1;i<=graph->getCount();i++){
                            SPVertex* v2=graph->getVertexAt(i);
                            for(int j=0;j<v2->getParams()->count();j++)
                            {
                                SPVertexParam *vp=v2->getParams()->at(j);
                                if(vp->getP()==pos)
                                {
                                    vp->setDeg(calcDeg(v->getCenterX(),v->getCenterY(),vp->getX(),vp->getY())
                                               -calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY()));
                                    vp->setDis(calcDis(v->getCenterX(),v->getCenterY(),vp->getX(),vp->getY()));
                                }
                            }
                        }
                    }else{
                        if(v->getSelected())
                            readyMultiMove=true;
                        else{
                            multiSelect=false;
                            for(int i=1;i<=graph->getCount();i++){
                                graph->getVertexAt(i)->setSelected(false);
                            }
                            moveVertexPos=pos;
                            moveVertexCenterX=v->getCenterX();
                            moveVertexCenterY=v->getCenterY();
                            v->setSelected(true);
                            for(int i=0;i<v->getParams()->count();i++){
                                SPVertexParam* vp=v->getParams()->at(i);
                                SPVertex* v1=graph->getVertexAt(vp->getP());
                                vp->setDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),vp->getX(),vp->getY())
                                           -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                                vp->setDis(calcDis(v1->getCenterX(),v1->getCenterY(),vp->getX(),vp->getY()));
                            }
                            for(int i=1;i<=graph->getCount();i++){
                                SPVertex* v2=graph->getVertexAt(i);
                                for(int j=0;j<v2->getParams()->count();j++)
                                {
                                    SPVertexParam *vp=v2->getParams()->at(j);
                                    if(vp->getP()==pos)
                                    {
                                        vp->setDeg(calcDeg(v->getCenterX(),v->getCenterY(),vp->getX(),vp->getY())
                                                   -calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY()));
                                        vp->setDis(calcDis(v->getCenterX(),v->getCenterY(),vp->getX(),vp->getY()));
                                    }
                                }
                            }
                        }

                    }
                }else{

                    maybeMultiSelect=true;
                    maybeMultiSelectMouseX=maybeMultiSelectMouseX2=x;
                    maybeMultiSelectMouseY=maybeMultiSelectMouseY2=y;
                    if(multiSelect){

                        multiSelect=false;
                        readyMultiMove=false;
                    }
                }
                currentLMouseX=x;
                currentLMouseY=y;
                bool bBreak=false;
                for(int i=1;i<=graph->getCount();i++){
                    SPVertex* v=graph->getVertexAt(i);
                    for(int i=0;i<v->getParams()->count();i++){
                        SPVertexParam* param=v->getParams()->at(i);
                        QPoint mouseReal=mouseToReal(x,y);
                        if(mouseReal.x()<param->getX()+param->getWidth()/2&&
                                mouseReal.x()>param->getX()-param->getWidth()/2&&
                                mouseReal.y()<param->getY()+VERTEX_SIZE/2&&
                                mouseReal.y()>param->getY()-VERTEX_SIZE/2){
                            maybeMultiSelect=false;
                            moveEdgeLabel=true;
                            param->setMoveFlag(true);
                            param->saveXY();
                            bBreak=true;
                            break;
                        }
                    }
                    if(bBreak)break;
                }



            }else{
                if(pos>0){
                    SPVertex* v=graph->getVertexAt(pos);
                    v->setSelected(true);
                    createEdge=true;
                    createEdgeVertexHead=pos;
                    createEdgeMouseX=event->x();
                    createEdgeMouseY=event->y();
                }
            }

        }

    }
    else
    {
        if(event->button()==Qt::LeftButton&&method==SPFrame::Floyd){
            int pos=checkMouseMoveVertex(event->pos());
            if(pos>0){
                FloydMark* mark=graph->getFloydMark();
                if(mark!=NULL){
                    mark->reset();
                    mark->setFloydStart(pos);
                }
            }
        }
    }
    if(event->button()==Qt::RightButton){
        winStartMove=true;
        moveVertexPos=0;
        maybeMultiSelect=false;
        readyMultiMove=false;
        createEdge=false;
        findEdgeTail=false;
        winOffsetX=winOriOffsetX;
        winOffsetY=winOriOffsetY;
        winStartOffsetX=x;
        winStartOffsetY=y;
        setCursor(Qt::OpenHandCursor);
    }
    update();
    event->accept();
}
void SPFrame::reset(){
    refresh=false;
}

void SPFrame::mouseMoveEvent(QMouseEvent *event){
    setFocus();
    int x=event->x();
    int y=event->y();
    if(winStartMove){
        winOffsetX=winOriOffsetX+(event->x()-winStartOffsetX);
        winOffsetY=winOriOffsetY+(event->y()-winStartOffsetY);
    }
    if(editable){


        realX=(x-winOffsetX)/winScale;
        realY=(y-winOffsetY)/winScale;

        if(maybeMultiSelect){
            bool b=false;
            maybeMultiSelectMouseX2=x;
            maybeMultiSelectMouseY2=y;
            if(!(abs(maybeMultiSelectMouseX-x)<5)||!(abs(maybeMultiSelectMouseY-y)<5))
                for(int i=1;i<=graph->getCount();i++)
                {
                    int xx=graph->getVertexAt(i)->getCenterX()*winScale+winOffsetX;
                    int yy=graph->getVertexAt(i)->getCenterY()*winScale+winOffsetY;
                    if(((xx>maybeMultiSelectMouseX&&xx<maybeMultiSelectMouseX2)||
                        (xx>maybeMultiSelectMouseX2&&xx<maybeMultiSelectMouseX))&&
                            ((yy>maybeMultiSelectMouseY&&yy<maybeMultiSelectMouseY2)||
                             (yy>maybeMultiSelectMouseY2&&yy<maybeMultiSelectMouseY))
                            ){
                        SPVertex* v=graph->getVertexAt(i);
                        v->setSelected(true);
                        graph->getVertexAt(i)->saveCenter();
                        b=true;
                    }else graph->getVertexAt(i)->setSelected(false);
                }
            if(b){
                multiSelect=true;
            }

        }
        if(readyMultiMove){
            for(int i=1;i<=graph->getCount();i++)
            {
                SPVertex *v=graph->getVertexAt(i);
                if(v->getSelected()){
                    v->setCenterX(v->getOriCenterX()+(x-currentLMouseX)/winScale);
                    v->setCenterY(v->getOriCenterY()+(y-currentLMouseY)/winScale);
                    for(int j=0;j<v->getParams()->count();j++){
                        SPVertexParam *vp=v->getParams()->at(j);
                        SPVertex* v1=graph->getVertexAt(vp->getP());

                        QPoint p=calcTail(v1->getCenterX(),v1->getCenterY(),
                                          calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY())
                                          +vp->getDeg()
                                          ,vp->getDis());
                        vp->setX(p.x());
                        vp->setY(p.y());
                    }
                    for(int j=1;j<=graph->getCount();j++){
                        SPVertex *v2=graph->getVertexAt(j);
                        for(int k=0;k<v2->getParams()->count();k++){
                            SPVertexParam *vp2=v2->getParams()->at(k);
                            if(vp2->getP()==i)
                            {
                                QPoint p=calcTail(v->getCenterX(),v->getCenterY(),
                                                  calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY())
                                                  +vp2->getDeg()
                                                  ,vp2->getDis());
                                vp2->setX(p.x());
                                vp2->setY(p.y());
                            }
                        }
                    }
                }

            }
        }
        else if(moveVertexPos>0){
            moveEdgeLabel=false;
            SPVertex *v=graph->getVertexAt(moveVertexPos);
            v->setCenterX(moveVertexCenterX+(x-currentLMouseX)/winScale);
            v->setCenterY(moveVertexCenterY+(y-currentLMouseY)/winScale);

        }
        if(createEdge){
            for(int i=1;i<=graph->getCount();i++){
                graph->getVertexAt(i)->setSelected(false);
            }
            graph->getVertexAt(createEdgeVertexHead)->setSelected(true);
            int pos=checkLBtnDownVertex();
            if(pos>0)
            {
                findEdgeTail=true;
                createEdgeVertexTail=pos;
                SPVertex* v=graph->getVertexAt(pos);
                v->setSelected(true);
            }
            else{
                findEdgeTail=false;
                createEdgeMouseX=event->x();
                createEdgeMouseY=event->y();
            }
        }
        if(moveEdgeLabel){
            for(int i=1;i<=graph->getCount();i++){
                SPVertex* v=graph->getVertexAt(i);
                for(int i=0;i<v->getParams()->count();i++){
                    SPVertexParam* param=v->getParams()->at(i);
                    if(param->getMoveFlag()){
                        param->setX(param->getOrix()+(x-currentLMouseX)/winScale);
                        param->setY(param->getOriy()+(y-currentLMouseY)/winScale);
                        SPVertex* v1=graph->getVertexAt(param->getP());
                        param->setDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),param->getX(),param->getY())
                                      -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                        param->setDis(calcDis(v1->getCenterX(),v1->getCenterY(),param->getX(),param->getY()));
                    }
                }
            }
        }
        for(int i=1;i<=graph->getCount();i++){
            SPVertex* v=graph->getVertexAt(i);
            for(int j=0;j<v->getParams()->count();j++){
                SPVertexParam* param=v->getParams()->at(j);
                param->setHover(false);
                QPoint mouseReal=mouseToReal(x,y);
                if(mouseReal.x()<param->getX()+param->getWidth()/2&&
                        mouseReal.x()>param->getX()-param->getWidth()/2&&
                        mouseReal.y()<param->getY()+VERTEX_SIZE/2&&
                        mouseReal.y()>param->getY()-VERTEX_SIZE/2){

                    param->setHover(true);
                }
            }
            if(v->getSelected()){
                for(int j=0;j<v->getParams()->count();j++){
                    SPVertexParam *vp=v->getParams()->at(j);
                    SPVertex* v1=graph->getVertexAt(vp->getP());

                    QPoint p=calcTail(v1->getCenterX(),v1->getCenterY(),
                                      calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY())
                                      +vp->getDeg()
                                      ,vp->getDis());
                    vp->setX(p.x());
                    vp->setY(p.y());
                }
                for(int j=1;j<=graph->getCount();j++){
                    SPVertex *v2=graph->getVertexAt(j);
                    for(int k=0;k<v2->getParams()->count();k++){
                        SPVertexParam *vp2=v2->getParams()->at(k);
                        if(vp2->getP()==i)
                        {
                            QPoint p=calcTail(v->getCenterX(),v->getCenterY(),
                                              calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY())
                                              +vp2->getDeg()
                                              ,vp2->getDis());
                            vp2->setX(p.x());
                            vp2->setY(p.y());
                        }
                    }
                }
            }
        }
    }else{
        int pos=checkMouseMoveVertex(event->pos());
        if(method==SPFrame::Bellman){
            BellmanMark* mark=graph->getBellmanMark();
            if(mark!=NULL)
            {
                if(pos>1){
                    mark->reset();
                    int p=mark->getP(pos);
                    if(p!=0)
                    {
                        mark->addVertex(pos);
                        mark->addVertex(p);
                    }
                    while(p!=1){
                        p=mark->getP(p);
                        if(mark->findVertex(p)){
                            mark->setNega(p);
                            mark->setNegaCircuit(true);
                            break;
                        }
                        mark->addVertex(p);
                    }
                    if(!mark->getNegaCircuit()){
                        strHint="1->"+QString::number(pos)+"的最短路径为:";
                        for(int i=0;i<mark->getVertex()->count()-1;i++){
                            strHint+=QString::number(mark->getVertex()->at(mark->getVertex()->count()-1-i))+"->";

                        }
                        strHint+=QString::number(pos);
                        strHint+=" 总长度:"+QString::number(mark->getD(pos));
                    }else{
                        if(mark->getD(pos)==POS_INFINITY){
                            strHint="此路不通";
                        }
                        else{
                            strHint="由于节点";
                            for(int i=mark->getVertex()->count()-1;i>=0;i--)
                            {
                                int v=mark->getVertex()->at(i);
                                if(v!=mark->getNega()){
                                    strHint+=QString::number(v)+",";
                                }else{
                                    strHint+=QString::number(v);
                                    break;
                                }
                            }
                            strHint+="构成了负权值回路,因此无法计算最短路径";
                        }

                    }
                }
                else
                {
                    strHint="";
                    mark->reset();
                }
            }



        }else if(method==SPFrame::Floyd){
            FloydMark* mark=graph->getFloydMark();
            if(mark!=NULL)
            {
                if(pos>0){
                    mark->reset();
                    if(mark->getFloydStart()>0&&pos!=mark->getFloydStart()){
                        int p=mark->getP(mark->getFloydStart(),pos);
                        int d=mark->getD(mark->getFloydStart(),pos);
                        if(p!=0)
                        {
                            mark->addVertex(pos);
                            mark->addVertex(p);
                            if(d==POS_INFINITY)
                                goto END;
                        }
                        while(p!=mark->getFloydStart()){
                            p=mark->getP(mark->getFloydStart(),p);

                            if(mark->findVertex(p)){
                                mark->setNega(p);
                                mark->setNegaCircuit(true);
                                break;
                            }
                            mark->addVertex(p);
                        }

                        if(!mark->getNegaCircuit()){
 END:                           if(mark->getD(mark->getFloydStart(),pos)==POS_INFINITY){
                                strHint="此路不通";
                            }
                            else{
                                strHint=QString::number(mark->getFloydStart())+"->"+QString::number(pos)+"的最短路径为:";
                                for(int i=0;i<mark->getVertex()->count()-1;i++){
                                    strHint+=QString::number(mark->getVertex()->at(mark->getVertex()->count()-1-i))+"->";

                                }
                                strHint+=QString::number(pos);
                                strHint+=" 总长度:"+QString::number(mark->getD(mark->getFloydStart(),pos));
                            }
                        }else{

                            strHint="由于节点";
                            for(int i=mark->getVertex()->count()-1;i>=0;i--)
                            {
                                int v=mark->getVertex()->at(i);
                                if(v!=mark->getNega()){
                                    strHint+=QString::number(v)+",";
                                }else{
                                    strHint+=QString::number(v);
                                    break;
                                }
                            }
                            strHint+="构成了负权值回路,因此无法计算最短路径";

                        }

                    }
                }
                else{
                    strHint="";
                    mark->reset();
                }
            }
        }
        emit hintChanged(strHint);
    }

    update();
    event->accept();
}
void SPFrame::mouseReleaseEvent(QMouseEvent *event){
    if(editable)
    {
        if(event->button()==Qt::LeftButton){
            for(int i=1;i<=graph->getCount();i++){
                graph->getVertexAt(i)->saveCenter();
                if(!multiSelect){
                    graph->getVertexAt(i)->setSelected(false);
                }
            }
            if(createEdge){
                if(findEdgeTail){
                    if(createEdgeVertexHead!=createEdgeVertexTail){
                        SPVertex * v=graph->getVertexAt(createEdgeVertexTail);
                        SetDistanceDialog dialog(this);
                        if(dialog.exec()==QDialog::Accepted){
                            double dis=dialog.getDistance();
                            SPVertex* v1=graph->getVertexAt(createEdgeVertexHead);
                            QPoint edgeCenter=calcEdgeCenter(v1,v);
                            int deg=calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY())-90;
                            QPoint disText=calcTail(edgeCenter.x(),edgeCenter.y(),deg,VERTEX_SIZE/2);
                            SPVertexParam* vp=new SPVertexParam(createEdgeVertexHead,dis);
                            for(int i=0;i<v1->getParams()->count();i++){
                                SPVertexParam *vp1=v1->getParams()->at(i);
                                if(vp1->getP()==createEdgeVertexTail){
                                    vp1->setCurve(true);
                                    vp->setCurve(true);
                                    break;
                                }
                            }
                            vp->setX(disText.x());
                            vp->setY(disText.y());
                            int width=QFontMetrics(QFont("微软雅黑",15)).horizontalAdvance(QString::number(dis));
                            vp->setWidth(width);
                            vp->setDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),vp->getX(),vp->getY())
                                       -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                            vp->setDis(calcDis(v1->getCenterX(),v1->getCenterY(),vp->getX(),vp->getY()));
                            v->addVertexParams(vp);

                        }else{
                            bool b=false;
                            for(int i=0;i<v->getParams()->count();i++){
                                if(v->getParams()->at(i)->getP()==createEdgeVertexHead){
                                    SPVertex *v1=graph->getVertexAt(createEdgeVertexHead);
                                    for(int j=0;j<v1->getParams()->count();j++){
                                        SPVertexParam *vp1=v1->getParams()->at(j);
                                        if(vp1->getP()==createEdgeVertexTail)
                                        {
                                            vp1->setCurve(false);
                                            b=true;
                                            break;
                                        }

                                        if(b)break;
                                    }
                                    v->getParams()->removeAt(i);
                                    break;
                                }
                            }
                        }

                    }
                    keyCtrlDown=false;
                }

            }
            for(int i=1;i<=graph->getCount();i++){
                SPVertex* v=graph->getVertexAt(i);
                for(int i=0;i<v->getParams()->count();i++){
                    SPVertexParam* param=v->getParams()->at(i);
                    param->setMoveFlag(false);
                }
            }
        }
    }
    if(event->button()==Qt::RightButton){
        winStartMove=false;
        winOriOffsetX=winOffsetX;
        winOriOffsetY=winOffsetY;
        setCursor(Qt::ArrowCursor);
    }
    moveVertexPos=0;
    maybeMultiSelect=false;
    readyMultiMove=false;
    createEdge=false;
    findEdgeTail=false;
    moveEdgeLabel=false;
    update();
    event->accept();
}
void SPFrame::wheelEvent(QWheelEvent *event){
    QPoint delta=event->angleDelta();
    int x=event->x();
    int y=event->y();
    int oriOffsetX=winOffsetX;
    int oriOffsetY=winOffsetY;
    double oriscale=winScale;
    if(delta.ry()<0){
        if(winScale>0.3)
            winScale-=0.05;
    }else{
        if(winScale<4)
            winScale+=0.05;
    }
    double nowScale=winScale;
    winOffsetX=x-(x-oriOffsetX)*nowScale/oriscale;
    winOffsetY=y-(y-oriOffsetY)*nowScale/oriscale;
    winOriOffsetX=winOffsetX;
    winOriOffsetY=winOffsetY;
    saveWinOffset();
    update();
    event->accept();
}
void SPFrame::keyPressEvent(QKeyEvent *event){
    if(editable)
    {
        if(event->key()==Qt::Key_Control){
            keyCtrlDown=true;
            multiSelect=false;
            maybeMultiSelect=false;
            readyMultiMove=false;
            if(moveVertexPos!=0){
                SPVertex* v=graph->getVertexAt(moveVertexPos);
                v->setSelected(false);
                moveVertexPos=0;
            }
            for(int i=1;i<=graph->getCount();i++){
                graph->getVertexAt(i)->setSelected(false);
            }
        }else if(event->key()==Qt::Key_Delete){
            for(int i=1;i<=graph->getCount();i++){
                SPVertex* v=graph->getVertexAt(i);
                if(v->getSelected()){
                    graph->removeVertexAt(i);
                    i=0;
                    continue;
                }
            }
        }
    }
    update();
    event->accept();

}
void SPFrame::keyReleaseEvent(QKeyEvent *event){
    if(editable)
    {
        if(event->key()==Qt::Key_Control){
            keyCtrlDown=false;
            createEdge=false;
        }
    }
    update();
    event->accept();
}

bool SPFrame::getMethod() const
{
    return method;
}

void SPFrame::setMethod(bool value)
{
    method = value;
}

void SPFrame::saveWinOffset()
{
    winOriOffsetX=winOffsetX;
    winOriOffsetY=winOffsetY;
}

void SPFrame::setWinScale(double value)
{
    winScale = value;
}

void SPFrame::setWinOffsetY(int value)
{
    winOffsetY = value;
}

void SPFrame::setWinOffsetX(int value)
{
    winOffsetX = value;
}

int SPFrame::getWinOffsetY() const
{
    return winOffsetY;
}

bool SPFrame::getEditable() const
{
    return editable;
}

void SPFrame::setEditable(bool value)
{
    editable = value;
    if(editable){
        if(method==Bellman)
        {
            BellmanMark* m=graph->getBellmanMark();
            if(m!=NULL)
                m->reset();
        }

        else if(method==Floyd)
        {
            FloydMark* m=graph->getFloydMark();
            if(m!=NULL)
                m->reset();
        }
    }
}

int SPFrame::getWinOffsetX() const
{
    return winOffsetX;
}

double SPFrame::getWinScale() const
{
    return winScale;
}
QPoint SPFrame::mouseToReal(int x,int y)
{
    return QPoint((x-winOffsetX)/winScale,(y-winOffsetY)/winScale);
}
QPoint SPFrame::realToMouse(int x,int y)
{
    return QPoint(x*winScale+winOffsetX,y*winScale+winOffsetY);
}
SPGraph *SPFrame::getGraph() const
{
    return graph;
}
void SPFrame::clearState(){

    winStartMove=false;
    keyCtrlDown=false;
    maybeMultiSelect=false;
    multiSelect=false;
    readyMultiMove=false;
    createEdge=false;
    findEdgeTail=false;
    moveEdgeLabel=false;
}
