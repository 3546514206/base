#include <iostream>


int main() {

    int array[] = {10, 20, 300};

    int *ptr = array;
    for (int i = 0; i < 3; ++i) {
        std::cout << *ptr << std::endl;
        ptr++;
    }

    // 指针运算符 * 应用到 array + i 上是完全可以接受的，但修改 array 的值是非法的。这是因为 array 是一个指向数组开头的常量，不能作为左值。
    // 由于一个数组名对应一个指针常量，只要不改变数组的值，仍然可以用指针形式的表达式。
    int* ptr_tmp = array;
    for (int i = 0; i < 3; ++i) {
        *(array + i) = i;

        // 非法
        // array = i;
        std::cout << *ptr << std::endl;
        ptr++;
    }

    return 0;
}
