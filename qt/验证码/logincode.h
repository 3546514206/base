#ifndef LOGINCODE_H
#define LOGINCODE_H

#include <QtWidgets/QWidget>
#include "ui_logincode.h"
#include "codearea.h"
#include <QMessageBox>

class LoginCode : public QWidget
{
	Q_OBJECT

public:
	LoginCode(QWidget *parent = 0);
	~LoginCode();

private slots:
	void on_tbnReplace_clicked();
	void on_btnLogin_clicked();
	void on_btnClose_clicked();

private:
	Ui::LoginCodeClass ui;

	CodeArea *m_pCodeArea = nullptr;		// ��֤��������
	QMessageBox *m_pMsgBox = nullptr;		// ��ʾ����
};

#endif // LOGINCODE_H
