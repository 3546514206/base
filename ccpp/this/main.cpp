#include <iostream>

using namespace std;

//在 C++ 中，每一个对象都能通过 this 指针来访问自己的地址。this 指针是所有
//成员函数的隐含参数。因此，在成员函数内部，它可以用来指向调用对象。
//友元函数没有 this 指针，因为友元不是类的成员。只有成员函数才有 this 指针。
class box {
public:
    // 构造函数定义
    box(double l = 2.0, double b = 2.0, double h = 2.0) {
        cout << "Constructor called." << endl;
        length = l;
        breadth = b;
        height = h;
    }

//  得到this的地址
    box *get_address() {
        return this;
    }

    double volume() {
        return length * breadth * height;
    }

    int compare(box box) {
        return this->volume() > box.volume();
    }

private:
    double length;     // Length of a box
    double breadth;    // Breadth of a box
    double height;     // Height of a box
};

int main() {
    box box1(3.3, 1.2, 1.5);    // Declare box1
    box box2(8.5, 6.0, 2.0);    // Declare box2

    if (box1.compare(box2)) {
        cout << "box2 is smaller than box1" << endl;
    } else {
        cout << "box2 is equal to or larger than box1" << endl;
    }

    cout << "box1's address is " << box1.get_address() << endl;
    cout << "box2's address is " << box2.get_address() << endl;
    return 0;
}