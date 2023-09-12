#include "selectInterface.h"

selectInterface::selectInterface(QWidget *parent) : QWidget(parent)
{
    //"查找界面"
    //定义“输入你选定查找的数据”标签和“输入你选定查找的数据”输入框
    searchInputLabel =new QLabel;
    searchInputLabel->setText(tr("输入你选定查找的数据"));
    seachInputEdit= new QLineEdit;
    seachInputEdit->setText(tr(""));

    //定义“查找”按钮和定义点击动作
    selectSearchBtn = new QPushButton;
    selectSearchBtn->setText(tr("查找"));
    connect(selectSearchBtn,SIGNAL(clicked()),this,SLOT(selectSql()));
    //定义“查找全部”按钮和定义点击动作
    selectAllSearchBtn = new QPushButton;
    selectAllSearchBtn->setText(tr("查找全部"));
    connect(selectAllSearchBtn,SIGNAL(clicked()),this,SLOT(selectAll()));

    //定义QTableView用于显示数据库SQL查找结果
    tView = new QTableView;

    //一下是默认的QComboxBox显示的数据，并定义点击动作
    selectComboBox=new QComboBox;
    selectComboBox->addItem(tr("课程"));
    selectComboBox->addItem(tr("家庭信息"));
    selectComboBox->addItem(tr("班级"));
    selectComboBox->addItem(tr("成绩"));
    selectComboBox->addItem(tr("学生"));
    selectItemCombo=new QComboBox;
    selectItemCombo->addItem(tr("课程号"));
    selectItemCombo->addItem(tr("课程名"));
    selectItemCombo->addItem(tr("任课教师"));
    connect(selectComboBox,SIGNAL(currentIndexChanged(int)),this,SLOT(OnComboIndexChanged()));

    //布局管理
    selectGridLayout = new QGridLayout;
    selectGridLayout->setColumnStretch(0,1);
    selectGridLayout->setColumnStretch(1,1);
    selectGridLayout->setColumnStretch(2,1);
    selectGridLayout->addWidget(selectComboBox,0,0);
    selectGridLayout->addWidget(selectItemCombo,0,1);
    selectGridLayout->addWidget(selectSearchBtn,0,2);
    selectGridLayout->addWidget(searchInputLabel,1,0);
    selectGridLayout->addWidget(seachInputEdit,1,1);
    selectGridLayout->addWidget(selectAllSearchBtn,1,2);

    selectVBoxLayout= new QVBoxLayout;
    selectVBoxLayout->addLayout(selectGridLayout);
    selectVBoxLayout->addWidget(tView);

    this->setLayout(selectVBoxLayout);
}

selectInterface::~selectInterface()
{

}

void selectInterface::selectSql(){
    QString str1,str2,str3;
    str1 = cnToen(selectComboBox->currentText());
    str2 = cnToen(selectItemCombo->currentText());
    str3 = seachInputEdit->text();
    tView->clearFocus();
    sql.selectFromSql(str1,str2,str3);
    tView->setModel(&sql.model);
}

void selectInterface::selectAll()
{
    tView->clearFocus();
    sql.selectAllInf(selectComboBox->currentIndex());
    tView->setModel(&sql.model);
}

void selectInterface::OnComboIndexChanged(){
    int i = selectComboBox->currentIndex();
    selectItemCombo->clear();
    switch(i){
    case 0:
        selectItemCombo->addItem(tr("课程号"));
        selectItemCombo->addItem(tr("课程名"));
        selectItemCombo->addItem(tr("任课教师"));
        break;
    case 1:
        selectItemCombo->addItem(tr("家庭信息号"));
        selectItemCombo->addItem(tr("家属姓名"));
        selectItemCombo->addItem(tr("联系电话"));
        selectItemCombo->addItem(tr("与本人关系"));
        selectItemCombo->addItem(tr("学号"));
        break;
    case 3:
        selectItemCombo->addItem(tr("学号"));
        selectItemCombo->addItem(tr("课程号"));
        selectItemCombo->addItem(tr("学期"));
        selectItemCombo->addItem(tr("分数"));
        break;
    case 2:
        selectItemCombo->addItem(tr("班号"));
        selectItemCombo->addItem(tr("所在届"));
        selectItemCombo->addItem(tr("年级"));
        selectItemCombo->addItem(tr("班主任"));
        selectItemCombo->addItem(tr("人数"));
        break;
    case 4:
        selectItemCombo->addItem(tr("学号"));
        selectItemCombo->addItem(tr("学生姓名"));
        selectItemCombo->addItem(tr("性别"));
        selectItemCombo->addItem(tr("出生日期"));
        selectItemCombo->addItem(tr("民族"));
        selectItemCombo->addItem(tr("家庭住址"));
        selectItemCombo->addItem(tr("手机号码"));
        selectItemCombo->addItem(tr("毕业去向"));
        selectItemCombo->addItem(tr("班号"));
        break;
    }
}

QString selectInterface::cnToen(QString str){
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
