/********************************************************************************
** Form generated from reading UI file 'loadinfodialog.ui'
**
** Created by: Qt User Interface Compiler version 6.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LOADINFODIALOG_H
#define UI_LOADINFODIALOG_H

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

class Ui_LoadInfoDialog
{
public:
    QGridLayout *gridLayout;
    QHBoxLayout *horizontalLayout;
    QLabel *label;
    QLineEdit *tbVertex;
    QPushButton *btnVertex;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label_2;
    QLineEdit *tbVexel;
    QPushButton *btnVexel;
    QSpacerItem *horizontalSpacer;
    QPushButton *btnOK;

    void setupUi(QDialog *LoadInfoDialog)
    {
        if (LoadInfoDialog->objectName().isEmpty())
            LoadInfoDialog->setObjectName("LoadInfoDialog");
        LoadInfoDialog->resize(429, 159);
        gridLayout = new QGridLayout(LoadInfoDialog);
        gridLayout->setObjectName("gridLayout");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        label = new QLabel(LoadInfoDialog);
        label->setObjectName("label");

        horizontalLayout->addWidget(label);

        tbVertex = new QLineEdit(LoadInfoDialog);
        tbVertex->setObjectName("tbVertex");

        horizontalLayout->addWidget(tbVertex);

        btnVertex = new QPushButton(LoadInfoDialog);
        btnVertex->setObjectName("btnVertex");
        btnVertex->setMaximumSize(QSize(35, 16777215));

        horizontalLayout->addWidget(btnVertex);


        gridLayout->addLayout(horizontalLayout, 0, 0, 1, 2);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        label_2 = new QLabel(LoadInfoDialog);
        label_2->setObjectName("label_2");

        horizontalLayout_2->addWidget(label_2);

        tbVexel = new QLineEdit(LoadInfoDialog);
        tbVexel->setObjectName("tbVexel");

        horizontalLayout_2->addWidget(tbVexel);

        btnVexel = new QPushButton(LoadInfoDialog);
        btnVexel->setObjectName("btnVexel");
        btnVexel->setMaximumSize(QSize(35, 16777215));

        horizontalLayout_2->addWidget(btnVexel);


        gridLayout->addLayout(horizontalLayout_2, 1, 0, 1, 2);

        horizontalSpacer = new QSpacerItem(327, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        gridLayout->addItem(horizontalSpacer, 2, 0, 1, 1);

        btnOK = new QPushButton(LoadInfoDialog);
        btnOK->setObjectName("btnOK");

        gridLayout->addWidget(btnOK, 2, 1, 1, 1);


        retranslateUi(LoadInfoDialog);

        QMetaObject::connectSlotsByName(LoadInfoDialog);
    } // setupUi

    void retranslateUi(QDialog *LoadInfoDialog)
    {
        LoadInfoDialog->setWindowTitle(QCoreApplication::translate("LoadInfoDialog", "\345\257\274\345\205\245", nullptr));
        label->setText(QCoreApplication::translate("LoadInfoDialog", "\351\241\266\347\202\271\344\277\241\346\201\257", nullptr));
        btnVertex->setText(QCoreApplication::translate("LoadInfoDialog", "...", nullptr));
        label_2->setText(QCoreApplication::translate("LoadInfoDialog", "\344\275\223\345\205\203\344\277\241\346\201\257", nullptr));
        btnVexel->setText(QCoreApplication::translate("LoadInfoDialog", "...", nullptr));
        btnOK->setText(QCoreApplication::translate("LoadInfoDialog", "\347\241\256\345\256\232", nullptr));
    } // retranslateUi

};

namespace Ui {
    class LoadInfoDialog: public Ui_LoadInfoDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LOADINFODIALOG_H
