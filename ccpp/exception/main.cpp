#include <iostream>


double division_zero(int a, int b) {
    if (b == 0) {
//        throw 语句的操作数可以是任意的表达式，表达式的结果的类型决定了抛出的异常的类型。
        throw "division by zero exception";
    }

    return a / b;
}

int main() {

    int rz = -1;

    try {
        rz = division_zero(10, 0);
        std::cout << rz << std::endl;
    } catch (const char *exp_msg) {
        std::cerr << exp_msg << std::endl;
    }



    return 0;
}
