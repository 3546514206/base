/********************************************************************************
** Form generated from reading UI file 'launchdialog.ui'
**
** Created by: Qt User Interface Compiler version 6.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LAUNCHDIALOG_H
#define UI_LAUNCHDIALOG_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_LaunchDialog
{
public:
    QGridLayout *gridLayout;
    QPushButton *btnAbout;
    QSpacerItem *horizontalSpacer;
    QVBoxLayout *verticalLayout;
    QPushButton *btnShortestPath;
    QPushButton *btnNSM;

    void setupUi(QDialog *LaunchDialog)
    {
        if (LaunchDialog->objectName().isEmpty())
            LaunchDialog->setObjectName("LaunchDialog");
        LaunchDialog->resize(366, 300);
        LaunchDialog->setMinimumSize(QSize(366, 300));
        LaunchDialog->setMaximumSize(QSize(366, 300));
        LaunchDialog->setStyleSheet(QString::fromUtf8(""));
        gridLayout = new QGridLayout(LaunchDialog);
        gridLayout->setObjectName("gridLayout");
        btnAbout = new QPushButton(LaunchDialog);
        btnAbout->setObjectName("btnAbout");

        gridLayout->addWidget(btnAbout, 1, 1, 1, 1);

        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        gridLayout->addItem(horizontalSpacer, 1, 0, 1, 1);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        btnShortestPath = new QPushButton(LaunchDialog);
        btnShortestPath->setObjectName("btnShortestPath");
        btnShortestPath->setMinimumSize(QSize(0, 51));

        verticalLayout->addWidget(btnShortestPath);

        btnNSM = new QPushButton(LaunchDialog);
        btnNSM->setObjectName("btnNSM");
        btnNSM->setMinimumSize(QSize(0, 51));

        verticalLayout->addWidget(btnNSM);


        gridLayout->addLayout(verticalLayout, 0, 0, 1, 2);


        retranslateUi(LaunchDialog);

        QMetaObject::connectSlotsByName(LaunchDialog);
    } // setupUi

    void retranslateUi(QDialog *LaunchDialog)
    {
        LaunchDialog->setWindowTitle(QCoreApplication::translate("LaunchDialog", "\345\233\276\344\270\216\347\275\221\347\273\234\344\274\230\345\214\226", nullptr));
        btnAbout->setText(QCoreApplication::translate("LaunchDialog", "\345\205\263\344\272\216", nullptr));
        btnShortestPath->setText(QCoreApplication::translate("LaunchDialog", "\346\234\200\347\237\255\350\267\257\345\276\204", nullptr));
        btnNSM->setText(QCoreApplication::translate("LaunchDialog", "\347\275\221\347\273\234\345\215\225\347\272\257\345\275\242\346\263\225", nullptr));
    } // retranslateUi

};

namespace Ui {
    class LaunchDialog: public Ui_LaunchDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LAUNCHDIALOG_H
