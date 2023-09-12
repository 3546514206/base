#ifndef REGISTERDIALOG_H
#define REGISTERDIALOG_H

#include <QDialog>
#include <QEvent>
#include <QKeyEvent>
#include "app/iconhelper.h"
#include "app/myhelper.h"
#include "app/mysqlapi.h"

namespace Ui {
class RegisterDialog;
}

class RegisterDialog : public QDialog
{
    Q_OBJECT
    
public:
    explicit RegisterDialog(QWidget *parent = 0);
    ~RegisterDialog();
    void InitForm();
    void InitControls();        //�ؼ���ʼ��

protected:
    bool eventFilter(QObject *obj, QEvent *event);//�����¼�����Ӧ
    void mouseMoveEvent(QMouseEvent *e);
    void mousePressEvent(QMouseEvent *e);
    void mouseReleaseEvent(QMouseEvent *);
    
private slots:
    void on_btnOk_clicked();

    void on_btnCancel_clicked();

    void on_btnMenu_Close_clicked();

public slots:
    void CheckPwdIsSame();          //���ͻ���������������Ƿ�һ��

private:
    Ui::RegisterDialog *ui;

    MysqlApi *mysql;

    QRect location;
    QPoint mousePoint;
    bool mousePressed;
};

#endif // REGISTERDIALOG_H
