/********************************************************************************
** Form generated from reading UI file 'setdemanddialog.ui'
**
** Created by: Qt User Interface Compiler version 6.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SETDEMANDDIALOG_H
#define UI_SETDEMANDDIALOG_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>

QT_BEGIN_NAMESPACE

class Ui_SetDemandDialog
{
public:
    QGridLayout *gridLayout;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label;
    QLineEdit *lineEdit;
    QHBoxLayout *horizontalLayout;
    QLabel *label_2;
    QPushButton *btnOK;

    void setupUi(QDialog *SetDemandDialog)
    {
        if (SetDemandDialog->objectName().isEmpty())
            SetDemandDialog->setObjectName("SetDemandDialog");
        SetDemandDialog->setWindowModality(Qt::WindowModal);
        SetDemandDialog->resize(380, 86);
        gridLayout = new QGridLayout(SetDemandDialog);
        gridLayout->setObjectName("gridLayout");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        label = new QLabel(SetDemandDialog);
        label->setObjectName("label");

        horizontalLayout_2->addWidget(label);

        lineEdit = new QLineEdit(SetDemandDialog);
        lineEdit->setObjectName("lineEdit");

        horizontalLayout_2->addWidget(lineEdit);


        gridLayout->addLayout(horizontalLayout_2, 0, 0, 1, 1);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        label_2 = new QLabel(SetDemandDialog);
        label_2->setObjectName("label_2");

        horizontalLayout->addWidget(label_2);

        btnOK = new QPushButton(SetDemandDialog);
        btnOK->setObjectName("btnOK");

        horizontalLayout->addWidget(btnOK);


        gridLayout->addLayout(horizontalLayout, 1, 0, 1, 1);


        retranslateUi(SetDemandDialog);

        QMetaObject::connectSlotsByName(SetDemandDialog);
    } // setupUi

    void retranslateUi(QDialog *SetDemandDialog)
    {
        SetDemandDialog->setWindowTitle(QCoreApplication::translate("SetDemandDialog", "\350\256\276\347\275\256\351\234\200\346\261\202", nullptr));
        label->setText(QCoreApplication::translate("SetDemandDialog", "\351\234\200\346\261\202", nullptr));
        label_2->setText(QCoreApplication::translate("SetDemandDialog", "\357\274\210\350\264\237\344\270\272\345\217\221\351\200\201 \346\255\243\344\270\272\346\216\245\346\224\266 0\346\210\226\347\251\272\344\270\272\344\270\255\350\275\254\357\274\211", nullptr));
        btnOK->setText(QCoreApplication::translate("SetDemandDialog", "\347\241\256\345\256\232", nullptr));
    } // retranslateUi

};

namespace Ui {
    class SetDemandDialog: public Ui_SetDemandDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SETDEMANDDIALOG_H
