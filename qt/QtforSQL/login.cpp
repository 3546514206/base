#include "login.h"

Login::Login(QWidget *parent)
    : QDialog(parent)
{
    setWindowTitle(tr("中学生档案管理系统登录界面"));

    setMinimumSize(278,177);    //设置固定大小
    setMaximumSize(278,177);

    QPixmap pixmap(":/loginPicture.jpg");
    QPalette palette;
    palette.setBrush(backgroundRole(), QBrush(pixmap));
    setPalette(palette);

    hSpacer1 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);
    hSpacer2 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

    userLabel = new QLabel;
    userLabel->setText(tr("用户名："));
    userEdit = new QLineEdit;
    userEdit->setText(tr("10011006"));

    pwdLabel = new QLabel;
    pwdLabel->setText(tr("密  码："));
    pwdEdit = new QLineEdit;
    pwdEdit->setText(tr("0"));

    ackBtn=new QPushButton;
    ackBtn->setText(tr("确认"));
    cleanBtn=new QPushButton;
    cleanBtn->setText(tr("清空"));
    quitBtn=new QPushButton;
    quitBtn->setText(tr("退出"));

    hLayout1=new QHBoxLayout;
    hLayout1->addWidget(userLabel);
    hLayout1->addWidget(userEdit);
    hLayout1->addSpacerItem(hSpacer1);

    hLayout2=new QHBoxLayout;
    hLayout2->addWidget(pwdLabel);
    hLayout2->addWidget(pwdEdit);
    hLayout2->addSpacerItem(hSpacer2);

    hLayout3=new QHBoxLayout;
    hLayout3->addWidget(ackBtn);
    hLayout3->addWidget(cleanBtn);
    hLayout3->addWidget(quitBtn);
    hLayout3->setSpacing(40);

    vLayout=new QVBoxLayout;
    vLayout->addLayout(hLayout1);
    vLayout->addLayout(hLayout2);
    vLayout->addLayout(hLayout3);
    vLayout->setMargin(15);

    connect(ackBtn,SIGNAL(clicked()), this, SLOT(onAckBtn()));
    connect(cleanBtn,SIGNAL(clicked()), this, SLOT(onCleanBtn()));
    connect(quitBtn,SIGNAL(clicked()), this, SLOT(onQuitBtn()));

    this->setLayout(vLayout);
}

Login::~Login()
{

}

void Login::onAckBtn(){
    if(pwdEdit->text().isEmpty()||userEdit->text().isEmpty()){
        QMessageBox::information(this,tr("warn"),tr("Please enter your whole login information!"),QMessageBox::Ok);
        return;
    }
    user=userEdit->text();
    pwd=pwdEdit->text();
    QString correctpwd="0";
    QString correctuser="10011006";
    if(user==correctuser&&pwd==correctpwd){
        this->close();
        mainform *w=new mainform();
        w->show();
    }else{
        QMessageBox::warning(this,tr("warn"),tr("Please check your user and password!"),QMessageBox::Ok);
        userEdit->clear();
        pwdEdit->clear();
    }
}

void Login::onCleanBtn(){
    userEdit->clear();
    pwdEdit->clear();
}

void Login::onQuitBtn(){
    this->close();
}

