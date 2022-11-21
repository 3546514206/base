#include <iostream>

using namespace std;

//类的析构函数是类的一种特殊的成员函数，它会在每次删除所创建的对象时执行。
//析构函数的名称与类的名称是完全相同的，只是在前面加了个波浪号（~）作为前缀，
// 它不会返回任何值，也不能带有任何参数(构造函数式可以带参数的！！！)。析构函数有
// 助于在跳出程序（比如关闭文件、释放内存等）前释放资源。

// 调用时机：
// 撤销类对象时会自动调用析构函数；
// 变量在超出作用域时会自动撤销；
// 动态分配的对象只有在指向该对象的指针被删除时才撤销；
// 如果没有删除指向动态对象的指针，则不会运行该对象的析构函数，对象会一直存在，从而导致内存泄漏。
//
// PS：当对象的引用或者指针超出作用域时，不会运行析构函数。
// 只有删除指向动态分配对象的指针或者实际对象（而不是对象的指针）超出作用域时，才会运行析构函数。

// 下面的实例有助于更好地理解析构函数的概念：

class line {
public:
    void set_length(double len);

    double get_length(void);

    line();   // 这是构造函数声明
    line(int length);

    ~line();  // 这是析构函数声明

private:
    double length;
};

// 成员函数定义，包括构造函数
line::line(void) {
    cout<< this << endl;
    cout << "Object is being created" << endl;
}

line::line(int length) : length(length) {
//    this 指针指向调用该方法的对象
    cout<< this << endl;
    cout << "Object is being created,length is " << this->length << endl;
}

line::~line(void) {
    cout << "Object is being deleted，length is " << this->length << endl;
}

void line::set_length(double len) {
    length = len;
}

double line::get_length(void) {
    return length;
}

// 程序的主函数
int main() {
    // 直接声明的对象可以不用管，本质上该对象是当前栈帧的一个局部变量
//    超过当前作用域，该对象自行消失
    line line_instance;

    // 设置长度
    line_instance.set_length(6.0);
    cout << "Length of line : " << line_instance.get_length() << endl;

    line *l_instance = new line(100);
    // 动态分配的对象要删除其指针，否则该对象的析构函数不会执行，该对象一直存在
    delete l_instance;

    return 0;

}