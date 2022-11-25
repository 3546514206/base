#include <iostream>
#include <exception>

class my_exception : public std::exception {
public:

    my_exception(char *msg) : exception_msg(msg) {
    }

    void print_exception_msg() {
        std::cout << this->exception_msg << std::endl;
    }

private:
    const char *exception_msg;


};

int main() {

    try {
        throw new my_exception("系统异常");
    } catch (my_exception *e) {
        e->print_exception_msg();
    }

    return 0;
}
