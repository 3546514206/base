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

	/* 获取当前容器内的原件列表 */
	inline QList<Component*> getComponentList()
	{
		return m_lComponent;
	}

protected:
	void mousePressEvent(QMouseEvent *event);
	void mouseMoveEvent(QMouseEvent *event);

private:
	/* 记录容器内原件的位置 */
	inline void updateComponentPos();

private slots:
	/* 新建原件按钮槽 */
	void on_btnNewComponent_clicked();

	/* 隐藏工具栏 */
	void on_btnHideToolBar_clicked();

	/* 唤出工具栏 */
	void on_btnCallToolBar_clicked();

	/* 关闭原件响应槽 */
	void doComponentClose(int nMyComponentId);

private:
	Ui::LComponentClass ui;

	QList<Component*> m_lComponent;
	QList<QPoint*> m_lComponentPos;

	QPoint m_qpMousePressPoint;

	int m_nCurrentComponentId = 0;
};

#endif // LCOMPONENT_H
