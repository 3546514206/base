#ifndef MODIFTROOMPRICE_H
#define MODIFTROOMPRICE_H

#include <QWidget>
#include <QTableView>
#include <QSqlQueryModel>

namespace Ui {
class ModiftRoomPrice;
}

class ModiftRoomPrice : public QWidget
{
    Q_OBJECT
    
public:
    explicit ModiftRoomPrice(QWidget *parent = 0);
    ~ModiftRoomPrice();

    void initForm();

    //��ĳ�����е����ݰ󶨵�ָ����TableView��
    void MyBindTable(QString tableName, QTableView *tableView,
                     QString columnNames[], int columnWidths[]);
    
private slots:
    void on_pbnAdd_clicked();

    void on_pbnModify_clicked();

    void on_pbnSave_clicked();

    void on_pbnCancel_clicked();

public slots:
    void showRoomInfo();//��ʾ������Ϣ�ۺ���

private:
    Ui::ModiftRoomPrice *ui;

    QSqlQueryModel *QueryModel;     //��ѯģ��
    QTableView *TableView;          //��ʾ���ݵı�����
    QString ColumnNames[3];         //������������
    int ColumnWidths[3];            //�п���������
};

#endif // MODIFTROOMPRICE_H
