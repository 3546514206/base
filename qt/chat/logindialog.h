#ifndef LOGINDIALOG_H
#define LOGINDIALOG_H

#include <QDialog>

namespace Ui {
class LoginDialog;
}

class LoginDialog : public QDialog
{
    Q_OBJECT
    
public:
    explicit LoginDialog(QWidget *parent = 0);
    ~LoginDialog();
    QString getUserName();
private slots:
    void on_pushButton_clicked();

private:
    Ui::LoginDialog *ui;
    void readSettings();
    void writeSettings();
};

#endif // LOGINDIALOG_H
