#include <iostream>


#include "singleton.h"

int main() {
    singleton &singleton_instance = singleton::get_singleton();
    singleton_instance.do_something();
    return 0;
}
