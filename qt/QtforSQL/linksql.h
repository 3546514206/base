#ifndef LINKSQL
#define LINKSQL

#include <QtSql/QSqlDatabase>
#include <QString>
#include <QtSql/QSqlQuery>
#include <QStringList>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQueryModel>
#include <QMessageBox>

class LinkSql{
public:
    LinkSql();
    ~LinkSql();

    bool isLinkToSql();
    void selectAllInf(int i);

    void updateFromSql(QString str1,QString str2,QString str3,QString str4,QString str5,QString str6);
    void deleteFromSql(QString str1,QString str2,QString str3);
    QSqlQuery selectFromSql(QString str1,QString str2,QString str3);

    QSqlQuery addToStudent(QStringList list);
    QSqlQuery addToCourse(QStringList list);
    QSqlQuery addToMark(QStringList list);
    QSqlQuery addToFamily(QStringList list);
    QSqlQuery addToGrade(QStringList list);

public:
    QSqlQueryModel model;
    QSqlDatabase db;

};


#endif // LINKSQL

