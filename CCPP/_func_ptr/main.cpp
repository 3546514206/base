#include <iostream>

// 定义一个接受两个整数和一个函数指针的函数
int calculate(int x, int y, int (*operation)(int, int)) {
    return operation(x, y);
}

// 定义一个简单的加法函数
int add(int a, int b) {
    return a + b;
}

int main() {
    // 使用函数指针调用 calculate 函数
    int resultAdd = calculate(5, 3, add);

    // 输出结果
    std::cout << "Addition Result: " << resultAdd << std::endl;  // 输出：8

    return 0;
}
