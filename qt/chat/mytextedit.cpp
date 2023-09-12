#include "mytextedit.h"
#include <QKeyEvent>

MyTextEdit::MyTextEdit(QWidget *parent) :
    QTextEdit(parent)
{
}
void MyTextEdit::keyPressEvent(QKeyEvent *e)
{
    if(e->modifiers() == Qt::ControlModifier && e->key() == Qt::Key_Enter){
        emit sendMessage();
    } else QTextEdit::keyPressEvent(e);
}
