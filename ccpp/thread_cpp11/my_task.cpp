//
// Created by 杨海波 on 2022/11/29.
//

#include "my_task.hpp"

using namespace task_def_space;

template<typename T>
T sample_task<T>::do_something() {
    std::cout << this->data << std::endl;
}


template<typename T>
sample_task<T>::sample_task(T &data) {
    this->data = data;
}
