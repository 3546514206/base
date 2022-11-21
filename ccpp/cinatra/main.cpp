#include <iostream>

#include <boost/version.hpp>
#include <boost/config.hpp>

void test_boost(){
    std::cout << BOOST_VERSION << std::endl;		//	boost版本号
    std::cout << BOOST_LIB_VERSION << std::endl;	//	boost版本号
    std::cout << BOOST_PLATFORM << std::endl;		//	操作系统
    std::cout << BOOST_COMPILER << std::endl;		//	编译器
    std::cout << BOOST_STDLIB << std::endl;			//	标准库
}

int main() {
    test_boost();
    return 0;
}
