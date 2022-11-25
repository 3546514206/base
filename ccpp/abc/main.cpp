#include <iostream>


// 抽象类
class shape {
public:
    virtual int get_area() = 0;

    void set_width(int width);

    void set_height(int height);

protected:
    int width;

    int height;
};

void shape::set_height(int height) {
    this->height = height;
}

void shape::set_width(int width) {
    this->width = width;
}

class rectangle : public shape {
public:
    int get_area();
};

int rectangle::get_area() {
    return this->width * this->height;
}

int main() {

    rectangle rect;

    rect.set_width(5);
    rect.set_height(7);
    // 输出对象的面积
    std::cout << "Total Rectangle area: " << rect.get_area() << std::endl;
    return 0;
}
