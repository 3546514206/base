#ifndef ADDFRADE_H
#define ADDGRADE_H

#include <QWidget>
#include <QLineEdit>
#include <QLabel>
#include <QPushButton>
#include <QGridLayout>
#include <QMessageBox>

#include "linksql.h"

class addGrade : public QWidget
{
    Q_OBJECT
public:
    addGrade(QWidget *parent = 0);
    ~addGrade();
    LinkSql sql;
    QSqlQuery query;

private:
    QLabel *GnoLabel;
    QLabel *GperiodLabel;
    QLabel *GgradeLabel;
    QLabel *GstunumLabel;
    QLabel *GchargeLabel;

    QLineEdit *GnoEdit;
    QLineEdit *GperiodEdit;
    QLineEdit *GgradeEdit;
    QLineEdit *GstunumEdit;
    QLineEdit *GchargeEdit;

    QPushButton *ackBtn;
    QPushButton *cleanBtn;

    QGridLayout *gridLayout;
private slots:
    void onAckBtn();
    void onCleanBtn();
};

#endif // ADDGRADE_H
