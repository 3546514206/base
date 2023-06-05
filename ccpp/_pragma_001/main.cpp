#include <iostream>

#pragma warning(disable:4996)

void deprecatedFunction() {
    std::cout << "This function is deprecated." << std::endl;
}

#pragma warning(push)          // 保存当前警告设置
#pragma warning(disable: 4100) // 禁用未使用参数的警告

void unusedParameterFunction(int param) {
    // 参数未使用
}

// 恢复之前保存的警告设置
#pragma warning(pop)

#pragma once

struct MyStruct {
    int x;
    int y;
};

#pragma pack(push, 1) // 以1字节对齐

struct PackedStruct {
    char a;
    int b;
};

#pragma pack(pop)     // 恢复默认对齐方式

#pragma message("Compiling this file...")

int main() {
    deprecatedFunction();
    unusedParameterFunction(42);

    MyStruct myStruct{};
    myStruct.x = 1;
    myStruct.y = 2;

    PackedStruct packedStruct;
    packedStruct.a = 'A';
    packedStruct.b = 123;

    std::cout << "x: " << myStruct.x << ", y: " << myStruct.y << std::endl;
    std::cout << "a: " << packedStruct.a << ", b: " << packedStruct.b << std::endl;

    return 0;
}