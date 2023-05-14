#include <iostream>

class complex {
public:
//    complex(double x = 1, double y = 2) : re(x), im(y) {
//        std::cout<<"test 0001"<<std::endl;
//    }

    complex() : re(0), im(0) {
        std::cout<<"test 0002"<<std::endl;
    }

private:
    double
//    实部
    re,
//    虚部
    im;
};

int main() {
//    构造函数存在歧义
//    complex c1;
//    构造函数存在歧义
//    complex c2();
// 构造函数不存在歧义
    complex c2;
    return 0;
}
