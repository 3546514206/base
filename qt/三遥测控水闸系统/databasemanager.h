/************************************************************
 * 数据库管理类,用来处理从数据库取数据的请求
 * 以及将新的数据保存在数据中
 * 闸机数据表中的顺序为 id 时间 上水位 下水位 开度 流量
 */
#ifndef DATABASEMANAGER_H
#define DATABASEMANAGER_H

#include <QObject>
class QSqlTableModel;

class DatabaseManager : public QObject
{
    Q_OBJECT

public:
    explicit DatabaseManager(QObject *parent = 0);

    // 请求数据库闸机数据, id 为具体的闸机号, 99 为魔数,当 id 为 99 时提取所有闸机数据
    Q_INVOKABLE QString requestSluiceData(int count, int id = 99);
    Q_INVOKABLE QString requestSluiceSensor(int count, int id = 99);

    // 请求数据库环境数据
    Q_INVOKABLE QString requestEnvironmentalData(int count);
    Q_INVOKABLE QString requestEnvironmentalSensor(int count);

    void updateSluiceData(int id, QStringList& list);
    void updateSluiceSensor(int id, QList<int>& list);
    void updateEnvironmentalData(QStringList& list);
    void updateEnvironmentalSensor(QList<int>& list);

signals:

public slots:

private:
    QSqlTableModel* model;
};

#endif // DATABASEMANAGER_H
