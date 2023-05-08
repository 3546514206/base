#ifndef BROSWER_H
#define BROSWER_H


#include<qobject.h>
#include <QToolBar>
#include <QVBoxLayout>
#include<QMouseEvent>
class web;
class broswer :public QWidget {
    Q_OBJECT
public:
    broswer(QWidget* parent = nullptr);
    ~broswer();
    void setFrameless(bool);
protected:
    void mousePressEvent(QMouseEvent* event) override;
    void mouseMoveEvent(QMouseEvent* event) override;
    void mouseReleaseEvent(QMouseEvent* event) override;
private:

    bool is_pin;
    bool is_framless;
    web* m_web_view;
    QToolBar* m_toolBar;

    bool resizing;
    QPoint pressPos;
    const int border = 8;  // adjust the border size as you need


};

#endif // BROSWER_H
