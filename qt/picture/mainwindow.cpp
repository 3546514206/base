#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMenu>
#include <QDebug>
#include <QFileDialog>
#include <QMessageBox>
#include <QPixmap>
#include <QMatrix>
MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    //给menu指定属于哪个组件
    QMenu* menu = new QMenu(ui->toolButton);
    //添加菜单内容
    menu->addAction("导入文件");
    menu->addAction("删除文件");
    menu->addAction("清空文件");
    //设置菜单
    ui->toolButton->setMenu(menu);
}

MainWindow::~MainWindow()
{
    delete ui;
}



//toolbutton的点击事件槽函数
void MainWindow::on_toolButton_clicked()
{
    //点击了tool那肯定就得展开菜单
    ui->toolButton->showMenu();
}
//选择菜单选项的槽函数
void MainWindow::on_toolButton_triggered(QAction *arg1)
{
    //判断用戶选择的什么
    if("导入文件" == arg1->text())
    {
        QStringList files =
        QFileDialog::getOpenFileNames(this,
        "请选择图片",
        "C:\\Users\\25755\\Desktop\\background",
        "Images (*.png *.xpm *.jpg)");

        //将获得的图片加入 listWidget
        ui->listWidget->addItems(files);
    }
    if("清空文件" == arg1->text())
    {
        int ret = QMessageBox::warning(this,tr("注意"),tr("确认清空?"),QMessageBox::Ok| QMessageBox::No);

        if(ret == QMessageBox::Ok)
        {
            ui->listWidget->clear();
        }
    }
    if("删除文件" == arg1->text())
    {
        int ret = QMessageBox::warning(this,tr("注意"),tr("确认删除?"),QMessageBox::Ok| QMessageBox::No);

        if(ret == QMessageBox::Ok)
        {
            //先获得当前选择的item位置
            int row = ui->listWidget->currentRow();
            //删除该行
            ui->listWidget->takeItem(row);
        }

    }
}

//展示图片功能
void MainWindow::shouw_picture(const QString path)
{
    //获得图片初始大小(其实也就是label的大小)
    x = ui->listWidget->width();
    y = ui->listWidget->height();
    //初始化图片
    QPixmap pic(path);

    //设置图片的自适应
    pic = pic.scaled(ui->label->width(),ui->label->height());

    //显示到标签中
    ui->label->setPixmap(pic);
}

//双击listwidget中的条数 的信号处理槽函数 （实现功能展示图片）

void MainWindow::on_listWidget_itemDoubleClicked(QListWidgetItem *item)
{
    //展示图片
    QString path = item->text();
    shouw_picture(path);
}

//上一张图片
void MainWindow::on_pushButton_3_clicked()
{
    //获取当前行数
    int row = ui->listWidget->currentRow();
    //第一张行数是0 所以上一张是-- 不是++
    row--;

    //获取总行数
    int count = ui->listWidget->count();
    if(row < 0)
    {
        row = count - 1;
    }
    //设置当前行（因为你选中行也得变 不能光图片变
    ui->listWidget->setCurrentRow(row);
    shouw_picture(ui->listWidget->currentItem()->text());
}

void MainWindow::on_pushButton_4_clicked()
{
    //获取当前行数
    int row = ui->listWidget->currentRow();
    //第一张行数是0 所以上一张是-- 不是++
    row++;

    //获取总行数
    int count = ui->listWidget->count();
    if(row == count)
    {
        row = 0;
    }
    //设置当前行（因为你选中行也得变 不能光图片变）
    ui->listWidget->setCurrentRow(row);
    shouw_picture(ui->listWidget->currentItem()->text());
}

//放大图片槽函数
void MainWindow::on_pushButton_clicked()
{
    //修改当前图片的大小(不用担心 会影响其他图片)
    //因为每次换图片都会重新调用一次展示图片 x y也会重新赋值为label大小
    x *= 1.2;
    y *= 1.2;

    //初始化图片
    QPixmap pic(ui->listWidget->currentItem()->text());

    //设置图片的自适应
    pic = pic.scaled(x,y);

    //显示到标签中
    ui->label->setPixmap(pic);
}

//缩小图片
void MainWindow::on_pushButton_2_clicked()
{
    //修改当前图片的大小(不用担心 会影响其他图片)
    //因为每次换图片都会重新调用一次展示图片 x y也会重新赋值为label大小
    x *= 0.8;
    y *= 0.8;

    //初始化图片
    QPixmap pic(ui->listWidget->currentItem()->text());

    //设置图片的自适应
    pic = pic.scaled(x,y);

    //显示到标签中
    ui->label->setPixmap(pic);
}

//旋转图片
void MainWindow::on_pushButton_5_clicked()
{
    //初始化图片
    QPixmap pic(ui->listWidget->currentItem()->text());

    z += 90;
    QMatrix ro;

    //设置图片角度
    ro = ro.rotate(z);

    //旋转图片
    pic = pic.transformed(ro);

    //设置图片的自适应
    pic = pic.scaled(x,y);

    //显示到标签中
    ui->label->setPixmap(pic);
}
