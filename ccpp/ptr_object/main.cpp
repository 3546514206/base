#include <iostream>


using namespace std;


//一个指向 C++ 类的指针与指向结构的指针类似，访问指向类的指针的成员，需要使用成员访问运算符 ->，就
// 像访问指向结构的指针一样。与所有的指针一样，您必须在使用指针之前，对指针进行初始化。
class box {
public:
    // 构造函数定义
    box(double l = 2.0, double b = 2.0, double h = 2.0) {
        cout << "Constructor called." << endl;
        length = l;
        breadth = b;
        height = h;
    }

    double volume() {
        return length * breadth * height;
    }

private:
    double length;     // Length of a box
    double breadth;    // Breadth of a box
    double height;     // Height of a box
};

int main(void) {
    box Box1(3.3, 1.2, 1.5);    // Declare box1
    box Box2(8.5, 6.0, 2.0);    // Declare box2
    box *ptrBox;                // Declare pointer to a class.

    // 保存第一个对象的地址
    ptrBox = &Box1;

    // 现在尝试使用成员访问运算符来访问成员
    cout << "volume of Box1: " << ptrBox->volume() << endl;

    // 保存第二个对象的地址
    ptrBox = &Box2;

    // 现在尝试使用成员访问运算符来访问成员
    cout << "volume of Box2: " << ptrBox->volume() << endl;

    return 0;
}
