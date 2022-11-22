#include <iostream>

using namespace std;

// static 的使用有以下几种情况：
// 静态成员属性（面向对象）
// 静态成员方法（面向对象）
// 静态全局变量（面向过程）
// 静态局部变量（面向过程）
// 静态函数（面向过程）
//
//C/C++ 静态变量的一句话总结：静态变量具有全局变量的生命周期，但只能作用于自己的作用域。
//前半句和 Java 一样，但是后半句和 Java 是相反的。Java 中静态变量拥有全局作用域。

// 对象的静态成员属性
namespace static_class_member_case {

    class the_other_class {
    public:
        static int sum;
    };

    int the_other_class::sum = -100;

    class my_class {
    public:
        my_class(int a, int b, int c);

        void get_sum();

    private:
        int a, b, c;
        static int sum;//声明静态数据成员
    };

    int my_class::sum = 0;    //定义并初始化静态数据成员

    my_class::my_class(int a, int b, int c) {
        this->a = a;
        this->b = b;
        this->c = c;
        sum += a + b + c;
    }

    void my_class::get_sum() {
        cout << "sum=" << sum << endl;
    }

    void test_static_class_member() {
        my_class m(1, 2, 3);
        m.get_sum();
        my_class n(4, 5, 6);
        n.get_sum();
        m.get_sum();

        the_other_class l;

        // 一下两行代码等价
        cout << l.sum << endl;
        cout << static_class_member_case::the_other_class::sum << endl;

        m.get_sum();

    }
}

int main() {
    static_class_member_case::test_static_class_member();

    return 0;
}