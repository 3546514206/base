#include <iostream>


class box {
public:
    inline double get_volume();

    void set_length(double length);

    void set_width(double width);

    void set_height(double height);

    box operator+(const box &b);

private:
    double length;
    double width;
    double height;
};

double box::get_volume() {
    return this->height * this->width * this->length;
}

void box::set_length(double length) {
    this->length = length;
}

void box::set_width(double width) {
    this->width = width;
}

void box::set_height(double height) {
    this->height = height;
}

//您可以重定义或重载大部分 C++ 内置的运算符。这样，您就能使用自定义类型的运算符。
//重载的运算符是带有特殊名称的函数，函数名是由关键字 operator 和其后要重载的运算
//符符号构成的。与其他函数一样，重载运算符有一个返回类型和一个参数列表。例如：
//      Box operator+(const Box&);
//运算符重载实际上改变了运算符的操作元素
box box::operator+(const box &b) {
    box *new_box = new box;
    new_box->width = this->width + b.width;
    new_box->length = this->length + b.length;
    new_box->height = this->height + b.height;
    return *new_box;
}

int main() {

    box box1;
    box box2;
    box box3;

    box1.set_length(3);
    box1.set_width(6);
    box1.set_height(1);

    box2.set_length(2);
    box2.set_width(2);
    box2.set_height(1);

    box3 = box1 + box2;

    std::cout << "box1 的体积：" << box1.get_volume() << std::endl;
    std::cout << "box2 的体积：" << box2.get_volume() << std::endl;
    std::cout << "box3 的体积：" << box3.get_volume() << std::endl;

    return 0;
}







