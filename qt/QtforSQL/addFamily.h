#ifndef ADDFAMILY_H
#define ADDFAMILY_H

#include <QDialog>
#include <QLineEdit>
#include <QLabel>
#include <QPushButton>
#include <QGridLayout>
#include <QMessageBox>

#include "linksql.h"

class addFamily : public QDialog
{
    Q_OBJECT

public:
    addFamily(QWidget *parent = 0);
    ~addFamily();
    LinkSql sql;
    QSqlQuery query;
private:
    QLabel *FnoLabel;
    QLabel *FnameLabel;
    QLabel *FphoneLabel;
    QLabel *FrelationLabel;
    QLabel *SnoLabel;

    QLineEdit *FnoEdit;
    QLineEdit *FnameEdit;
    QLineEdit *FphoneEdit;
    QLineEdit *FrelationEdit;
    QLineEdit *SnoEdit;

    QPushButton *ackBtn;
    QPushButton *cleanBtn;

    QGridLayout *gridLayout;
private slots:
    void onAckBtn();
    void onCleanBtn();
};

#endif // ADDFAMILY_H
