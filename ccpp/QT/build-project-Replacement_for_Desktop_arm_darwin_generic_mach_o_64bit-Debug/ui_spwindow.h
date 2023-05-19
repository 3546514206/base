/********************************************************************************
** Form generated from reading UI file 'spwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SPWINDOW_H
#define UI_SPWINDOW_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QFrame>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>
#include "spframe.h"

QT_BEGIN_NAMESPACE

class Ui_SPWindow
{
public:
    QAction *actionOpen;
    QAction *actionSave;
    QAction *actionExit;
    QAction *actionNoDirGraph;
    QWidget *centralwidget;
    QGridLayout *gridLayout_3;
    QGridLayout *gridLayout;
    QVBoxLayout *verticalLayout;
    SPFrame *shortestpath;
    QHBoxLayout *horizontalLayout_2;
    QRadioButton *rbEditMode;
    QSpacerItem *horizontalSpacer;
    QHBoxLayout *horizontalLayout;
    QPushButton *btnAddVertex;
    QPushButton *btnRemoveAllVertex;
    QFrame *frame_2;
    QGridLayout *gridLayout_2;
    QTextBrowser *tbDetail;
    QHBoxLayout *horizontalLayout_3;
    QLabel *label;
    QComboBox *cbMethod;
    QPushButton *btnCalculate;
    QLabel *label_2;
    QPushButton *btnFloydToExcel;
    QMenuBar *menubar;
    QMenu *menu;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *SPWindow)
    {
        if (SPWindow->objectName().isEmpty())
            SPWindow->setObjectName("SPWindow");
        SPWindow->resize(1114, 699);
        SPWindow->setStyleSheet(QString::fromUtf8(""));
        actionOpen = new QAction(SPWindow);
        actionOpen->setObjectName("actionOpen");
        actionOpen->setShortcutVisibleInContextMenu(false);
        actionSave = new QAction(SPWindow);
        actionSave->setObjectName("actionSave");
        actionExit = new QAction(SPWindow);
        actionExit->setObjectName("actionExit");
        actionNoDirGraph = new QAction(SPWindow);
        actionNoDirGraph->setObjectName("actionNoDirGraph");
        centralwidget = new QWidget(SPWindow);
        centralwidget->setObjectName("centralwidget");
        gridLayout_3 = new QGridLayout(centralwidget);
        gridLayout_3->setObjectName("gridLayout_3");
        gridLayout_3->setContentsMargins(0, 0, 3, 9);
        gridLayout = new QGridLayout();
        gridLayout->setObjectName("gridLayout");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(0);
        verticalLayout->setObjectName("verticalLayout");
        shortestpath = new SPFrame(centralwidget);
        shortestpath->setObjectName("shortestpath");
        QSizePolicy sizePolicy(QSizePolicy::Expanding, QSizePolicy::Expanding);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(shortestpath->sizePolicy().hasHeightForWidth());
        shortestpath->setSizePolicy(sizePolicy);
        shortestpath->setMinimumSize(QSize(800, 600));
        shortestpath->setFrameShape(QFrame::StyledPanel);
        shortestpath->setFrameShadow(QFrame::Raised);

        verticalLayout->addWidget(shortestpath);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        horizontalLayout_2->setContentsMargins(6, -1, -1, -1);
        rbEditMode = new QRadioButton(centralwidget);
        rbEditMode->setObjectName("rbEditMode");
        rbEditMode->setCheckable(true);
        rbEditMode->setChecked(true);

        horizontalLayout_2->addWidget(rbEditMode);

        horizontalSpacer = new QSpacerItem(376, 38, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_2->addItem(horizontalSpacer);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        btnAddVertex = new QPushButton(centralwidget);
        btnAddVertex->setObjectName("btnAddVertex");

        horizontalLayout->addWidget(btnAddVertex);

        btnRemoveAllVertex = new QPushButton(centralwidget);
        btnRemoveAllVertex->setObjectName("btnRemoveAllVertex");

        horizontalLayout->addWidget(btnRemoveAllVertex);


        horizontalLayout_2->addLayout(horizontalLayout);


        verticalLayout->addLayout(horizontalLayout_2);


        gridLayout->addLayout(verticalLayout, 0, 0, 1, 1);

        frame_2 = new QFrame(centralwidget);
        frame_2->setObjectName("frame_2");
        frame_2->setMinimumSize(QSize(300, 0));
        frame_2->setMaximumSize(QSize(600, 16777215));
        frame_2->setFrameShape(QFrame::StyledPanel);
        frame_2->setFrameShadow(QFrame::Raised);
        gridLayout_2 = new QGridLayout(frame_2);
        gridLayout_2->setObjectName("gridLayout_2");
        tbDetail = new QTextBrowser(frame_2);
        tbDetail->setObjectName("tbDetail");
        tbDetail->setVerticalScrollBarPolicy(Qt::ScrollBarAlwaysOn);
        tbDetail->setHorizontalScrollBarPolicy(Qt::ScrollBarAlwaysOn);
        tbDetail->setLineWrapMode(QTextEdit::NoWrap);

        gridLayout_2->addWidget(tbDetail, 2, 0, 1, 1);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        label = new QLabel(frame_2);
        label->setObjectName("label");

        horizontalLayout_3->addWidget(label);

        cbMethod = new QComboBox(frame_2);
        cbMethod->setObjectName("cbMethod");

        horizontalLayout_3->addWidget(cbMethod);

        btnCalculate = new QPushButton(frame_2);
        btnCalculate->setObjectName("btnCalculate");
        btnCalculate->setMinimumSize(QSize(0, 40));
        btnCalculate->setMaximumSize(QSize(11111111, 16777215));

        horizontalLayout_3->addWidget(btnCalculate);


        gridLayout_2->addLayout(horizontalLayout_3, 0, 0, 1, 1);

        label_2 = new QLabel(frame_2);
        label_2->setObjectName("label_2");

        gridLayout_2->addWidget(label_2, 1, 0, 1, 1);

        btnFloydToExcel = new QPushButton(frame_2);
        btnFloydToExcel->setObjectName("btnFloydToExcel");
        btnFloydToExcel->setMinimumSize(QSize(0, 50));

        gridLayout_2->addWidget(btnFloydToExcel, 3, 0, 1, 1);


        gridLayout->addWidget(frame_2, 0, 1, 1, 1);


        gridLayout_3->addLayout(gridLayout, 0, 0, 1, 1);

        SPWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(SPWindow);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 1114, 26));
        menu = new QMenu(menubar);
        menu->setObjectName("menu");
        SPWindow->setMenuBar(menubar);
        statusBar = new QStatusBar(SPWindow);
        statusBar->setObjectName("statusBar");
        SPWindow->setStatusBar(statusBar);

        menubar->addAction(menu->menuAction());
        menu->addAction(actionOpen);
        menu->addAction(actionSave);
        menu->addAction(actionNoDirGraph);
        menu->addSeparator();
        menu->addAction(actionExit);

        retranslateUi(SPWindow);
        QObject::connect(actionExit, &QAction::triggered, SPWindow, qOverload<>(&QMainWindow::close));

        QMetaObject::connectSlotsByName(SPWindow);
    } // setupUi

    void retranslateUi(QMainWindow *SPWindow)
    {
        SPWindow->setWindowTitle(QCoreApplication::translate("SPWindow", "\346\234\200\347\237\255\350\267\257\345\276\204", nullptr));
        actionOpen->setText(QCoreApplication::translate("SPWindow", "\346\211\223\345\274\200(&O)", nullptr));
#if QT_CONFIG(shortcut)
        actionOpen->setShortcut(QCoreApplication::translate("SPWindow", "Ctrl+O", nullptr));
#endif // QT_CONFIG(shortcut)
        actionSave->setText(QCoreApplication::translate("SPWindow", "\344\277\235\345\255\230(&S)", nullptr));
#if QT_CONFIG(shortcut)
        actionSave->setShortcut(QCoreApplication::translate("SPWindow", "Ctrl+S", nullptr));
#endif // QT_CONFIG(shortcut)
        actionExit->setText(QCoreApplication::translate("SPWindow", "\351\200\200\345\207\272(&X)", nullptr));
        actionNoDirGraph->setText(QCoreApplication::translate("SPWindow", "\346\227\240\345\220\221\345\233\276\345\257\274\345\205\245(&L)", nullptr));
        rbEditMode->setText(QCoreApplication::translate("SPWindow", "\347\274\226\350\276\221\346\250\241\345\274\217", nullptr));
        btnAddVertex->setText(QCoreApplication::translate("SPWindow", "\346\267\273\345\212\240\351\241\266\347\202\271", nullptr));
        btnRemoveAllVertex->setText(QCoreApplication::translate("SPWindow", "\345\205\250\351\203\250\345\210\240\351\231\244", nullptr));
        tbDetail->setHtml(QCoreApplication::translate("SPWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'SimSun'; font-size:9pt; font-weight:400; font-style:normal;\">\n"
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><br /></p></body></html>", nullptr));
        label->setText(QCoreApplication::translate("SPWindow", "\346\226\271\346\263\225\357\274\232", nullptr));
        btnCalculate->setText(QCoreApplication::translate("SPWindow", "\350\256\241\347\256\227", nullptr));
        label_2->setText(QCoreApplication::translate("SPWindow", "\350\256\241\347\256\227\346\255\245\351\252\244", nullptr));
        btnFloydToExcel->setText(QCoreApplication::translate("SPWindow", "\350\276\223\345\207\272\345\210\260Excel(Floyd)", nullptr));
        menu->setTitle(QCoreApplication::translate("SPWindow", "\346\226\207\344\273\266(&F)", nullptr));
    } // retranslateUi

};

namespace Ui {
    class SPWindow: public Ui_SPWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SPWINDOW_H
