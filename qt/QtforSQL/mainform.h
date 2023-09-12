#ifndef MAINFORM_H
#define MAINFORM_H

#include <QDialog>
#include <QVBoxLayout>
#include <QGridLayout>
#include <QToolBox>

#include "appendinterface.h"
#include "aboutInterface.h"
#include "selectInterface.h"
#include "updateInterface.h"
#include "deleteInterface.h"

#include "linksql.h"

class mainform : public QDialog
{
    Q_OBJECT

public:
    mainform(QWidget *parent = 0);
    ~mainform();
    LinkSql sql;
    QSqlQuery query;

    QString cnToen(QString str);

private:
    QLabel *deleteHLineLabel;

    QToolBox *toolBox;

    QWidget *deleteToolBoxWidget;

    QGridLayout *gridLayout;
    QVBoxLayout *deleteVBoxLayout;
};

#endif // MAINFORM_H
