#include <iostream>


class base_a {
public:
    virtual void do_something() {
        std::cout << "base_a doing" << std::endl;
    }
};

class base_b {
public:
//
    virtual void do_something() {
        std::cout << "base_a doing" << std::endl;
    }
};

class sub_class : public base_a, public base_b {

};

int main() {
    sub_class *sub = new sub_class;

    sub->base_a::do_something();

    //会报错，无法通过编译检查
//    sub->do_something();
    sub->base_b::do_something();

    delete sub;

    return 0;
}
