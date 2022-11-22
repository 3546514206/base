#include <iostream>


// 由例子可见，友元函数和友元类只有接受box类型或其引用才有意义
using namespace std;

class box {
private:
    double width;
public:
    // 声明 print_width 函数为 box 类型的友元
    friend void print_width(box box);

    // 声明 big_box 类型为 box 类型的友元
    friend class big_box;

    void set_width(double wid);
};

class big_box {
public :
    void print(int width, box &box) {
        //big_box是box的友元类，它可以直接访问box类的任何成员
        box.set_width(width);
        cout << "Width of box : " << box.width << endl;
    }
};

// 成员函数定义
void box::set_width(double wid) {
    width = wid;
}

// 请注意：print_width() 不是任何类的成员函数，不存在 this 指针
void print_width(box box) {
    /* 因为 print_width() 是 box 的友元，它可以直接访问该类的任何成员 */
    cout << "Width of box : " << box.width << endl;
}

// 程序的主函数
int main() {
    box box_instance;
    big_box big_box_instance;

    // 使用成员函数设置宽度
    box_instance.set_width(10.0);

    // 使用友元函数输出宽度
    print_width(box_instance);

    // 使用友元类中的方法设置宽度
    big_box_instance.print(20, box_instance);

    getchar();
    return 0;
}