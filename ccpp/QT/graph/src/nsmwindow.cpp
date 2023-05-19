#include "nsmwindow.h"
#include "ui_nsmwindow.h"
#include "launchdialog.h"
#include "setdemanddialog.h"
#include <QScreen>
#include <QMessageBox>
#include <QTextCodec>
#include <QFileDialog>
#include <QCloseEvent>
#include "common.h"
NSMWindow::NSMWindow(QWidget *parent) :
    mParent(parent),
    ui(new Ui::NSMWindow)
{
    setWindowFlags(windowFlags()|Qt::Window);
    ui->setupUi(this);
    QScreen* screen=QGuiApplication::primaryScreen();
    QSize size=screen->size();
    int width=geometry().width();
    int height=geometry().height();
    setGeometry((size.width()-width)/2,(size.height()-height)/2,width,height);
    init();

}
void NSMWindow::closeEvent(QCloseEvent *event){
    ((LaunchDialog*)parent())->show();
    event->accept();
}

QWidget *NSMWindow::parent() const
{
    return mParent;
}
NSMWindow::~NSMWindow()
{
    delete ui;


}
void NSMWindow::init(){
    nsm=ui->nsm;
    connect(ui->btnAddVertex,SIGNAL(clicked()),this,SLOT(onBtnAddVertexClicked()));
    connect(ui->btnRemoveAllVertex,SIGNAL(clicked()),this,SLOT(onBtnRemoveAllVertexClicked()));
    connect(ui->btnNext,SIGNAL(clicked()),this,SLOT(onBtnNextClicked()));
    connect(ui->btnPrev,SIGNAL(clicked()),this,SLOT(onBtnPrevClicked()));
    connect(ui->rbEditMode,SIGNAL(toggled(bool)),this,SLOT(onRadioBtnEditModeToggled(bool)));
    connect(ui->btnCalculate,SIGNAL(clicked()),this,SLOT(onBtnCalcClicked()));
    connect(ui->actionOpen,SIGNAL(triggered()),this,SLOT(onActionOpen()));
    connect(ui->actionSave,SIGNAL(triggered()),this,SLOT(onActionSave()));
    connect(ui->cbStep,SIGNAL(currentIndexChanged(int)),this,SLOT(onCbStepCurrentIndexChanged(int)));

}
void NSMWindow::addVertex(){
    SetDemandDialog dialog(this);
    if(dialog.exec()==QDialog::Accepted){
        QRect rect=nsm->geometry();
        NSMGraph* graph=nsm->getGraph();
        NSMVertex *v=new NSMVertex();
        if(graph->getCount()==0){
            v->setCenterX(VERTEX_SIZE/2);
            v->setCenterY(VERTEX_SIZE/2);

        }else{
            int lastx=graph->getLastX();
            int lasty=graph->getLastY();
            if(lastx+VERTEX_SIZE>(rect.width()*3/8-nsm->getWinOffsetX())/nsm->getWinScale()){
                v->setCenterX(VERTEX_SIZE/2);
                v->setCenterY(lasty+VERTEX_SIZE*3);
            }else{
                v->setCenterX(lastx+VERTEX_SIZE*3/2);
                v->setCenterY(lasty);
            }

        }
        v->setBCenterY(v->getCenterY()-VERTEX_SIZE*3/2);
        v->setBCenterX(v->getCenterX());
        v->saveBCenter();
        nsm->moveVertexLabel(v);
        int width=QFontMetrics(QFont("微软雅黑",15)).horizontalAdvance(QString::number(dialog.getDemand()));
        v->setBWidth(width+LABELLRMARGIN);
        v->setB(dialog.getDemand());
        width=QFontMetrics(QFont("微软雅黑",15)).horizontalAdvance("∞");
        v->setPiWidth(width+LABELLRMARGIN);
        v->setPi(POS_INFINITY);
        graph->addVertex(v);
    }

}
void NSMWindow::onBtnAddVertexClicked(){

    addVertex();
    nsm->setFocus();
    nsm->update();

}
void NSMWindow::onBtnCalcClicked(){
    nsm->getGraph()->clearFlow();
    nsm->getGraph()->setCost();
    if(nsm->getGraph()->getCount()<2){
        QMessageBox::critical(this,"错误","至少要有两个节点！",QMessageBox::Ok);
        ui->rbEditMode->setEnabled(false);
    }else{
        int code=nsm->getGraph()->ctsma();
        if(code==UNSUPPORTED_ERROR){
            QMessageBox::critical(this,"错误","这里不会做了。。",QMessageBox::Ok);
            return;
        }
        QList<NSMGraphData*>* dataList=nsm->getGraph()->getGraphData();
        NSMVertex* dummy=nsm->getDummyVertex();
        QPoint point=nsm->mouseToReal2(nsm->getPainterRect().width()/4,nsm->getPainterRect().height()/2);
        dummy->setCenterX(point.x());
        dummy->setCenterY(point.y());
        dummy->saveCenter();
        dummy->setLabelPos(0);
        dummy->setB(0);
        dummy->setPi(POS_INFINITY);
        nsm->moveVertexLabel(dummy);
        for(int i=0;i<nsm->getGraph()->getDummyEdge()->count();i++){
            NSMDummyEdge* edge=nsm->getGraph()->getDummyEdge()->at(i);
            NSMVertex* v=nsm->getGraph()->getVertexAt(edge->getP());
            QPoint edgeCenter=nsm->calcEdgeCenter(dummy,v);

            edge->setFX(edgeCenter.x());
            edge->setFY(edgeCenter.y());
            edge->setCX(edgeCenter.x());
            edge->setCY(edgeCenter.y());
        }
        disconnect(ui->cbStep,SIGNAL(currentIndexChanged(int)),this,SLOT(onCbStepCurrentIndexChanged(int)));
        ui->cbStep->clear();
        connect(ui->cbStep,SIGNAL(currentIndexChanged(int)),this,SLOT(onCbStepCurrentIndexChanged(int)));
        int p1=0;
        int p2=0;
        for(int i=0;i<dataList->count();i++){
            NSMGraphData* data=dataList->at(i);
            if(data->getPhase()==1){
                p1++;
                ui->cbStep->addItem(QString("Phase1:%1").arg(p1));
            }else if(data->getPhase()==2){
                p2++;
                ui->cbStep->addItem(QString("Phase2:%1").arg(p2));
            }else{

            }

            ui->cbStep->setCurrentIndex(ui->cbStep->count()-1);
            nsm->setGraphData(ui->cbStep->count()-1);
        }
        if(ERROR_CODE==code){
            QMessageBox::critical(this,"错误","未找到初始可行解，无法求得最小费用流",QMessageBox::Ok);
        }
        ui->rbEditMode->setEnabled(true);
        ui->rbEditMode->setChecked(false);
    }

    nsm->update();

}
void NSMWindow::onBtnRemoveAllVertexClicked(){
    if(QMessageBox::Yes==QMessageBox::warning(this,"警告","是否全部删除?",QMessageBox::Yes,QMessageBox::No))
    {
        NSMGraph* graph=nsm->getGraph();
        graph->clearVertexs();
        nsm->reset();
        nsm->setFocus();
        nsm->update();
    }
}
void NSMWindow::onRadioBtnEditModeToggled(bool b){
    if(b){
        ui->rbEditMode->setEnabled(false);
        nsm->clearCurrentGraphData();
        nsm->setEditable(true);
        nsm->clearState();
        nsm->getGraph()->clearVerticesStates();
        nsm->getGraph()->clearFlow();
        nsm->getGraph()->setCost();
        ui->btnAddVertex->setEnabled(true);
        ui->btnRemoveAllVertex->setEnabled(true);
        ui->btnPrev->setEnabled(false);
        ui->btnNext->setEnabled(false);
        ui->cbStep->setEnabled(false);
    }
    else
    {

        int index=ui->cbStep->currentIndex();
        if(index!=-1)
            nsm->setGraphData(index);
        nsm->moveDummyLabel();
        nsm->setEditable(false);
        ui->btnAddVertex->setEnabled(false);
        ui->btnRemoveAllVertex->setEnabled(false);
        ui->btnPrev->setEnabled(true);
        ui->btnNext->setEnabled(true);
        ui->cbStep->setEnabled(true);

    }
    nsm->setFocus();
    nsm->update();
}
void NSMWindow::onActionOpen(){
    nsm->clearState();
    QTextCodec *codec=QTextCodec::codecForName("utf-8");
    QString filter=codec->toUnicode("网络单纯形法文件(*.nsm)");
    QString fileName=QFileDialog::getOpenFileName(
                this,"打开文件",QString(),filter);
    if(!fileName.isEmpty()){
        QFile file(fileName);
        if(file.open(QIODevice::ReadOnly))
        {
            int tempint;
            double tempdouble;
            bool tempbool;
            file.read((char*)&tempint,sizeof(int));
            nsm->setWinOffsetX(tempint);
            file.read((char*)&tempint,sizeof(int));
            nsm->setWinOffsetY(tempint);
            file.read((char*)&tempdouble,sizeof(double));
            nsm->setWinScale(tempdouble);
            NSMGraph* g=nsm->getGraph();
            g->clearVertexs();
            int vertexcount;
            file.read((char*)&vertexcount,sizeof(int));
            for(int i=1;i<=vertexcount;i++){
                NSMVertex* v=new NSMVertex();
                file.read((char*)&tempint,sizeof(int));
                v->setB(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setPi(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setBCenterX(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setBCenterY(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setBWidth(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setPiCenterX(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setPiCenterY(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setPiWidth(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setLabelPos(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setCenterX(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setCenterY(tempint);
                int paramcount;
                file.read((char*)&paramcount,sizeof(int));
                for(int j=0;j<paramcount;j++){

                    NSMVertexParam* vp=new NSMVertexParam();
                    file.read((char*)&tempint,sizeof(int));
                    vp->setCost(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setOriCost(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setFlow(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setCapacity(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setCX(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setCY(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setCWidth(tempint);
                    file.read((char*)&tempdouble,sizeof(double));
                    vp->setCDeg(tempdouble);
                    file.read((char*)&tempdouble,sizeof(double));
                    vp->setCDis(tempdouble);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setFX(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setFY(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setFWidth(tempint);
                    file.read((char*)&tempdouble,sizeof(double));
                    vp->setFDeg(tempdouble);
                    file.read((char*)&tempdouble,sizeof(double));
                    vp->setFDis(tempdouble);
                    file.read((char*)&tempbool,sizeof(bool));
                    vp->setCurve(tempbool);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setP(tempint);
                    v->addVertexParams(vp);
                    vp->saveCXY();
                    vp->saveFXY();


                }

                g->addVertex(v);
                v->saveBCenter();
                v->saveCenter();
                v->savePiCenter();
                nsm->moveVertexLabel(v);

            }
            nsm->saveWinOffset();
            ui->rbEditMode->setChecked(true);
            nsm->clearState();
            nsm->getGraph()->clearVerticesStates();
            update();
        }

    }
}
void NSMWindow::onActionSave(){
    nsm->clearState();
    QTextCodec *codec=QTextCodec::codecForName("utf-8");
    QString filter=codec->toUnicode("网络单纯形法文件(*.nsm)");
    QString fileName=QFileDialog::getSaveFileName(
                this,"保存文件",QString(),filter);
    if(!fileName.isEmpty()){
        QFile file(fileName);
        if(file.open(QIODevice::WriteOnly))
        {
            int tempint;
            double tempdouble;
            bool tempbool;
            tempint=nsm->getWinOffsetX();
            file.write((char*)&tempint,sizeof(int));
            tempint=nsm->getWinOffsetY();
            file.write((char*)&tempint,sizeof(int));
            tempdouble=nsm->getWinScale();
            file.write((char*)&tempdouble,sizeof(double));
            NSMGraph* g=nsm->getGraph();
            tempint=g->getCount();
            file.write((char*)&tempint,sizeof(int));
            for(int i=1;i<=g->getCount();i++){
                NSMVertex* v=g->getVertexAt(i);
                tempint=v->getB();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getPi();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getBCenterX();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getBCenterY();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getBWidth();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getPiCenterX();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getPiCenterY();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getPiWidth();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getLabelPos();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getCenterX();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getCenterY();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getParams()->count();
                file.write((char*)&tempint,sizeof(int));
                for(int j=0;j<v->getParams()->count();j++){
                    NSMVertexParam* vp=v->getParams()->at(j);
                    tempint=vp->getCost();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getOriCost();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getFlow();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getCapacity();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getCX();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getCY();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getCWidth();
                    file.write((char*)&tempint,sizeof(int));
                    tempdouble=vp->getCDeg();
                    file.write((char*)&tempdouble,sizeof(double));
                    tempdouble=vp->getCDis();
                    file.write((char*)&tempdouble,sizeof(double));
                    tempint=vp->getFX();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getFY();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getFWidth();
                    file.write((char*)&tempint,sizeof(int));
                    tempdouble=vp->getFDeg();
                    file.write((char*)&tempdouble,sizeof(double));
                    tempdouble=vp->getFDis();
                    file.write((char*)&tempdouble,sizeof(double));
                    tempbool=vp->getCurve();
                    file.write((char*)&tempbool,sizeof(bool));
                    tempint=vp->getP();
                    file.write((char*)&tempint,sizeof(int));


                }
            }
            QMessageBox::information(this,"提示","保存成功",QMessageBox::Ok);
        }

    }


}
void NSMWindow::onBtnPrevClicked(){
    int i=ui->cbStep->currentIndex();
    if(i>0){
        i--;
    }
    ui->cbStep->setCurrentIndex(i);

}


void NSMWindow::onBtnNextClicked(){
    int i=ui->cbStep->currentIndex();
    if(i<nsm->getGraph()->getGraphData()->count()-1){
        i++;
    }
    ui->cbStep->setCurrentIndex(i);
}
void NSMWindow::onCbStepCurrentIndexChanged(int num){
    nsm->setGraphData(num);
}

