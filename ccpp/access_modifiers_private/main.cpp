#include<iostream>
#include<assert.h>

using namespace std;

class A {
public:
    int a;

    A() {
        a1 = 1;
        a2 = 2;
        a3 = 3;
        a = 4;
    }

    void fun() {
        cout << a << endl;    //正确
        cout << a1 << endl;   //正确
        cout << a2 << endl;   //正确
        cout << a3 << endl;   //正确
    }

public:
    int a1;
protected:
    int a2;
private:
    int a3;
};

// 即便不写 private ，默认的继承方式也是 private
class B : private A {
public:
    int a;

    B(int i) {
        A();
        a = i;
    }

    void fun() {
        cout << a << endl;       //正确，public成员。
        cout << a1 << endl;       //正确，基类public成员,在派生类中变成了private,可以被派生类访问。
        cout << a2 << endl;       //正确，基类的protected成员，在派生类中变成了private,可以被派生类访问。
//        cout << a3 << endl;       //错误，基类的private成员不能被派生类访问。
    }
};

int main() {
    B b(10);
    cout << b.a << endl;       //正确。public成员
//    cout << b.a1 << endl;      //错误，private成员不能在类外访问。
//    cout << b.a2 << endl;      //错误, private成员不能在类外访问。
//    cout << b.a3 << endl;      //错误，private成员不能在类外访问。
    system("pause");
    return 0;
}
