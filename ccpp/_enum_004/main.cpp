#include <iostream>

/**
 * enum 声明后的冒号 (:) 表示指定枚举类型的基础类型。在这个例子中，enum_type 是一
 * 个枚举类型，基础类型为 int。基础类型指定了枚举值的底层存储类型，默认情况下是 int。
通过指定基础类型，可以控制枚举值的范围和可取值的大小。使用不同的基础类型，可以选择不
 同的整数类型，例如 int、unsigned int、short、unsigned short 等，以满足特定的需求。
 */
enum enum_type : int {
    VALUE1 = 0,
    VALUE2 = 1,
    VALUE3 = 2
};

int main() {
    enum_type value = VALUE2;

    if (value == VALUE1) {
        std::cout << "Value is VALUE1" << std::endl;
    } else if (value == VALUE2) {
        std::cout << "Value is VALUE2" << std::endl;
    } else if (value == VALUE3) {
        std::cout << "Value is VALUE3" << std::endl;
    }

    return 0;
}
