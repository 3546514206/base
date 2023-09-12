#ifndef ADDMARK_H
#define ADDMARK_H

#include <QDialog>
#include <QLineEdit>
#include <QLabel>
#include <QPushButton>
#include <QGridLayout>
#include <QMessageBox>
#include <QComboBox>

#include "linksql.h"

class addMark : public QDialog
{
    Q_OBJECT

public:
    addMark(QWidget *parent = 0);
    ~addMark();
    LinkSql sql;
    QSqlQuery query;
private:
    QLabel *CnoLabel;
    QLabel *SnoLabel;
    QLabel *MpointLabel;
    QLabel *MsemesterLabel;

    QLineEdit *CnoEdit;
    QLineEdit *SnoEdit;
    QLineEdit *MpointEdit;
    QComboBox *MsemesterBox;

    QPushButton *ackBtn;
    QPushButton *cleanBtn;

    QGridLayout *gridLayout;
private slots:
    void onAckBtn();
    void onCleanBtn();
};

#endif // ADDMARK_H
