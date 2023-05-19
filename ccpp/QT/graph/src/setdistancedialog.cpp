#include "setdistancedialog.h"
#include "ui_setdistancedialog.h"
#include <QRegExpValidator>
SetDistanceDialog::SetDistanceDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::SetDistanceDialog)
{
    distance=0;
    ui->setupUi(this);
    QRegExp regexp("-?\\d*\\.?\\d*");
    QRegExpValidator *validator=new QRegExpValidator(regexp,this);
    ui->lineEdit->setValidator(validator);
    connect(ui->pushButton,SIGNAL(clicked()),this,SLOT(onBtnOkClicked()));


}

SetDistanceDialog::~SetDistanceDialog()
{
    delete ui;
}

double SetDistanceDialog::getDistance() const
{
    return distance;
}

void SetDistanceDialog::onBtnOkClicked(){
    getDis();
    this->close();
}

void SetDistanceDialog::getDis(){
    QString s=ui->lineEdit->text();
    bool b;
    double i=s.toDouble(&b);
    if(b){
        distance=i;
        this->accept();
    }else this->reject();
}
