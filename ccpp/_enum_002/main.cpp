#include <iostream>

enum class Color {
    RED,     // 默认值为 Color::RED
    GREEN,   // 默认值为 Color::GREEN
    BLUE     // 默认值为 Color::BLUE
};

int main() {
    // 创建枚举变量
    Color myColor = Color::RED;

    // 使用枚举变量
    if (myColor == Color::RED) {
        // 执行某些操作
        // ...
    }

    return 0;
}

