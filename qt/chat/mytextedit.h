#ifndef MYTEXTEDIT_H
#define MYTEXTEDIT_H

#include <QTextEdit>

class MyTextEdit : public QTextEdit
{
    Q_OBJECT
public:
    explicit MyTextEdit(QWidget *parent = 0);
protected:
    void keyPressEvent(QKeyEvent *e);
signals:
    void sendMessage();
public slots:
    
};

#endif // MYTEXTEDIT_H
