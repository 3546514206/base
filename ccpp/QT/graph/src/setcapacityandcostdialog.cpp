#include "setcapacityandcostdialog.h"
#include "ui_setcapacityandcostdialog.h"
#include "common.h"
#include <QRegExpValidator>
SetCapacityAndCostDialog::SetCapacityAndCostDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::SetCapacityAndCostDialog)
{
    ui->setupUi(this);
    capacity=POS_INFINITY;
    cost=0;
    connect(ui->btnOK,SIGNAL(clicked()),this,SLOT(onBtnOKClicked()));
    QRegExpValidator *validator=new QRegExpValidator(QRegExp("\\d*"),this);
    ui->tbCapacity->setValidator(validator);
    ui->tbCost->setValidator(validator);
}

SetCapacityAndCostDialog::~SetCapacityAndCostDialog()
{
    delete ui;
}
void SetCapacityAndCostDialog::onBtnOKClicked(){
    QString strCapacity=ui->tbCapacity->text();
    QString strCost=ui->tbCost->text();
    if(strCost.isEmpty()){
        this->reject();
        return;
    }else{
        cost=strCost.toInt();
    }
    if(!strCapacity.isEmpty()){
        capacity=strCapacity.toInt();
    }
    this->accept();

}

int SetCapacityAndCostDialog::getCost() const
{
    return cost;
}

void SetCapacityAndCostDialog::setCost(int value)
{
    cost = value;
    if(cost!=POS_INFINITY){
        ui->tbCost->setText(QString::number(cost));
        ui->tbCapacity->setFocus();
    }
}

int SetCapacityAndCostDialog::getCapacity() const
{
    return capacity;
}

void SetCapacityAndCostDialog::setCapacity(int value)
{
    capacity = value;
}
