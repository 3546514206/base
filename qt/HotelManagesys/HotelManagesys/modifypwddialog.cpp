#include "modifypwddialog.h"
#include "ui_modifypwddialog.h"
#include "app/myhelper.h"
#include "app/myapp.h"

#include <QSqlQuery>
#include <QDebug>

ModifyPwdDialog::ModifyPwdDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ModifyPwdDialog)
{
    ui->setupUi(this);
    this->InitForm();
}

ModifyPwdDialog::~ModifyPwdDialog()
{
    delete ui;
}

void ModifyPwdDialog::InitForm()
{
    ui->lineEditOldPed->setFocus();
    //��ʾ��ǰ���û���
    QString currentName = SearchTableValue("currentuser","UserName");
    QString currentType = SearchTableValue("currentuser","UserType");
    ui->labelUserName->setText(currentName);
    ui->labelUserType->setText(currentType);
}

/*
 *�������ܣ���ȡ���е�ĳһ�ֶ�ֵ
*/
QString ModifyPwdDialog::SearchTableValue(QString tablename,QString targetvalue)
{
    QSqlQuery query;
    QString value = "";
    QString sql = "select "+targetvalue+" from "+tablename+";";
    qDebug() <<sql;
    query.exec(sql);
    if(query.next())
    {
        value = query.value(0).toString();
    }
    qDebug() <<value;
    return value;

}

/*
 *�������ܣ�ȷ���޸����룬����д�����
 *˵�����ھ������Ƿ���ȷ��ǰ���½����޸�
*/
void ModifyPwdDialog::on_pbnModifyPwd_clicked()
{
    QString UserName = Myapp::CurrentUserName;
    QString UserOldPwd = Myapp::CurrentUserPwd;

    QString OldPwd = ui->lineEditOldPed->text();
    QString NewPwd = ui->lineEditNewPwd->text();
    QString ReNewPwd = ui->lineEditReNewPwd->text();

    if(OldPwd.isEmpty() ||NewPwd.isEmpty()||ReNewPwd.isEmpty())
    {
        myHelper::ShowMessageBoxInfo(tr("���벻��Ϊ��!"));
    }
    else
    {
        if(NewPwd != ReNewPwd)
        {
            myHelper::ShowMessageBoxInfo(tr("�������벻һ��!����������"));
            ui->lineEditReNewPwd->setFocus();
        }
        else if(UserOldPwd == OldPwd)
        {
            QSqlQuery query;
            QString sql = "update staff set StaffPassword = '"+NewPwd+"' where StaffName='"+UserName+"';";
            qDebug() <<sql;
            query.exec(sql);
            qDebug() <<"update ok!";
            myHelper::ShowMessageBoxInfo(tr("�޸�����ɹ�!"));

            myHelper::MyLoginBlog("logblog","�޸�����","�޸Ĺ���Ա������","����Ա");
        }
        else
        {
            myHelper::ShowMessageBoxError(tr("�������������������"));

            ui->lineEditNewPwd->clear();
            ui->lineEditReNewPwd->clear();
        }

    }

}

/*
 *�������ܣ�ȡ����ť���������
*/
void ModifyPwdDialog::on_pbnCancel_clicked()
{
    ui->lineEditOldPed->clear();
    ui->lineEditNewPwd->clear();
    ui->lineEditReNewPwd->clear();
}
