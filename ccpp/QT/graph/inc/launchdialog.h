#ifndef LAUNCHDIALOG_H
#define LAUNCHDIALOG_H

#include <QDialog>

namespace Ui {
class LaunchDialog;
}

class LaunchDialog : public QDialog
{
    Q_OBJECT

public:
    explicit LaunchDialog(QWidget *parent = 0);
    ~LaunchDialog();

public slots:
    void onBtnShortestPathClicked();
    void onBtnNSMClicked();
    void onBtnAboutClicked();
private:
    Ui::LaunchDialog *ui;
};

#endif // LAUNCHDIALOG_H
