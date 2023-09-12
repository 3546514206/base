#ifndef UPDATEINTERFACE_H
#define UPDATEINTERFACE_H

#include <QWidget>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include <QComboBox>
#include <QGridLayout>

#include "linksql.h"

class updateInterface : public QWidget
{
    Q_OBJECT
public:
    updateInterface(QWidget *parent = 0);
    ~updateInterface();
    LinkSql sql;
    QSqlQuery query;
    QString cnToen(QString str);
private:
    QLabel *updateTableLabel;
    QLabel *updateSetLabel;
    QLineEdit *updateSetEdit;
    QLabel *updateWhereLabel;
    QLineEdit *updateWhereEdit;

    QPushButton *updateAckBtn;

    QComboBox *updateTableComboBox;
    QComboBox *updateSetComboBox;
    QComboBox *updateWhereComboBox;
    QComboBox *updateWhereItemCombo4;

    QGridLayout *updateLayout;

private slots:
    void updateSql();
    void OnComboIndexChanged3();
    void OnComboIndexChanged4();
};

#endif // UPDATEINTERFACE_H
