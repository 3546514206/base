/********************************************************************************
** Form generated from reading UI file 'setdistancedialog.ui'
**
** Created by: Qt User Interface Compiler version 6.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SETDISTANCEDIALOG_H
#define UI_SETDISTANCEDIALOG_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>

QT_BEGIN_NAMESPACE

class Ui_SetDistanceDialog
{
public:
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *lineEdit;
    QHBoxLayout *horizontalLayout;
    QSpacerItem *horizontalSpacer;
    QLabel *label_2;
    QPushButton *pushButton;

    void setupUi(QDialog *SetDistanceDialog)
    {
        if (SetDistanceDialog->objectName().isEmpty())
            SetDistanceDialog->setObjectName("SetDistanceDialog");
        SetDistanceDialog->resize(308, 98);
        SetDistanceDialog->setMinimumSize(QSize(308, 98));
        SetDistanceDialog->setMaximumSize(QSize(308, 98));
        gridLayout = new QGridLayout(SetDistanceDialog);
        gridLayout->setObjectName("gridLayout");
        label = new QLabel(SetDistanceDialog);
        label->setObjectName("label");
        label->setMinimumSize(QSize(51, 0));

        gridLayout->addWidget(label, 0, 0, 1, 1);

        lineEdit = new QLineEdit(SetDistanceDialog);
        lineEdit->setObjectName("lineEdit");

        gridLayout->addWidget(lineEdit, 0, 1, 1, 1);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        label_2 = new QLabel(SetDistanceDialog);
        label_2->setObjectName("label_2");
        label_2->setMinimumSize(QSize(51, 0));

        horizontalLayout->addWidget(label_2);

        pushButton = new QPushButton(SetDistanceDialog);
        pushButton->setObjectName("pushButton");
        pushButton->setMaximumSize(QSize(141, 16777215));

        horizontalLayout->addWidget(pushButton);


        gridLayout->addLayout(horizontalLayout, 1, 1, 1, 1);


        retranslateUi(SetDistanceDialog);

        QMetaObject::connectSlotsByName(SetDistanceDialog);
    } // setupUi

    void retranslateUi(QDialog *SetDistanceDialog)
    {
        SetDistanceDialog->setWindowTitle(QCoreApplication::translate("SetDistanceDialog", "\350\256\276\347\275\256\350\267\235\347\246\273", nullptr));
        label->setText(QCoreApplication::translate("SetDistanceDialog", "\350\267\235\347\246\273", nullptr));
        label_2->setText(QCoreApplication::translate("SetDistanceDialog", "(\347\251\272\345\210\231\346\270\205\351\231\244\357\274\211", nullptr));
        pushButton->setText(QCoreApplication::translate("SetDistanceDialog", "\347\241\256\345\256\232", nullptr));
    } // retranslateUi

};

namespace Ui {
    class SetDistanceDialog: public Ui_SetDistanceDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SETDISTANCEDIALOG_H
