#include <iostream>

//一元运算符只对一个操作数进行操作，
//一元运算符通常出现在它们所操作的对象的左边，比如 !obj、-obj 和 ++obj，但有时它们也可以作为后缀，比如 obj++ 或 obj--。
//下面的实例演示了如何重载一元减运算符（ - ）。


class distance {
public:
    distance();

    distance(int f, int i);

    void display_distance();

    distance operator-();

private:
    int feet;
    int inches;
};

distance::distance() {
    this->feet = 0;
    this->inches = 0;
}

distance::distance(int f, int i) {
    this->inches = i;
    this->feet = f;
}

void distance::display_distance() {
    std::cout << "F: " << this->feet << std::endl;
    std::cout << "I: " << this->inches << std::endl;
}

distance distance::operator-() {
    this->feet = -feet;
    this->inches = -inches;

    return *this;
}

int main() {

    distance *distance1 = new distance(2, 3);
//  没有参数的话可以省略括号
    distance *distance2 = new distance(1, 2);

    distance1->display_distance();
    distance2->display_distance();

//    - 运算符不能对指针操作
    distance distance3 = -(*distance1);
    distance3.display_distance();

    return 0;
}
