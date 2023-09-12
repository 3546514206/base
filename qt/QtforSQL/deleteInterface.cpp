#include "deleteInterface.h"

deleteInterface::deleteInterface(QWidget *parent)
    : QWidget(parent)
{
    //删除
    //定义可以选择的表删除，用QComboBox，并设置默认值
    deleteComboBox=new QComboBox;
    deleteComboBox->addItem(tr("课程"));
    deleteComboBox->addItem(tr("家庭信息"));
    deleteComboBox->addItem(tr("班级"));
    deleteComboBox->addItem(tr("成绩"));
    deleteComboBox->addItem(tr("学生"));
    //定义已经选择的表的项目删除，用QComboBox，并设置默认值
    deleteItemCombo=new QComboBox;
    deleteItemCombo->addItem(tr("课程号"));
    deleteItemCombo->addItem(tr("课程名"));
    deleteItemCombo->addItem(tr("任课教师"));
    connect(deleteComboBox,SIGNAL(currentIndexChanged(int)),this,SLOT(OnComboIndexChanged2()));

    //定义“删除”按钮，并定义点击动作
    deleteAckBtn = new QPushButton;
    deleteAckBtn->setText(tr("删除"));
    connect(deleteAckBtn,SIGNAL(clicked()),this,SLOT(delectSql()));

    //定义“删除条件”标签（居中对齐）和“删除条件”输入框
    deleteConditionLabel = new QLabel;
    deleteConditionLabel->setText(tr("删除条件："));
    deleteConditionLabel->setAlignment(Qt::AlignCenter);
    deleteConditionEdit = new QLineEdit;

    //”删除“布局管理
    deleteLayout = new QGridLayout;
    deleteLayout->addWidget(deleteComboBox,0,0);
    deleteLayout->addWidget(deleteItemCombo,0,1);
    deleteLayout->addWidget(deleteAckBtn,0,2);
    deleteLayout->addWidget(deleteConditionLabel,1,0);
    deleteLayout->addWidget(deleteConditionEdit,1,1);
    deleteLayout->setColumnStretch(0,1);
    deleteLayout->setColumnStretch(1,1);
    deleteLayout->setColumnStretch(2,1);

    this->setLayout(deleteLayout);

}

deleteInterface::~deleteInterface(){

}

void deleteInterface::delectSql(){
    QString str1,str2,str3;
    str1 = cnToen(deleteComboBox->currentText());
    str2 = cnToen(deleteItemCombo->currentText());
    str3 = deleteConditionEdit->text();
    sql.deleteFromSql(str1,str2,str3);
}

void deleteInterface::OnComboIndexChanged2(){
    int i = deleteComboBox->currentIndex();
    deleteItemCombo->clear();
    switch(i){
    case 0:
        deleteItemCombo->addItem(tr("课程号"));
        deleteItemCombo->addItem(tr("课程名"));
        deleteItemCombo->addItem(tr("任课教师"));
        break;
    case 1:
        deleteItemCombo->addItem(tr("家庭信息号"));
        deleteItemCombo->addItem(tr("家属姓名"));
        deleteItemCombo->addItem(tr("联系电话"));
        deleteItemCombo->addItem(tr("与本人关系"));
        deleteItemCombo->addItem(tr("学号"));
        break;
    case 3:
        deleteItemCombo->addItem(tr("学号"));
        deleteItemCombo->addItem(tr("课程号"));
        deleteItemCombo->addItem(tr("学期"));
        deleteItemCombo->addItem(tr("分数"));
        break;
    case 2:
        deleteItemCombo->addItem(tr("班号"));
        deleteItemCombo->addItem(tr("所在届"));
        deleteItemCombo->addItem(tr("年级"));
        deleteItemCombo->addItem(tr("班主任"));
        deleteItemCombo->addItem(tr("人数"));
        break;
    case 4:
        deleteItemCombo->addItem(tr("学号"));
        deleteItemCombo->addItem(tr("学生姓名"));
        deleteItemCombo->addItem(tr("性别"));
        deleteItemCombo->addItem(tr("出生日期"));
        deleteItemCombo->addItem(tr("民族"));
        deleteItemCombo->addItem(tr("家庭住址"));
        deleteItemCombo->addItem(tr("手机号码"));
        deleteItemCombo->addItem(tr("毕业去向"));
        deleteItemCombo->addItem(tr("班号"));
        break;
    }
}

QString deleteInterface::cnToen(QString str){
    if(str=="课程号")
        return "Cno";
    else if(str=="课程名")
        return "Cname";
    else if(str=="任课教师")
        return "Cteacher";
    else if(str=="家庭信息号")
        return "Fno";
    else if(str=="家属姓名")
        return "Fname";
    else if(str=="联系电话")
        return "Fphone";
    else if(str=="与本人关系")
        return "Frelation";
    else if(str=="学期")
        return "Msemester";
    else if(str=="成绩")
        return "Mark";
    else if(str=="班号")
        return "Gno";
    else if(str=="所在届")
        return "Gperiod";
    else if(str=="年级")
        return "Ggrade";
    else if(str=="班主任")
        return "Gcharge";
    else if(str=="人数")
        return "Gstunum";
    else if(str=="学号")
        return "Sno";
    else if(str=="学生姓名")
        return "Sname";
    else if(str=="性别")
        return "Ssex";
    else if(str=="出生日期")
        return "Sdate";
    else if(str=="民族")
        return "Snation";
    else if(str=="家庭住址")
        return "Saddress";
    else if(str=="手机号码")
        return "Sphone";
    else if(str=="毕业去向")
        return "Sgraduate";
    else if(str=="学生")
        return "Student";
    else if(str=="课程")
        return "Course";
    else if(str=="家庭信息")
        return "Family";
    else if(str=="班级")
        return "Grade";
    else if(str=="分数")
        return "Mpoint";
    else return "";
}
