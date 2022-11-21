#include <iostream>

// 还记得 static 么，c 语言中的 static 相当于是一种访问修饰符，static 变量只允许当前的源文件访问。

class base {
public:
    void set_length(double length) {
        this->length = length;
    }

    double get_length(void) {
        return this->length;
    }

protected:
    double width;

private:
    double length;
};

// sub 是 base 的派生
class sub : base {
public:
protected:
private:
};


class box {
public:
    double get_width() {
        return this->width;
    }

protected:
    double width;
    double height = 1;
};

// small_box 是派生类
class small_box : box {
public:
    void set_small_width(double wid);

    double get_small_width(void);

    double get_width() {
        return this->width;
    }

    double get_small_height(){
        return height;
    }

protected:

private:
    double height = 2;
};

// 子类的成员函数
double small_box::get_small_width(void) {
    return width;
}

void small_box::set_small_width(double wid) {
    width = wid;
}

int main() {
    base base_instance;

    base_instance.set_length(2000);
    std::cout << base_instance.get_length() << "\n";

    sub sub_instance;
//    以下代码会报错，因为 get_length 方法未曾在子类定义，
//    java 的继承，是可以直接访问父类受保护和公共的方法的
//    C++ 不行
//    std::cout << sub_instance.get_length() << "\n";

    small_box small_box_instance;
//  不会报错，因为子类自己也定义了 get_width 方法
//  由此可见，继承，是指成员变量的继承，而不是方法的继承
    small_box_instance.set_small_width(999);
    std::cout << small_box_instance.get_width() << "\n";

//  子类可以覆盖父类的成员
    std::cout << small_box_instance.get_small_height() << "\n";


    return 0;
}
