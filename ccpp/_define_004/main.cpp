#include <iostream>

#define CONCAT(a, b) a##b

int main() {
    int number = 42;
    // 生成标识符number2
    int result = CONCAT(4, 2);
    std::cout << result + 1 << std::endl;
    return 0;
}

