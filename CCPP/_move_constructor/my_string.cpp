//
// Created by 杨海波 on 2023/12/5.
//

#include "my_string.h"

move_constructor::my_string::my_string(move_constructor::my_string && other)  noexcept {
    this->data = other.data;
    other.data = nullptr;
}

move_constructor::my_string::~my_string() {
    delete[] data;
}