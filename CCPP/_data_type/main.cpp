#include <iostream>

int main() {
    // char 的大小是一个字节
    std::cout << "size of char " << sizeof(char) << std::endl;
    // 短整型
    std::cout << "size of short int " << sizeof(short int) << std::endl;
    // int 的大小
    std::cout << "size of int " << sizeof(int) << std::endl;
    // long int 的大小
    std::cout << "size of long int " << sizeof(long int) << std::endl;
    // 浮点型大小
    std::cout << "size of float " << sizeof(float) << std::endl;
    // double 的大小
    std::cout << "size of double " << sizeof(double) << std::endl;
    // 宽字符类型的大小
    std::cout<< "size of wchar_t " << sizeof(wchar_t ) << std::endl;

    return 0;
}
