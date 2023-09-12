#ifndef MASK_H
#define MASK_H

#include <QWidget>
#include <QStyleOption>
#include <QPainter>
#include <QLabel>

class Mask : public QWidget
{
    Q_OBJECT
public:
    explicit Mask(QWidget *parent = 0);
    void setMessage(QString);
signals:

public slots:
private:
    QLabel *info;
    void paintEvent(QPaintEvent*);
};

#endif // MASK_H
