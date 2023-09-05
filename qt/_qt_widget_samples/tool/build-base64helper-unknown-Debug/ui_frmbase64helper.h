/********************************************************************************
** Form generated from reading UI file 'frmbase64helper.ui'
**
** Created by: Qt User Interface Compiler version 6.5.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_FRMBASE64HELPER_H
#define UI_FRMBASE64HELPER_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_frmBase64Helper
{
public:
    QGridLayout *gridLayout;
    QLineEdit *txtText;
    QPushButton *btnClear;
    QLabel *labImage;
    QPushButton *btnImageToBase64;
    QPushButton *btnBase64ToText;
    QPushButton *btnOpen;
    QLineEdit *txtFile;
    QTextEdit *txtBase64;
    QPushButton *btnBase64ToImage;
    QPushButton *btnTextToBase64;
    QLabel *labInfo;

    void setupUi(QWidget *frmBase64Helper)
    {
        if (frmBase64Helper->objectName().isEmpty())
            frmBase64Helper->setObjectName("frmBase64Helper");
        frmBase64Helper->resize(800, 600);
        gridLayout = new QGridLayout(frmBase64Helper);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName("gridLayout");
        txtText = new QLineEdit(frmBase64Helper);
        txtText->setObjectName("txtText");

        gridLayout->addWidget(txtText, 1, 0, 1, 1);

        btnClear = new QPushButton(frmBase64Helper);
        btnClear->setObjectName("btnClear");
        btnClear->setMinimumSize(QSize(120, 0));

        gridLayout->addWidget(btnClear, 1, 1, 1, 1);

        labImage = new QLabel(frmBase64Helper);
        labImage->setObjectName("labImage");
        QSizePolicy sizePolicy(QSizePolicy::Preferred, QSizePolicy::Expanding);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(labImage->sizePolicy().hasHeightForWidth());
        labImage->setSizePolicy(sizePolicy);
        labImage->setFrameShape(QFrame::Box);
        labImage->setFrameShadow(QFrame::Sunken);
        labImage->setAlignment(Qt::AlignCenter);

        gridLayout->addWidget(labImage, 3, 0, 1, 4);

        btnImageToBase64 = new QPushButton(frmBase64Helper);
        btnImageToBase64->setObjectName("btnImageToBase64");
        btnImageToBase64->setMinimumSize(QSize(120, 0));

        gridLayout->addWidget(btnImageToBase64, 0, 2, 1, 1);

        btnBase64ToText = new QPushButton(frmBase64Helper);
        btnBase64ToText->setObjectName("btnBase64ToText");
        btnBase64ToText->setMinimumSize(QSize(120, 0));

        gridLayout->addWidget(btnBase64ToText, 1, 3, 1, 1);

        btnOpen = new QPushButton(frmBase64Helper);
        btnOpen->setObjectName("btnOpen");
        btnOpen->setMinimumSize(QSize(120, 0));

        gridLayout->addWidget(btnOpen, 0, 1, 1, 1);

        txtFile = new QLineEdit(frmBase64Helper);
        txtFile->setObjectName("txtFile");

        gridLayout->addWidget(txtFile, 0, 0, 1, 1);

        txtBase64 = new QTextEdit(frmBase64Helper);
        txtBase64->setObjectName("txtBase64");

        gridLayout->addWidget(txtBase64, 4, 0, 1, 4);

        btnBase64ToImage = new QPushButton(frmBase64Helper);
        btnBase64ToImage->setObjectName("btnBase64ToImage");
        btnBase64ToImage->setMinimumSize(QSize(120, 0));

        gridLayout->addWidget(btnBase64ToImage, 1, 2, 1, 1);

        btnTextToBase64 = new QPushButton(frmBase64Helper);
        btnTextToBase64->setObjectName("btnTextToBase64");
        btnTextToBase64->setMinimumSize(QSize(120, 0));

        gridLayout->addWidget(btnTextToBase64, 0, 3, 1, 1);

        labInfo = new QLabel(frmBase64Helper);
        labInfo->setObjectName("labInfo");
        labInfo->setFrameShape(QFrame::Box);
        labInfo->setFrameShadow(QFrame::Sunken);

        gridLayout->addWidget(labInfo, 2, 0, 1, 1);

        QWidget::setTabOrder(txtFile, txtText);
        QWidget::setTabOrder(txtText, btnOpen);
        QWidget::setTabOrder(btnOpen, btnClear);
        QWidget::setTabOrder(btnClear, btnImageToBase64);
        QWidget::setTabOrder(btnImageToBase64, btnBase64ToImage);
        QWidget::setTabOrder(btnBase64ToImage, btnTextToBase64);
        QWidget::setTabOrder(btnTextToBase64, btnBase64ToText);
        QWidget::setTabOrder(btnBase64ToText, txtBase64);

        retranslateUi(frmBase64Helper);

        QMetaObject::connectSlotsByName(frmBase64Helper);
    } // setupUi

    void retranslateUi(QWidget *frmBase64Helper)
    {
        frmBase64Helper->setWindowTitle(QCoreApplication::translate("frmBase64Helper", "Widget", nullptr));
        txtText->setText(QString());
        btnClear->setText(QCoreApplication::translate("frmBase64Helper", "\346\270\205\347\251\272\346\225\260\346\215\256", nullptr));
        labImage->setText(QString());
        btnImageToBase64->setText(QCoreApplication::translate("frmBase64Helper", "\345\233\276\347\211\207\350\275\254base64", nullptr));
        btnBase64ToText->setText(QCoreApplication::translate("frmBase64Helper", "base64\350\275\254\346\226\207\345\255\227", nullptr));
        btnOpen->setText(QCoreApplication::translate("frmBase64Helper", "\346\211\223\345\274\200\346\226\207\344\273\266", nullptr));
        txtFile->setText(QString());
        btnBase64ToImage->setText(QCoreApplication::translate("frmBase64Helper", "base64\350\275\254\345\233\276\347\211\207", nullptr));
        btnTextToBase64->setText(QCoreApplication::translate("frmBase64Helper", "\346\226\207\345\255\227\350\275\254base64", nullptr));
        labInfo->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class frmBase64Helper: public Ui_frmBase64Helper {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_FRMBASE64HELPER_H
