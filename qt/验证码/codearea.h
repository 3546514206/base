#ifndef RENDERAREA_H
#define RENDERAREA_H

#include <QList>
#include <QPainterPath>
#include <QRect>
#include <QWidget>

#define DEF_CODECOUNT 4					// ��֤��Ĭ��λ��

#define DEF_NOISYPOINTCOUNT 60			// �������
#define DEF_CONVERSEROTATE 10			// ת���Ƕȷ�Χ
#define DEF_CONVERSESCALE 15			// ת����С��Χ

class CodeArea : public QWidget
{
    Q_OBJECT

public:
    CodeArea(QWidget *parent = 0);
	~CodeArea();

	/* ������֤��ͼƬ */
	void replaceCodePic();

	/* ������֤��λ�� */
	void setCodeCount(int nCodeCount);

	/* ����������� */
	void setNoisyPointCount(int nNoisyPointCount);

	/* ������֤�� */
	bool checkCode(QString sCode);

protected:
    void paintEvent(QPaintEvent *event);

private:
	/* ��ʼ����֤�뷶Χ */
	inline void initCodeRange();
	/* ��ʼ����֤�������ɫ�б� */
	inline void initCodeColorList();
	/* ������֤�� */
	inline void updateLoginCode();
	/* ������֤��ͼƬ */
	inline void updateCodePic();
	/* �����������û��������֤�����Ƚϵ��� */
	inline void updateCode();
	/* ���Ʊ�Ե���߿� */
    inline void drawOutline(QPainter &painter);
	/* ������֤�� */
	inline void drawCode(QPainter &painter, int nCodeIndex);
	/* ������� */
	inline void drawNoisyPoint(QPainter &painter);
	/* ����֤����̬ת�� */
	inline void drawConversion(QPainter &painter);
	/* ������֤��ͼƬ */
	inline void setCodePic(const QList<QPainterPath *> &lCodePic);

private:
	QString m_sCode = "";						// �������û��������֤�����Ƚϵ��� 
	QStringList m_slCodeRange;					// ��֤�����ɷ�Χ
	QStringList m_slLoginCode;					// ��֤���б�����������֤��ͼƬ
	QPainterPath *m_pCodePic;					// ����λ����֤��ͼƬ
	QList<QPainterPath *> m_lCodePic;			// ��֤��ͼƬ
	QList<Qt::GlobalColor> m_lCodeColor;		// ��֤�������ɫ�б�

	int m_nNoisyPointCount = DEF_NOISYPOINTCOUNT;
	int m_nConverseRotate = DEF_CONVERSEROTATE;
	int m_nConverseScale = DEF_CONVERSESCALE;
	int m_nCodeCount = DEF_CODECOUNT;
};

#endif // RENDERAREA_H
