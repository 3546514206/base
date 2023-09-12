/*
 * 该文件用来创建基于SqlLite的数据库,数据存放在sluice.db文件中
 * 数据库中有三个表格,分别为sluicedata, sluicesensor, environmentaldata, environmentalsensor
 * 第一个值为主键,闸门表格第二个数据为其编号
 */
#ifndef CONNECTION_H
#define CONNECTION_H

#include <QSqlDatabase>
#include <QSqlQuery>

static bool createConnection()
{
    QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE");
    db.setDatabaseName("sluice.db");
    if (!db.open()) {
        return false;
    }
    QSqlQuery query;

    query.exec("create table sluicedata (id int primary key,"
                "number int,"
                "time varchar(20),"
                "upperlimit varchar,"
                "lowerlimit varchar,"
                "openingvalue varchar)");
    query.exec(QString("insert into sluicedata "
               "values(1, 1, '2014-07-21 12:20:00', '24.00', '0.00', '15.91')"));
    query.exec(QString("insert into sluicedata "
               "values(2, 2, '2014-07-21 12:20:00', '35.00', '10.00', '18.88')"));

    query.exec("create table sluicesensor (id int primary key,"
                "number int,"
                "time varchar(20),"
                "upperlimit int,"
                "lowerlimit int,"
                "openingvalue int)");
    query.exec(QString("insert into sluicesensor "
               "values(1, 1, '2014-07-21 12:20:00', 1, 1, 1)"));
    query.exec(QString("insert into sluicesensor "
               "values(2, 2, '2014-07-21 12:20:00', 1, 1, 1)"));

    query.exec("create table environmentaldata (id int primary key,"
                "time varchar(20),"
                "upstream varchar,"
                "downstream varchar,"
                "flow varchar,"
                "rainfall varchar,"
                "winddirect varchar)");
    query.exec(QString("insert into environmentaldata "
               "values(1, '2014-07-21 12:20:00', '38.73', '10.49', '11.82', '12.25', '2')"));

    query.exec("create table environmentalsensor (id int primary key,"
                "time varchar(20),"
                "upstream int,"
                "downstream int,"
                "flow int,"
                "rainfall int,"
                "winddirect int)");
    query.exec(QString("insert into environmentalsensor "
               "values(1, '2014-07-21 12:20:00', 1, 1, 1, 1, 1)"));

    return true;
}
//! [0]

#endif
