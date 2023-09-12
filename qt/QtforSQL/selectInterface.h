#ifndef SELECTINTERFACE_H
#define SELECTINTERFACE_H

#include <QWidget>
#include <QLabel>
#include <QPushButton>
#include <QGridLayout>
#include <QVBoxLayout>
#include <QComboBox>
#include <QTableView>
#include <QLineEdit>

#include "linksql.h"

class selectInterface : public QWidget
{
    Q_OBJECT
public:
    selectInterface(QWidget *parent = 0);
    ~selectInterface();

    LinkSql sql;
    QSqlQuery query;

    QString cnToen(QString str);
private:
    QLabel *searchInputLabel;
    QLineEdit *seachInputEdit;

    QPushButton *selectSearchBtn;
    QPushButton *selectAllSearchBtn;

    QComboBox *selectComboBox;
    QComboBox *selectItemCombo;

    QTableView *tView;

    QGridLayout *selectGridLayout;
    QVBoxLayout *selectVBoxLayout;

private slots:
    void OnComboIndexChanged();
    void selectAll();
    void selectSql();
};

#endif // SELECTINTERFACE_H
