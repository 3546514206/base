#include <iostream>

// 函数对象，重载了 () 的类

class average {
public:
    int operator()(int numbers[]);
};

int average::operator()(int numbers[]) {
    int sum = 0;
    int length = sizeof(numbers) / sizeof(numbers[0]);
    for (int i = 0; i < length; ++i) {
        sum += numbers[i];
    }
    return sum;
}

int main() {
    int numbers[] = {2, 5, 1, 8, 3, 9};

    average *ave = new average;
    // 指针不能用来对一个引用变量赋值
    // average & a = ave;
    std::cout<< ave->operator()(numbers)<<std::endl;
    std::cout<< (*ave)(numbers)<<std::endl;

    return 0;
}
