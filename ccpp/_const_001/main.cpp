#include <iostream>

using namespace std;

class Person {
public:
    Person(string name) : name(name) {}

    void setName(string name) {
        this->name = name;
    }

    void printName() const {
        name = "test";
        cout << "My name is " << name << endl;
    }

private:
    string name;
};

int main() {
    Person p1("Alice");
    const Person p2("Bob");

    p1.printName();  // 通过非常量对象调用常量成员函数
    p2.printName();  // 通过常量对象调用常量成员函数

    // p2.setName("Charlie");  // 不能调用非常量成员函数来修改常量对象的数据成员

    return 0;
}
