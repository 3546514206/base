#include <iostream>
#include <vector>
#include <cstdlib>
#include <string>
#include <stdexcept>

template<class T>
class stack {
private:
    std::vector<T> elems;

public:
    void push(T const &);

    void pop();

    T top() const;

    bool is_empty() {
        return this->elems.empty();
    }
};

template<class T>
void stack<T>::push(T const &elem) {
    elems.push_back(elem);
}

template<class T>
void stack<T>::pop() {
    if (this->elems.empty()) {
        throw std::out_of_range("stack<>::pop(): empty stack");
    }
    // 删除最后一个元素
    elems.pop_back();
}


template<class T>
T stack<T>::top() const {
    if (elems.empty()) {
        throw std::out_of_range("Stack<>::top(): empty stack");
    }
    // 返回最后一个元素的副本
    return elems.back();
}

int main() {
    stack<int> int_stack;
    stack<std::string> string_stack;

    int_stack.push(7);
    int_stack.pop();

    string_stack.push("hello");
    std::cout << string_stack.top() << std::endl;

    return 0;
}
