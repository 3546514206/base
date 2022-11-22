#include <iostream>

using namespace std;

// virtual 修饰的方法类似于 java 中抽象类或者接口的抽象方法，是希望子类去重写（实现）的
class GrandFather {
public:
    GrandFather() {}


    virtual void fun() {
        cout << "GrandFather call function!" << endl;
    }
};


class Father : public GrandFather {
public:
    Father() {}


    void fun() {
        cout << "Father call function!" << endl;
    }
};


class Son : public Father {
public:
    Son() {}


    void fun() {
        cout << "Son call function!" << endl;
    }
};


void print(GrandFather *father) {
    father->fun();
}


int main() {
//    c++ 也有上转型，动态绑定
    Father *pfather = new Son;
    pfather->fun();
    GrandFather *pgfather = new Father;
    print(pgfather);
    return 0;
}
