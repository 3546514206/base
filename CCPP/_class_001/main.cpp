#include <iostream>

using namespace std;

class Box {
public:
    double length;   // 长度
    double breadth;  // 宽度
    double height;   // 高度
};

int main() {
    Box Box1;        // 声明 Box1，类型为 Box
    Box Box2;        // 声明 Box2，类型为 Box
    double volume = 0.0;     // 用于存储体积

    // box 1 详述
    Box1.height = 5.0;
    Box1.length = 6.0;
    Box1.breadth = 7.0;

    // box 2 详述
    Box2.height = 10.0;
    Box2.length = 12.0;
    Box2.breadth = 13.0;

    // box 1 的体积
    volume = Box1.height * Box1.length * Box1.breadth;
    cout << "Box1 的体积：" << volume << endl;

    // box 2 的体积
    volume = Box2.height * Box2.length * Box2.breadth;
    cout << "Box2 的体积：" << volume << endl;
    return 0;
}