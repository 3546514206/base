#include "addStudent.h"

addStudent::addStudent(QWidget *parent)
    : QDialog(parent)
{
    sql.isLinkToSql();

    setWindowTitle(tr("学生注册界面"));
    this->setWindowFlags(Qt::MSWindowsFixedSizeDialogHint);

    //定义“学号”标签和“学号”输入框
    SnoLabel = new QLabel;
    SnoLabel->setText(tr("学号:"));
    SnoEdit = new QLineEdit;
    SnoEdit->setText(tr(""));

    //定义“姓名”标签和“姓名”输入框
    SnameLabel = new QLabel;
    SnameLabel->setText(tr("姓名:"));
    SnameEdit = new QLineEdit;
    SnameEdit->setText(tr(""));

    //定义“性别”标签和“性别”下拉框，下拉框包括“女”和“男”选项
    SsexLabel = new QLabel;
    SsexLabel->setText(tr("性别:"));
    SsexComboBox=new QComboBox;
    SsexComboBox->addItem(tr("女"));
    SsexComboBox->addItem(tr("男"));

    //定义“出生日期”标签和“出生日期”输入框
    SdateLabel = new QLabel;
    SdateLabel->setText(tr("出生日期:"));
    SdateEdit = new QLineEdit;
    SdateEdit->setText(tr(""));

    //定义“民族”标签和“民族”输入框
    SnationLabel = new QLabel;
    SnationLabel->setText(tr("民族:"));
    SnationEdit = new QLineEdit;
    SnationEdit->setText(tr(""));

    //定义“手机号码”标签和“手机号码”输入框
    SphoneLabel = new QLabel;
    SphoneLabel->setText(tr("手机号码:"));
    SphoneEdit = new QLineEdit;
    SphoneEdit->setText(tr(""));

    //定义“家庭住址”标签和“家庭住址”输入框
    SaddressLabel = new QLabel;
    SaddressLabel->setText(tr("家庭住址:"));
    SaddressEdit = new QLineEdit;
    SaddressEdit->setText(tr(""));

    //定义“毕业去向”标签和“毕业去向”输入框
    SgraduateLabel = new QLabel;
    SgraduateLabel->setText(tr("毕业去向:"));
    SgraduateEdit = new QLineEdit;
    SgraduateEdit->setText(tr(""));

    //定义“班号”标签和“班号”输入框
    GnoLabel = new QLabel;
    GnoLabel->setText(tr("班号:"));
    GnoEdit = new QLineEdit;
    GnoEdit->setText(tr(""));

    //定义确认、退出、清空按钮，并定义点击动作
    ackBtn=new QPushButton;
    ackBtn->setText(tr("确认"));
    connect(ackBtn,SIGNAL(clicked()), this, SLOT(onAckBtn()));
    cleanBtn=new QPushButton;
    cleanBtn->setText(tr("清空"));
    connect(cleanBtn,SIGNAL(clicked()), this, SLOT(onCleanBtn()));

    //布局管理
    gridLayout = new QGridLayout;
    gridLayout->addWidget(SnameLabel,0,0);
    gridLayout->addWidget(SnameEdit,0,1);
    gridLayout->addWidget(SnoLabel,1,0);
    gridLayout->addWidget(SnoEdit,1,1);
    gridLayout->addWidget(SsexLabel,2,0);
    gridLayout->addWidget(SsexComboBox,2,1);
    gridLayout->addWidget(SnationLabel,3,0);
    gridLayout->addWidget(SnationEdit,3,1);
    gridLayout->addWidget(SdateLabel,4,0);
    gridLayout->addWidget(SdateEdit,4,1);
    gridLayout->addWidget(SphoneLabel,5,0);
    gridLayout->addWidget(SphoneEdit,5,1);
    gridLayout->addWidget(SaddressLabel,6,0);
    gridLayout->addWidget(SaddressEdit,6,1);
    gridLayout->addWidget(SgraduateLabel,7,0);
    gridLayout->addWidget(SgraduateEdit,7,1);
    gridLayout->addWidget(GnoLabel,8,0);
    gridLayout->addWidget(GnoEdit,8,1);
    gridLayout->addWidget(cleanBtn,9,0);
    gridLayout->addWidget(ackBtn,9,1);
    gridLayout->setColumnStretch(0,1);
    gridLayout->setColumnStretch(1,2);

    this->setLayout(gridLayout);
}

addStudent::~addStudent(){

}

void addStudent::onCleanBtn(){
    SnoEdit->clear();
    SnameEdit->clear();
    SdateEdit->clear();
    SnationEdit->clear();
    SphoneEdit->clear();
    SaddressEdit->clear();
    SgraduateEdit->clear();
    GnoEdit->clear();
}
void addStudent::onAckBtn(){
    QStringList list;
    if(SnoEdit->text().isEmpty()||SnameEdit->text().isEmpty()||SsexComboBox->currentText().isEmpty()||SdateEdit->text().isEmpty()||SnationEdit->text().isEmpty()
            ||SphoneEdit->text().isEmpty()||SaddressEdit->text().isEmpty()||SgraduateEdit->text().isEmpty()||GnoEdit->text().isEmpty()){
        QMessageBox msg;
        msg.setText("Please enter the whole information!");
        msg.exec();
        return;
    }
    QString str1,str2,str3,str4,str5,str6,str7,str8,str9;
    str1 = SnoEdit ->text();
    str2 = SnameEdit ->text();
    str3 = SsexComboBox ->currentText();
    str4 = SdateEdit->text();
    str5 = SnationEdit->text();
    str6 = SphoneEdit->text();
    str7 = SaddressEdit->text();
    str8 = SgraduateEdit->text();
    str9 = GnoEdit->text();

    list<<str1<<str2<<str3<<str4<<str5<<str6<<str7<<str8<<str9;
    query=sql.addToStudent(list);
}
