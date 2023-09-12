#include "updateInterface.h"

updateInterface::updateInterface(QWidget *parent) : QWidget(parent)
{
    //修改
    //定义“要更新的表”的标签
    updateTableLabel = new QLabel;
    updateTableLabel->setText(tr("要更新的表："));
    updateTableLabel->setAlignment(Qt::AlignCenter);

    //定义可以选择的表更新，用QComboBox，并设置默认值
    updateTableComboBox = new QComboBox;
    updateTableComboBox->addItem(tr("课程"));
    updateTableComboBox->addItem(tr("家庭信息"));
    updateTableComboBox->addItem(tr("班级"));
    updateTableComboBox->addItem(tr("成绩"));
    updateTableComboBox->addItem(tr("学生"));
    updateSetComboBox=new QComboBox;
    updateSetComboBox->addItem(tr("课程号"));
    updateSetComboBox->addItem(tr("课程名"));
    updateSetComboBox->addItem(tr("任课教师"));
    connect(updateTableComboBox,SIGNAL(currentIndexChanged(int)),this,SLOT(OnComboIndexChanged3()));

    //定义带有“确认更新”标签的按钮，并定义点击动作
    updateAckBtn = new QPushButton;
    updateAckBtn->setText(tr("确认更新"));
    connect(updateAckBtn,SIGNAL(clicked()),this,SLOT(updateSql()));

    //定义数据库更新句子的“set”标签
    updateSetLabel = new QLabel;
    updateSetLabel->setText(tr("set"));
    updateSetLabel->setAlignment(Qt::AlignCenter);
    updateSetEdit = new QLineEdit;
    updateSetEdit->setText(tr(""));

    //定义数据库更新句子的“where”标签
    updateWhereLabel = new QLabel;
    updateWhereLabel->setText(tr("where"));
    updateWhereLabel->setAlignment(Qt::AlignCenter);

    //定义更新的条件的表名，用QComboBox，并设置默认值
    updateWhereComboBox = new QComboBox;
    updateWhereComboBox->addItem(tr("课程"));
    updateWhereComboBox->addItem(tr("家庭信息"));
    updateWhereComboBox->addItem(tr("班级"));
    updateWhereComboBox->addItem(tr("成绩"));
    updateWhereComboBox->addItem(tr("学生"));
    updateWhereItemCombo4=new QComboBox;
    updateWhereItemCombo4->addItem(tr("课程号"));
    updateWhereItemCombo4->addItem(tr("课程名"));
    updateWhereItemCombo4->addItem(tr("任课教师"));
    connect(updateWhereComboBox,SIGNAL(currentIndexChanged(int)),this,SLOT(OnComboIndexChanged4()));

    //定义更新是需要输入的条件的输入框
    updateWhereEdit = new QLineEdit;
    updateWhereEdit->setText(tr(""));

    //“更新”布局管理
    updateLayout = new QGridLayout;
    updateLayout->addWidget(updateTableLabel,0,0);
    updateLayout->addWidget(updateTableComboBox,0,1);
    updateLayout->addWidget(updateAckBtn,0,3);
    updateLayout->addWidget(updateSetLabel,1,0);
    updateLayout->addWidget(updateSetComboBox,1,1);
    updateLayout->addWidget(updateSetEdit,1,2);
    updateLayout->addWidget(updateWhereLabel,2,0);
    updateLayout->addWidget(updateWhereComboBox,2,1);
    updateLayout->addWidget(updateWhereItemCombo4,2,2);
    updateLayout->addWidget(updateWhereEdit,2,3);
    updateLayout->setColumnStretch(0,1);
    updateLayout->setColumnStretch(1,1);
    updateLayout->setColumnStretch(2,1);
    updateLayout->setColumnStretch(3,1);

    this->setLayout(updateLayout);
}

updateInterface::~updateInterface(){

}

void updateInterface::updateSql(){
    QString str1,str2,str3,str4,str5,str6;
    str1=cnToen(updateTableComboBox->currentText());
    str2=cnToen(updateSetComboBox->currentText());
    str3=updateSetEdit->text();
    str4=cnToen(updateWhereComboBox->currentText());
    str5=cnToen(updateWhereItemCombo4->currentText());
    str6=updateWhereEdit->text();
    sql.updateFromSql(str1,str2,str3,str4,str5,str6);
}

void updateInterface::OnComboIndexChanged3(){
    int i = updateTableComboBox->currentIndex();
    updateSetComboBox->clear();
    switch(i){
    case 0:
        updateSetComboBox->addItem(tr("课程号"));
        updateSetComboBox->addItem(tr("课程名"));
        updateSetComboBox->addItem(tr("任课教师"));
        break;
    case 1:
        updateSetComboBox->addItem(tr("家庭信息号"));
        updateSetComboBox->addItem(tr("家属姓名"));
        updateSetComboBox->addItem(tr("联系电话"));
        updateSetComboBox->addItem(tr("与本人关系"));
        updateSetComboBox->addItem(tr("学号"));
        break;
    case 3:
        updateSetComboBox->addItem(tr("学号"));
        updateSetComboBox->addItem(tr("课程号"));
        updateSetComboBox->addItem(tr("学期"));
        updateSetComboBox->addItem(tr("分数"));
        break;
    case 2:
        updateSetComboBox->addItem(tr("班号"));
        updateSetComboBox->addItem(tr("所在届"));
        updateSetComboBox->addItem(tr("年级"));
        updateSetComboBox->addItem(tr("班主任"));
        updateSetComboBox->addItem(tr("人数"));
        break;
    case 4:
        updateSetComboBox->addItem(tr("学号"));
        updateSetComboBox->addItem(tr("学生姓名"));
        updateSetComboBox->addItem(tr("性别"));
        updateSetComboBox->addItem(tr("出生日期"));
        updateSetComboBox->addItem(tr("民族"));
        updateSetComboBox->addItem(tr("家庭住址"));
        updateSetComboBox->addItem(tr("手机号码"));
        updateSetComboBox->addItem(tr("毕业去向"));
        updateSetComboBox->addItem(tr("班号"));
        break;
    }
}

void updateInterface::OnComboIndexChanged4(){
    int i = updateWhereComboBox->currentIndex();
    updateWhereItemCombo4->clear();
    switch(i){
    case 0:
        updateWhereItemCombo4->addItem(tr("课程号"));
        updateWhereItemCombo4->addItem(tr("课程名"));
        updateWhereItemCombo4->addItem(tr("任课教师"));
        break;
    case 1:
        updateWhereItemCombo4->addItem(tr("家庭信息号"));
        updateWhereItemCombo4->addItem(tr("家属姓名"));
        updateWhereItemCombo4->addItem(tr("联系电话"));
        updateWhereItemCombo4->addItem(tr("与本人关系"));
        updateWhereItemCombo4->addItem(tr("学号"));
        break;
    case 3:
        updateWhereItemCombo4->addItem(tr("学号"));
        updateWhereItemCombo4->addItem(tr("课程号"));
        updateWhereItemCombo4->addItem(tr("学期"));
        updateWhereItemCombo4->addItem(tr("分数"));
        break;
    case 2:
        updateWhereItemCombo4->addItem(tr("班号"));
        updateWhereItemCombo4->addItem(tr("所在届"));
        updateWhereItemCombo4->addItem(tr("年级"));
        updateWhereItemCombo4->addItem(tr("班主任"));
        updateWhereItemCombo4->addItem(tr("人数"));
        break;
    case 4:
        updateWhereItemCombo4->addItem(tr("学号"));
        updateWhereItemCombo4->addItem(tr("学生姓名"));
        updateWhereItemCombo4->addItem(tr("性别"));
        updateWhereItemCombo4->addItem(tr("出生日期"));
        updateWhereItemCombo4->addItem(tr("民族"));
        updateWhereItemCombo4->addItem(tr("家庭住址"));
        updateWhereItemCombo4->addItem(tr("手机号码"));
        updateWhereItemCombo4->addItem(tr("毕业去向"));
        updateWhereItemCombo4->addItem(tr("班号"));
        break;
    }
}

QString updateInterface::cnToen(QString str){
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
