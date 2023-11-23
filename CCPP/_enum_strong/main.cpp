#include <iostream>

enum class my_enum : int {
    value_1,
    value_2,
    value_3
};

int main() {
    std::cout << "value_1: " << static_cast<int>(my_enum::value_1) << std::endl;
    std::cout << "value_2: " << static_cast<int>(my_enum::value_2) << std::endl;
    std::cout << "value_3: " << static_cast<int>(my_enum::value_3) << std::endl;

    int a = static_cast<int>(my_enum::value_1);
    std::cout << "value_1: " << a << std::endl;

    return 0;
}

