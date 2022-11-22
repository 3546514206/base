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
namespace static_class_member_attribute_case {


//    1 静态成员变量是该类的所有对象所共有的。对于普通成员变量，每个类对象都有自己的一份拷贝（浅拷贝）。而静态成员变量一共就一份，无论这个类的对象被定义
//    了多少个，静态成员变量只分配一次内存，由该类的所有对象共享访问。所以，静态数据成员的值对每个对象都是一样的，它的值可以更新；
//    2 因为静态数据成员在全局数据区分配内存，由本类的所有对象共享，所以，它不属于特定的类对象，不占用对象的内存，而是在所有对象之外开辟内存，
//    在没有产生类对象时其作用域就可见。因此，在没有类的实例存在时，静态成员变量就已经存在，我们就可以操作它；
//    3 静态成员变量存储在全局数据区。static 成员变量的内存空间既不是在声明类时分配，也不是在创建对象时分配，而是在初始化时分配。静态成员变
//    量必须初始化，而且只能在类体外进行。否则，编译能通过，链接不能通过。在Example 5中，语句int Myclass::Sum=0;是定义并初始化静态成员
//    变量。初始化时可以赋初值，也可以不赋值。如果不赋值，那么会被默认初始化，一般是 0。静态数据区的变量都有默认的初始值，而动态数据区（堆
//    区、栈区）的变量默认是垃圾值。
//    4 static 成员变量和普通 static 变量一样，编译时在静态数据区分配内存，到程序结束时才释放。这就意味着，static 成员变量不随对象的创
//    建而分配内存，也不随对象的销毁而释放内存。而普通成员变量在对象创建时分配内存，在对象销毁时释放内存。
//    5 静态数据成员初始化与一般数据成员初始化不同。初始化时可以不加 static，但必须要有数据类型。被 private、protected、public 修饰的
//    static 成员变量都可以用这种方式初始化。静态数据成员初始化的格式为：＜数据类型＞＜类名＞::＜静态数据成员名＞=＜值＞
//    6 类的静态成员变量访问形式1：＜类对象名＞.＜静态数据成员名＞
//    7 类的静态成员变量访问形式2：＜类类型名＞::＜静态数据成员名＞，也即，静态成员不需要通过对象就能访问。
//    8 静态数据成员和普通数据成员一样遵从public,protected,private访问规则；
//    9 如果静态数据成员的访问权限允许的话（即public的成员），可在程序中，按上述格式来引用静态数据成员 ；
//    10 sizeof 运算符不会计算 静态成员变量。
//    class CMyclass{
//        int n;
//        static int s;
//    };    //则sizeof（CMyclass）等于4

//   何时使用静态成员变量？
//   设置静态成员（变量和函数）这种机制的目的是将某些和类紧密相关的全局变量和函数写到类里面，看上去像一个整体，易于理解和维护。如果想在同
//   类的多个对象之间实现数据共享，又不要用全局变量，那么就可以使用静态成员变量。也即，静态数据成员主要用在各个对象都有相同的某项属性的时候。
//   比如对于一个存款类，每个实例的利息都是相同的。所以，应该把利息设为存款类的静态数据成员。这有两个好处：
//   1、不管定义多少个存款类对象，利息数据成员都共享分配在全局数据区的内存，节省存储空间。
//   2、一旦利息需要改变时，只要改变一次，则所有存款类对象的利息全改变过来了。
//  你也许会问，用全局变量不是也可以达到这个效果吗？

//  同全局变量相比，使用静态数据成员有两个优势：
//  1、静态成员变量没有进入程序的全局命名空间，因此不存在与程序中其它全局命名冲突的可能。
//  2、可以实现信息隐藏。静态成员变量可以是private成员，而全局变量不能。
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

    void test_static_class_attribute() {
        my_class m(1, 2, 3);
        m.get_sum();
        my_class n(4, 5, 6);
        n.get_sum();
        m.get_sum();

        the_other_class l;
        the_other_class i;
        // 一下两行代码等价
//        在 C++ 中和 Java 中，都可以通过类型或者对象访问静态成员属性，但是不推荐以对象的方式访问
        cout << l.sum << endl;
        cout << static_class_member_attribute_case::the_other_class::sum << endl;

        // 以下地址是相同的
        cout << &l.sum << endl;
        cout << &i.sum << endl;
        m.get_sum();

    }
}

namespace static_class_member_method_case {

//    出现在类体外的函数定义不能指定关键字static；
//    静态成员之间可以相互访问，即静态成员函数（仅）可以访问静态成员变量、静态成员函数；
//    静态成员函数不能访问非静态成员函数和非静态成员变量；
//    非静态成员函数可以任意地访问静态成员函数和静态数据成员；
//    由于没有this指针的额外开销，静态成员函数与类的全局函数相比速度上会稍快；
//    调用静态成员函数，两种方式：
//      1 通过成员访问操作符(.)和(->)，即通过类对象或指向类对象的指针调用静态成员函数。
//      2 直接通过类来调用静态成员函数。＜类名＞::＜静态成员函数名＞（＜参数表＞）。也即，静态成员不需要通过对象就能访问。

//    拷贝构造函数的问题
//  在使用包含静态成员的类时，有时候会调用拷贝构造函数生成临时的隐藏的类对象，而这个临时对象在消亡时会调用析构函数有可能会对静态
//  变量做操作（例如total_num--），可是这些对象在生成时却没有执行构造函数中的total_num++的操作。解决方案是为这个类写一个拷贝
//  构造函数，在该拷贝构造函数中完成total_num++的操作。

    class student {
    private:
        char *name;
        int age;
        float score;
        static int num;    //学生人数
        static float total;  //总分
    public:
        student(char *, int, float);

        void say();

        static float get_average();  //静态成员函数，用来获得平均成绩
    };

    int student::num = 0;
    float student::total = 0;

    student::student(char *name, int age, float score) {
        this->name = name;
        this->age = age;
        this->score = score;
        num++;
        total += score;
    }

    void student::say() {
        cout << name << "的年龄是 " << age << "，成绩是 " << score << "（当前共" << num << "名学生）" << endl;
    }

//    外部声明不在需要 static 关键字
    float student::get_average() {
        return total / num;
    }

    void test_static_class_method() {
        (new student("小明", 15, 90))->say();
        (new student("李磊", 16, 80))->say();
        (new student("张华", 16, 99))->say();
        (new student("王康", 14, 60))->say();
        cout << "平均成绩为 " << student::get_average() << endl;

    }
}


void test_static_global_variable();

void test_static_locality_variable();

static void test_static_function_v1();//声明静态函数

static void test_static_function_v2() {
    int n = 99;
    cout << n << endl;
}

//静态全局变量有以下特点：
//1、该变量在全局数据区分配内存；
//2、未经初始化的静态全局变量会被程序自动初始化为0（自动变量的自动初始化值是随机的）；
//3、静态全局变量在声明它的整个文件都是可见的，而在文件之外是不可见的； 　
//4、静态变量都在全局数据区分配内存，包括后面将要提到的静态局部变量。对于一个完整的程序，在内存中的分布
// 情况如下：【代码区】【全局数据区】【堆区】【栈区】，一般程序的由new产生的动态数据存放在堆区，函数内部的自动变量存放在栈区，静
// 态数据（即使是函数内部的静态局部变量）存放在全局数据区。自动变量一般会随着函数的退出而释放空间，而全局数据区的数据并不会因为函
// 数的退出而释放空间。
static int n; //定义静态全局变量

//定义全局变量就可以实现变量在文件中的共享，但定义静态全局变量还有以下好处：
//1、静态全局变量不能被其它文件所用；
//2、其它文件中可以定义相同名字的变量，不会发生冲突；
int m; //定义全局变量

int main() {
//    静态成员属性（面向对象）
//    static_class_member_attribute_case::test_static_class_attribute();
//    静态成员方法（面向对象）
//    static_class_member_method_case::test_static_class_method();
//    test_static_global_variable();
//    调两次，观察 v 的值
//    test_static_locality_variable();
//    test_static_locality_variable();

    test_static_function_v1();
    test_static_function_v2();

    return 0;
}

// 静态全局变量
void test_static_global_variable() {
    n++;
    cout << n << endl;
}


//静态局部变量有以下特点：
//1、静态局部变量在全局数据区分配内存；
//2、静态局部变量在程序执行到该对象的声明处时被首次初始化，即以后的函数调用不再进行初始化；
//3、静态局部变量一般在声明处初始化，如果没有显式初始化，会被程序自动初始化为0；
//4、静态局部变量始终驻留在全局数据区，直到程序运行结束。但其作用域为局部作用域，当定义它的函数或语句块结束时，其作用域随之结束；
void test_static_locality_variable() {
    static int v = 10;
    v++;
    cout << v << endl;
}

//定义静态函数的好处：（类似于静态全局变量）:
//静态函数不能被其它文件所用；
//其它文件中可以定义相同名字的函数，不会发生冲突；
void test_static_function_v1() {
    int n = 10;
    cout << n << endl;
}

