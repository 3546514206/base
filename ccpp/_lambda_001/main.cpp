#include <iostream>
#include <vector>
#include <algorithm>

int main() {
    std::vector<int> numbers = {1, 2, 3, 4, 5};

    // 使用Lambda表达式打印每个元素
    std::for_each(numbers.begin(), numbers.end(), [](int num) {
        std::cout << num << std::endl;
    });

    return 0;
}
