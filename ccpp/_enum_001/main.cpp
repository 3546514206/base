#include <iostream>

// 定义枚举类型
enum Color {
    RED,     // 0
    GREEN,   // 1
    BLUE     // 2
};

int main() {
    // 创建枚举变量
    Color myColor = RED;

    // 使用枚举变量
    if (myColor == RED) {
        // 执行某些操作
        std::cout<<Color::RED<<std::endl;
        std::cout<<RED<<std::endl;
    }

    return 0;
}
