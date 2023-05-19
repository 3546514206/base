#ifndef SPWINDOW_H
#define SPWINDOW_H

#include <QMainWindow>
class SPFrame;
namespace Ui {
class SPWindow;
}

class SPWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit SPWindow(QWidget *parent = 0);
    ~SPWindow();

    QWidget *parent() const;

public slots:
    void onBtnAddVertexClicked();
    void onBtnRemoveAllVertexClicked();
    void onRadioBtnEditModeToggled(bool);
    void onBtnCalcClicked();
    void onActionOpen();
    void onActionSave();
    void onActionNoDirGraph();
    void onHintChanged(QString str);
    void onBtnFloydToExcelClicked();
protected:
    void closeEvent(QCloseEvent *event);
private:
    QWidget* mParent;
    Ui::SPWindow *ui;
    void init();
    SPFrame *shortestpath;
    void makeHintText(QStringList list);
    void addVertex();
};

#endif // SPWINDOW_H
