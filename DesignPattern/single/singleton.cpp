//
// Created by 杨海波 on 2023/8/25.
//

#include "singleton.h"
#include "iostream"

std::mutex singleton::mutex;
singleton *singleton::instance = nullptr;

singleton &singleton::get_singleton() {
    if (instance == nullptr) {
        std::lock_guard<std::mutex> lock(mutex); // 加锁
        if (instance == nullptr) {
            instance = new singleton();
        }
    }
    return *instance;
}

singleton::singleton() {
    // 初始化单例实例的其他操作
}

void singleton::do_something() {
    std::cout << "test string";
}

