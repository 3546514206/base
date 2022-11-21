#include <iostream>
#include <stdlib.h>

// C++ explicit关键字详解
// 首先, C++中的explicit关键字只能用于修饰只有一个参数的类构造函数, 它的作用是表明该构造函数是显示的, 而非隐式的,
// 跟它相对应的另一个关键字是 implicit, 意思是隐藏的,类构造函数默认情况下即声明为implicit(隐式).
// 那么显示声明的构造函数和隐式声明的有什么区别呢? 我们来看下面的例子:


// 没有使用explicit关键字的类声明, 即默认为隐式声明
class test_implicit_class {
public:
    char *_pstr;
    int _size;

    test_implicit_class(int size) {
        // class的预设大小
        _size = size;
        // 分配class的内存
        _pstr = (char *) malloc(size + 1);
        memset(_pstr, 0, size + 1);
    }

    test_implicit_class(const char *p) {
        int size = strlen(p);
        // 分配class的内存
        _pstr = (char *) malloc(size + 1);
        // 复制字符串
        strcpy(_pstr, p);
        _size = strlen(_pstr);
    }
    // 析构函数这里不讨论, 省略...
};

void test_implicit() {
    // 这样是OK的，构造函数调用, 为 class1 预分配24字节的大小的内存
    test_implicit_class class1(24);
//    这样是OK的,为 class2 预分配10字节的大小的内存，隐式调用了构造函数 test_implicit_class(int size)
//    这句为什么是可以的呢? 在C++中, 如果的构造函数只有一个参数时,那么在编译的时候就会有一个缺省的转换操作:将该构造函数对应
//    数据类型的数据转换为该类对象. 也就是说 “ test_implicit_class class2 = 10;” 这段代码, 编译器自动将整型转换为CxString类对象, 实际上等同于下面的操作:
//    step1: test_implicit_class class2;
//    step1: test_implicit_class temp(10);
//    step1: class2 = temp;
    test_implicit_class class2 = 10;
    // 这样是不行的, 因为没有默认构造函数, 错误为: “test_implicit_class”: 没有合适的默认构造函数可用
//    test_implicit_class class3;
// 这样是OK的，构造函数调用
    test_implicit_class class4("aaaa");
    // 这样也是OK的, 调用的是 test_implicit_class(const char *p)
    test_implicit_class class5 = "bbb";
    // 这样也是OK的, 其实调用的是 test_implicit_class(int size), 且size等于'c'的ascii码
    test_implicit_class class6 = 'c';
    // 这样也是OK的, 为 class1 预分配2字节的大小的内存
    class1 = 2;
    // 这样也是OK的, 为 class2 预分配3字节的大小的内存
    class2 = 3;
    // 这样也是OK的, 至少编译是没问题的, 但是如果析构函数里用free释放_pstr内存指针的时候可能会报错, 完整的代码必须重载运算符"=", 并在其中处理内存释放
    test_implicit_class class3 = class1;
}


// 上面的代码中的_size代表的是字符串内存分配的大小, 那么调用的第二句 “test_implicit_class class2 = 10;”
// 和第六句 “test_implicit_class class6 = 'c';” 就显得不伦不类, 而且容易让人疑惑. 有什么办法阻止这种用法呢?
// 答案就是使用explicit关键字. 我们把上面的代码修改一下, 如下:
// 使用关键字explicit的类声明, 显示转换
class test_explicit_class {
public:
    char *_pstr;
    int _size;

    explicit test_explicit_class(int size) {
        _size = size;
        // 代码同上, 省略...
    }

    test_explicit_class(const char *p) {

        // 代码同上, 省略...
    }
};

void test_explicit() {
    // 下面是调用:
    // 这样是OK的，构造函数调用
    test_explicit_class string1(24);
//    test_explicit_class string2 = 10;    // 这样是不行的, 因为explicit关键字取消了隐式转换
//    class test_explicit string3;         // 这样是不行的, 因为没有默认构造函数
    test_explicit_class string4("aaaa"); // 这样是OK的，构造函数调用
    test_explicit_class string5 = "bbb"; // 这样也是OK的, 调用的是 test_explicit(const char *p)
//    test_explicit_class string6 = 'c';   // 这样是不行的, 其实调用的是 test_explicit(int size), 且size等于'c'的ascii码, 但explicit关键字取消了隐式转换
//    test_explicit_class string1 = 2;              // 这样也是不行的, 因为取消了隐式转换
//    test_explicit_class string2 = 3;              // 这样也是不行的, 因为取消了隐式转换
//    test_explicit_class string3 = string1;        // 这样也是不行的, 因为取消了隐式转换, 除非类实现操作符"="的重载
}

//
// explicit关键字只对有一个参数的类构造函数有效, 如果类构造函数参数大于或等于两个时, 是不会产生隐式转换的,所以explicit关键字也就无效了.
class test_explicit_v2_class {
public:
    char *_pstr;
    int _age;
    int _size;

    // explicit关键字在类构造函数参数大于或等于两个时无效
    explicit test_explicit_v2_class(int age, int size) {
        _age = age;
        _size = size;
        // 代码同上, 省略...
    }

//   test_explicit_v2_class(const char *p) 仍然是隐式构造函数
    test_explicit_v2_class(const char *p) {
        // 代码同上, 省略...
    }
};


// 但是, 也有一个例外, 就是当除了第一个参数以外的其他参数都有默认值的时候, explicit关键字依然有效, 此时,
// 当调用构造函数时只传入一个参数, 等效于只有一个参数的类构造函数
class test_explicit_v3_class {
public:
    int _age;
    int _size;

    explicit test_explicit_v3_class(int age, int size = 0) {
        _age = age;
        _size = size;
        // 代码同上, 省略...
    }

    test_explicit_v3_class(const char *p) {
        // 代码同上, 省略...
    }
};

void test_explicit_v3() {
    test_explicit_v3_class string1(24);     // 这样是OK的，构造函数调用
//    test_explicit_v3_class string2 = 10;    // 这样是不行的, 因为explicit关键字取消了隐式转换
//    test_explicit_v3_class string3;         // 这样是不行的, 因为没有默认构造函数
//    string1 = 2;              // 这样也是不行的, 因为取消了隐式转换
//    string2 = 3;              // 这样也是不行的, 因为取消了隐式转换
}


int main() {
    test_implicit();
    test_explicit();
    test_explicit_v3();
    return 0;
}
