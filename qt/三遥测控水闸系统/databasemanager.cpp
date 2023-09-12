#include "databasemanager.h"
#include <QSqlTableModel>
#include <QDateTime>
#include <QDebug>

DatabaseManager::DatabaseManager(QObject *parent) :
    QObject(parent)
{
    // 初始化model
    model = new QSqlTableModel(this);
}

/*************************************************************
 * 更新数据库函数,id为闸机编号
 */
// list = 上限,下限,开度
void DatabaseManager::updateSluiceData(int id, QStringList& list)
{
    model->setTable("sluicedata");
    model->select();

    QDateTime time = QDateTime::currentDateTime();//获取系统现在的时间
    QString timeStr = time.toString("yyyy-MM-dd hh:mm:ss"); //设置显示格式

    model->insertRow(model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 0), model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 1), id);
    model->setData(model->index(model->rowCount() - 1, 2), timeStr);

    for ( int i = 0; i < list.length(); ++i )
    {
        model->setData(model->index(model->rowCount() - 1, i+3), list.at(i));
    }
    model->submitAll();

    qDebug() << "更新SluiceData数据库:" << timeStr << " " << id << " " << list;
}

void DatabaseManager::updateSluiceSensor(int id, QList<int>& list)
{
    model->setTable("sluicesensor");
    model->select();

    QDateTime time = QDateTime::currentDateTime();
    QString timeStr = time.toString("yyyy-MM-dd hh:mm:ss");


    model->insertRow(model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 0), model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 1), id);
    model->setData(model->index(model->rowCount() - 1, 2), timeStr);

    for ( int i = 0; i < list.length(); ++i )
    {
        model->setData(model->index(model->rowCount() - 1, i+3), list.at(i));
    }
    model->submitAll();

    qDebug() << "更新SluiceSensor数据库:" << timeStr << " " << id << " " << list;
}

// list = 上游水位,下游水位,流量,雨量,风向
void DatabaseManager::updateEnvironmentalData(QStringList& list)
{
    model->setTable("environmentaldata");
    model->select();

    QDateTime time = QDateTime::currentDateTime();
    QString timeStr = time.toString("yyyy-MM-dd hh:mm:ss");

    // 添加一行
    model->insertRow(model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 0), model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 1), timeStr);

    for ( int i = 0; i < list.length(); ++i )
    {
        model->setData(model->index(model->rowCount() - 1, i+2), list.at(i));
    }
    model->submitAll();

    qDebug() << "更新EnvironmentalData数据库:" << timeStr << " " << list;
}

void DatabaseManager::updateEnvironmentalSensor(QList<int>& list)
{
    model->setTable("environmentalsensor");
    model->select();

    QDateTime time = QDateTime::currentDateTime();
    QString timeStr = time.toString("yyyy-MM-dd hh:mm:ss");

    model->insertRow(model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 0), model->rowCount());
    model->setData(model->index(model->rowCount() - 1, 1), timeStr);

    for ( int i = 0; i < list.length(); ++i )
    {
        model->setData(model->index(model->rowCount() - 1, i+2), list.at(i));
    }
    model->submitAll();

    qDebug() << "更新EnvironmentalSensor数据库:" << timeStr << " " << list;
}


/*************************************************************
 * 请求数据库数据,id为闸机编号,count为数据行数
 * 返回QString类型数据
 */
QString DatabaseManager::requestSluiceData(int count, int id)
{
    QString retval;
    model->setTable("sluicedata");

    if ( id != 99 )
        model->setFilter(QString("number = '%1'").arg(id));

    model->select();

    if ( model->rowCount() < count ) {
        qDebug() << "超出了sluicedata最大行数,count下降为:" << model->rowCount();
        count = model->rowCount();
    }

    int endRow = model->rowCount() - count - 1;
    for ( int i = model->rowCount() - 1; i > endRow; --i)
    {
        for ( int j = 1; j < model->columnCount(); ++j)
        {
            retval += model->index(i, j, QModelIndex()).data().toString();
            retval += ",";
        }
        retval += "\n";
    }
    return retval;
}

QString DatabaseManager::requestSluiceSensor(int count, int id)
{
    QString retval;
    model->setTable("sluicesensor");

    if ( id != 99 )
        model->setFilter(QString("number = '%1'").arg(id));

    model->select();
    if ( model->rowCount() < count ) {
        qDebug() << "超出了sluicesensor最大行数,count下降为:" << model->rowCount();
        count = model->rowCount();
    }

    int endRow = model->rowCount() - count - 1;
    for ( int i = model->rowCount() - 1; i > endRow; --i)
    {
        for ( int j = 1; j < model->columnCount(); ++j)
        {
            retval += model->index(i, j, QModelIndex()).data().toString();
            retval += ",";
        }
        retval += "\n";
    }
    return retval;
}

QString DatabaseManager::requestEnvironmentalData(int count)
{
    QString retval;
    model->setTable("environmentaldata");
    model->select();
    if ( model->rowCount() < count ) {
        qDebug() << "超出了environmentaldata最大行数,count下降为:" << model->rowCount();
        count = model->rowCount();
    }

    int endRow = model->rowCount() - count - 1;
    for ( int i = model->rowCount() - 1; i > endRow; --i)
    {
        for ( int j = 1; j < model->columnCount(); ++j)
        {
            retval += model->index(i, j, QModelIndex()).data().toString();
            retval += ",";
        }
        retval += "\n";
    }
    //qDebug() << "返回的数据是:" << retval;
    return retval;
}

QString DatabaseManager::requestEnvironmentalSensor(int count)
{
    QString retval;
    model->setTable("environmentalsensor");
    model->select();
    if ( model->rowCount() < count ) {
        qDebug() << "超出了environmentalsensor最大行数,count下降为:" << model->rowCount();
        count = model->rowCount();
    }

    int endRow = model->rowCount() - count - 1;
    for ( int i = model->rowCount() - 1; i > endRow; --i)
    {
        for ( int j = 1; j < model->columnCount(); ++j)
        {
            retval += model->index(i, j, QModelIndex()).data().toString();
            retval += ",";
        }
        retval += "\n";
    }
    return retval;
}
