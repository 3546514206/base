#ifndef DELETEINTERFACE_H
#define DELETEINTERFACE_H

#include <QWidget>
#include <QLabel>
#include <QLineEdit>
#include <QPushButton>
#include <QComboBox>
#include <QVBoxLayout>
#include <QGridLayout>

#include <linksql.h>

class deleteInterface : public QWidget
{
    Q_OBJECT
public:
    deleteInterface(QWidget *parent = 0);
    ~deleteInterface();
    LinkSql sql;
    QSqlQuery query;
    QString cnToen(QString str);
private:
    QLabel *deleteConditionLabel;
    QLineEdit *deleteConditionEdit;

    QPushButton *deleteAckBtn;

    QComboBox *deleteComboBox;
    QComboBox *deleteItemCombo;

    QGridLayout *deleteLayout;

private slots:
    void delectSql();
    void OnComboIndexChanged2();
};

#endif // DELETEINTERFACE_H
