//
// Created by 杨海波 on 2022/12/8.
//

#ifndef QT_TEST_MAIN_WINDOWS_HPP
#define QT_TEST_MAIN_WINDOWS_HPP

#include <QWidget>


QT_BEGIN_NAMESPACE
namespace Ui { class main_windows; }
QT_END_NAMESPACE

class main_windows : public QWidget {
Q_OBJECT

public:
    explicit main_windows(QWidget *parent = nullptr);

    ~main_windows() override;

private:
    Ui::main_windows *ui;
};


#endif //QT_TEST_MAIN_WINDOWS_HPP
