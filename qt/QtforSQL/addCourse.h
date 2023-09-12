#ifndef ADDCOURSE_H
#define ADDCOURSE_H

#include <QDialog>
#include <QLineEdit>
#include <QLabel>
#include <QPushButton>
#include <QGridLayout>
#include <QMessageBox>

#include "linksql.h"

class addCourse : public QDialog
{
    Q_OBJECT

public:
    addCourse(QWidget *parent = 0);
    ~addCourse();
    LinkSql sql;
    QSqlQuery query;

private:
    QLabel *CnoLabel;
    QLabel *CnameLabel;
    QLabel *CteacherLabel;

    QLineEdit *CnoEdit;
    QLineEdit *CnameEdit;
    QLineEdit *CteacherEdit;

    QPushButton *ackBtn;
    QPushButton *cleanBtn;

    QGridLayout *gridLayout;
private slots:
    void onAckBtn();
    void onCleanBtn();
};

#endif // ADDCOURSE_H
