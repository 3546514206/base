/********************************************************************************
** Form generated from reading UI file 'frmemailtool.ui'
**
** Created by: Qt User Interface Compiler version 6.5.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_FRMEMAILTOOL_H
#define UI_FRMEMAILTOOL_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_frmEmailTool
{
public:
    QVBoxLayout *verticalLayout;
    QGridLayout *gridLayout;
    QPushButton *btnSend;
    QComboBox *cboxPort;
    QCheckBox *ckSSL;
    QLabel *labPort;
    QPushButton *btnSelect;
    QLineEdit *txtReceiverAddr;
    QLineEdit *txtFileName;
    QLabel *labTitle;
    QLineEdit *txtTitle;
    QComboBox *cboxServer;
    QLabel *labReceiverAddr;
    QLabel *labFileName;
    QLabel *labServer;
    QLabel *labSenderAddr;
    QLineEdit *txtSenderAddr;
    QLabel *labSenderPwd;
    QLineEdit *txtSenderPwd;
    QTextBrowser *txtContent;

    void setupUi(QWidget *frmEmailTool)
    {
        if (frmEmailTool->objectName().isEmpty())
            frmEmailTool->setObjectName("frmEmailTool");
        frmEmailTool->resize(764, 578);
        verticalLayout = new QVBoxLayout(frmEmailTool);
        verticalLayout->setObjectName("verticalLayout");
        gridLayout = new QGridLayout();
        gridLayout->setObjectName("gridLayout");
        btnSend = new QPushButton(frmEmailTool);
        btnSend->setObjectName("btnSend");
        btnSend->setCursor(QCursor(Qt::PointingHandCursor));

        gridLayout->addWidget(btnSend, 2, 4, 1, 1);

        cboxPort = new QComboBox(frmEmailTool);
        cboxPort->addItem(QString());
        cboxPort->addItem(QString());
        cboxPort->addItem(QString());
        cboxPort->setObjectName("cboxPort");

        gridLayout->addWidget(cboxPort, 1, 3, 1, 1);

        ckSSL = new QCheckBox(frmEmailTool);
        ckSSL->setObjectName("ckSSL");

        gridLayout->addWidget(ckSSL, 1, 4, 1, 1);

        labPort = new QLabel(frmEmailTool);
        labPort->setObjectName("labPort");

        gridLayout->addWidget(labPort, 1, 2, 1, 1);

        btnSelect = new QPushButton(frmEmailTool);
        btnSelect->setObjectName("btnSelect");
        btnSelect->setCursor(QCursor(Qt::PointingHandCursor));

        gridLayout->addWidget(btnSelect, 3, 4, 1, 1);

        txtReceiverAddr = new QLineEdit(frmEmailTool);
        txtReceiverAddr->setObjectName("txtReceiverAddr");

        gridLayout->addWidget(txtReceiverAddr, 2, 3, 1, 1);

        txtFileName = new QLineEdit(frmEmailTool);
        txtFileName->setObjectName("txtFileName");

        gridLayout->addWidget(txtFileName, 3, 1, 1, 3);

        labTitle = new QLabel(frmEmailTool);
        labTitle->setObjectName("labTitle");

        gridLayout->addWidget(labTitle, 2, 0, 1, 1);

        txtTitle = new QLineEdit(frmEmailTool);
        txtTitle->setObjectName("txtTitle");

        gridLayout->addWidget(txtTitle, 2, 1, 1, 1);

        cboxServer = new QComboBox(frmEmailTool);
        cboxServer->addItem(QString());
        cboxServer->addItem(QString());
        cboxServer->addItem(QString());
        cboxServer->addItem(QString());
        cboxServer->addItem(QString());
        cboxServer->addItem(QString());
        cboxServer->addItem(QString());
        cboxServer->setObjectName("cboxServer");

        gridLayout->addWidget(cboxServer, 1, 1, 1, 1);

        labReceiverAddr = new QLabel(frmEmailTool);
        labReceiverAddr->setObjectName("labReceiverAddr");

        gridLayout->addWidget(labReceiverAddr, 2, 2, 1, 1);

        labFileName = new QLabel(frmEmailTool);
        labFileName->setObjectName("labFileName");

        gridLayout->addWidget(labFileName, 3, 0, 1, 1);

        labServer = new QLabel(frmEmailTool);
        labServer->setObjectName("labServer");

        gridLayout->addWidget(labServer, 1, 0, 1, 1);

        labSenderAddr = new QLabel(frmEmailTool);
        labSenderAddr->setObjectName("labSenderAddr");

        gridLayout->addWidget(labSenderAddr, 0, 0, 1, 1);

        txtSenderAddr = new QLineEdit(frmEmailTool);
        txtSenderAddr->setObjectName("txtSenderAddr");

        gridLayout->addWidget(txtSenderAddr, 0, 1, 1, 1);

        labSenderPwd = new QLabel(frmEmailTool);
        labSenderPwd->setObjectName("labSenderPwd");

        gridLayout->addWidget(labSenderPwd, 0, 2, 1, 1);

        txtSenderPwd = new QLineEdit(frmEmailTool);
        txtSenderPwd->setObjectName("txtSenderPwd");
        txtSenderPwd->setEchoMode(QLineEdit::Password);

        gridLayout->addWidget(txtSenderPwd, 0, 3, 1, 1);


        verticalLayout->addLayout(gridLayout);

        txtContent = new QTextBrowser(frmEmailTool);
        txtContent->setObjectName("txtContent");
        txtContent->setReadOnly(false);

        verticalLayout->addWidget(txtContent);

        QWidget::setTabOrder(txtSenderAddr, txtSenderPwd);
        QWidget::setTabOrder(txtSenderPwd, cboxServer);
        QWidget::setTabOrder(cboxServer, cboxPort);
        QWidget::setTabOrder(cboxPort, ckSSL);
        QWidget::setTabOrder(ckSSL, txtTitle);
        QWidget::setTabOrder(txtTitle, txtReceiverAddr);
        QWidget::setTabOrder(txtReceiverAddr, btnSend);
        QWidget::setTabOrder(btnSend, txtFileName);
        QWidget::setTabOrder(txtFileName, btnSelect);
        QWidget::setTabOrder(btnSelect, txtContent);

        retranslateUi(frmEmailTool);

        QMetaObject::connectSlotsByName(frmEmailTool);
    } // setupUi

    void retranslateUi(QWidget *frmEmailTool)
    {
        frmEmailTool->setWindowTitle(QCoreApplication::translate("frmEmailTool", "Form", nullptr));
        btnSend->setText(QCoreApplication::translate("frmEmailTool", "\345\217\221\351\200\201", nullptr));
        cboxPort->setItemText(0, QCoreApplication::translate("frmEmailTool", "25", nullptr));
        cboxPort->setItemText(1, QCoreApplication::translate("frmEmailTool", "465", nullptr));
        cboxPort->setItemText(2, QCoreApplication::translate("frmEmailTool", "587", nullptr));

        ckSSL->setText(QCoreApplication::translate("frmEmailTool", "SSL", nullptr));
        labPort->setText(QCoreApplication::translate("frmEmailTool", "\346\234\215\345\212\241\347\253\257\345\217\243", nullptr));
        btnSelect->setText(QCoreApplication::translate("frmEmailTool", "\346\265\217\350\247\210", nullptr));
        txtReceiverAddr->setText(QCoreApplication::translate("frmEmailTool", "feiyangqingyun@163.com;517216493@qq.com", nullptr));
        labTitle->setText(QCoreApplication::translate("frmEmailTool", "\351\202\256\344\273\266\346\240\207\351\242\230", nullptr));
        txtTitle->setText(QCoreApplication::translate("frmEmailTool", "\346\265\213\350\257\225\351\202\256\344\273\266", nullptr));
        cboxServer->setItemText(0, QCoreApplication::translate("frmEmailTool", "smtp.163.com", nullptr));
        cboxServer->setItemText(1, QCoreApplication::translate("frmEmailTool", "smtp.126.com", nullptr));
        cboxServer->setItemText(2, QCoreApplication::translate("frmEmailTool", "smtp.qq.com", nullptr));
        cboxServer->setItemText(3, QCoreApplication::translate("frmEmailTool", "smt.sina.com", nullptr));
        cboxServer->setItemText(4, QCoreApplication::translate("frmEmailTool", "smtp.sohu.com", nullptr));
        cboxServer->setItemText(5, QCoreApplication::translate("frmEmailTool", "smtp.139.com", nullptr));
        cboxServer->setItemText(6, QCoreApplication::translate("frmEmailTool", "smtp.189.com", nullptr));

        labReceiverAddr->setText(QCoreApplication::translate("frmEmailTool", "\346\224\266\344\273\266\345\234\260\345\235\200", nullptr));
        labFileName->setText(QCoreApplication::translate("frmEmailTool", "\351\200\211\346\213\251\351\231\204\344\273\266", nullptr));
        labServer->setText(QCoreApplication::translate("frmEmailTool", "\346\234\215\345\212\241\345\234\260\345\235\200", nullptr));
        labSenderAddr->setText(QCoreApplication::translate("frmEmailTool", "\347\224\250\346\210\267\345\220\215\347\247\260", nullptr));
        txtSenderAddr->setText(QCoreApplication::translate("frmEmailTool", "feiyangqingyun@126.com", nullptr));
        labSenderPwd->setText(QCoreApplication::translate("frmEmailTool", "\347\224\250\346\210\267\345\257\206\347\240\201", nullptr));
        txtSenderPwd->setText(QString());
        txtContent->setHtml(QCoreApplication::translate("frmEmailTool", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'SimSun'; font-size:9.07563pt; font-weight:400; font-style:normal;\">\n"
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">1</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\346\234\200\347\237\255\347\232\204\347\210\261\346\203\205\345\223\262\347\220\206\345\260\217\350\257\264\357\274\232\342\200\234</span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">\344\275\240\345\272\224\350\257\245\345\253\201\347\273\231\346\210\221\345\225\246\357\274\237</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\342\200\235\302\240\342\200\234"
                        "\302\240</span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">\344\270\215</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\342\200\235\302\240</span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">\344\272\216\346\230\257\344\273\226\344\277\251\345\217\210\347\273\247\347\273\255\345\271\270\347\246\217\345\234\260\347\224\237\346\264\273\345\234\250\344\270\200\350\265\267</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\357\274\201<br /></span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">2</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\350\277\221\345\271\264\346\235\245\344\270\255\345\233\275\346\234\200\347\262\276\345\275\251\347\232\204\345\206\231\345\256\236\345\260\217\350\257\264\357\274\214\345\205\250\346\226\207\345\205\253\344\270\252\345\255\227\357\274\232</span><span style=\" font-family:'Times New Roman'; font-size:16pt; font-weight:"
                        "600; font-style:italic; color:#ff007f;\">\346\255\244\345\234\260\351\222\261\345\244\232\344\272\272\345\202\273\351\200\237\346\235\245</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\302\240 \346\215\256\350\257\264\346\230\257\345\217\221\350\207\252\346\235\255\345\267\236\345\270\202\345\256\235\347\237\263\345\261\261\344\270\213\344\270\200\345\207\272\347\247\237\346\210\277\347\232\204\346\261\207\346\254\276\345\215\225\344\270\212\347\232\204\347\256\200\347\237\255\351\231\204\350\250\200\357\274\214\346\230\257\350\257\245\346\214\211\346\221\251\345\245\263\347\273\231\345\256\266\344\271\241\345\246\271\345\246\271\346\261\207\302\240\346\254\276\346\227\266\351\232\217\346\211\213\346\266\202\351\270\246\347\232\204\357\274\214\344\273\244\346\227\240\346\225\260\344\270\223\344\270\232\344\275\234\345\256\266\346\261\227\351\242\234\357\274\201<br /></span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">3</span><span style=\" font-family:'Times "
                        "New Roman'; font-size:11pt;\">\343\200\201\346\234\200\347\237\255\347\232\204\345\271\275\351\273\230\345\260\217\350\257\264\302\240\343\200\212\345\244\234\343\200\213\302\240\347\224\267\357\274\232\347\226\274\344\271\210\357\274\237\345\245\263\357\274\232\346\201\251\357\274\201\347\224\267\357\274\232\347\256\227\344\272\206\357\274\237\345\245\263\357\274\232\345\210\253\357\274\201</span></p>\n"
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">4</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\346\234\200\347\237\255\347\232\204\350\215\222\350\257\236\345\260\217\350\257\264\357\274\232\346\234\211\344\270\200\344\270\252\351\235\242\345\214\205\350\265\260\345\234\250\350\241\227\344\270\212\357\274\214\345\256\203\350\247\211\345\276\227\350\207\252\345\267\261\345\276\210\351\245\277\357\274\214\345\260\261\346\212\212\350"
                        "\207\252\345\267\261\345\220\203\344\272\206\343\200\202<br /></span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">5</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\344\270\226\347\225\214\346\234\200\347\237\255\350\250\200\346\203\205\345\260\217\350\257\264\357\274\232\344\273\226\346\255\273\347\232\204\351\202\243\345\244\251\357\274\214\345\255\251\345\255\220\345\207\272\347\224\237\344\272\206\343\200\202<br /></span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">6</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\344\270\226\347\225\214\346\234\200\347\237\255\346\255\246\344\276\240\345\260\217\350\257\264\357\274\232</span><span style=\" font-family:'Times New Roman'; font-size:11pt; font-weight:600; color:#00aa00;\">\351\253\230\346\211\213\350\242\253\350\261\206\350\205\220\347\240\270\346\255\273\344\272\206</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\""
                        ">\343\200\202<br /></span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">7</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\344\270\226\347\225\214\346\234\200\347\237\255\347\247\221\345\271\273\345\260\217\350\257\264\357\274\232\346\234\200\345\220\216\344\270\200\344\270\252\345\234\260\347\220\203\344\272\272\345\235\220\345\234\250\345\256\266\351\207\214\357\274\214\347\252\201\347\204\266\345\223\215\350\265\267\344\272\206\346\225\262\351\227\250\345\243\260\343\200\202<br /></span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">8</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\344\270\226\347\225\214\346\234\200\347\237\255\346\202\254\347\226\221\345\260\217\350\257\264\357\274\232\347\224\237\357\274\214\346\255\273\357\274\214\347\224\237\343\200\202<br /></span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">9</span><span style=\" font-family:'Times New "
                        "Roman'; font-size:11pt;\">\343\200\201\344\270\226\347\225\214\346\234\200\347\237\255\346\216\250\347\220\206\345\260\217\350\257\264\357\274\232\344\273\226\346\255\273\344\272\206\357\274\214\344\270\200\345\256\232\346\233\276\347\273\217\346\264\273\350\277\207\343\200\202\302\240<br />1</span><span style=\" font-family:'\345\256\213\344\275\223'; font-size:11pt;\">0</span><span style=\" font-family:'Times New Roman'; font-size:11pt;\">\343\200\201\344\270\226\347\225\214\346\234\200\347\237\255\346\201\220\346\200\226\345\260\217\350\257\264\357\274\232\346\203\212\351\206\222\357\274\214\350\272\253\350\276\271\350\272\272\347\235\200\350\207\252\345\267\261\347\232\204\345\260\270\344\275\223\343\200\202</span></p></body></html>", nullptr));
    } // retranslateUi

};

namespace Ui {
    class frmEmailTool: public Ui_frmEmailTool {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_FRMEMAILTOOL_H
