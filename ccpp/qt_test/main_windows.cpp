//
// Created by 杨海波 on 2022/12/8.
//

// You may need to build the project (run Qt uic code generator) to get "ui_main_windows.h" resolved

#include "main_windows.hpp"
#include "ui_main_windows.h"


main_windows::main_windows(QWidget *parent) :
        QWidget(parent), ui(new Ui::main_windows) {
    ui->setupUi(this);
}

main_windows::~main_windows() {
    delete ui;
}

