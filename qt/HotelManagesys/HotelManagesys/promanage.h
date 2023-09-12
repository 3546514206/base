#ifndef PROMANAGE_H
#define PROMANAGE_H

#include "app/mythread.h"
#include "app/mysqlapi.h"
#include <QWidget>
#include <QTimer>

namespace Ui {
class ProManage;
}

class ProManage : public QWidget
{
    Q_OBJECT
    
public:
    explicit ProManage(QWidget *parent = 0);
    ~ProManage();

    void initForm();
    //��ĳ�����е����ݰ󶨵�ָ����TableView��
    void MyBindTable(QString tableName, QTableView *tableView,
                     QString columnNames[], int columnWidths[]);
    //���ͷ���Ϣ��ʾ��tableview��
    void BindHotelInfo(QString tableName, QTableView *tableView,
                       QString columnNames[], int columnWidths[]);

    //������---������
    void BindDataSelect(QString tableName,QTableView *tableView,
                        QString orderColumn, QString orderType, QString where,
                        QString columnNames[], int columnWidths[]);

public slots:
    void showtimeslot();//��ʾʱ��

    void showCurrentRoomNo();//��ʾ��ǰ������
    
private slots:

    void on_comboBoxRoomType_activated(const QString &arg1);//ֵ�ı�ʱ������Ӧ�Ķ���
    void on_comboBoxRoomfloor_activated(const QString &arg1);

    void on_comboBoxRoomStatus_activated(const QString &arg1);

private:
    Ui::ProManage *ui;

    QSqlQueryModel *QueryModel;     //��ѯģ��
    QTableView *TableView;          //��ʾ���ݵı�����
    MysqlApi *mysql;                //���ݿ��������
    QString ColumnNames[5];         //������������
    int ColumnWidths[5];            //�п���������
    //Mythread mythread;
    QTimer *timer;
};

#endif // PROMANAGE_H
