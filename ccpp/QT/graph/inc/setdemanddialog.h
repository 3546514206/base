#ifndef SETDEMANDDIALOG_H
#define SETDEMANDDIALOG_H

#include <QDialog>

namespace Ui {
class SetDemandDialog;
}

class SetDemandDialog : public QDialog
{
    Q_OBJECT

public:
    explicit SetDemandDialog(QWidget *parent = 0);
    ~SetDemandDialog();

    int getDemand() const;

    void setDemand(int value);

    void setDemandText(QString s);
public slots:
    void onBtnOkClicked();
private:
    Ui::SetDemandDialog *ui;
     int demand;
};

#endif // SETDEMANDDIALOG_H
