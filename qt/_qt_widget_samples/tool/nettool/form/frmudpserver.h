﻿#ifndef FRMUDPSERVER_H
#define FRMUDPSERVER_H

#include <QWidget>
#include <QtNetwork>

namespace Ui {
class frmUdpServer;
}

class frmUdpServer : public QWidget
{
    Q_OBJECT

public:
    explicit frmUdpServer(QWidget *parent = 0);
    ~frmUdpServer();

protected:
    bool eventFilter(QObject *watched, QEvent *event);

private:
    Ui::frmUdpServer *ui;

    QUdpSocket *socket;
    QTimer *timer;

private slots:
    void initForm();
    void initConfig();
    void saveConfig();
    void initTimer();
    void append(int type, const QString &data, bool clear = false);

private slots:
    void error();
    void readData();
    void sendData(const QString &ip, int port, const QString &data);

private slots:
    void on_btnListen_clicked();
    void on_btnSave_clicked();
    void on_btnClear_clicked();
    void on_btnSend_clicked();
    void on_btnRemove_clicked();
};

#endif // FRMUDPSERVER_H
