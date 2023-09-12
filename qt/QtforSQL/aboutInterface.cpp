#include "aboutInterface.h"

aboutInterface::aboutInterface(QWidget *parent) : QWidget(parent)
{
    //"关于界面"
    QString textStr;
    textStr="                  中学生档案管理系统\n\n         ";
    textStr.append("           作者：徐秀天\n\n");
    textStr.append("             工具：Qt SDK and Qt Creator \n\n");
    textStr.append("                    版本：V5 版本 \n\n");

    //用QFrame::VLine定义“关于”界面的垂直标签
    aboutVLineLabel = new QLabel;
    aboutVLineLabel->setFrameStyle(QFrame::VLine | QFrame::Raised);
    //用QFrame::HLine定义“关于”界面的水平标签
    aboutHLineLabel = new QLabel;
    aboutHLineLabel->setFrameStyle(QFrame::HLine | QFrame::Raised);
    //用QFrame::WinPanel定义“关于”界面的图片标签，并用QPixmap类加载图片
    aboutPictureLabel = new QLabel;
    aboutPictureLabel->setFrameStyle(QFrame::WinPanel | QFrame::Raised);
    QPixmap pix(":/aboutPicture.jpg");
    aboutPictureLabel->setPixmap(pix);
    //用QFrame::Box定义“关于”界面的文本标签载体，并添加文本
    aboutTextLabel = new QLabel;
    aboutTextLabel->setFrameStyle(QFrame::Box | QFrame::Raised);
    aboutTextLabel->setWordWrap(true);
    aboutTextLabel->setText(textStr);

    //定义退出按钮，并定义点击动作
    quitBtn = new QPushButton;
    quitBtn->setText(tr("退出"));
    connect(quitBtn,SIGNAL(clicked()),qApp,SLOT(quit()));

    //布局管理
    aboutLeftVBoxLayout = new QVBoxLayout;
    aboutLeftVBoxLayout->addWidget(aboutPictureLabel);
    aboutLeftVBoxLayout->addStretch(1);
    aboutRightVBoxLayout = new QVBoxLayout;
    aboutRightVBoxLayout->addWidget(aboutTextLabel);
    aboutRightVBoxLayout->addWidget(aboutHLineLabel);
    aboutRightVBoxLayout->addWidget(quitBtn);
    aboutRightVBoxLayout->setStretch(0,9);
    aboutRightVBoxLayout->setStretch(1,2);
    aboutRightVBoxLayout->setStretch(2,2);
    aboutHBoxLayout = new QHBoxLayout;
    aboutHBoxLayout->addLayout(aboutLeftVBoxLayout);
    aboutHBoxLayout->addWidget(aboutVLineLabel);
    aboutHBoxLayout->addLayout(aboutRightVBoxLayout);
    aboutHBoxLayout->setStretch(0,5);
    aboutHBoxLayout->setStretch(1,1);
    aboutHBoxLayout->setStretch(2,11);

    this->setLayout(aboutHBoxLayout);
}

aboutInterface::~aboutInterface(){

}

