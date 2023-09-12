#ifndef ADDSTUDENT_H
#define ADDSTUDENT_H

#include <QDialog>
#include <QLineEdit>
#include <QLabel>
#include <QPushButton>
#include <QGridLayout>
#include <QMessageBox>
#include <QComboBox>

#include "linksql.h"

class addStudent : public QDialog
{
    Q_OBJECT

public:
    addStudent(QWidget *parent = 0);
    ~addStudent();
    LinkSql sql;
    QSqlQuery query;
private:
    QLabel *SnoLabel;
    QLabel *SnameLabel;
    QLabel *SsexLabel;
    QLabel *SdateLabel;
    QLabel *SnationLabel;
    QLabel *SphoneLabel;
    QLabel *SaddressLabel;
    QLabel *SgraduateLabel;
    QLabel *GnoLabel;

    QLineEdit *SnoEdit;
    QLineEdit *SnameEdit;
    QComboBox *SsexComboBox;
    QLineEdit *SdateEdit;
    QLineEdit *SnationEdit;
    QLineEdit *SphoneEdit;
    QLineEdit *SaddressEdit;
    QLineEdit *SgraduateEdit;
    QLineEdit *GnoEdit;

    QPushButton *ackBtn;
    QPushButton *cleanBtn;

    QGridLayout *gridLayout;
private slots:
    void onAckBtn();
    void onCleanBtn();
};

#endif // ADDSTUDENT_H
