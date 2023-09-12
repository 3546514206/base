#include "codearea.h"

#include <QPainter>
#include <QPaintEvent>

CodeArea::CodeArea(QWidget *parent)
    : QWidget(parent)
{
	initCodeRange();
	initCodeColorList();
	replaceCodePic();
}

CodeArea::~CodeArea()
{
	if (m_pCodePic)
	{
		delete m_pCodePic;
		m_pCodePic = nullptr;
	}
}

/* 更换验证码图片 */
void CodeArea::replaceCodePic()
{
	updateLoginCode();
	updateCodePic();
}

/* 设置验证码位数 */
void CodeArea::setCodeCount(int nCodeCount)
{
	m_nCodeCount = nCodeCount;
}

/* 设置噪点数量 */
void CodeArea::setNoisyPointCount(int nNoisyPointCount)
{
	m_nNoisyPointCount = nNoisyPointCount;
}

/* 检验验证码 */
bool CodeArea::checkCode(QString sCode)
{
	updateCode();
	return m_sCode == sCode;
}

void CodeArea::paintEvent(QPaintEvent *event)
{
    QPainter painter(this);
    painter.setRenderHint(QPainter::Antialiasing);
	painter.fillRect(event->rect(), QBrush(Qt::white));

    painter.translate(0, 0);
    painter.save();
	painter.translate(event->rect().center().x() - m_lCodePic.size() / 2 * 6, event->rect().center().y());
	for (int i = 0; i < m_lCodePic.size(); i++)
	{
		drawConversion(painter);
		drawCode(painter, i);
		painter.translate(10, 0);
	}
    painter.restore();
    drawOutline(painter);
	drawNoisyPoint(painter);
}

/* 初始化验证码范围 */
void CodeArea::initCodeRange()
{
	m_slCodeRange = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
		"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
		"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
}

/* 初始化验证码可用颜色列表 */
void CodeArea::initCodeColorList()
{
	m_lCodeColor << Qt::darkRed << Qt::darkGreen << Qt::darkBlue << Qt::darkCyan 
		<< Qt::darkMagenta << Qt::darkYellow << Qt::darkGray;
}

/* 更新验证码 */
void CodeArea::updateLoginCode()
{
	m_slLoginCode.clear();
	for (int i = 0; i < m_nCodeCount; i++)
		m_slLoginCode << m_slCodeRange[qrand() % 62];
}

/* 更新验证码图片 */
void CodeArea::updateCodePic()
{
	m_lCodePic.clear();
	for (int i = 0; i < m_nCodeCount; i++)
	{
		m_pCodePic = new QPainterPath(QPointF(0, 0));
		QFont font;
		font.setBold(true);
		font.setPixelSize(20);
		QRect fontBoundingRect = QFontMetrics(font).boundingRect(m_slLoginCode[i]);
		m_pCodePic->addText(-QPointF(fontBoundingRect.center()), font, m_slLoginCode[i]);
		m_lCodePic << m_pCodePic;
	}

	setCodePic(m_lCodePic);
}

/* 更新用于与用户输入的验证码做比较的码 */
void CodeArea::updateCode()
{
	m_sCode = "";
	for (int i = 0; i < m_nCodeCount; i++)
	{
		m_sCode += m_slLoginCode[i];
	}
}

/* 绘制边缘虚线框 */
void CodeArea::drawOutline(QPainter &painter)
{
    painter.setPen(Qt::darkGreen);
    painter.setPen(Qt::DashLine);
    painter.setBrush(Qt::NoBrush);
    painter.drawRect(0, 0, 80, 20);
}

/* 绘制验证码 */
void CodeArea::drawCode(QPainter &painter, int nCodeIndex)
{
	painter.fillPath(*m_lCodePic[nCodeIndex], QBrush(m_lCodeColor[qrand() % 7]));
}

/* 绘制噪点 */
void CodeArea::drawNoisyPoint(QPainter &painter)
{
	painter.setPen(Qt::red);
	painter.setPen(Qt::DotLine);
	painter.setBrush(Qt::NoBrush);
	for (int i = 0; i < m_nNoisyPointCount; i++)
		painter.drawPoint(QPointF(qrand() % 80, qrand() % 20));
}

/* 做验证码形态转换 */
void CodeArea::drawConversion(QPainter &painter)
{
	if (qrand() % 2)
		painter.rotate(qrand() % m_nConverseRotate);
	else
		painter.rotate(-(qrand() % m_nConverseRotate));

	painter.scale((qrand() % m_nConverseScale + (100 - m_nConverseScale)) / 100.0 , 
		(qrand() % m_nConverseScale + (100 - m_nConverseScale)) / 100.0);
}

/* 设置验证码图片 */
void CodeArea::setCodePic(const QList<QPainterPath *> &lCodePic)
{
	this->m_lCodePic = lCodePic;
	update();
}
