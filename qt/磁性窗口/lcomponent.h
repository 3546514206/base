#ifndef LCOMPONENT_H
#define LCOMPONENT_H

#include <QWidget>
#include "ui_lcomponent.h"
#include <QMouseEvent>
#include <QPoint>
#include "component.h"

class LComponent : public QWidget
{
	Q_OBJECT

public:
	LComponent(QWidget *parent = 0);
	~LComponent();

	/* ��ȡ��ǰ�����ڵ�ԭ���б� */
	inline QList<Component*> getComponentList()
	{
		return m_lComponent;
	}

protected:
	void mousePressEvent(QMouseEvent *event);
	void mouseMoveEvent(QMouseEvent *event);

private:
	/* ��¼������ԭ����λ�� */
	inline void updateComponentPos();

private slots:
	/* �½�ԭ����ť�� */
	void on_btnNewComponent_clicked();

	/* ���ع����� */
	void on_btnHideToolBar_clicked();

	/* ���������� */
	void on_btnCallToolBar_clicked();

	/* �ر�ԭ����Ӧ�� */
	void doComponentClose(int nMyComponentId);

private:
	Ui::LComponentClass ui;

	QList<Component*> m_lComponent;
	QList<QPoint*> m_lComponentPos;

	QPoint m_qpMousePressPoint;

	int m_nCurrentComponentId = 0;
};

#endif // LCOMPONENT_H
