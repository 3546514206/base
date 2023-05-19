#ifndef SETCAPACITYANDCOSTDIALOG_H
#define SETCAPACITYANDCOSTDIALOG_H

#include <QDialog>

namespace Ui {
class SetCapacityAndCostDialog;
}

class SetCapacityAndCostDialog : public QDialog
{
    Q_OBJECT

public:
    explicit SetCapacityAndCostDialog(QWidget *parent = 0);
    ~SetCapacityAndCostDialog();

    int getCapacity() const;
    void setCapacity(int value);

    int getCost() const;
    void setCost(int value);

public slots:
    void onBtnOKClicked();
private:
    Ui::SetCapacityAndCostDialog *ui;
    int capacity;
    int cost;
};

#endif // SETCAPACITYANDCOSTDIALOG_H
