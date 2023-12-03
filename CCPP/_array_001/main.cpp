#include <iostream>

// 全局数组未初始化，默认值是0
int global[10];

int main() {

    // 局部数据未初始化，默认值是垃圾值
    int n[10];

    for (int i = 0; i < 10; ++i) {
        std::cout << n[i] << std::endl;
    }

    std::cout << std::endl;

    for (int i = 0; i < 10; ++i) {
        std::cout << global[i] << std::endl;
    }

    return 0;
}
