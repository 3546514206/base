#include "logblogdialog.h"
#include "ui_logblogdialog.h"

#include "app/myapp.h"

LogBlogDialog::LogBlogDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::LogBlogDialog)
{
    ui->setupUi(this);
    this->InitForm();
}

LogBlogDialog::~LogBlogDialog()
{
    delete ui;
}

void LogBlogDialog::InitForm()
{
    //��ʼ������������п�
    ColumnNames[0] = tr("ʱ��");
    ColumnNames[1] = tr("�����Ķ���");
    ColumnNames[2] = tr("����");
    ColumnNames[3] = tr("����Ա����");

    ColumnWidths[0] = 220;
    ColumnWidths[1] = 120;
    ColumnWidths[2] = 180;
    ColumnWidths[3] = 120;

    ui->ckTriggerType->setChecked(false);
    ui->ckUserName->setChecked(false);

    ui->dateStart->setEnabled(false);
    ui->dateEnd->setEnabled(false);
    ui->cboxUserName->setEnabled(false);
    ui->cboxTriggerType->setEditable(false);

    this->MyBindTable("logblog"," ",ui->tableView,ColumnNames,ColumnWidths);

    ui->dateStart->setDate(QDate::currentDate());
    ui->dateEnd->setDate(QDate::currentDate());

    if(Myapp::LastLoginter !="admin")
    {
        ui->btnDelete->setEnabled(false);
    }
}

void LogBlogDialog::MyBindTable(QString tableName, QString where,QTableView *tableView, QString columnNames[], int columnWidths[])
{
    QueryModel = new QSqlQueryModel(this);
    TableView = tableView;
    QString sql = "SELECT * FROM " + tableName+where;
    QueryModel->setQuery(sql);
    TableView->setModel(QueryModel);

    //���������б��⡢�п��
    for (int i = 0;i<TableView->model()->columnCount();i++)
    {
        QueryModel->setHeaderData(i,Qt::Horizontal,columnNames[i]);     //�����б���
        TableView->setColumnWidth(i,columnWidths[i]);                   //�����п�
    }

    TableView->horizontalHeader()->setHighlightSections(false);         //�����ͷʱ���Ա�ͷ����
    TableView->setSelectionMode(QAbstractItemView::ContiguousSelection);//ѡ��ģʽΪ����ѡ��
    TableView->setSelectionBehavior(QAbstractItemView::SelectRows);     //ѡ������
    TableView->setStyleSheet( "QTableView::item:hover{background-color:rgb(92,188,227,200)}"
                              "QTableView::item:selected{background-color:#1B89A1}");
}

/*
 *�������ܣ���������ѯ
*/
void LogBlogDialog::on_btnSelect_clicked()
{
    QString sql = " where 1 = 1";

    if(ui->ckUserName->isChecked())
    {
        sql+=" and TriggerUser='"+ui->cboxUserName->currentText()+"'";
    }
    if(ui->ckTriggerType->isChecked())
    {
        sql+=" and Trigger ='"+ui->cboxTriggerType->currentText();
    }
    if(ui->ckTriggerTime->isChecked())
    {
        if (ui->dateStart->date() > ui->dateEnd->date())
        {
            myHelper::ShowMessageBoxError(tr("��ʼʱ�䲻�ܴ��ڽ���ʱ��!"));
            return;
        }
        sql+=" and date(TriggerTimer) >='"+ui->dateStart->date().toString("yyyy-MM-dd")+"'";
        sql+=" and date(TriggerTimer) <='"+ui->dateEnd->date().toString("yyyy-MM-dd")+"'";
    }

    this->MyBindTable("logblog",sql,ui->tableView,ColumnNames,ColumnWidths);
}


/*
 *�������ܣ���ӡ����
 *˵��������ǰ�����ݴ�ӡ���

*/
void LogBlogDialog::on_btnPrint_clicked()
{
}

void LogBlogDialog::on_ckTriggerTime_clicked(bool checked)
{
    ui->dateEnd->setEnabled(checked);
    ui->dateStart->setEnabled(checked);
}


void LogBlogDialog::on_ckTriggerType_clicked(bool checked)
{
    ui->cboxTriggerType->setEditable(checked);
}



void LogBlogDialog::on_ckUserName_clicked(bool checked)
{
    ui->cboxUserName->setEnabled(checked);
}

void LogBlogDialog::on_btnDelete_clicked()
{
    if(Myapp::CurrentUserName != "����Ա")
    {
        myHelper::ShowMessageBoxError(tr("��û��Ȩ��ɾ����¼"));
        return;
    }
}
