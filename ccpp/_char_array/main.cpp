#include <iostream>
#include <cstring>

const int MAX_SIZE = 100;

int main() {
    char arr1[MAX_SIZE];
    char arr2[MAX_SIZE];

    // 输入字符数组
    std::cout << "请输入第一个字符数组: ";
    std::cin.getline(arr1, MAX_SIZE);

    std::cout << "请输入第二个字符数组: ";
    std::cin.getline(arr2, MAX_SIZE);

    // 输出字符数组
    std::cout << "第一个字符数组为: " << arr1 << std::endl;
    std::cout << "第二个字符数组为: " << arr2 << std::endl;

    // 遍历字符数组并打印每个字符
    std::cout << "遍历第一个字符数组: ";
    for (int i = 0; i < strlen(arr1); i++) {
        std::cout << arr1[i] << " ";
    }
    std::cout << std::endl;

    // 获取字符数组的长度
    int length = strlen(arr2);
    std::cout << "第二个字符数组的长度为: " << length << std::endl;

    // 拷贝字符数组
    char arr3[MAX_SIZE];
    strcpy(arr3, arr1);
    std::cout << "拷贝第一个字符数组到第三个字符数组: " << arr3 << std::endl;

    // 连接字符数组
    strcat(arr1, arr2);
    std::cout << "连接第一个字符数组和第二个字符数组: " << arr1 << std::endl;

    // 比较字符数组
    int cmp = strcmp(arr2, arr3);
    if (cmp == 0) {
        std::cout << "第二个字符数组等于第三个字符数组" << std::endl;
    } else if (cmp < 0) {
        std::cout << "第二个字符数组小于第三个字符数组" << std::endl;
    } else {
        std::cout << "第二个字符数组大于第三个字符数组" << std::endl;
    }

    // 搜索字符数组
    char *ptr = strstr(arr1, arr2);
    if (ptr != nullptr) {
        std::cout << "第二个字符数组在第一个字符数组中的位置为: " << (ptr - arr1) << std::endl;
    } else {
        std::cout << "第二个字符数组不在第一个字符数组中" << std::endl;
    }

    return 0;
}
