#include <iostream>
#include <string>

//小括号初始化
std::string str("hello");

//等号初始化
std::string str2 = "hello";

//POD对象与POD数组列表初始化
struct Studnet {
    char *name;
    int age;
};
Studnet s = {"dablelv", 18}; //纯数据（Plain of Data,POD）类型对象
Studnet sArr[] = {{"dablelv", 18},
                  {"tommy",   19}};  //POD数组

//构造函数的初始化列表
class Class {
    int x;
public:
    Class() : x(0) {}
};


int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
