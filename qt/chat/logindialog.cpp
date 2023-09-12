#include "logindialog.h"
#include "ui_logindialog.h"
#include <QSettings>

LoginDialog::LoginDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::LoginDialog)
{
    ui->setupUi(this);
    readSettings();
}
void LoginDialog::readSettings()
{
    QSettings settings("YGTQ","NAME");
    ui->lineEdit->setText(settings.value("name","unknown").toString());
}
void LoginDialog::writeSettings()
{
    QSettings settings("YGTQ","NAME");
    settings.setValue("name",ui->lineEdit->text());
}

LoginDialog::~LoginDialog()
{
    writeSettings();
    delete ui;
}

QString LoginDialog::getUserName()
{
    return ui->lineEdit->text();
}

void LoginDialog::on_pushButton_clicked()
{
    close();
}
