#include <iostream>

using namespace std;

// 基类 shape
class shape {
public:
    void set_width(int w) {
        width = w;
    }

    void set_height(int h) {
        height = h;
    }

protected:
    int width;
    int height;
};

// 基类 paint_cost
class paint_cost {
public:
    int get_cost(int area) {
        return area * 70;
    }
};

// 派生类
class rectangle : public shape, public paint_cost {
public:
    int get_area() {
        return (width * height);
    }
};

int main() {
    rectangle rect;
    int area;

    rect.set_width(5);
    rect.set_height(7);

    area = rect.get_area();

    // 输出对象的面积
    cout << "Total area: " << rect.get_area() << endl;

    // 输出总花费
    cout << "Total paint cost: $" << rect.get_cost(area) << endl;

    return 0;
}