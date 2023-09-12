#ifndef LOGBLOGDIALOG_H
#define LOGBLOGDIALOG_H

#include <QDialog>
#include <QSqlQueryModel>
#include <QTableView>

#include "app/mysqlapi.h"

namespace Ui {
class LogBlogDialog;
}

class LogBlogDialog : public QDialog
{
    Q_OBJECT
    
public:
    explicit LogBlogDialog(QWidget *parent = 0);
    ~LogBlogDialog();
    void InitForm();

    //��ĳ�����е����ݰ󶨵�ָ����TableView��
    void MyBindTable(QString tableName, QString where,QTableView *tableView,
                     QString columnNames[], int columnWidths[]);
    
private slots:
    void on_btnSelect_clicked();

    void on_btnPrint_clicked();

    void on_ckTriggerTime_clicked(bool checked);


    void on_ckTriggerType_clicked(bool checked);


    void on_ckUserName_clicked(bool checked);

    void on_btnDelete_clicked();

private:
    Ui::LogBlogDialog *ui;

    QSqlQueryModel *QueryModel;     //��ѯģ��
    QTableView *TableView;          //��ʾ���ݵı�����

    QString ColumnNames[5];         //������������
    int ColumnWidths[5];            //�п���������
};

#endif // LOGBLOGDIALOG_H
