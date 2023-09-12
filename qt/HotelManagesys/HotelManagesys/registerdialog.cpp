#include "registerdialog.h"
#include "ui_registerdialog.h"
#include <QDate>
#include <QSqlQuery>
#include <QDebug>

RegisterDialog::RegisterDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::RegisterDialog)
{
    ui->setupUi(this);
    this->InitForm();
}

RegisterDialog::~RegisterDialog()
{
    delete ui;
}

void RegisterDialog::InitForm()
{
    this->setWindowFlags(Qt::FramelessWindowHint);
    setWindowFlags(windowFlags()&~Qt::WindowCloseButtonHint&~Qt::WindowContextHelpButtonHint);//ȥ����¼����ʺ�
    this->InitControls();       //�ؼ���ʼ��
    //this->connect(ui->CustomerRePwd,SIGNAL(textChanged(QString)),SLOT(CheckPwdIsSame()));
}

/*
 *�������ܣ��ؼ���ʼ��
 *˵������ʼ������Ŀؼ�
*/
void RegisterDialog::InitControls()
{
    ui->CustomerName->setFocus();
    /*���ڿؼ�������*/
    ui->CustomerDateTime->setCalendarPopup(true);
    ui->CustomerDateTime->setDate(QDate::currentDate());

    location = this->geometry();
    mousePressed = false;
    //��װ�¼�������,�ñ�����ʶ�����˫��
    ui->lab_Title->installEventFilter(this);
    IconHelper::Instance()->SetIcon(ui->btnMenu_Close, QChar(0xf00d), 10);
    IconHelper::Instance()->SetIcon(ui->lab_Ico, QChar(0xf015), 12);

    ui->btnCancel->setToolTip(tr("ȡ��"));
    ui->btnOk->setToolTip(tr("ע��"));

    //������ʽ������������ݽ������ƣ��绰����11λ
    QRegExp rxPhone("\\d{11}$");
    QRegExpValidator *regPhone = new QRegExpValidator(rxPhone,this);
    ui->CustomerPhone->setValidator(regPhone);
}

bool RegisterDialog::eventFilter(QObject *obj, QEvent *event)
{
    //�û����»س���,������¼�ź�.
    if (event->type() == QEvent::KeyPress)
    {
        QKeyEvent *keyEvent = static_cast<QKeyEvent *>(event);

        if (keyEvent->key() == Qt::Key_Return || keyEvent->key()==Qt::Key_Enter)
        {
            this->on_btnOk_clicked();
            return true;
        }
    }
    return QObject::eventFilter(obj,event);
}

void RegisterDialog::mouseMoveEvent(QMouseEvent *e)
{
    if(mousePressed && (e->buttons()) && Qt::LeftButton)
    {
        this->move(e->globalPos() - mousePoint);
        e->accept();
    }
}

void RegisterDialog::mousePressEvent(QMouseEvent *e)
{
    if(e->button() == Qt::LeftButton)
    {
        mousePressed = true;
        mousePoint = e->globalPos() - this->pos();
        e->accept();
    }
}

void RegisterDialog::mouseReleaseEvent(QMouseEvent *)
{
    mousePressed = false;
}

/*
 *�������ܣ�ע��
 *˵��������д����������ͻ���Ϣд�����ݿ�
*/
void RegisterDialog::on_btnOk_clicked()
{
    int customerId = mysql->SearchMaxValue("Id","Customer") + 1;
    QString customerName = ui->CustomerName->text();
    QString customerSex = ui->comboBoxSex->currentText();
    QString customerPwd = ui->CustomerPwd->text();
    QString customerRePwd = ui->CustomerRePwd->text();
    QString customerPhone = ui->CustomerPhone->text();
    QString customerAddress = ui->CustomerAddress->text();
    QString customerRemark = ui->CustomerRemark->text();

    QString CustomerData = QDateTime::currentDateTime().toString("yyyy-MM-dd");
    if(customerName.isEmpty()&& customerPwd.isEmpty() &&customerRePwd.isEmpty())
    {
        myHelper::ShowMessageBoxInfo(tr("���������� * ������!"));
    }
    else if(customerRePwd != customerPwd)
    {
        myHelper::ShowMessageBoxError(tr("�������벻һ�£�����������!"));
        ui->CustomerPwd->setFocus();
    }
    else
    {

        QSqlQuery query;
        bool ok = query.prepare("INSERT INTO Customer (Id, CustomerName,CustomerSex,CustomerPassword,"
                                "CustomerPhone,CustomerAddress,CustomerData,CustomerRemark)"
                                "VALUES (:Id,:CustomerName,:CustomerSex,:CustomerPassword,:CustomerPhone,:CustomerAddress,:CustomerData,:CustomerRemark)");
        query.bindValue(":Id",customerId);
        query.bindValue(":CustomerName",customerName);
        query.bindValue(":CustomerSex",customerSex);
        query.bindValue(":CustomerPassword",customerPwd);
        query.bindValue(":CustomerPhone",customerPhone);
        query.bindValue(":CustomerAddress",customerAddress);
        query.bindValue(":CustomerData",CustomerData);
        query.bindValue(":CustomerRemark",customerRemark);
        query.setForwardOnly(true);
        query.exec();

        if(ok)
        {
            myHelper::MyLoginBlog("logblog","ע��","���û�ע��",customerName);
            myHelper::ShowMessageBoxInfo(tr("ע��ɹ�!"));
        }
    }
}

void RegisterDialog::on_btnCancel_clicked()
{
    this->close();
}

void RegisterDialog::on_btnMenu_Close_clicked()
{
    this->close();
}

/*
 *�������ܣ�������������������Ƿ�һ��
 *˵��������һ�£����ͻ����ѣ�Ҫ����������
*/
void RegisterDialog::CheckPwdIsSame()
{
    QString pwd = ui->CustomerPwd->text();
    QString RePwd = ui->CustomerRePwd->text();
    if(pwd != RePwd)
    {
        myHelper::ShowMessageBoxError(tr("�������벻һ�£�����������!"));
        ui->CustomerPwd->setFocus();
        return;
    }
}
