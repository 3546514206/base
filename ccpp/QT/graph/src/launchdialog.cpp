#include "launchdialog.h"
#include "ui_launchdialog.h"
#include "spwindow.h"
#include "nsmwindow.h"
LaunchDialog::LaunchDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::LaunchDialog)
{
    ui->setupUi(this);
    connect(ui->btnShortestPath,SIGNAL(clicked()),this,SLOT(onBtnShortestPathClicked()));
    connect(ui->btnNSM,SIGNAL(clicked()),this,SLOT(onBtnNSMClicked()));
    connect(ui->btnAbout,SIGNAL(clicked()),this,SLOT(onBtnAboutClicked()));

}

LaunchDialog::~LaunchDialog()
{
    delete ui;
}
void LaunchDialog::onBtnShortestPathClicked(){
    this->hide();
    SPWindow* window=new SPWindow(this);
    window->show();



}
void LaunchDialog::onBtnNSMClicked(){
    this->hide();
    NSMWindow* window=new NSMWindow(this);
    window->show();

}

void LaunchDialog::onBtnAboutClicked()
{
    QApplication::aboutQt();

}
