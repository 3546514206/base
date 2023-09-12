#include "logincode.h"

LoginCode::LoginCode(QWidget *parent)
	: QWidget(parent)
{
	ui.setupUi(this);
	setWindowFlags(Qt::FramelessWindowHint);
	m_pCodeArea = new CodeArea(this);
	ui.CodeLayout->addWidget(m_pCodeArea);
}

LoginCode::~LoginCode()
{
	if (m_pCodeArea)
	{
		delete m_pCodeArea;
		m_pCodeArea = nullptr;
	}
	if (m_pMsgBox)
	{
		delete m_pMsgBox;
		m_pMsgBox = nullptr;
	}
}

void LoginCode::on_tbnReplace_clicked()
{
	m_pCodeArea->replaceCodePic();
}

void LoginCode::on_btnLogin_clicked()
{
	if (m_pCodeArea->checkCode(ui.edCode->text()))
	{
		m_pMsgBox = new QMessageBox(QMessageBox::Information, QStringLiteral("提示"), QStringLiteral("登陆成功"),
			QMessageBox::Ok, this);
	}
	else{
		m_pMsgBox = new QMessageBox(QMessageBox::Information, QStringLiteral("提示"), QStringLiteral("验证码不正确"),
			QMessageBox::Ok, this);
	}
	m_pMsgBox->exec();
}

void LoginCode::on_btnClose_clicked()
{
	close();
}
