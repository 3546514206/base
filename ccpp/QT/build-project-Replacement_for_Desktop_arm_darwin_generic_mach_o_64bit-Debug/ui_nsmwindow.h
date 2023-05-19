/********************************************************************************
** Form generated from reading UI file 'nsmwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_NSMWINDOW_H
#define UI_NSMWINDOW_H

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
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>
#include "nsmframe.h"

QT_BEGIN_NAMESPACE

class Ui_NSMWindow
{
public:
    QAction *actionOpen;
    QAction *actionSave;
    QAction *actionExit;
    QWidget *centralwidget;
    QGridLayout *gridLayout;
    NSMFrame *nsm;
    QFrame *frame_2;
    QGridLayout *gridLayout_2;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QRadioButton *rbEditMode;
    QSpacerItem *horizontalSpacer;
    QPushButton *btnAddVertex;
    QPushButton *btnRemoveAllVertex;
    QPushButton *btnCalculate;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label;
    QSpacerItem *horizontalSpacer_2;
    QComboBox *cbStep;
    QPushButton *btnPrev;
    QPushButton *btnNext;
    QMenuBar *menubar;
    QMenu *menu_F;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *NSMWindow)
    {
        if (NSMWindow->objectName().isEmpty())
            NSMWindow->setObjectName("NSMWindow");
        NSMWindow->resize(1116, 700);
        NSMWindow->setMinimumSize(QSize(800, 700));
        actionOpen = new QAction(NSMWindow);
        actionOpen->setObjectName("actionOpen");
        actionSave = new QAction(NSMWindow);
        actionSave->setObjectName("actionSave");
        actionExit = new QAction(NSMWindow);
        actionExit->setObjectName("actionExit");
        centralwidget = new QWidget(NSMWindow);
        centralwidget->setObjectName("centralwidget");
        gridLayout = new QGridLayout(centralwidget);
        gridLayout->setObjectName("gridLayout");
        nsm = new NSMFrame(centralwidget);
        nsm->setObjectName("nsm");
        nsm->setFrameShape(QFrame::StyledPanel);
        nsm->setFrameShadow(QFrame::Raised);

        gridLayout->addWidget(nsm, 0, 0, 1, 1);

        frame_2 = new QFrame(centralwidget);
        frame_2->setObjectName("frame_2");
        frame_2->setMaximumSize(QSize(16777215, 100));
        frame_2->setFrameShape(QFrame::StyledPanel);
        frame_2->setFrameShadow(QFrame::Raised);
        gridLayout_2 = new QGridLayout(frame_2);
        gridLayout_2->setSpacing(6);
        gridLayout_2->setObjectName("gridLayout_2");
        gridLayout_2->setContentsMargins(0, 0, 0, 0);
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        verticalLayout->setSizeConstraint(QLayout::SetDefaultConstraint);
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(7);
        horizontalLayout->setObjectName("horizontalLayout");
        horizontalLayout->setSizeConstraint(QLayout::SetDefaultConstraint);
        rbEditMode = new QRadioButton(frame_2);
        rbEditMode->setObjectName("rbEditMode");
        rbEditMode->setChecked(true);

        horizontalLayout->addWidget(rbEditMode);

        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        btnAddVertex = new QPushButton(frame_2);
        btnAddVertex->setObjectName("btnAddVertex");

        horizontalLayout->addWidget(btnAddVertex);

        btnRemoveAllVertex = new QPushButton(frame_2);
        btnRemoveAllVertex->setObjectName("btnRemoveAllVertex");

        horizontalLayout->addWidget(btnRemoveAllVertex);

        btnCalculate = new QPushButton(frame_2);
        btnCalculate->setObjectName("btnCalculate");

        horizontalLayout->addWidget(btnCalculate);


        verticalLayout->addLayout(horizontalLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        label = new QLabel(frame_2);
        label->setObjectName("label");

        horizontalLayout_2->addWidget(label);

        horizontalSpacer_2 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_2->addItem(horizontalSpacer_2);

        cbStep = new QComboBox(frame_2);
        cbStep->setObjectName("cbStep");
        cbStep->setMinimumSize(QSize(140, 0));

        horizontalLayout_2->addWidget(cbStep);

        btnPrev = new QPushButton(frame_2);
        btnPrev->setObjectName("btnPrev");

        horizontalLayout_2->addWidget(btnPrev);

        btnNext = new QPushButton(frame_2);
        btnNext->setObjectName("btnNext");

        horizontalLayout_2->addWidget(btnNext);


        verticalLayout->addLayout(horizontalLayout_2);


        gridLayout_2->addLayout(verticalLayout, 0, 0, 1, 1);


        gridLayout->addWidget(frame_2, 1, 0, 1, 1);

        NSMWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(NSMWindow);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 1116, 26));
        menu_F = new QMenu(menubar);
        menu_F->setObjectName("menu_F");
        NSMWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(NSMWindow);
        statusbar->setObjectName("statusbar");
        NSMWindow->setStatusBar(statusbar);

        menubar->addAction(menu_F->menuAction());
        menu_F->addAction(actionOpen);
        menu_F->addAction(actionSave);
        menu_F->addSeparator();
        menu_F->addAction(actionExit);

        retranslateUi(NSMWindow);
        QObject::connect(actionExit, &QAction::triggered, NSMWindow, qOverload<>(&QMainWindow::close));

        QMetaObject::connectSlotsByName(NSMWindow);
    } // setupUi

    void retranslateUi(QMainWindow *NSMWindow)
    {
        NSMWindow->setWindowTitle(QCoreApplication::translate("NSMWindow", "\347\275\221\347\273\234\345\215\225\347\272\257\345\275\242\346\263\225", nullptr));
        actionOpen->setText(QCoreApplication::translate("NSMWindow", "\346\211\223\345\274\200(&O)", nullptr));
#if QT_CONFIG(shortcut)
        actionOpen->setShortcut(QCoreApplication::translate("NSMWindow", "Ctrl+O", nullptr));
#endif // QT_CONFIG(shortcut)
        actionSave->setText(QCoreApplication::translate("NSMWindow", "\344\277\235\345\255\230(&S)", nullptr));
#if QT_CONFIG(shortcut)
        actionSave->setShortcut(QCoreApplication::translate("NSMWindow", "Ctrl+S", nullptr));
#endif // QT_CONFIG(shortcut)
        actionExit->setText(QCoreApplication::translate("NSMWindow", "\351\200\200\345\207\272(&X)", nullptr));
        rbEditMode->setText(QCoreApplication::translate("NSMWindow", "\347\274\226\350\276\221\346\250\241\345\274\217", nullptr));
        btnAddVertex->setText(QCoreApplication::translate("NSMWindow", "\346\267\273\345\212\240\350\212\202\347\202\271", nullptr));
        btnRemoveAllVertex->setText(QCoreApplication::translate("NSMWindow", "\345\205\250\351\203\250\345\210\240\351\231\244", nullptr));
        btnCalculate->setText(QCoreApplication::translate("NSMWindow", "\350\256\241\347\256\227", nullptr));
        label->setText(QCoreApplication::translate("NSMWindow", "<html><head/><body><p><span style=\" color:#ff8000;\">\346\251\231\350\211\262\344\270\272\345\237\272\345\217\230\351\207\217</span>&nbsp;&nbsp;&nbsp;<span style=\" color:#00ff00;\">\347\273\277\350\211\262\344\270\272\350\277\233\345\237\272\345\217\230\351\207\217</span>&nbsp;&nbsp;&nbsp;<span style=\" color:#ff0000;\">\347\272\242\350\211\262\344\270\272\345\207\272\345\237\272\345\217\230\351\207\217</span>&nbsp;&nbsp;&nbsp;<span style=\" color:#0000ff;\">\350\223\235\350\211\262\344\270\272\344\272\272\345\267\245\345\217\230\351\207\217</span></p></body></html>", nullptr));
        btnPrev->setText(QCoreApplication::translate("NSMWindow", "\344\270\212\344\270\200\346\255\245", nullptr));
        btnNext->setText(QCoreApplication::translate("NSMWindow", "\344\270\213\344\270\200\346\255\245", nullptr));
        menu_F->setTitle(QCoreApplication::translate("NSMWindow", "\346\226\207\344\273\266(&F)", nullptr));
    } // retranslateUi

};

namespace Ui {
    class NSMWindow: public Ui_NSMWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_NSMWINDOW_H
