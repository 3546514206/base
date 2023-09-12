#include "addMark.h"

addMark::addMark(QWidget *parent)
    : QDialog(parent)
{
    sql.isLinkToSql();

    setWindowTitle(tr("添加成绩界面"));
    this->setWindowFlags(Qt::MSWindowsFixedSizeDialogHint);

    //定义“学  号”标签和“学  号”输入框
    SnoLabel = new QLabel;
    SnoLabel->setText(tr("学  号:"));
    SnoEdit = new QLineEdit;
    SnoEdit->setText(tr(""));

    //定义“课程号”标签和“课程号”输入框
    CnoLabel = new QLabel;
    CnoLabel->setText(tr("课程号:"));
    CnoEdit = new QLineEdit;
    CnoEdit->setText(tr(""));

    //定义“分  数”标签和“分  数”输入框
    MpointLabel = new QLabel;
    MpointLabel->setText(tr("分  数:"));
    MpointEdit = new QLineEdit;
    MpointEdit->setText(tr(""));

    //定义“学  期”标签和“学  期”下拉框，下拉框有“期中”和“期末”选项
    MsemesterLabel = new QLabel;
    MsemesterLabel->setText(tr("学  期:"));
    MsemesterBox = new QComboBox;
    MsemesterBox->addItem(tr("期中"));
    MsemesterBox->addItem(tr("期末"));

    //定义确认、退出、清空按钮，并定义点击动作
    ackBtn=new QPushButton;
    ackBtn->setText(tr("确认"));
    connect(ackBtn,SIGNAL(clicked()), this, SLOT(onAckBtn()));
    cleanBtn=new QPushButton;
    cleanBtn->setText(tr("清空"));
    connect(cleanBtn,SIGNAL(clicked()), this, SLOT(onCleanBtn()));

    //布局管理
    gridLayout = new QGridLayout;
    gridLayout->addWidget(SnoLabel,0,0);
    gridLayout->addWidget(SnoEdit,0,1);
    gridLayout->addWidget(CnoLabel,1,0);
    gridLayout->addWidget(CnoEdit,1,1);
    gridLayout->addWidget(MpointLabel,2,0);
    gridLayout->addWidget(MpointEdit,2,1);
    gridLayout->addWidget(MsemesterLabel,3,0);
    gridLayout->addWidget(MsemesterBox,3,1);
    gridLayout->addWidget(cleanBtn,4,0);
    gridLayout->addWidget(ackBtn,4,1);
    gridLayout->setColumnStretch(0,1);
    gridLayout->setColumnStretch(1,2);

    this->setLayout(gridLayout);
}

addMark::~addMark()
{

}

void addMark::onCleanBtn(){
    CnoEdit->clear();
    SnoEdit->clear();
    MpointEdit->clear();
}

void addMark::onAckBtn(){
    QStringList list;
    if(CnoEdit->text().isEmpty()||SnoEdit->text().isEmpty()||MpointEdit->text().isEmpty()||MsemesterBox->currentText().isEmpty()){
        QMessageBox msg;
        msg.setText("Please enter the whole information!");
        msg.exec();
        return;
    }
    QString str1,str2,str3,str4;
    str1 = CnoEdit ->text();
    str2 = SnoEdit ->text();
    str3 = MpointEdit ->text();
    str4 = MsemesterBox->currentText();

    list<<str1<<str2<<str3<<str4;
    query=sql.addToMark(list);
}
