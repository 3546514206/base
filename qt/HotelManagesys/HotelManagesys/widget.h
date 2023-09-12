#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <Qtimer>
#include "app/frmmessagebox.h"
#include <QMouseEvent>
#include <QDesktopWidget>
#include <QDateTime>

#include "promanage.h"                       //ǰ̨�������Ľ���
#include "costomerregisterinfodialog.h"      //ע��ͻ���Ϣ
#include "romminfo.h"                       //������Ϣ����
#include "modiftroomprice.h"                //�޸ķ�����Ϣ����
#include "checkcutomdialog.h"               //�˿͵Ǽ���ס����
#include "logblogdialog.h"                  //������־����
#include "modifypwddialog.h"                //�޸��������
#include "roompicdialog.h"                  //������Ƭչʾ����
#include "backupdatanasedialog.h"           //���ݿⱸ�ݽ���
#include "checkoutdialog.h"                 //�˷�����

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT
    
public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();
protected:
    bool eventFilter(QObject *obj, QEvent *event);
    void mouseMoveEvent(QMouseEvent *e);
    void mousePressEvent(QMouseEvent *e);
    void mouseReleaseEvent(QMouseEvent *);
private slots:
    void on_btnMenu_Close_clicked();

    void on_btnMenu_Max_clicked();

    void on_btnMenu_Min_clicked();

    void on_pbnAsk_clicked();

    void on_pbnError_clicked();

    void on_pbnHint_clicked();
    void on_pbnRegisterInfo_clicked();

    void on_pbnRoomInfo_clicked();

    void on_pbnModify_clicked();

    void on_pbnModiftPwd_clicked();

    void on_pbnCheck_clicked();

    void on_pbnLonBlog_clicked();

    void on_pbnRoomLock_clicked();

    void on_pbnbackhome_clicked();

    void on_pbnBackDatabase_clicked();

    void on_pbnCheckOut_clicked();

    void on_pbnHelp_clicked();

public slots:
    void showCurrentTime();
private:
    Ui::Widget *ui;
    QPoint mousePoint;
    bool mousePressed;
    bool max;
    QRect location;
    void InitStyle();

    QTimer *timer;

    ProManage *promanage;                       //ǰ̨����
    CostomerRegisterInfoDialog *constumerInfo;  //ע��ͻ���Ϣ����
    RommInfo *romminfo;                         //������Ϣ����
    ModiftRoomPrice *modifyRoom;                //�޸ķ�����Ϣ
    CheckCutomDialog *checkcustom;              //�˿͵Ǽ���ס����
    LogBlogDialog *logblog;                     //������־
    ModifyPwdDialog *modifypwd;                 //�޸�����
    RoomPicDialog *roompicture;                 //������Ƭչ
    BackupDatanaseDialog *backdatabase;         //���ݿⱸ�ݽ���
    CheckOutDialog *checkoutRoom;               //�˷�����
};

#endif // WIDGET_H
