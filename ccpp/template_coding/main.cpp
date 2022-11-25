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
    if (elems.empty()) {
        throw std::out_of_range("Stack<>::pop(): empty stack");
    }
    // 删除最后一个元素
    elems.pop_back();
}

template<class T>
T stack<T>::top() const {

}

int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
