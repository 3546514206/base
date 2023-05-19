#include "nsmframe.h"
#include "common.h"
#include <QPainter>
#include <QPaintEvent>
#include <QMouseEvent>
#include "setcapacityandcostdialog.h"
#include "setdemanddialog.h"
#include "common.h"
#include <QMessageBox>
#include <math.h>
NSMFrame::NSMFrame(QWidget *parent):mParent(parent)
{
    graph=new NSMGraph();
    refresh=false;
    dummyVertex=new NSMVertex(0);
    currentGraphData=NULL;
    init();
    setMouseTracking(true);
}

NSMFrame::~NSMFrame()
{
    delete graph;
    delete dummyVertex;

}

void NSMFrame::paintEvent(QPaintEvent *event)
{
    QPainter painter(this);
    painter.setRenderHint(QPainter::Antialiasing);
    painterRect=this->rect();
    painter.save();
    if(!refresh){
        refresh=true;
        init();
        winOffsetX=painterRect.width()/8-VERTEX_SIZE/2;
        winOffsetY=painterRect.height()/4-VERTEX_SIZE/2;
        winOriOffsetX=winOffsetX;
        winOriOffsetY=winOffsetY;
    }

    if(editable){
        painter.fillRect(painterRect,Qt::white);
    }else{
        painter.fillRect(painterRect,QColor(248,248,248));
    }
    painter.setWindow(0,0,painterRect.width()/2,painterRect.height());
    painter.setViewport(painter.window());
    painter.setClipRect(painter.window());
    drawDemandsAndArcFlowsFrame(&painter);
    painter.setClipRect(painter.window().adjusted(2,2,-2,-2));
    painter.translate(winOffsetX,winOffsetY);
    painter.scale(winScale,winScale);
    drawDemandsAndArcFlows(&painter);
    painter.resetTransform();
    painter.restore();
    painter.translate(painterRect.width()/2,0);
    painter.setViewport(0,0,painterRect.width()/2,painterRect.height());
    painter.setWindow(0,0,painterRect.width()/2,painterRect.height());
    painter.setClipRect(painter.window());
    drawCostAndDualVariablesFrame(&painter);
    painter.setClipRect(painter.window().adjusted(2,2,-2,-2));
    painter.translate(winOffsetX,winOffsetY);
    painter.scale(winScale,winScale);
    drawCostAndDualVariables(&painter);
    event->accept();
}
void NSMFrame::drawSelects(QPainter* painter){
    if(maybeMultiSelect){
        painter->setBrush(QBrush(QColor(128,128,128,24)));
        int xx=maybeMultiSelectMouseX;
        int xx2=maybeMultiSelectMouseX2;
        int yy=maybeMultiSelectMouseY;
        int yy2=maybeMultiSelectMouseY2;
        QPoint p,p1,p2;
        if(xx<=painterRect.width()/2){
            p1=mouseToReal2(xx,yy);
            p2=mouseToReal2(xx2,yy2);
            p.setX(MIN(p1.x(),p2.x()));
            p.setY(MIN(p1.y(),p2.y()));

        }else{
            if(xx2<painterRect.width()/2){
                p1=mouseToReal(xx,yy);
                p2=mouseToReal2(xx2-painterRect.width()/2,yy2);
                p.setX(MIN(p1.x(),p2.x()));
                p.setY(MIN(p1.y(),p2.y()));
            }else{

                p1=mouseToReal(xx,yy);
                p2=mouseToReal(xx2,yy2);
                p.setX(MIN(p1.x(),p2.x()));
                p.setY(MIN(p1.y(),p2.y()));
            }
        }


        int w=abs(p1.x()-p2.x());
        int h=abs(p1.y()-p2.y());
        painter->setPen(QPen(QBrush(Qt::black),0.5));
        painter->drawRect(p.x(),p.y(),w,h);
    }
}
void NSMFrame::drawDemandsAndArcFlows(QPainter* painter){

    drawEdges(painter,DF);
    drawVertexs(painter,DF);
    drawSelects(painter);
}

void NSMFrame::drawCostAndDualVariables(QPainter* painter){
    drawEdges(painter,CV);
    drawVertexs(painter,CV);
    drawSelects(painter);
}
void NSMFrame::drawFlowAndCapacity(QPainter* painter,NSMVertexParam *param){
    if(param->getCapacity()==POS_INFINITY){
        painter->drawText(QRect(param->getFX()-param->getFWidth()/2,param->getFY()-VERTEX_SIZE/2,param->getFWidth(),VERTEX_SIZE)
                          ,QString("%1").arg(param->getFlow()),QTextOption(Qt::AlignCenter));
    }else{
        painter->drawText(QRect(param->getFX()-param->getFWidth()/2,param->getFY()-VERTEX_SIZE/2,param->getFWidth(),VERTEX_SIZE)
                          ,QString("%1 (%2)").arg(param->getFlow()).arg(param->getCapacity()),QTextOption(Qt::AlignCenter));
    }
    painter->setPen(QPen());
}
void NSMFrame::drawCost(QPainter* painter,NSMVertexParam* param){
    if(currentGraphData==NULL){
        painter->drawText(QRect(param->getCX()-param->getCWidth()/2,param->getCY()-VERTEX_SIZE/2,param->getCWidth(),VERTEX_SIZE)
                          ,QString("%1").arg(param->getCost()),QTextOption(Qt::AlignCenter));
    }else if(currentGraphData->getPhase()==2){
        painter->drawText(QRect(param->getCX()-param->getCWidth()/2,param->getCY()-VERTEX_SIZE/2,param->getCWidth(),VERTEX_SIZE)
                          ,QString("%1 [%2]").arg(param->getCost()).arg(param->getRc()),QTextOption(Qt::AlignCenter));

    }else{
        painter->drawText(QRect(param->getCX()-param->getCWidth()/2,param->getCY()-VERTEX_SIZE/2,param->getCWidth(),VERTEX_SIZE)
                          ,QString("[%1]").arg(param->getRc()),QTextOption(Qt::AlignCenter));

    }
    painter->setPen(QPen());
}
void NSMFrame::drawVertexs(QPainter* painter,Type type){
    drawVerticesSelf(painter);
    if(type==DF){
        drawDemand(painter);
    }else if(type==CV){
        drawDualVariable(painter);
    }
}
void NSMFrame::drawEdges(QPainter* painter,Type type){


    painter->setPen(QPen());
    for(int i=1;i<=graph->getCount();i++){
        NSMVertex* v=graph->getVertexAt(i);
        for(int j=0;j<v->getParams()->count();j++){
            NSMVertexParam* param=v->getParams()->at(j);
            if(param->getP()<=graph->getCount()){
                NSMVertex* v1=graph->getVertexAt(param->getP());
                NSMVertex* v2=v;
                if(currentGraphData!=NULL){
                    if(currentGraphData->getBaseMatrix()->isBaseVector(param->getP(),i))
                        painter->setPen(QPen(QColor(255,128,0)));
                    int v1=currentGraphData->getInVector()->getV1();
                    int v2=currentGraphData->getInVector()->getV2();
                    if((v1==param->getP()&&v2==i)||(v1==i&&v2==param->getP())){
                        painter->setPen(QPen(Qt::green));
                    }
                    v1=currentGraphData->getOutVector()->getV1();
                    v2=currentGraphData->getOutVector()->getV2();
                    if((v1==param->getP()&&v2==i)||(v1==i&&v2==param->getP())){
                        painter->setPen(QPen(Qt::red));
                    }
                }
                if(param->getHover())
                    painter->setPen(QColor(Qt::darkYellow));

                if(!param->getCurve()){
                    drawStraightEdge(painter,v1,v2);
                }else{
                    drawCurveEdge(painter,v1,v2);
                }
            }

            if(type==DF){
                drawFlowAndCapacity(painter,param);
            }else if(type==CV){
                drawCost(painter,param);
            }
        }

    }
    if(currentGraphData!=NULL&&currentGraphData->getPhase()==1){

        QList<NSMDummyEdge*> *edges=graph->getDummyEdge();
        NSMVertex* v2=dummyVertex;

        for(int i=0;i<edges->count();i++){
            NSMDummyEdge* edge=edges->at(i);
            NSMVertex* v1=graph->getVertexAt(edge->getP());
            painter->setPen(QPen(Qt::blue));

            if(edge->getDirIn()){
                if(currentGraphData->getBaseMatrix()->isBaseVector(edge->getP(),graph->getCount()+1)){
                    int b1=edge->getP();
                    int b2=graph->getCount()+1;
                    int a1=currentGraphData->getOutVector()->getV1();
                    int a2=currentGraphData->getOutVector()->getV2();
                    if((a1==b1&&a2==b2)||(a1==b2&&a2==b1)){
                        drawStraightDummyEdge(painter,v1,v2,true,true);
                    }else
                        drawStraightDummyEdge(painter,v1,v2,true,false);
                }
                else  drawStraightDummyEdge(painter,v1,v2,false,false);
            }
            else{
                if(currentGraphData->getBaseMatrix()->isBaseVector(edge->getP(),graph->getCount()+1)){
                    int b1=edge->getP();
                    int b2=graph->getCount()+1;
                    int a1=currentGraphData->getOutVector()->getV1();
                    int a2=currentGraphData->getOutVector()->getV2();
                    if((a1==b1&&a2==b2)||(a1==b2&&a2==b1)){
                        drawStraightDummyEdge(painter,v2,v1,true,true);
                    }else
                        drawStraightDummyEdge(painter,v2,v1,true,false);
                }
                else drawStraightDummyEdge(painter,v2,v1,false,false);
            }

            painter->setPen(QPen(Qt::blue));
            if(currentGraphData->getBaseMatrix()->isBaseVector(edge->getP(),graph->getCount()+1)){
                painter->setPen(QPen(QColor(255,128,0)));
                int b1=edge->getP();
                int b2=graph->getCount()+1;
                int a1=currentGraphData->getOutVector()->getV1();
                int a2=currentGraphData->getOutVector()->getV2();
                if((a1==b1&&a2==b2)||(a1==b2&&a2==b1)){
                    painter->setPen(QPen(Qt::red));
                }
            }
            if(type==DF){
                painter->drawText(QRect(edge->getFX()-edge->getFWidth()/2,edge->getFY()-VERTEX_SIZE/2,edge->getFWidth(),VERTEX_SIZE)
                                  ,QString("%1").arg(edge->getFlow()),QTextOption(Qt::AlignCenter));
            }else if(type==CV){
                painter->drawText(QRect(edge->getCX()-edge->getCWidth()/2,edge->getCY()-VERTEX_SIZE/2,edge->getCWidth(),VERTEX_SIZE)
                                  ,QString("[%1]").arg(edge->getRc()),QTextOption(Qt::AlignCenter));
            }
            painter->setPen(QPen());
        }

        painter->setPen(QPen());
    }
    if(createEdge){
        if(!findEdgeTail){
            NSMVertex* v=graph->getVertexAt(createEdgeVertexHead);
            QPoint realPoint=mouseToReal(createEdgeMouseX,createEdgeMouseY);
            drawStraightMaybeEdge(painter,v,realPoint);
        }
        else{
            NSMVertex* v1=graph->getVertexAt(createEdgeVertexHead);
            NSMVertex* v2=graph->getVertexAt(createEdgeVertexTail);
            drawStraightEdge(painter,v1,v2);
        }
    }

}
void NSMFrame::drawVerticesSelf(QPainter* painter){
    painter->setPen(QPen());
    for(int i=1;i<=graph->getCount();i++){
        NSMVertex* v=graph->getVertexAt(i);
        QRect rect;
        rect.setLeft(v->getCenterX()-VERTEX_SIZE/2);
        rect.setTop(v->getCenterY()-VERTEX_SIZE/2);
        rect.setWidth(VERTEX_SIZE);
        rect.setHeight(VERTEX_SIZE);
        if(editable){
            if(v->getSelected()){
                painter->setBrush(QBrush(QColor(234,234,234)));
            }else{
                painter->setBrush(QBrush(QColor(Qt::transparent)));
            }if(keyCtrlDown){
                painter->setPen(QPen(Qt::black,2));
            }else
                painter->setPen(QPen());
        }else{
            painter->setBrush(QColor(248,248,248));
        }
        painter->drawEllipse(rect);
        painter->drawText(rect,QString::number(i),QTextOption(Qt::AlignCenter));
    }
    if(currentGraphData!=NULL&&currentGraphData->getPhase()==1){
        NSMVertex* v=dummyVertex;
        QRect rect;
        rect.setLeft(v->getCenterX()-VERTEX_SIZE/2);
        rect.setTop(v->getCenterY()-VERTEX_SIZE/2);
        rect.setWidth(VERTEX_SIZE);
        rect.setHeight(VERTEX_SIZE);
        painter->setPen(QPen(Qt::blue));
        if(v->getSelected())
            painter->setBrush(QBrush(QColor(128,128,255,64)));
        else
            painter->setBrush(QBrush(QColor(Qt::transparent)));
        painter->drawEllipse(rect);
        painter->drawText(rect,QString::number(graph->getCount()+1),QTextOption(Qt::AlignCenter));

    }
    painter->setPen(QPen());
}
void NSMFrame::drawDemandsAndArcFlowsFrame(QPainter* painter){
    painter->setFont(QFont("微软雅黑",12));
    painter->setPen(QColor(128,0,0));
    painter->drawRect(painter->window().adjusted(0,0,-1,-1));
    painter->drawText(10,20,"Demands and Arc Flows");
    painter->drawText(10,40,"方框内为需求，括号外为流，括号内为容量");
    painter->setFont(QFont("微软雅黑",15));
    if(currentGraphData!=NULL){
        painter->drawText(10,80,QString("Phase:%1").arg(currentGraphData->getPhase()));
    }
    painter->setPen(QPen());

}

void NSMFrame::drawCostAndDualVariablesFrame(QPainter* painter){
    painter->setFont(QFont("微软雅黑",12));
    painter->setPen(QColor(0,128,0));
    painter->drawRect(painter->window().adjusted(1,1,0,0));
    painter->drawText(10,20,"Cost and Dual Variables");
    painter->drawText(10,40,"方框内为对偶变量，括号外为费用，括号内为检验数");
    painter->setFont(QFont("微软雅黑",15));
    if(currentGraphData!=NULL){
        painter->drawText(10,80,QString("Phase:%1").arg(currentGraphData->getPhase()));
    }
    painter->setPen(QPen());

}
void NSMFrame::mousePressEvent(QMouseEvent *event)
{
    int x=event->x();
    int y=event->y();
    currentLMouseX=x;
    currentLMouseY=y;
    if(editable){
        if(event->button()==Qt::LeftButton){
            int pos=checkLBtnDownVertex();
            if(!keyCtrlDown){
                if(pos>0){
                    NSMVertex* v=graph->getVertexAt(pos);
                    if(!multiSelect){
                        moveVertexPos=pos;
                        moveVertexCenterX=v->getCenterX();
                        moveVertexCenterY=v->getCenterY();
                        v->setSelected(true);
                        for(int i=0;i<v->getParams()->count();i++){
                            NSMVertexParam* vp=v->getParams()->at(i);
                            NSMVertex* v1=graph->getVertexAt(vp->getP());
                            setDegAndDisByVertexMove(v1,v,vp);

                        }
                        for(int i=1;i<=graph->getCount();i++){
                            NSMVertex* v2=graph->getVertexAt(i);
                            for(int j=0;j<v2->getParams()->count();j++)
                            {
                                NSMVertexParam *vp=v2->getParams()->at(j);
                                if(vp->getP()==pos)
                                {
                                    setDegAndDisByVertexMove(v,v2,vp);
                                }
                            }
                        }
                    }else{
                        if(v->getSelected()){
                            readyMultiMove=true;
                            v->saveBCenter();
                            v->savePiCenter();
                        }
                        else{
                            multiSelect=false;
                            for(int i=1;i<=graph->getCount();i++){
                                graph->getVertexAt(i)->setSelected(false);
                            }
                            moveVertexPos=pos;
                            moveVertexCenterX=v->getCenterX();
                            moveVertexCenterY=v->getCenterY();
                            v->saveBCenter();
                            v->savePiCenter();
                            v->setSelected(true);
                            for(int i=0;i<v->getParams()->count();i++){
                                NSMVertexParam* vp=v->getParams()->at(i);
                                NSMVertex* v1=graph->getVertexAt(vp->getP());
                                setDegAndDisByVertexMove(v1,v,vp);
                            }
                            for(int i=1;i<=graph->getCount();i++){
                                NSMVertex* v2=graph->getVertexAt(i);
                                for(int j=0;j<v2->getParams()->count();j++)
                                {
                                    NSMVertexParam *vp=v2->getParams()->at(j);
                                    if(vp->getP()==pos)
                                    {
                                        setDegAndDisByVertexMove(v,v2,vp);
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

                if(x<painterRect.width()/2)
                {
                    bool bBreak=false;
                    for(int i=1;i<=graph->getCount();i++){
                        NSMVertex* v=graph->getVertexAt(i);
                        for(int i=0;i<v->getParams()->count();i++){
                            NSMVertexParam* param=v->getParams()->at(i);
                            QPoint mouseReal=mouseToReal2(x,y);
                            if(mouseReal.x()<param->getFX()+param->getFWidth()/2&&
                                    mouseReal.x()>param->getFX()-param->getFWidth()/2&&
                                    mouseReal.y()<param->getFY()+VERTEX_SIZE/2&&
                                    mouseReal.y()>param->getFY()-VERTEX_SIZE/2){
                                maybeMultiSelect=false;
                                moveEdgeLabel=true;
                                param->setMoveFFlag(true);
                                param->saveFXY();
                                bBreak=true;
                                break;
                            }
                        }
                        if(bBreak)break;
                    }

                    for(int i=1;i<=graph->getCount();i++){
                        NSMVertex* v=graph->getVertexAt(i);
                        QPoint mouseReal=mouseToReal2(x,y);
                        if(mouseReal.x()<v->getBCenterX()+v->getBWidth()/2&&
                                mouseReal.x()>v->getBCenterX()-v->getBWidth()/2&&
                                mouseReal.y()<v->getBCenterY()+VERTEX_SIZE/2&&
                                mouseReal.y()>v->getBCenterY()-VERTEX_SIZE/2){
                            v->setBClicked(true);
                        }
                    }
                }else{
                    bool bBreak=false;
                    for(int i=1;i<=graph->getCount();i++){
                        NSMVertex* v=graph->getVertexAt(i);
                        for(int i=0;i<v->getParams()->count();i++){
                            NSMVertexParam* param=v->getParams()->at(i);
                            QPoint mouseReal=mouseToReal2(x-painterRect.width()/2,y);
                            if(mouseReal.x()<param->getCX()+param->getCWidth()/2&&
                                    mouseReal.x()>param->getCX()-param->getCWidth()/2&&
                                    mouseReal.y()<param->getCY()+VERTEX_SIZE/2&&
                                    mouseReal.y()>param->getCY()-VERTEX_SIZE/2){
                                maybeMultiSelect=false;
                                moveEdgeLabel=true;
                                param->setMoveCFlag(true);
                                param->saveCXY();
                                bBreak=true;
                                break;
                            }
                        }
                        if(bBreak)break;
                    }
                }
            }else{
                if(pos>0){
                    NSMVertex* v=graph->getVertexAt(pos);
                    v->setSelected(true);
                    createEdge=true;
                    createEdgeVertexHead=pos;
                    createEdgeMouseX=event->x();
                    createEdgeMouseY=event->y();
                }
            }

        }

    }
    else if(currentGraphData!=NULL&&currentGraphData->getPhase()==1){
        NSMVertex* v=dummyVertex;
        double dis=sqrt((realX-v->getCenterX())*(realX-v->getCenterX())+(realY-v->getCenterY())*(realY-v->getCenterY()));
        if(dis<VERTEX_SIZE/2){
            moveDummyVertex=true;
            moveVertexCenterX=v->getCenterX();
            moveVertexCenterY=v->getCenterY();
            v->saveBCenter();
            v->savePiCenter();
            v->setSelected(true);

        }
        if(x<painterRect.width()/2){


        }else{


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

void NSMFrame::mouseMoveEvent(QMouseEvent *event)
{
    setFocus();
    int x=event->x();
    int y=event->y();
    int x1=x;
    int y1=y;
    if(x1>painterRect.width()/2){
        x1-=painterRect.width()/2;
    }
    realX=(x1-winOffsetX)/winScale;
    realY=(y1-winOffsetY)/winScale;
    if(winStartMove){
        winOffsetX=winOriOffsetX+(event->x()-winStartOffsetX);
        winOffsetY=winOriOffsetY+(event->y()-winStartOffsetY);
    }
    if(editable){
        if(maybeMultiSelect){
            bool b=false;
            maybeMultiSelectMouseX2=x;
            maybeMultiSelectMouseY2=y;
            int xx1,yy1,xx2,yy2;
            if(maybeMultiSelectMouseX<painterRect.width()/2){
                if(maybeMultiSelectMouseX2<painterRect.width()/2){
                    QPoint pp1=mouseToReal(maybeMultiSelectMouseX,maybeMultiSelectMouseY);
                    QPoint pp2=mouseToReal(maybeMultiSelectMouseX2,maybeMultiSelectMouseY2);
                    xx1=pp1.x();
                    yy1=pp1.y();
                    xx2=pp2.x();
                    yy2=pp2.y();
                }else{
                    QPoint pp1=mouseToReal(maybeMultiSelectMouseX,maybeMultiSelectMouseY);
                    QPoint pp2=mouseToReal2(maybeMultiSelectMouseX2,maybeMultiSelectMouseY2);
                    xx1=pp1.x();
                    yy1=pp1.y();
                    xx2=pp2.x();
                    yy2=pp2.y();
                }
            }else{
                if(maybeMultiSelectMouseX2<painterRect.width()/2){
                    QPoint pp1=mouseToReal(maybeMultiSelectMouseX,maybeMultiSelectMouseY);
                    QPoint pp2=mouseToReal2(maybeMultiSelectMouseX2-painterRect.width()/2,maybeMultiSelectMouseY2);
                    xx1=pp1.x();
                    yy1=pp1.y();
                    xx2=pp2.x();
                    yy2=pp2.y();
                }else{
                    QPoint pp1=mouseToReal(maybeMultiSelectMouseX,maybeMultiSelectMouseY);
                    QPoint pp2=mouseToReal(maybeMultiSelectMouseX2,maybeMultiSelectMouseY2);
                    xx1=pp1.x();
                    yy1=pp1.y();
                    xx2=pp2.x();
                    yy2=pp2.y();
                }
            }
            if((abs(maybeMultiSelectMouseX-x)>5)&&(abs(maybeMultiSelectMouseY-y)>5))
                for(int i=1;i<=graph->getCount();i++)
                {

                    int xx=graph->getVertexAt(i)->getCenterX();
                    int yy=graph->getVertexAt(i)->getCenterY();

                    if(((xx>xx1&&xx<xx2)||
                        (xx>xx2&&xx<xx1))&&
                            ((yy>yy1&&yy<yy2)||
                             (yy>yy2&&yy<yy1))
                            ){
                        NSMVertex* v=graph->getVertexAt(i);
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
                NSMVertex *v=graph->getVertexAt(i);
                if(v->getSelected()){
                    v->setCenterX(v->getOriCenterX()+(x-currentLMouseX)/winScale);
                    v->setCenterY(v->getOriCenterY()+(y-currentLMouseY)/winScale);
                    v->setBCenterX(v->getBOriCenterX()+(x-currentLMouseX)/winScale);
                    v->setBCenterY(v->getBOriCenterY()+(y-currentLMouseY)/winScale);
                    v->setPiCenterX(v->getPiOriCenterX()+(x-currentLMouseX)/winScale);
                    v->setPiCenterY(v->getPiOriCenterY()+(y-currentLMouseY)/winScale);
                    for(int j=0;j<v->getParams()->count();j++){
                        NSMVertexParam *vp=v->getParams()->at(j);
                        NSMVertex* v1=graph->getVertexAt(vp->getP());

                        QPoint p=calcTail(v1->getCenterX(),v1->getCenterY(),
                                          calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY())
                                          +vp->getCDeg()
                                          ,vp->getCDis());
                        vp->setCX(p.x());
                        vp->setCY(p.y());
                        p=calcTail(v1->getCenterX(),v1->getCenterY(),
                                   calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY())
                                   +vp->getFDeg()
                                   ,vp->getFDis());
                        vp->setFX(p.x());
                        vp->setFY(p.y());
                    }
                    for(int j=1;j<=graph->getCount();j++){
                        NSMVertex *v2=graph->getVertexAt(j);
                        for(int k=0;k<v2->getParams()->count();k++){
                            NSMVertexParam *vp2=v2->getParams()->at(k);
                            if(vp2->getP()==i)
                            {
                                QPoint p=calcTail(v->getCenterX(),v->getCenterY(),
                                                  calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY())
                                                  +vp2->getCDeg()
                                                  ,vp2->getCDis());
                                vp2->setCX(p.x());
                                vp2->setCY(p.y());
                                p=calcTail(v->getCenterX(),v->getCenterY(),
                                           calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY())
                                           +vp2->getFDeg()
                                           ,vp2->getFDis());
                                vp2->setFX(p.x());
                                vp2->setFY(p.y());
                            }
                        }
                    }
                }

            }
        }
        else if(moveVertexPos>0){
            moveEdgeLabel=false;
            NSMVertex *v=graph->getVertexAt(moveVertexPos);
            int offsetx=(x-currentLMouseX)/winScale;
            int offsety=(y-currentLMouseY)/winScale;
            v->setCenterX(moveVertexCenterX+offsetx);
            v->setCenterY(moveVertexCenterY+offsety);
            v->setBCenterX(v->getBOriCenterX()+offsetx);
            v->setBCenterY(v->getBOriCenterY()+offsety);
            v->setPiCenterX(v->getPiOriCenterX()+offsetx);
            v->setPiCenterY(v->getPiOriCenterY()+offsety);

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
                NSMVertex* v=graph->getVertexAt(pos);
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
                NSMVertex* v=graph->getVertexAt(i);
                for(int i=0;i<v->getParams()->count();i++){
                    NSMVertexParam* param=v->getParams()->at(i);
                    if(param->getMoveCFlag()){
                        param->setCX(param->getOricX()+(x-currentLMouseX)/winScale);
                        param->setCY(param->getOricY()+(y-currentLMouseY)/winScale);
                        NSMVertex* v1=graph->getVertexAt(param->getP());
                        param->setCDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),param->getCX(),param->getCY())
                                       -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                        param->setCDis(calcDis(v1->getCenterX(),v1->getCenterY(),param->getCX(),param->getCY()));
                    }
                    if(param->getMoveFFlag()){
                        param->setFX(param->getOrifX()+(x-currentLMouseX)/winScale);
                        param->setFY(param->getOrifY()+(y-currentLMouseY)/winScale);
                        NSMVertex* v1=graph->getVertexAt(param->getP());
                        param->setFDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),param->getFX(),param->getFY())
                                       -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                        param->setFDis(calcDis(v1->getCenterX(),v1->getCenterY(),param->getFX(),param->getFY()));
                    }
                }
            }
        }
        for(int i=1;i<=graph->getCount();i++){
            NSMVertex* v=graph->getVertexAt(i);
            if(v->getSelected()){
                for(int j=0;j<v->getParams()->count();j++){
                    NSMVertexParam *vp=v->getParams()->at(j);
                    NSMVertex* v1=graph->getVertexAt(vp->getP());

                    QPoint p=calcTail(v1->getCenterX(),v1->getCenterY(),
                                      calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY())
                                      +vp->getCDeg()
                                      ,vp->getCDis());
                    vp->setCX(p.x());
                    vp->setCY(p.y());
                    p=calcTail(v1->getCenterX(),v1->getCenterY(),
                               calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY())
                               +vp->getFDeg()
                               ,vp->getFDis());
                    vp->setFX(p.x());
                    vp->setFY(p.y());
                }
                for(int j=1;j<=graph->getCount();j++){
                    NSMVertex *v2=graph->getVertexAt(j);
                    for(int k=0;k<v2->getParams()->count();k++){
                        NSMVertexParam *vp2=v2->getParams()->at(k);
                        if(vp2->getP()==i)
                        {
                            QPoint p=calcTail(v->getCenterX(),v->getCenterY(),
                                              calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY())
                                              +vp2->getCDeg()
                                              ,vp2->getCDis());
                            vp2->setCX(p.x());
                            vp2->setCY(p.y());
                            p=calcTail(v->getCenterX(),v->getCenterY(),
                                       calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY())
                                       +vp2->getFDeg()
                                       ,vp2->getFDis());
                            vp2->setFX(p.x());
                            vp2->setFY(p.y());
                        }
                    }
                }
            }
        }
    }
    if(moveDummyVertex){
        NSMVertex *v=dummyVertex;
        int offsetx=(x-currentLMouseX)/winScale;
        int offsety=(y-currentLMouseY)/winScale;
        v->setCenterX(moveVertexCenterX+offsetx);
        v->setCenterY(moveVertexCenterY+offsety);
        v->setBCenterX(v->getBOriCenterX()+offsetx);
        v->setBCenterY(v->getBOriCenterY()+offsety);
        v->setPiCenterX(v->getPiOriCenterX()+offsetx);
        v->setPiCenterY(v->getPiOriCenterY()+offsety);
        moveDummyLabel();
    }
    for(int i=1;i<=graph->getCount();i++){
        NSMVertex* v=graph->getVertexAt(i);
        for(int j=0;j<v->getParams()->count();j++){
            NSMVertexParam* param=v->getParams()->at(j);
            param->setHover(false);
            if(x<painterRect.width()/2){
                QPoint mouseReal=mouseToReal2(x,y);
                if(mouseReal.x()<param->getFX()+param->getFWidth()/2&&
                        mouseReal.x()>param->getFX()-param->getFWidth()/2&&
                        mouseReal.y()<param->getFY()+VERTEX_SIZE/2&&
                        mouseReal.y()>param->getFY()-VERTEX_SIZE/2){

                    param->setHover(true);
                }
            }else{
                QPoint mouseReal=mouseToReal2(x-painterRect.width()/2,y);
                if(mouseReal.x()<param->getCX()+param->getCWidth()/2&&
                        mouseReal.x()>param->getCX()-param->getCWidth()/2&&
                        mouseReal.y()<param->getCY()+VERTEX_SIZE/2&&
                        mouseReal.y()>param->getCY()-VERTEX_SIZE/2){

                    param->setHover(true);
                }
            }
        }
    }

    update();
    event->accept();
}

void NSMFrame::mouseReleaseEvent(QMouseEvent *event)
{
    int x=event->x();
    int y=event->y();

    if(editable)
    {
        if(event->button()==Qt::LeftButton)
        {
            for(int i=1;i<=graph->getCount();i++)
            {
                NSMVertex* v=graph->getVertexAt(i);
                if(v->getBClicked()&&x<painterRect.width()/2){
                    if(abs(maybeMultiSelectMouseX-maybeMultiSelectMouseX2)<2&&
                            abs(maybeMultiSelectMouseY-maybeMultiSelectMouseY2)<2)
                    {
                        QPoint mouseReal=mouseToReal2(x,y);
                        if(mouseReal.x()<v->getBCenterX()+v->getBWidth()/2&&
                                mouseReal.x()>v->getBCenterX()-v->getBWidth()/2&&
                                mouseReal.y()<v->getBCenterY()+VERTEX_SIZE/2&&
                                mouseReal.y()>v->getBCenterY()-VERTEX_SIZE/2)
                        {
                            SetDemandDialog dialog(this);
                            dialog.setDemandText(QString::number(v->getB()));
                            if(dialog.exec()==QDialog::Accepted){
                                v->setB(dialog.getDemand());
                            }
                        }
                    }

                }
                if(!keyCtrlDown&&!multiSelect&&!maybeMultiSelect){
                    if(v->getSelected()&&abs(v->getOriCenterX()-v->getCenterX())<2&&abs(v->getOriCenterY()-v->getCenterY())<2)
                    {
                        int pos=v->getLabelPos();
                        pos++;
                        if(pos>3)
                            pos=0;
                        v->setLabelPos(pos);
                        moveVertexLabel(v);
                    }
                }
                v->saveCenter();
                v->saveBCenter();
                v->savePiCenter();
                v->setBClicked(false);
                if(!multiSelect){
                    v->setSelected(false);
                }
            }
            if(createEdge){
                if(findEdgeTail){
                    if(createEdgeVertexHead!=createEdgeVertexTail){
                        NSMVertex * v=graph->getVertexAt(createEdgeVertexTail);
                        NSMVertex* v1=graph->getVertexAt(createEdgeVertexHead);
                        int cc=POS_INFINITY;
                        for(int i=0;i<v->getParams()->count();i++){
                            NSMVertexParam* vp=v->getParams()->at(i);
                            if(vp->getP()==createEdgeVertexHead){
                                cc=vp->getCost();
                            }
                        }
                        SetCapacityAndCostDialog dialog(this);
                        dialog.setCost(cc);
                        if(dialog.exec()==QDialog::Accepted){
                            int cost=dialog.getCost();
                            int capacity=dialog.getCapacity();
                            QPoint edgeCenter=calcEdgeCenter(v1,v);
                            NSMVertexParam* vp=new NSMVertexParam(createEdgeVertexHead,cost,capacity);
//                            for(int i=0;i<v1->getParams()->count();i++){
//                                NSMVertexParam *vp1=v1->getParams()->at(i);
//                                if(vp1->getP()==createEdgeVertexTail){
//                                    vp1->setCurve(true);
//                                    vp->setCurve(true);
//                                    break;
//                                }
//                            }
                            vp->setFX(edgeCenter.x());
                            vp->setFY(edgeCenter.y());
                            int width;
                            if(vp->getCapacity()==POS_INFINITY){
                                width= QFontMetrics(QFont("微软雅黑",15)).horizontalAdvance(QString("%1 (∞)").arg(vp->getFlow()));
                            }else{
                                width= QFontMetrics(QFont("微软雅黑",15)).horizontalAdvance(QString("%1 (%2)").arg(vp->getFlow()).arg(vp->getCapacity()));
                            }
                            vp->setFWidth(LABELLRMARGIN+width);
                            vp->setFDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),vp->getFX(),vp->getFY())
                                        -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                            vp->setFDis(calcDis(v1->getCenterX(),v1->getCenterY(),vp->getFX(),vp->getFY()));
                            vp->setCX(edgeCenter.x());
                            vp->setCY(edgeCenter.y());
                            width=QFontMetrics(QFont("微软雅黑",15)).horizontalAdvance(QString("%1 [%2]").arg(vp->getCost()).arg(vp->getRc()));
                            vp->setCWidth(LABELLRMARGIN+width);
                            vp->setCDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),vp->getCX(),vp->getCY())
                                        -calcDeg(v1->getCenterX(),v1->getCenterY(),v->getCenterX(),v->getCenterY()));
                            vp->setCDis(calcDis(v1->getCenterX(),v1->getCenterY(),vp->getCX(),vp->getCY()));
                            v->addVertexParams(vp);

                        }else{
                            bool b=false;
                            for(int i=0;i<v->getParams()->count();i++){
                                if(v->getParams()->at(i)->getP()==createEdgeVertexHead){
                                    NSMVertex *v1=graph->getVertexAt(createEdgeVertexHead);
                                    for(int j=0;j<v1->getParams()->count();j++){
                                        NSMVertexParam *vp1=v1->getParams()->at(j);
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
                NSMVertex* v=graph->getVertexAt(i);
                for(int i=0;i<v->getParams()->count();i++){
                    NSMVertexParam* param=v->getParams()->at(i);
                    param->setMoveCFlag(false);
                    param->setMoveFFlag(false);
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
    if(moveDummyVertex){
        if(dummyVertex->getSelected()&&abs(dummyVertex->getOriCenterX()-dummyVertex->getCenterX())<2
                &&abs(dummyVertex->getOriCenterY()-dummyVertex->getCenterY())<2)
        {
            int pos=dummyVertex->getLabelPos();
            pos++;
            if(pos>3)
                pos=0;
            dummyVertex->setLabelPos(pos);
            moveVertexLabel(dummyVertex);
        }
        moveDummyVertex=false;
        dummyVertex->saveCenter();
        dummyVertex->saveBCenter();
        dummyVertex->savePiCenter();
        dummyVertex->setSelected(false);
    }
    update();
    event->accept();


}

void NSMFrame::wheelEvent(QWheelEvent *event)
{
    QPoint delta=event->angleDelta();
    int x=event->x();
    int y=event->y();
    int oriOffsetX=winOffsetX;
    int oriOffsetY=winOffsetY;
    double oriscale=winScale;
    if(delta.ry()<0){
        if(winScale>0.4)
            winScale-=0.05;
    }else{
        if(winScale<1.5)
            winScale+=0.05;
    }
    double nowScale=winScale;
    int width=painterRect.width();
    if(x>=width/2){
        x-=width/2;
    }
    winOffsetX=x-(x-oriOffsetX)*nowScale/oriscale;
    winOffsetY=y-(y-oriOffsetY)*nowScale/oriscale;
    saveWinOffset();
    update();
    event->accept();
}

void NSMFrame::keyPressEvent(QKeyEvent *event)
{
    if(editable)
    {
        if(event->key()==Qt::Key_Control){
            keyCtrlDown=true;
            multiSelect=false;
            maybeMultiSelect=false;
            readyMultiMove=false;
            if(moveVertexPos!=0){
                NSMVertex* v=graph->getVertexAt(moveVertexPos);
                v->setSelected(false);
                moveVertexPos=0;
            }
            for(int i=1;i<=graph->getCount();i++){
                graph->getVertexAt(i)->setSelected(false);
            }
        }else if(event->key()==Qt::Key_Delete){
            for(int i=1;i<=graph->getCount();i++){
                NSMVertex* v=graph->getVertexAt(i);
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

void NSMFrame::keyReleaseEvent(QKeyEvent *event)
{
    if(editable)
    {
        if(event->key()==Qt::Key_Control){
            keyCtrlDown=false;
            createEdge=false;
            for(int i=1;i<=graph->getCount();i++){
                graph->getVertexAt(i)->setSelected(false);
            }
        }

    }
    update();
    event->accept();
}

QRect NSMFrame::getPainterRect() const
{
    return painterRect;
}

NSMVertex *NSMFrame::getDummyVertex() const
{
    return dummyVertex;
}

bool NSMFrame::getEditable() const
{
    return editable;
}

void NSMFrame::setEditable(bool value)
{
    editable = value;
}

int NSMFrame::getWinOffsetY() const
{
    return winOffsetY;
}

void NSMFrame::setWinOffsetY(int value)
{
    winOffsetY = value;
}

NSMGraph *NSMFrame::getGraph()
{
    return graph;
}

int NSMFrame::getWinOffsetX() const
{
    return winOffsetX;
}

void NSMFrame::setWinOffsetX(int value)
{
    winOffsetX = value;
}

double NSMFrame::getWinScale() const
{
    return winScale;
}
void NSMFrame::init(){
    clearState();
    editable=true;
    winScale=0.8;
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
void NSMFrame::setWinScale(double value)
{
    winScale = value;
}

void NSMFrame::clearState()
{
    winStartMove=false;
    keyCtrlDown=false;
    maybeMultiSelect=false;
    multiSelect=false;
    readyMultiMove=false;
    createEdge=false;
    findEdgeTail=false;
    moveEdgeLabel=false;
    moveDummyVertex=false;
}

void NSMFrame::saveWinOffset()
{
    winOriOffsetX=winOffsetX;
    winOriOffsetY=winOffsetY;
}

QPoint NSMFrame::mouseToReal(int x,int y)
{
    int width=painterRect.width();
    if(x>=width/2){
        x-=width/2;
    }
    return QPoint((x-winOffsetX)/winScale,(y-winOffsetY)/winScale);
}
QPoint NSMFrame::mouseToReal2(int x,int y)
{

    return QPoint((x-winOffsetX)/winScale,(y-winOffsetY)/winScale);
}
QPoint NSMFrame::realToMouse(int x,int y)
{
    int width=painterRect.width();
    if(x>=width/2){
        x-=width/2;
    }
    return QPoint(x*winScale+winOffsetX,y*winScale+winOffsetY);
}
int NSMFrame::checkLBtnDownVertex(){

    int ret=0;

    for(int i=graph->getCount();i>=1;i--){
        NSMVertex* v=graph->getVertexAt(i);
        double dis=sqrt((realX-v->getCenterX())*(realX-v->getCenterX())+(realY-v->getCenterY())*(realY-v->getCenterY()));
        if(dis<VERTEX_SIZE/2){
            ret=i;
            break;
        }
    }
    return ret;
}
void NSMFrame::reset(){
    refresh=false;
}
void NSMFrame::drawStraightMaybeEdge(QPainter* painter, NSMVertex *v1, QPoint p){
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
void NSMFrame::drawStraightEdge(QPainter* painter,NSMVertex* v1,NSMVertex* v2)
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
void NSMFrame::drawCurveEdge(QPainter* painter,NSMVertex* v1,NSMVertex* v2)
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
void NSMFrame::drawStraightDummyEdge(QPainter* painter,NSMVertex* v1,NSMVertex* v2,bool base,bool out)
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
        if(base) {
            if(out){
                painter->setPen(QPen(QBrush(Qt::red),1,Qt::DashLine));
            }else{
                painter->setPen(QPen(QBrush(QColor(255,128,0)),1,Qt::DashLine));
            }
        }
        else painter->setPen(QPen(QBrush(Qt::blue),1,Qt::DashLine));
        painter->drawLine(startPoint,realPoint);
        if(base){
            if(out){
                painter->setPen(QPen(Qt::red));
            }else{
                painter->setPen(QPen(QColor(255,128,0)));
            }
        }

        else painter->setPen(QPen(Qt::blue));
        double deg=calcDeg(realPoint.x(),realPoint.y(),startPoint.x(),startPoint.y());
        for(int j=-30;j<=30;j++)
        {
            QPoint p=calcTail(realPoint.x(),realPoint.y(),deg+j,VERTEX_SIZE/8/cos(j*PI/180));
            painter->drawLine(realPoint,p);
        }

    }
    painter->setPen(QPen());
}
void NSMFrame::drawCurveDummyEdge(QPainter* painter,NSMVertex* v1,NSMVertex* v2,bool base,bool out)
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
        if(base) {
            if(out){
                painter->setPen(QPen(QBrush(Qt::red),1,Qt::DashLine));
            }else{
                painter->setPen(QPen(QBrush(QColor(255,128,0)),1,Qt::DashLine));
            }
        }
        else painter->setPen(QPen(QBrush(Qt::blue),1,Qt::DashLine));
        painter->drawPath(path);
        if(base){
            if(out){
                painter->setPen(QPen(Qt::red));
            }else{
                painter->setPen(QPen(QColor(255,128,0)));
            }
        }

        else painter->setPen(QPen(Qt::blue));
        deg=calcDeg(realPoint.x(),realPoint.y(),startPoint.x(),startPoint.y());
        deg+=degoffset;
        for(int j=-30;j<=30;j++)
        {
            QPoint p=calcTail(realPoint.x(),realPoint.y(),deg+j,VERTEX_SIZE/8/cos(j*PI/180));
            painter->drawLine(realPoint,p);
        }

    }
    painter->setPen(QPen());
}



void NSMFrame::drawDemand(QPainter* painter){
    painter->setBrush(Qt::transparent);
    painter->setFont(QFont("微软雅黑",15));
    for(int i=1;i<=graph->getCount();i++){
        NSMVertex* v=graph->getVertexAt(i);
        QRect rect;
        rect.setLeft(v->getBCenterX()-v->getBWidth()/2);
        rect.setTop(v->getBCenterY()-VERTEX_SIZE/2);
        rect.setWidth(v->getBWidth());
        rect.setHeight(VERTEX_SIZE);

        painter->drawRect(rect);
        painter->drawText(rect,QString::number(v->getB()),QTextOption(Qt::AlignCenter));
    }
    if(currentGraphData!=NULL&&currentGraphData->getPhase()==1){
        NSMVertex* v=dummyVertex;
        QRect rect;
        rect.setLeft(v->getBCenterX()-v->getBWidth()/2);
        rect.setTop(v->getBCenterY()-VERTEX_SIZE/2);
        rect.setWidth(v->getBWidth());
        rect.setHeight(VERTEX_SIZE);
        painter->setPen(QPen(Qt::blue));
        painter->drawRect(rect);
        painter->drawText(rect,QString::number(v->getB()),QTextOption(Qt::AlignCenter));

    }
    painter->setPen(QPen());

}
void NSMFrame::drawDualVariable(QPainter* painter){
    painter->setBrush(Qt::transparent);
    painter->setFont(QFont("微软雅黑",15));
    for(int i=1;i<=graph->getCount();i++){
        NSMVertex* v=graph->getVertexAt(i);
        QRect rect;
        rect.setLeft(v->getPiCenterX()-v->getPiWidth()/2);
        rect.setTop(v->getPiCenterY()-VERTEX_SIZE/2);
        rect.setWidth(v->getPiWidth());
        rect.setHeight(VERTEX_SIZE);
        painter->drawRect(rect);
        if(v->getPi()==POS_INFINITY){
            painter->drawText(rect,"∞",QTextOption(Qt::AlignCenter));
        }else
        {
            painter->drawText(rect,QString::number(v->getPi()),QTextOption(Qt::AlignCenter));
        }
    }
    if(currentGraphData!=NULL&&currentGraphData->getPhase()==1){
        NSMVertex* v=dummyVertex;
        QRect rect;
        rect.setLeft(v->getPiCenterX()-v->getPiWidth()/2);
        rect.setTop(v->getPiCenterY()-VERTEX_SIZE/2);
        rect.setWidth(v->getPiWidth());
        rect.setHeight(VERTEX_SIZE);
        painter->setPen(QPen(Qt::blue));
        painter->drawRect(rect);
        if(v->getPi()==POS_INFINITY){
            painter->drawText(rect,"∞",QTextOption(Qt::AlignCenter));
        }else
        {
            painter->drawText(rect,QString::number(v->getPi()),QTextOption(Qt::AlignCenter));
        }
    }
    painter->setPen(QPen());


}
void NSMFrame::moveVertexLabel(NSMVertex* v){
    int pos=v->getLabelPos();
    switch(pos)
    {
    case 0:
        v->setBCenterY(v->getCenterY()-VERTEX_SIZE*3/2);
        v->setBCenterX(v->getCenterX());
        v->setPiCenterY(v->getCenterY()-VERTEX_SIZE*3/2);
        v->setPiCenterX(v->getCenterX());
        break;
    case 1:
        v->setBCenterX(v->getCenterX()+VERTEX_SIZE+v->getBWidth()/2);
        v->setBCenterY(v->getCenterY());
        v->setPiCenterX(v->getCenterX()+VERTEX_SIZE+v->getPiWidth()/2);
        v->setPiCenterY(v->getCenterY());
        break;
    case 2:
        v->setBCenterY(v->getCenterY()+VERTEX_SIZE*3/2);
        v->setBCenterX(v->getCenterX());
        v->setPiCenterY(v->getCenterY()+VERTEX_SIZE*3/2);
        v->setPiCenterX(v->getCenterX());
        break;
    case 3:
        v->setBCenterX(v->getCenterX()-VERTEX_SIZE-v->getBWidth()/2);
        v->setBCenterY(v->getCenterY());
        v->setPiCenterX(v->getCenterX()-VERTEX_SIZE-v->getPiWidth()/2);
        v->setPiCenterY(v->getCenterY());
        break;
    }
    v->saveBCenter();
    v->savePiCenter();
}
QPoint NSMFrame::calcEdgeCenter(NSMVertex* v1,NSMVertex* v2){
    return  QPoint((v1->getCenterX()+v2->getCenterX())/2,(v1->getCenterY()+v2->getCenterY())/2);
}
void NSMFrame::setDegAndDisByVertexMove(NSMVertex* v1,NSMVertex* v2,NSMVertexParam* vp){

    vp->setCDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),vp->getCX(),vp->getCY())
                -calcDeg(v1->getCenterX(),v1->getCenterY(),v2->getCenterX(),v2->getCenterY()));
    vp->setCDis(calcDis(v1->getCenterX(),v1->getCenterY(),vp->getCX(),vp->getCY()));

    vp->setFDeg(calcDeg(v1->getCenterX(),v1->getCenterY(),vp->getFX(),vp->getFY())
                -calcDeg(v1->getCenterX(),v1->getCenterY(),v2->getCenterX(),v2->getCenterY()));
    vp->setFDis(calcDis(v1->getCenterX(),v1->getCenterY(),vp->getFX(),vp->getFY()));
}
void NSMFrame::setGraphData(int num){
    NSMGraphData* data=graph->getGraphData()->at(num);
    currentGraphData=data;

    QFontMetrics metrics(QFont("微软雅黑",15));
    for(int i=1;i<=graph->getCount();i++){
        NSMVertex* v=graph->getVertexAt(i);
        NSMVertexData* vd=data->getVertexDatas()->at(i-1);
        v->setB(vd->getB());
        v->setBWidth(LABELLRMARGIN+metrics.horizontalAdvance(QString::number(v->getB())));
        v->setPi(vd->getPi());

        for(int j=0;j<vd->getParams()->count();j++){

            NSMVertexParamData* pd=vd->getParams()->at(j);
            for(int k=0;k<v->getParams()->count();k++){
                NSMVertexParam* p=v->getParams()->at(k);
                if(pd->getP()==p->getP()){
                    p->setC(pd->getC());
                    p->setCapacity(pd->getCapacity());
                    p->setCost(pd->getCost());
                    p->setFlow(pd->getFlow());
                    p->setP(pd->getP());
                    p->setRc(pd->getRc());
                    int width=metrics.horizontalAdvance(QString("%1 [%2]").arg(p->getCost()).arg(p->getRc()));
                    p->setCWidth(LABELLRMARGIN+width);
                    if(p->getCapacity()==POS_INFINITY){
                        width= metrics.horizontalAdvance(QString("%1").arg(p->getFlow()));
                    }else{
                        width= metrics.horizontalAdvance(QString("%1 (%2)").arg(p->getFlow()).arg(p->getCapacity()));
                    }
                    p->setFWidth(LABELLRMARGIN+width);
                    break;
                }

            }
            if(pd->getP()==graph->getCount()+1){
                for(int l=0;l<graph->getDummyEdge()->count();l++){
                    NSMDummyEdge* edge=graph->getDummyEdge()->at(l);
                    if(edge->getP()==i){
                        edge->setFlow(pd->getFlow());
                        edge->setRc(pd->getRc());
                        break;
                    }
                }
            }

        }
    }
    if(data->getPhase()==1){
        NSMVertex* v=dummyVertex;
        NSMVertexData* vd=data->getVertexDatas()->at(data->getVertexDatas()->count()-1);

        v->setB(vd->getB());
        v->setBWidth(LABELLRMARGIN+metrics.horizontalAdvance(QString::number(v->getB())));
        v->setPi(vd->getPi());
        if(v->getPi()==POS_INFINITY){
            v->setPiWidth(LABELLRMARGIN+metrics.horizontalAdvance("∞"));
        }else{
            v->setPiWidth(LABELLRMARGIN+metrics.horizontalAdvance(QString::number(v->getPi())));
        }
        for(int j=0;j<vd->getParams()->count();j++){
            NSMVertexParamData* pd=vd->getParams()->at(j);
            NSMDummyEdge* p=graph->getDummyEdge()->at(pd->getP()-1);
            p->setFlow(pd->getFlow());
            p->setRc(pd->getRc());
        }
    }
    update();
}

void NSMFrame::clearCurrentGraphData()
{
    currentGraphData=NULL;
}
void NSMFrame::setCurrentGraphData(NSMGraphData* data)
{
    currentGraphData=data;
}
void NSMFrame::moveDummyLabel(){
    NSMVertex *v=dummyVertex;
    for(int i=0;i<getGraph()->getDummyEdge()->count();i++){
        NSMDummyEdge* edge=getGraph()->getDummyEdge()->at(i);
        NSMVertex* v1=graph->getVertexAt(edge->getP());
        QPoint edgeCenter=calcEdgeCenter(v1,v);
        edge->setCX(edgeCenter.x());
        edge->setCY(edgeCenter.y());
        edge->setFX(edgeCenter.x());
        edge->setFY(edgeCenter.y());

    }

}
