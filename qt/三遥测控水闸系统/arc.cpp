#include "arc.h"

#include <QPainter>

Arc::Arc(QQuickItem *parent)
    : QQuickPaintedItem(parent), m_angle(0)
{

}

Arc::~Arc()
{
}

QColor Arc::color() const
{
    return m_color;
}

void Arc::setColor(const QColor &color)
{
    m_color = color;
    emit colorChanged();
    update();
}

int Arc::angle() const
{
    return m_angle;
}

void Arc::setAngle(int agl)
{
    m_angle = agl;
    emit angleChanged();
    update();
}

void Arc::paint(QPainter *painter)
{
    QPen pen(m_color, 2);
    painter->setPen(pen);
    painter->setRenderHints(QPainter::Antialiasing, true);
    painter->drawArc(boundingRect().adjusted(1, 1, -1, -1), 90 * 16, m_angle * 16);
}
