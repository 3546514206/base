#include "mainform.h"

mainform::mainform(QWidget *parent)
    : QDialog(parent)
{
    sql.isLinkToSql();

    setWindowTitle(tr("中学生档案管理系统界面"));
    setFixedSize(620,460);
    //this->setWindowFlags(Qt::MSWindowsFixedSizeDialogHint);
    this->setWindowOpacity(1);
    //this->setWindowFlags(Qt::FramelessWindowHint);
    //this->setAttribute(Qt::WA_TranslucentBackground);

    //"关于界面"
    aboutInterface *about = new aboutInterface;

    //"添加界面"
    appendInterface *append = new appendInterface;

    //"查找界面"
    selectInterface *select = new selectInterface;

    //"删除*修改 界面"
    //删除
    deleteInterface *del = new deleteInterface;

    //修改
    updateInterface *update = new updateInterface;

    //用QFrame::HLine定义“删除”和“更新”之间的水平标签
    deleteHLineLabel = new QLabel;
    deleteHLineLabel->setFrameStyle(QFrame::HLine | QFrame::Raised);

    //“删除”和“更新”的布局管理
    deleteVBoxLayout = new QVBoxLayout;
    deleteVBoxLayout->addWidget(del);
    deleteVBoxLayout->addWidget(deleteHLineLabel);
    deleteVBoxLayout->addWidget(update);
    deleteVBoxLayout->setStretch(0,5);
    deleteVBoxLayout->setStretch(1,1);
    deleteVBoxLayout->setStretch(2,5);

    //往主界面（用QToolBox定义）添加“删除*修改”页面
    deleteToolBoxWidget = new QWidget;
    deleteToolBoxWidget->setLayout(deleteVBoxLayout);

    //"总界面按QToolBox布局"//
    toolBox = new QToolBox(this);
    toolBox->layout()->setSpacing(5);
    toolBox->addItem(select,"查找");
    toolBox->addItem(append,"添加");
    toolBox->addItem(deleteToolBoxWidget,"删除*修改");
    toolBox->addItem(about,"关于");

    gridLayout = new QGridLayout;
    gridLayout->addWidget(toolBox,0,0);

    this->setLayout(gridLayout);
}

mainform::~mainform()
{

}

