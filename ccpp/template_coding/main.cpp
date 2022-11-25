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
void stack<T>::push(T const &) {

}


int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
