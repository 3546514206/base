#include "loadinfodialog.h"
#include "ui_loadinfodialog.h"
#include <QFileDialog>
#include <math.h>
LoadInfoDialog::LoadInfoDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::LoadInfoDialog)
{
    ui->setupUi(this);
    connect(ui->btnVertex,SIGNAL(clicked()),this,SLOT(onBtnVertexClicked()));
    connect(ui->btnVexel,SIGNAL(clicked()),this,SLOT(onBtnVexelClicked()));
    connect(ui->btnOK,SIGNAL(clicked()),this,SLOT(onBtnOKClicked()));
    success=false;
    vpos=new QList<Vertex3Pos>();
    nodirEdge=new QList<NoDirEdge>();

}

LoadInfoDialog::~LoadInfoDialog()
{
    delete vpos;
    delete nodirEdge;
    delete ui;
}

double LoadInfoDialog::getDis(Vertex3Pos v1, Vertex3Pos v2)
{
    return sqrt((v1.x-v2.x)*(v1.x-v2.x)+
                (v1.y-v2.y)*(v1.y-v2.y)+
                (v1.z-v2.z)*(v1.z-v2.z));
}
void LoadInfoDialog::onBtnVertexClicked()
{
    ui->tbVertex->setText(QFileDialog::getOpenFileName(this,"打开顶点信息文件",QString(),tr("顶点信息文件(*.txt)")));
}
void LoadInfoDialog::onBtnVexelClicked()
{
    ui->tbVexel->setText(QFileDialog::getOpenFileName(this,"打开体元信息文件",QString(),tr("体元信息文件(*.txt)")));
}
void LoadInfoDialog::onBtnOKClicked()
{

    QFile file(ui->tbVertex->text());
    if(file.open(QIODevice::ReadOnly)){
        file.readLine();
        QString s=file.readLine();
        QStringList l=s.split(" ");
        QString c=l.at(l.count()-1);
        vcnt=c.toInt();
        while(!file.atEnd()){
            s=file.readLine();
            l=s.split(" ");
            Vertex3Pos p;
            p.x=((QString)l.at(4)).toDouble();
            p.y=((QString)l.at(10)).toDouble();
            p.z=((QString)l.at(16)).toDouble();
            vpos->append(p);
        }
    }else{
        return;
    }
    QFile file2(ui->tbVexel->text());
    if(file2.open(QIODevice::ReadOnly)){
        QString s;
        QStringList l;
        file2.readLine();
        file2.readLine();
        while(!file2.atEnd()){
            s=file2.readLine();
            l=s.split(" ");
            int a,b,c,d;
            a=((QString)l.at(2)).toInt();
            b=((QString)l.at(3)).toInt();
            c=((QString)l.at(4)).toInt();
            d=((QString)l.at(5)).toInt();
            double dis=getDis(vpos->at(a-1),vpos->at(b-1));
            nodirEdge->append(NoDirEdge(a,b,dis));
            nodirEdge->append(NoDirEdge(b,a,dis));

            dis=getDis(vpos->at(a-1),vpos->at(c-1));
            nodirEdge->append(NoDirEdge(a,c,dis));
            nodirEdge->append(NoDirEdge(c,a,dis));

            dis=getDis(vpos->at(a-1),vpos->at(d-1));
            nodirEdge->append(NoDirEdge(a,d,dis));
            nodirEdge->append(NoDirEdge(d,a,dis));

            dis=getDis(vpos->at(b-1),vpos->at(c-1));
            nodirEdge->append(NoDirEdge(b,c,dis));
            nodirEdge->append(NoDirEdge(c,b,dis));

            dis=getDis(vpos->at(b-1),vpos->at(d-1));
            nodirEdge->append(NoDirEdge(b,d,dis));
            nodirEdge->append(NoDirEdge(d,b,dis));

            dis=getDis(vpos->at(c-1),vpos->at(d-1));
            nodirEdge->append(NoDirEdge(c,d,dis));
            nodirEdge->append(NoDirEdge(d,c,dis));

        }
    }else{
        return;
    }
    success=true;
    this->close();
}

QList<Vertex3Pos> *LoadInfoDialog::getVpos() const
{
    return vpos;
}

QList<NoDirEdge> *LoadInfoDialog::getNodirEdge() const
{
    return nodirEdge;
}

bool LoadInfoDialog::getSuccess() const
{
    return success;
}
