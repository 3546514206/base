#ifndef LOGIN_H
#define LOGIN_H

#include <QDialog>
#include <QLineEdit>
#include <QLabel>
#include <QPushButton>
#include <QVBoxLayout>
#include <QHBoxLayout>
#include <QSpacerItem>
#include <QMessageBox>
#include <Qstring>
#include "mainform.h"

class Login : public QDialog
{
    Q_OBJECT

public:
    Login(QWidget *parent = 0);
    ~Login();
	
private slots:
	void onAckBtn();
	void onCleanBtn();
	void onQuitBtn();
	
private:
    QString user;
    QString pwd;

    QLabel *userLabel;
    QLineEdit *userEdit;
    QLabel *pwdLabel;
    QLineEdit *pwdEdit;

    QPushButton *ackBtn;
    QPushButton *cleanBtn;
    QPushButton *quitBtn;

    QHBoxLayout *hLayout1;
    QHBoxLayout *hLayout2;
    QHBoxLayout *hLayout3;
    QVBoxLayout *vLayout;

    QSpacerItem * hSpacer1;
    QSpacerItem * hSpacer2;
};

#endif // LOGIN_H
