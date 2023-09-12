#ifndef COMPONENT_1_H
#define COMPONENT_1_H

#include <QDialog>
#include <QWidget>
#include <QMouseEvent>
#include <QPainter>

#define CM_TITLE_HEIGHT 30					//原件标题栏高度

#define DEFAULT_BORDER_WIDTH 5				//边界宽度
#define DEFAULT_MAGNETIC_OFFSET 20			//磁性效力范围
#define DEFAULT_ERROR_RANGE 6				//脱离磁力误差范围
#define DEFAULT_TOOLBAR_WIDTH 80			//所在容器ToolBar宽度
#define DEFAULT_STATUS_HEIGHT 29			//所在容器StatusBar高度

namespace Ui {
class Component;
}

class Component : public QWidget
{
    Q_OBJECT

public:
    explicit Component(const int nComponentId, QWidget *parent = 0);
    ~Component();

	/* 设置原件的边界宽度 */
	void setBorderWidth(int nBorderWidth);

	/* 设置磁力效应影响范围  */
	void setMagneticOffset(int nMagneticOffset);

	/* 设置磁力效应脱离允许误差范围  */
	void setErrorRange(int nErrorRange);

	/* 设置所在容器ToolBar宽度  */
	void setToolBarWidth(int nToolBarWidth);

	/* 设置所在容器StatusBar宽度  */
	void setStatusBarHeight(int nStatusBarHeight);

signals:
	/* 原件关闭信号 */
	void onComponentClose(int nMyComponentId);

protected:
	bool event(QEvent* evt);

	void mousePressEvent(QMouseEvent *event);
	void mouseMoveEvent(QMouseEvent *event);
	void mouseReleaseEvent(QMouseEvent *event);

	void paintEvent(QPaintEvent *event);

	/* 设置鼠标resize时的样式 */
	void setMouseResizeCursor(const QPoint& pt);

private:
	/* 初始化容器 */
	inline void initComponent();
	/* 更新鼠标按下需求的相关数据 */
	inline void updateMousePressedData(QMouseEvent *event);
	/* 更新数据-按在边框 */
	inline void updateDataOnBorder(QMouseEvent *event);
	/* 更新数据-按在标题栏 */
	inline void updateDataOnTitleBar(QMouseEvent *event);
	/* 更新鼠标限制移动的区域 */
	inline void updateMouseLeftLimit();
	/* 限制鼠标移动区域 */
	inline void limitMouseArea();
	/* 重置拖拽相关 */
	inline void resetDragData(QMouseEvent *event);
	/* 最小边界的判断与处理---原件 */
	inline void minBoundaryToComponent(const QRect &parentRect, const QRect &bechmarkRect, const QRect &targetRect, QMouseEvent *event);
	/* 最小边界的判断与处理---容器 */
	inline void minBoundaryToVessel(const QRect &bechmarkRect, const QRect &targetRect, QMouseEvent *event);
	/* 判断鼠标按下时坐标是否在边框 */
	inline bool mouseOnWinBorder(const QPoint& pt);
	/* 改变原件大小 */
	inline void changeSize(QMouseEvent* evt);

	/* 计算改变的最小宽和高 */
	inline QSize calcMinSize(const QSize &sizeHint, const QSize &minSizeHint,
		const QSize &minSize, const QSize &maxSize,
		const QSizePolicy &sizePolicy);

	inline QRect leftBorder() const;
	inline QRect rightBorder() const;
	inline QRect topBorder() const;
	inline QRect bottomBorder() const;

private slots:
	/* 关闭原件按钮槽 */
	void on_btnClose_clicked();

private:
    Ui::Component *ui;

	const int m_nMyComponentId = 0;
	int m_nTitleHeight = CM_TITLE_HEIGHT;					//原件标题栏高度
	int m_nBorderWidth = DEFAULT_BORDER_WIDTH;				//边界宽度
	int m_nMagneticOffset = DEFAULT_MAGNETIC_OFFSET;		//磁性效力范围
	int m_nErrorRange = DEFAULT_ERROR_RANGE;				//脱离磁力误差范围
	int m_nToolBarWidth = DEFAULT_TOOLBAR_WIDTH;			//所在容器ToolBar宽度
	int m_nStatusBarHeight = DEFAULT_STATUS_HEIGHT;			//所在容器StatusBar高度
	int m_nMoveCount = 0;

	bool m_bMousePressedInTitle = false;
	bool m_bMousePressedInBorder = false;

	bool m_bHorRelatived = false;
	bool m_bVerRelatived = false;

	bool m_bHorStick_s = false;
	bool m_bVerStick_s = false;
	bool m_bHorStick_c = false;
	bool m_bVerStick_c = false;

	QPoint m_pDragPosition;					// 在窗口边上鼠标按下时的屏幕坐标位置
	QPoint m_pDragMoveOffset;				// 记录鼠标按下时与窗口右下角的偏移坐标
	QPoint m_qpMousePressPoint;				// 鼠标按下时的位置
	QPoint m_qpDiffPos;						// 鼠标移动时产生的偏移向量

	QRect m_rParentRect;					// 所在容器geometry
	QRect m_rMouseLimitRect;				// 鼠标拖拽时限制移动的区域

	QList<Component*> m_lComponent;			// 获取父容器所有的原件视图列表

	enum MousePosition
	{
		mpCenter, mpLeft, mpTop, mpRight, mpBottom,
		mpLeftTop, mpLeftBottom, mpRightTop, mpRightBottom
	} m_eMousePos = { mpCenter };
};

#endif // COMPONENT_1_H
