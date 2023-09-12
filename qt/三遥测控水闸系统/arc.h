#ifndef ARC_H
#define ARC_H

#include <QtQuick/QQuickPaintedItem>
#include <QColor>

class Arc : public QQuickPaintedItem
{
    Q_OBJECT
    //Q_DISABLE_COPY(Arc)

    Q_PROPERTY(int angle READ angle WRITE setAngle NOTIFY angleChanged)
    Q_PROPERTY(QColor color READ color WRITE setColor NOTIFY colorChanged)

public:
    Arc(QQuickItem *parent = 0);
    ~Arc();

    int angle() const;
    void setAngle(int agl);

    QColor color() const;
    void setColor(const QColor &color);

    void paint(QPainter *painter);

signals:
    void angleChanged();
    void colorChanged();

private:
    QColor m_color;
    int m_angle;
};

#endif // ARC_H
