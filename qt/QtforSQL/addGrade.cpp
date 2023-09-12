#include "addGrade.h"

addGrade::addGrade(QWidget *parent)
    : QWidget(parent)
{
    sql.isLinkToSql();

    setWindowTitle(tr("添加班级界面"));
    this->setWindowFlags(Qt::MSWindowsFixedSizeDialogHint);

    //定义“班号”标签和“班号”输入框
    GnoLabel = new QLabel;
    GnoLabel->setText(tr("班号:"));
    GnoEdit = new QLineEdit;
    GnoEdit->setText(tr(""));

    //定义“所在届”标签和“所在届”输入框
    GperiodLabel = new QLabel;
    GperiodLabel->setText(tr("所在届:"));
    GperiodEdit = new QLineEdit;
    GperiodEdit->setText(tr(""));

    //定义“年级”标签和“年级”输入框
    GgradeLabel = new QLabel;
    GgradeLabel->setText(tr("年级:"));
    GgradeEdit = new QLineEdit;
    GgradeEdit->setText(tr(""));

    //定义“学生人数”标签和“学生人数”输入框
    GstunumLabel = new QLabel;
    GstunumLabel->setText(tr("学生人数:"));
    GstunumEdit = new QLineEdit;
    GstunumEdit->setText(tr(""));

    //定义“班主任”标签和“班主任”输入框
    GchargeLabel = new QLabel;
    GchargeLabel->setText(tr("班主任:"));
    GchargeEdit = new QLineEdit;
    GchargeEdit->setText(tr(""));

    //定义确认、退出、清空按钮，并定义点击动作
    ackBtn=new QPushButton;
    ackBtn->setText(tr("确认"));
    connect(ackBtn,SIGNAL(clicked()), this, SLOT(onAckBtn()));
    cleanBtn=new QPushButton;
    cleanBtn->setText(tr("清空"));
    connect(cleanBtn,SIGNAL(clicked()), this, SLOT(onCleanBtn()));

    //布局管理
    gridLayout = new QGridLayout;
    gridLayout->addWidget(GnoLabel,0,0);
    gridLayout->addWidget(GnoEdit,0,1);
    gridLayout->addWidget(GperiodLabel,1,0);
    gridLayout->addWidget(GperiodEdit,1,1);
    gridLayout->addWidget(GgradeLabel,2,0);
    gridLayout->addWidget(GgradeEdit,2,1);
    gridLayout->addWidget(GstunumLabel,3,0);
    gridLayout->addWidget(GstunumEdit,3,1);
    gridLayout->addWidget(GchargeLabel,4,0);
    gridLayout->addWidget(GchargeEdit,4,1);
    gridLayout->addWidget(cleanBtn,5,0);
    gridLayout->addWidget(ackBtn,5,1);
    gridLayout->setColumnStretch(0,1);
    gridLayout->setColumnStretch(1,2);

    this->setLayout(gridLayout);
}

addGrade::~addGrade()
{

}

void addGrade::onCleanBtn(){
    GnoEdit->clear();
    GperiodEdit->clear();
    GgradeEdit->clear();
    GstunumEdit->clear();
    GchargeEdit->clear();
}

void addGrade::onAckBtn(){
    QStringList list;
    if(GnoEdit->text().isEmpty()||GperiodEdit->text().isEmpty()||GgradeEdit->text().isEmpty()||GstunumEdit->text().isEmpty()||GchargeEdit->text().isEmpty()){
        QMessageBox msg;
        msg.setText("Please enter the whole information!");
        msg.exec();
        return;
    }
    QString str1,str2,str3,str4,str5;
    str1 = GnoEdit ->text();
    str2 = GperiodEdit ->text();
    str3 = GgradeEdit ->text();
    str4 = GstunumEdit->text();
    str5 = GchargeEdit->text();

    list<<str1<<str2<<str3<<str4<<str5;
    query=sql.addToGrade(list);
}

