#include <iostream>

class Singleton {
public:

    static Singleton &getInstance();

private:
    // 私有化构造函数
    Singleton();

    // 禁用拷贝构造函数和赋值操作符
    Singleton(const Singleton &) = delete;

    Singleton &operator=(const Singleton &) = delete;
};

Singleton::Singleton() {
    // do nothing
}

Singleton &Singleton::getInstance() {
    // 在首次调用时初始化，保证线程安全
    static Singleton instance;
    return instance;
}

int main() {
    Singleton& s1 = Singleton::getInstance();
    Singleton& s2 = Singleton::getInstance();

    if (&s1 == &s2) {
        // s1 和 s2 引用相同的单例对象
        std::cout << "s1 and s2 are the same instance." << std::endl;
    } else {
        std::cout << "s1 and s2 are different instances." << std::endl;
    }

}
