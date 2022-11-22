#include <iostream>

// 当类中没有定义拷贝构造函数时，编译器会默认提供一个拷贝构造函数，进行成员变量之间的拷贝。
// 当类中存在指针成员的时候，并且析构函数正好释放这个指针的时候就会报错。
// 虽然所有的变量赋值都是深度拷贝，对于指针而言也是，程序会为新对象创建新的一个指针变量(打印&p即可知道)，
// 但是两个指针变量所指向的地址是一致的

class test_cls {
public:
    int *p;

    //无参构造函数
    test_cls() {
        std::cout << "无参构造函数" << std::endl;
        p = new int;
    }

//析构函数
    ~test_cls() {
        delete p;
        std::cout << "析构函数" << std::endl;
    }
};


int main(void) {
    test_cls t1;
    test_cls t2 = t1;   //效果等同于TestCls t2(t1);


    std::cout << t1.p << std::endl;
    std::cout << t2.p << std::endl;
    std::cout << &t1.p << std::endl;
    std::cout << &t2.p << std::endl;
    return 0;
}

