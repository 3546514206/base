#include <iostream>
#include "my_string.h"

move_constructor::my_string create_string() {
    move_constructor::my_string str;
    // 在实际应用中，可能会分配一些资源给 str.data
    // 返回一个临时对象
    return str;
}

int main() {


    move_constructor::my_string result = create_string();  // 使用移动构造函数

    return 0;
}

