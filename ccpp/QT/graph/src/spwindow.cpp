#include "spwindow.h"
#include "ui_spwindow.h"
#include "loadinfodialog.h"
#include "launchdialog.h"
#include "common.h"
#include <QMessageBox>
#include <QGuiApplication>
#include <QScreen>
#include <QFileDialog>
#include <QTextCodec>
#include <QCloseEvent>
#ifdef Q_OS_WIN
#include <QAxObject>
#endif
SPWindow::SPWindow(QWidget *parent) :
    mParent(parent),
    ui(new Ui::SPWindow)
{

    ui->setupUi(this);
    QScreen* screen=QGuiApplication::primaryScreen();
    QSize size=screen->size();
    int width=geometry().width();
    int height=geometry().height();
    setGeometry((size.width()-width)/2,(size.height()-height)/2,width,height);
    init();

}
void SPWindow::closeEvent(QCloseEvent *event){
    ((LaunchDialog*)parent())->show();
    event->accept();
}

QWidget *SPWindow::parent() const
{
    return mParent;
}
SPWindow::~SPWindow()
{
    delete ui;

}
void SPWindow::init(){

    shortestpath=ui->shortestpath;
    QStringList methodList;
    methodList<<"Bellman"<<"Floyd";
    ui->cbMethod->addItems(methodList);
    connect(ui->btnAddVertex,SIGNAL(clicked()),this,SLOT(onBtnAddVertexClicked()));
    connect(ui->btnRemoveAllVertex,SIGNAL(clicked()),this,SLOT(onBtnRemoveAllVertexClicked()));
    connect(ui->rbEditMode,SIGNAL(toggled(bool)),this,SLOT(onRadioBtnEditModeToggled(bool)));
    connect(ui->btnCalculate,SIGNAL(clicked()),this,SLOT(onBtnCalcClicked()));
    connect(ui->actionOpen,SIGNAL(triggered()),this,SLOT(onActionOpen()));
    connect(ui->actionSave,SIGNAL(triggered()),this,SLOT(onActionSave()));
    connect(ui->actionNoDirGraph,SIGNAL(triggered()),this,SLOT(onActionNoDirGraph()));
    connect(ui->shortestpath,SIGNAL(hintChanged(QString)),this,SLOT(onHintChanged(QString)));
    connect(ui->btnFloydToExcel,SIGNAL(clicked()),this,SLOT(onBtnFloydToExcelClicked()));



}
void SPWindow::onHintChanged(QString str){
    ui->statusBar->showMessage(str);
}

void SPWindow::onActionNoDirGraph(){
    LoadInfoDialog dialog(this);
    dialog.exec();
    if(dialog.getSuccess()){
        SPGraph* graph=shortestpath->getGraph();
        graph->clearVertexs();
        shortestpath->reset();
        for(int i=0;i<dialog.getVpos()->count();i++){
            addVertex();
        }
        for(int i=0;i<dialog.getNodirEdge()->count();i++){
            NoDirEdge n=dialog.getNodirEdge()->at(i);
            SPVertex* v=graph->getVertexAt(n.v1);
            SPVertex* v2=graph->getVertexAt(n.v2);
            QPoint p=SPFrame::calcEdgeCenter(v,v2);
            SPVertexParam *param=new SPVertexParam(n.v2,n.dis);
            param->setX(p.x());
            param->setY(p.y());
            int width=QFontMetrics(QFont("微软雅黑",15)).horizontalAdvance(QString::number(n.dis));
            param->setWidth(width);
            param->setDeg(calcDeg(v->getCenterX(),v->getCenterY(),param->getX(),param->getY())
                          -calcDeg(v->getCenterX(),v->getCenterY(),v2->getCenterX(),v2->getCenterY()));
            param->setDis(calcDis(v->getCenterX(),v->getCenterY(),param->getX(),param->getY()));
            v->addVertexParams(param);
            v=graph->getVertexAt(n.v2);
            param=new SPVertexParam(n.v1,n.dis);
            v->addVertexParams(param);

        }
    }

}

void SPWindow::onActionOpen(){
    shortestpath->clearState();
    QTextCodec *codec=QTextCodec::codecForName("utf-8");
    QString filter=codec->toUnicode("最短路径文件(*.stp)");
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
            shortestpath->setWinOffsetX(tempint);
            file.read((char*)&tempint,sizeof(int));
            shortestpath->setWinOffsetY(tempint);
            file.read((char*)&tempdouble,sizeof(double));
            shortestpath->setWinScale(tempdouble);
            SPGraph* g=shortestpath->getGraph();
            g->clearVertexs();
            int vertexcount;
            file.read((char*)&vertexcount,sizeof(int));
            for(int i=1;i<=vertexcount;i++){
                SPVertex* v=new SPVertex();
                file.read((char*)&tempint,sizeof(int));
                v->setCenterX(tempint);
                file.read((char*)&tempint,sizeof(int));
                v->setCenterY(tempint);
                int paramcount;
                file.read((char*)&paramcount,sizeof(int));
                for(int j=0;j<paramcount;j++){
                    SPVertexParam* vp=new SPVertexParam();
                    file.read((char*)&tempint,sizeof(int));
                    vp->setX(tempint);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setY(tempint);
                    file.read((char*)&tempdouble,sizeof(double));
                    vp->setDeg(tempdouble);
                    file.read((char*)&tempdouble,sizeof(double));
                    vp->setDis(tempdouble);
                    file.read((char*)&tempbool,sizeof(bool));
                    vp->setCurve(tempbool);
                    file.read((char*)&tempint,sizeof(int));
                    vp->setP(tempint);
                    file.read((char*)&tempdouble,sizeof(double));
                    vp->setE(tempdouble);
                    file.read((char*)&tempint,sizeof(tempint));
                    vp->setWidth(tempint);
                    v->addVertexParams(vp);


                }
                g->addVertex(v);
            }
            shortestpath->saveWinOffset();
            ui->rbEditMode->setChecked(true);
            update();
        }

    }
}
void SPWindow::onActionSave(){
    shortestpath->clearState();
    QTextCodec *codec=QTextCodec::codecForName("utf-8");
    QString filter=codec->toUnicode("最短路径文件(*.stp)");
    QString fileName=QFileDialog::getSaveFileName(
                this,"保存文件",QString(),filter);
    if(!fileName.isEmpty()){
        QFile file(fileName);
        if(file.open(QIODevice::WriteOnly))
        {
            int tempint;
            double tempdouble;
            bool tempbool;
            tempint=shortestpath->getWinOffsetX();
            file.write((char*)&tempint,sizeof(int));
            tempint=shortestpath->getWinOffsetY();
            file.write((char*)&tempint,sizeof(int));
            tempdouble=shortestpath->getWinScale();
            file.write((char*)&tempdouble,sizeof(double));
            SPGraph* g=shortestpath->getGraph();
            tempint=g->getCount();
            file.write((char*)&tempint,sizeof(int));
            for(int i=1;i<=g->getCount();i++){
                SPVertex* v=g->getVertexAt(i);
                tempint=v->getCenterX();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getCenterY();
                file.write((char*)&tempint,sizeof(int));
                tempint=v->getParams()->count();
                file.write((char*)&tempint,sizeof(int));
                for(int j=0;j<v->getParams()->count();j++){
                    SPVertexParam* vp=v->getParams()->at(j);
                    tempint=vp->getX();
                    file.write((char*)&tempint,sizeof(int));
                    tempint=vp->getY();
                    file.write((char*)&tempint,sizeof(int));
                    tempdouble=vp->getDeg();
                    file.write((char*)&tempdouble,sizeof(double));
                    tempdouble=vp->getDis();
                    file.write((char*)&tempdouble,sizeof(double));
                    tempbool=vp->getCurve();
                    file.write((char*)&tempbool,sizeof(bool));
                    tempint=vp->getP();
                    file.write((char*)&tempint,sizeof(int));
                    tempdouble=vp->getE();
                    file.write((char*)&tempdouble,sizeof(double));
                    tempint=vp->getWidth();
                    file.write((char*)&tempint,sizeof(int));

                }
            }
            QMessageBox::information(this,"提示","保存成功",QMessageBox::Ok);
        }

    }


}
void SPWindow::addVertex(){
    QRect rect=shortestpath->geometry();
    SPGraph* graph=shortestpath->getGraph();
    SPVertex *v=new SPVertex();
    if(graph->getCount()==0){
        v->setCenterX(VERTEX_SIZE/2);
        v->setCenterY(VERTEX_SIZE/2);

    }else{
        int lastx=graph->getLastX();
        int lasty=graph->getLastY();
        if(lastx+VERTEX_SIZE>(rect.width()*3/4-shortestpath->getWinOffsetX())/shortestpath->getWinScale()){
            v->setCenterX(VERTEX_SIZE/2);
            v->setCenterY(lasty+VERTEX_SIZE);
        }else{
            v->setCenterX(lastx+VERTEX_SIZE);
            v->setCenterY(lasty);
        }

    }
    graph->addVertex(v);
}
void SPWindow::onBtnAddVertexClicked(){

    addVertex();
    shortestpath->setFocus();
    shortestpath->update();

}
void SPWindow::onBtnRemoveAllVertexClicked(){
    if(QMessageBox::Yes==QMessageBox::warning(this,"警告","是否全部删除?",QMessageBox::Yes,QMessageBox::No))
    {
        SPGraph* graph=shortestpath->getGraph();
        graph->clearVertexs();
        shortestpath->reset();
        shortestpath->setFocus();
        shortestpath->update();
    }
}
void SPWindow::onRadioBtnEditModeToggled(bool b){
    if(b){
        shortestpath->setEditable(true);
        ui->btnAddVertex->setEnabled(true);
        ui->btnRemoveAllVertex->setEnabled(true);
        ui->rbEditMode->setEnabled(false);
    }
    else
    {
        shortestpath->setEditable(false);
        ui->btnAddVertex->setEnabled(false);
        ui->btnRemoveAllVertex->setEnabled(false);
    }
    shortestpath->setFocus();
    shortestpath->update();
}

void SPWindow::onBtnCalcClicked()
{
    ui->rbEditMode->setChecked(false);
    ui->rbEditMode->setEnabled(true);
    int i=ui->cbMethod->currentIndex();
    if(i==0){
        shortestpath->setMethod(SPFrame::Bellman);
        shortestpath->getGraph()->bellman();
        QStringList list=shortestpath->getGraph()->getCalcResult();
        makeHintText(list);
    }else if(i==1){
        shortestpath->setMethod(SPFrame::Floyd);
        shortestpath->getGraph()->floyd();
        QStringList list=shortestpath->getGraph()->getCalcResult();
        makeHintText(list);
    }

}
void SPWindow::makeHintText(QStringList list){
    QString s="<!DOCTYPE HTML><html><head><meta name='qrichtext' content='1' /></head><body style=\" font-family:'SimSun'; font-size:9pt; font-style:normal;\">";
    s+=list.join("<br>");
    s+="</body></html>";
    ui->tbDetail->setHtml(s);

}
void SPWindow::onBtnFloydToExcelClicked(){
#ifdef Q_OS_WIN
    if(shortestpath->getMethod()==SPFrame::Floyd){
        QAxObject *excel = new QAxObject("Excel.Application");
        excel->dynamicCall("SetVisible (bool Visible)",false);
        excel->setProperty("DisplayAlerts", true);
        QAxObject *workbooks = excel->querySubObject("WorkBooks");
        workbooks->dynamicCall("Add");
        QAxObject *workbook = excel->querySubObject("ActiveWorkBook");
        QAxObject *worksheets = workbook->querySubObject("Sheets");
        worksheets->dynamicCall("Add");
        QAxObject *worksheet = worksheets->querySubObject("Item(int)",1);
        QList<QVariant> allRowsData;
        allRowsData.clear();
        FloydMark* mark=shortestpath->getGraph()->getFloydMark();
        int size=mark->getCount();
        for(int row = 1; row <= size; row++)
        {
            QList<QVariant> aRowData;
            for(int column = 1; column <= size; column++)
            {
                aRowData.append(QVariant(mark->getP(row,column)));
            }
            allRowsData.append(QVariant(aRowData));
        }
        QString strSize;
        convertToColName(size,strSize);
        QString cmd=QString("A1:%1%2").arg(strSize).arg(size);
        QAxObject *range = worksheet->querySubObject("Range(const QString)",cmd);
        range->dynamicCall("SetValue(const QVariant&)",QVariant(allRowsData));
        worksheet = worksheets->querySubObject("Item(int)",2);
        allRowsData.clear();
        for(int row = 1; row <= size; row++)
        {
            QList<QVariant> aRowData;
            for(int column = 1; column <= size; column++)
            {
                aRowData.append(QVariant(mark->getD(row,column)));
            }
            allRowsData.append(QVariant(aRowData));
        }
        range = worksheet->querySubObject("Range(const QString)",cmd);
        range->dynamicCall("SetValue(const QVariant&)",QVariant(allRowsData));
        workbook->dynamicCall("SaveAs(const QString&)",QDir::toNativeSeparators(QDir::currentPath()+"\\floyd.xlsx"));
        workbook->dynamicCall("Close()");
        excel->dynamicCall("Quit()");
        delete excel;
        QMessageBox::information(this,"提示","保存成功",QMessageBox::Ok);
    }

#endif
}
