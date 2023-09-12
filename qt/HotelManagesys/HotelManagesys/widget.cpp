#include "widget.h"
#include "ui_widget.h"
#include "app/iconhelper.h"
#include "app/myhelper.h"
#include "app/myapp.h"

#include <QDesktopServices>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    this->InitStyle();
}

Widget::~Widget()
{
    delete ui;
}

bool Widget::eventFilter(QObject *obj, QEvent *event)
{
    if (event->type() == QEvent::MouseButtonDblClick) {
        this->on_btnMenu_Max_clicked();
        return true;
    }
    return QObject::eventFilter(obj, event);
}

void Widget::mouseMoveEvent(QMouseEvent *e)
{
    if (mousePressed && (e->buttons() && Qt::LeftButton) && !max) {
        this->move(e->globalPos() - mousePoint);
        e->accept();
    }
}

void Widget::mousePressEvent(QMouseEvent *e)
{
    if (e->button() == Qt::LeftButton) {
        mousePressed = true;
        mousePoint = e->globalPos() - this->pos();
        e->accept();
    }
}

void Widget::mouseReleaseEvent(QMouseEvent *)
{
    mousePressed = false;
}

void Widget::on_btnMenu_Close_clicked()
{
    qApp->exit();
}

void Widget::on_btnMenu_Max_clicked()
{

    if (max)
    {
        this->setGeometry(location);
        IconHelper::Instance()->SetIcon(ui->btnMenu_Max, QChar(0xf096), 10);
        ui->btnMenu_Max->setToolTip("���");
    }
    else
    {
        location = this->geometry();
        this->setGeometry(qApp->desktop()->availableGeometry());
        IconHelper::Instance()->SetIcon(ui->btnMenu_Max, QChar(0xf079), 10);
        ui->btnMenu_Max->setToolTip("��ԭ");
    }
    max = !max;
}

void Widget::on_btnMenu_Min_clicked()
{
    this->showMinimized();
}

void Widget::InitStyle()
{
    //���ô������������
    this->setWindowFlags(Qt::FramelessWindowHint | Qt::WindowSystemMenuHint | Qt::WindowMinMaxButtonsHint);
    this->setMinimumSize(1000,600);
    location = this->geometry();
    max = false;
    mousePressed = false;
    //��װ�¼�������,�ñ�����ʶ�����˫��
    ui->lab_Title->installEventFilter(this);

    IconHelper::Instance()->SetIcon(ui->btnMenu_Close, QChar(0xf00d), 10);
    IconHelper::Instance()->SetIcon(ui->btnMenu_Max, QChar(0xf096), 10);
    IconHelper::Instance()->SetIcon(ui->btnMenu_Min, QChar(0xf068), 10);
    IconHelper::Instance()->SetIcon(ui->btnMenu, QChar(0xf0c9), 10);
    IconHelper::Instance()->SetIcon(ui->lab_Ico, QChar(0xf015), 12);

    connect(ui->pbnAsk,SIGNAL(clicked()),this,SLOT(on_pbnAsk_clicked()));
    connect(ui->pbnHint,SIGNAL(clicked()),this,SLOT(on_pbnHint_clicked()));

    timer = new QTimer();
    connect(timer,SIGNAL(timeout()),this,SLOT(showCurrentTime()));
    timer->start(1000);

    promanage = new ProManage();                        //ǰ̨���Ĺ������
    constumerInfo = new CostomerRegisterInfoDialog();   //ע��ͻ���Ϣ����
    romminfo = new RommInfo();                          //������Ϣ����
    modifyRoom = new ModiftRoomPrice();                 //�޸ķ�����Ϣ����
    checkcustom = new CheckCutomDialog();               //�ͻ��Ǽ���ס����
    logblog = new LogBlogDialog();                      //������־����
    modifypwd = new ModifyPwdDialog();                  //�޸��������
    roompicture = new RoomPicDialog();                  //չʾ�����Ƭ����
    backdatabase = new BackupDatanaseDialog();          //���ݿⱸ�ݽ���
    checkoutRoom = new CheckOutDialog();                //�˷�����

    //��ӵ���ջ�����У��Ա�����л�
    ui->stackedWidget->addWidget(promanage);
    ui->stackedWidget->addWidget(constumerInfo);
    ui->stackedWidget->addWidget(romminfo);
    ui->stackedWidget->addWidget(modifyRoom);
    ui->stackedWidget->addWidget(checkcustom);
    ui->stackedWidget->addWidget(logblog);
    ui->stackedWidget->addWidget(modifypwd);
    ui->stackedWidget->addWidget(roompicture);
    ui->stackedWidget->addWidget(backdatabase);
    ui->stackedWidget->addWidget(checkoutRoom);

    ui->label_CompanyName->setText(tr("̫ԭ��ҵѧԺ--���������ϵ"));
    ui->label_CurrentUser->setText(QString(tr("��ǰ�û�:%1��%2��")).arg(Myapp::CurrentUserName).arg("����Ա"));
    ui->label_SoftTime->setText(QString(tr("������:0��0ʱ0��0��")));
}

void Widget::on_pbnAsk_clicked()
{

    ui->stackedWidget->setCurrentIndex(2);
}

void Widget::on_pbnError_clicked()
{
    //myHelper::ShowMessageBoxError("���ܿ�����");
}

void Widget::on_pbnHint_clicked()
{
    myHelper::ShowMessageBoxInfo("���ܿ�����");
}


int day = 0;
int hour = 0;
int minute = 0;
int second = 0;
/*
 *�������ܣ���ʾϵͳʱ��
 *�����������
 *�����������
 *����ֵ����
 *˵������ʾʱ��Ĳۺ�����������ʾ��ǰϵͳ��ʱ���������е�ʱ��
*/
void Widget::showCurrentTime()
{
    second++;
    if(second == 60)
    {
        minute++;
        second = 0;
    }
    if(minute == 60)
    {
        hour++;
        minute = 0;
    }
    if(hour == 24)
    {
        day++;
        hour = 0;
    }
    ui->label_CurrentTime->setText(QDateTime::currentDateTime().toString(tr("��ǰʱ��:yyyy��MM��dd�� dddd HH:mm:ss")));
    ui->label_SoftTime->setText(QString(tr("������:%1��%2ʱ%3��%4��")).arg(day).arg(hour).arg(minute).arg(second));
}

/*
 *�������ܣ���ʾ�Ѿ�ע��ͻ���Ϣ
*/
void Widget::on_pbnRegisterInfo_clicked()
{
    ui->stackedWidget->setCurrentIndex(3);
}

/*
 *�������ܣ���ʾ������Ϣ(����Ľ���)
*/
void Widget::on_pbnRoomInfo_clicked()
{
    ui->stackedWidget->setCurrentIndex(4);
}

/*
 *�������ܣ��޸ķ�����Ϣ
*/
void Widget::on_pbnModify_clicked()
{
    ui->stackedWidget->setCurrentIndex(5);
}

/*
 *�������ܣ��޸ĵ�ǰ�û�����
*/
void Widget::on_pbnModiftPwd_clicked()
{
    ui->stackedWidget->setCurrentIndex(8);
}

/*
 *�������ܣ����͵Ǽ�
 *˵�������ڵǼ�ס�������Ϣ
*/
void Widget::on_pbnCheck_clicked()
{
    ui->stackedWidget->setCurrentIndex(6);
}

/*
 *�������ܣ���ʾ��¼��־
*/
void Widget::on_pbnLonBlog_clicked()
{
    ui->stackedWidget->setCurrentIndex(7);
}

/*
 *�������ܣ���ʾ�������
*/
void Widget::on_pbnRoomLock_clicked()
{
    ui->stackedWidget->setCurrentIndex(9);
}

/*
 *�������ܣ�������ҳ
*/
void Widget::on_pbnbackhome_clicked()
{
    ui->stackedWidget->setCurrentIndex(0);
}

void Widget::on_pbnBackDatabase_clicked()
{
    ui->stackedWidget->setCurrentIndex(10);
}

/*
 *�������ܣ��˷��ٽ���
*/
void Widget::on_pbnCheckOut_clicked()
{
    ui->stackedWidget->setCurrentIndex(11);
}

/*
 *�������ܣ�ʹ�ð���
*/
void Widget::on_pbnHelp_clicked()
{
    QDesktopServices::openUrl(QUrl::fromLocalFile("C:/Users/Administrator/Desktop/HotelManagesys/help/help.CHM"));
    qDebug() <<"open help info";
}
