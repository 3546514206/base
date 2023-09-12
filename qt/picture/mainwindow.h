#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include <QListWidgetItem>
#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    //展示图片功能
    void shouw_picture(const QString path);

private slots:
    void on_toolButton_triggered(QAction *arg1);
    void on_listWidget_itemDoubleClicked(QListWidgetItem *item);
    void on_toolButton_clicked();

    void on_pushButton_3_clicked();

    void on_pushButton_4_clicked();

    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

    void on_pushButton_5_clicked();

private:
    Ui::MainWindow *ui;
    int x;
    int y;
    int z;
};
#endif // MAINWINDOW_H
