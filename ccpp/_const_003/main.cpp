#include <iostream>

void print_value(const int& value) {
    std::cout << "The value is: " << value << std::endl;
    // value = 10; // 错误！不能修改常量引用的值
}

int main() {
    int num = 5;
    print_value(num); // 传递num的引用

    return 0;
}

