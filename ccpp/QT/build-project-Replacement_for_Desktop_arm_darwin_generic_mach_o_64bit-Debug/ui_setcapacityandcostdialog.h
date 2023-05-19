/********************************************************************************
** Form generated from reading UI file 'setcapacityandcostdialog.ui'
**
** Created by: Qt User Interface Compiler version 6.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SETCAPACITYANDCOSTDIALOG_H
#define UI_SETCAPACITYANDCOSTDIALOG_H

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

class Ui_SetCapacityAndCostDialog
{
public:
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *tbCost;
    QLabel *label_3;
    QLabel *label_2;
    QLineEdit *tbCapacity;
    QLabel *label_4;
    QHBoxLayout *horizontalLayout;
    QSpacerItem *horizontalSpacer;
    QPushButton *btnOK;

    void setupUi(QDialog *SetCapacityAndCostDialog)
    {
        if (SetCapacityAndCostDialog->objectName().isEmpty())
            SetCapacityAndCostDialog->setObjectName("SetCapacityAndCostDialog");
        SetCapacityAndCostDialog->setWindowModality(Qt::NonModal);
        SetCapacityAndCostDialog->resize(382, 124);
        SetCapacityAndCostDialog->setWindowOpacity(1.000000000000000);
        gridLayout = new QGridLayout(SetCapacityAndCostDialog);
        gridLayout->setObjectName("gridLayout");
        label = new QLabel(SetCapacityAndCostDialog);
        label->setObjectName("label");

        gridLayout->addWidget(label, 0, 0, 1, 1);

        tbCost = new QLineEdit(SetCapacityAndCostDialog);
        tbCost->setObjectName("tbCost");

        gridLayout->addWidget(tbCost, 0, 1, 1, 1);

        label_3 = new QLabel(SetCapacityAndCostDialog);
        label_3->setObjectName("label_3");

        gridLayout->addWidget(label_3, 0, 2, 1, 1);

        label_2 = new QLabel(SetCapacityAndCostDialog);
        label_2->setObjectName("label_2");

        gridLayout->addWidget(label_2, 1, 0, 1, 1);

        tbCapacity = new QLineEdit(SetCapacityAndCostDialog);
        tbCapacity->setObjectName("tbCapacity");

        gridLayout->addWidget(tbCapacity, 1, 1, 1, 1);

        label_4 = new QLabel(SetCapacityAndCostDialog);
        label_4->setObjectName("label_4");

        gridLayout->addWidget(label_4, 1, 2, 1, 1);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        btnOK = new QPushButton(SetCapacityAndCostDialog);
        btnOK->setObjectName("btnOK");

        horizontalLayout->addWidget(btnOK);


        gridLayout->addLayout(horizontalLayout, 2, 1, 1, 2);


        retranslateUi(SetCapacityAndCostDialog);

        QMetaObject::connectSlotsByName(SetCapacityAndCostDialog);
    } // setupUi

    void retranslateUi(QDialog *SetCapacityAndCostDialog)
    {
        SetCapacityAndCostDialog->setWindowTitle(QCoreApplication::translate("SetCapacityAndCostDialog", "\350\256\276\347\275\256\350\264\271\347\224\250\344\270\216\345\256\271\351\207\217", nullptr));
        label->setText(QCoreApplication::translate("SetCapacityAndCostDialog", "\350\264\271\347\224\250", nullptr));
        label_3->setText(QCoreApplication::translate("SetCapacityAndCostDialog", "(\347\251\272\345\210\231\345\210\240\351\231\244)", nullptr));
        label_2->setText(QCoreApplication::translate("SetCapacityAndCostDialog", "\345\256\271\351\207\217", nullptr));
        label_4->setText(QCoreApplication::translate("SetCapacityAndCostDialog", "(\347\251\272\345\210\231\344\270\272\342\210\236)", nullptr));
        btnOK->setText(QCoreApplication::translate("SetCapacityAndCostDialog", "\347\241\256\345\256\232", nullptr));
    } // retranslateUi

};

namespace Ui {
    class SetCapacityAndCostDialog: public Ui_SetCapacityAndCostDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SETCAPACITYANDCOSTDIALOG_H
