#include <iostream>
#include <string>


//拷贝和复制是一个意思，对应的英文单词都是copy。对于计算机来说，拷贝是指用一份原有的、已经存在的数据创建出一份新的数据，
// 最终的结果是多了一份相同的数据。例如，将 Word 文档拷贝到U盘去复印店打印，将 D 盘的图片拷贝到桌面以方便浏览，将重要
// 的文件上传到百度网盘以防止丢失等，都是「创建一份新数据」的意思。

//在 C++ 中，拷贝并没有脱离它本来的含义，只是将这个含义进行了“特化”，是指用已经存在的对象创建出一个新的对象。从本质上讲，
// 对象也是一份数据，因为它会占用内存。

//严格来说，对象的创建包括两个阶段，首先要分配内存空间，然后再进行初始化：
//1）分配内存很好理解，就是在堆区、栈区或者全局数据区留出足够多的字节。这个时候的内存还比较“原始”，没有被“教化”，它所包含的
// 数据一般是零值或者随机值，没有实际的意义。
//2）初始化就是首次对内存赋值，让它的数据有意义。注意是首次赋值，再次赋值不叫初始化。初始化的时候还可以为对象分配其他的资源
// （打开文件、连接网络、动态分配内存等），或者提前进行一些计算（根据价格和数量计算出总价、根据长度和宽度计算出矩形的面积等）
// 等。说白了，初始化就是调用构造函数。


using namespace std;

class student {
public:
    student(string name = "", int age = 0, float score = 0.0f);  //普通构造函数
    student(const student &stu);  //拷贝构造函数（声明）

//    1) 为什么必须是当前类的引用呢？
//    如果拷贝构造函数的参数不是当前类的指针，而是当前类的对象，那么在调用拷贝构造函数时，会将另外一个对象直接传递给形参，这本身就是
//    一次拷贝，会再次调用拷贝构造函数，然后又将一个对象直接传递给了形参，将继续调用拷贝构造函数……这个过程会一直持续下去，没有尽头，陷入死循环。
//    只有当参数是当前类的引用时，才不会导致再次调用拷贝构造函数，这不仅是逻辑上的要求，也是 C++ 语法的要求。
//    2) 为什么是 const 引用呢？
//    拷贝构造函数的目的是用其它对象的数据来初始化当前对象，并没有期望更改其它对象的数据，添加 const 限制后，这个含义更加明确了。
//    另外一个原因是，添加 const 限制后，可以将 const 对象和非 const 对象传递给形参了，因为非 const 类型可以转换为 const 类型。如果没有
//    const 限制，就不能将 const 对象传递给形参，因为 const 类型不能转换为非 const 类型，这就意味着，不能使用 const 对象来初始化当前对象了。

    int address;

    void display();

private:
    string m_name;
    int m_age;
    float m_score;
};

student::student(string name, int age, float score) : m_name(name), m_age(age), m_score(score) {}

//拷贝构造函数（定义）
student::student(const student &stu) {
    this->m_name = stu.m_name;
    this->m_age = stu.m_age;
    this->m_score = stu.m_score;

    cout << "Copy constructor was called." << endl;
}

void student::display() {
    cout << m_name << "的年龄是" << m_age << "，成绩是" << m_score << endl;
}

int main() {
    student stu1("小明", 16, 90.5);
    student stu2 = stu1;  //调用拷贝构造函数，隐式调用了 student(const student &stu);
    student stu3(stu1);  //调用拷贝构造函数
    stu1.display();
    stu2.display();
    stu3.display();
    cout << endl;
//    对象不是同一个对象
    cout << "stu1 的地址" << &stu1 << endl;
    cout << "stu2 的地址" << &stu2 << endl;
    cout << "stu3 的地址" << &stu3 << endl;
// 相同字段地址也不一样
    cout << "stu1.address 的地址" << &(stu1.address) << endl;
    cout << "stu2.address 的地址" << &(stu2.address) << endl;
    return 0;
}