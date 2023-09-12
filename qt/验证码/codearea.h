#ifndef RENDERAREA_H
#define RENDERAREA_H

#include <QList>
#include <QPainterPath>
#include <QRect>
#include <QWidget>

#define DEF_CODECOUNT 4					// 验证码默认位数

#define DEF_NOISYPOINTCOUNT 60			// 噪点数量
#define DEF_CONVERSEROTATE 10			// 转换角度范围
#define DEF_CONVERSESCALE 15			// 转换大小范围

class CodeArea : public QWidget
{
    Q_OBJECT

public:
    CodeArea(QWidget *parent = 0);
	~CodeArea();

	/* 更换验证码图片 */
	void replaceCodePic();

	/* 设置验证码位数 */
	void setCodeCount(int nCodeCount);

	/* 设置噪点数量 */
	void setNoisyPointCount(int nNoisyPointCount);

	/* 检验验证码 */
	bool checkCode(QString sCode);

protected:
    void paintEvent(QPaintEvent *event);

private:
	/* 初始化验证码范围 */
	inline void initCodeRange();
	/* 初始化验证码可用颜色列表 */
	inline void initCodeColorList();
	/* 更新验证码 */
	inline void updateLoginCode();
	/* 更新验证码图片 */
	inline void updateCodePic();
	/* 更新用于与用户输入的验证码做比较的码 */
	inline void updateCode();
	/* 绘制边缘虚线框 */
    inline void drawOutline(QPainter &painter);
	/* 绘制验证码 */
	inline void drawCode(QPainter &painter, int nCodeIndex);
	/* 绘制噪点 */
	inline void drawNoisyPoint(QPainter &painter);
	/* 做验证码形态转换 */
	inline void drawConversion(QPainter &painter);
	/* 设置验证码图片 */
	inline void setCodePic(const QList<QPainterPath *> &lCodePic);

private:
	QString m_sCode = "";						// 用于与用户输入的验证码做比较的码 
	QStringList m_slCodeRange;					// 验证码生成范围
	QStringList m_slLoginCode;					// 验证码列表，用于生成验证码图片
	QPainterPath *m_pCodePic;					// 单个位的验证码图片
	QList<QPainterPath *> m_lCodePic;			// 验证码图片
	QList<Qt::GlobalColor> m_lCodeColor;		// 验证码可用颜色列表

	int m_nNoisyPointCount = DEF_NOISYPOINTCOUNT;
	int m_nConverseRotate = DEF_CONVERSEROTATE;
	int m_nConverseScale = DEF_CONVERSESCALE;
	int m_nCodeCount = DEF_CODECOUNT;
};

#endif // RENDERAREA_H
