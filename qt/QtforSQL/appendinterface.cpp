#include "appendInterface.h"

appendInterface::appendInterface(QWidget *parent)
    : QWidget(parent)
{
    //"添加界面"
    addGrade *grade = new addGrade;
    addCourse *course= new addCourse;
    addFamily *family=new addFamily;
    addStudent *student = new addStudent;
    addMark *mark=new addMark;

    listWidget= new QListWidget(this);
    listWidget ->insertItem(0,"添加课程");
    listWidget ->insertItem(1,"添加家庭成员信息");
    listWidget ->insertItem(2,"添加班级");
    listWidget ->insertItem(3,"添加成绩");
    listWidget ->insertItem(4,"添加学生");

    stackWidget = new QStackedWidget(this);
    stackWidget->addWidget(course);
    stackWidget->addWidget(family);
    stackWidget->addWidget(grade);
    stackWidget->addWidget(mark);
    stackWidget->addWidget(student);

    stackWidgetLayout  = new QHBoxLayout;
    stackWidgetLayout->addWidget(listWidget);
    stackWidgetLayout->addWidget(stackWidget);
    stackWidgetLayout->setStretchFactor(listWidget,1);
    stackWidgetLayout->setStretchFactor(stackWidget,2);
    connect(listWidget,SIGNAL(currentRowChanged(int)),stackWidget,SLOT(setCurrentIndex(int)));

    this->setLayout(stackWidgetLayout);
}

appendInterface::~appendInterface()
{

}
