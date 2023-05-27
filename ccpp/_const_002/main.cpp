#include <iostream>
using namespace std;

class Person {
public:
    Person(string name) : name(name) {}

    void setName(string name) {
        this->name = name;
    }

    void printName() const {
        cout << "My name is " << name << endl;
    }

private:
    string name;
};

void foo(const Person& p) {
//     p.setName("test");  // 不能修改常量对象的数据成员
     p.printName();
}

int main() {
    const Person p("Alice");
    foo(p);

    return 0;
}
