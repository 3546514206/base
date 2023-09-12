#include "modiftroomprice.h"
#include "ui_modiftroomprice.h"
#include "app/myhelper.h"

#include <QDebug>
#include <QSqlRecord>
#include <QSqlQuery>

ModiftRoomPrice::ModiftRoomPrice(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::ModiftRoomPrice)
{
    ui->setupUi(this);
    this->initForm();

}

ModiftRoomPrice::~ModiftRoomPrice()
{
    delete ui;
}

void ModiftRoomPrice::initForm()
{
    //��ʼ������������п�
    ColumnNames[0] = tr("�������ͱ��");
    ColumnNames[1] = tr("��������");
    ColumnNames[2] = tr("����۸�");

    ColumnWidths[0] = 100;
    ColumnWidths[1] = 120;
    ColumnWidths[2] = 120;

    MyBindTable("roomtype",ui->tableView,ColumnNames,ColumnWidths);
    this->connect(ui->tableView,SIGNAL(clicked(QModelIndex)),SLOT(showRoomInfo()));

    //��ʼ��
    ui->lineEditNo->setEnabled(false);
    ui->lineEditName->setEnabled(false);
    ui->lineEditPrice->setEnabled(false);

    ui->pbnSave->setEnabled(false);
    ui->pbnCancel->setEnabled(false);


}

void ModiftRoomPrice::MyBindTable(QString tableName, QTableView *tableView, QString columnNames[], int columnWidths[])
{
    QueryModel = new QSqlQueryModel(this);

    TableView = tableView;
    QString sql = "SELECT * FROM " + tableName;
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

void ModiftRoomPrice::on_pbnAdd_clicked()
{
    //��տؼ��е�����
    ui->lineEditNo->clear();
    ui->lineEditName->clear();
    ui->lineEditPrice->clear();

    ui->lineEditNo->setEnabled(true);
    ui->lineEditName->setEnabled(true);
    ui->lineEditPrice->setEnabled(true);

    ui->pbnAdd->setEnabled(false);//��Ӱ�ť������
    ui->pbnModify->setEnabled(false);
    ui->pbnSave->setEnabled(true);
    ui->pbnCancel->setEnabled(true);
}

void ModiftRoomPrice::on_pbnModify_clicked()
{
    //�ؼ����ݿ��Խ�������
    ui->lineEditNo->setEnabled(true);
    ui->lineEditName->setEnabled(true);
    ui->lineEditPrice->setEnabled(true);

    ui->pbnModify->setEnabled(false);//�޸İ�ť������
    ui->pbnAdd->setEnabled(false);

    ui->pbnSave->setEnabled(true);
    ui->pbnCancel->setEnabled(true);
}

void ModiftRoomPrice::on_pbnSave_clicked()
{
    QString roomtypeNo = ui->lineEditNo->text();
    QString roomtypeName = ui->lineEditName->text();
    QString roomtypePrice = ui->lineEditPrice->text();

    if(roomtypeNo.isEmpty() &&roomtypeName.isEmpty() && roomtypePrice.isEmpty())
    {
        myHelper::ShowMessageBoxInfo(tr("�������������ֶΣ�"));
    }
    else
    {
        QSqlQuery query;
        bool ok = query.prepare("INSERT INTO roomtype (Id, Typename,TypePrice)"
                                "VALUES (:Id,:Typename,:TypePrice)");
        query.bindValue(":Id",roomtypeNo);
        query.bindValue(":Typename",roomtypeName);
        query.bindValue(":TypePrice",roomtypePrice);
        query.setForwardOnly(true);
        query.exec();

        if(ok)
        {
            myHelper::ShowMessageBoxInfo(tr("����ͷ���Ϣ�ɹ�!"));

            myHelper::MyLoginBlog("logblog","�޸ķ���۸�",+"����۸��Ϊ"+roomtypePrice,"����Ա");
        }
    }
}

void ModiftRoomPrice::on_pbnCancel_clicked()
{
    ui->lineEditNo->setEnabled(false);
    ui->lineEditName->setEnabled(false);
    ui->lineEditPrice->setEnabled(false);

    ui->pbnAdd->setEnabled(true);
    ui->pbnModify->setEnabled(true);

    ui->pbnCancel->setEnabled(false);
    ui->pbnSave->setEnabled(false);
}

/*
 *�������ܣ���ȡ����������ֵ������ʾ����Ӧ�Ľ�����
*/
void ModiftRoomPrice::showRoomInfo()
{
    ui->pbnModify->setEnabled(true);
    QSqlQueryModel userMode(ui->tableView);
    QString sql = "SELECT *FROM roomtype;";
    qDebug() <<sql;
    userMode.setQuery(QString(sql));
    int Row = ui->tableView->currentIndex().row();
    QSqlRecord record = userMode.record(Row);
    ui->lineEditNo->setText(record.value(0).toString());
    ui->lineEditName->setText(record.value(1).toString());
    ui->lineEditPrice->setText(record.value(2).toString());
}
