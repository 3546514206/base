#include <iostream>
#include <vector>

int main() {
    std::vector<int> numbers;

    numbers.push_back(10);
    numbers.push_back(20);
    numbers.push_back(30);

    // 使用迭代器遍历vector并打印元素
    std::cout << "Elements in vector: "<< std::endl;

    for (std::vector<int>::iterator it = numbers.begin(); it != numbers.end(); ++it) {
        std::cout << *it << std::endl;
    }

    numbers[1] = 50;
    // 使用范围-based循环遍历vector并打印元素
    std::cout << "Modified elements in vector: "<< std::endl;

    for (int number: numbers) {
        std::cout << number << std::endl;
    }

    // 获取vector的大小
    std::cout << "Size of vector: " << numbers.size() << std::endl;

    numbers.clear();

    if (numbers.empty()) {
        std::cout << "Vector is empty" << std::endl;
    }

    return 0;
}
