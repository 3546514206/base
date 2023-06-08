#include <iostream>
#include <list>

int main() {

    std::list<int> my_list;

    my_list.push_back(10);
    my_list.push_back(20);
    my_list.push_back(30);

    my_list.push_front(40);

    std::cout << "List elements: " << std::endl;
    for (const auto &elem: my_list) {
        std::cout << elem << std::endl;
    }

    std::cout << std::endl;

    my_list.pop_front();
    my_list.pop_back();

    // 遍历列表并打印元素
    std::cout << "List elements after deletion: " << std::endl;
    for (const auto &elem: my_list) {
        std::cout << elem << std::endl;
    }

    return 0;
}
