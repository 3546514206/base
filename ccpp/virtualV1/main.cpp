#include <iostream>

using namespace std;

class Animal {
public:
    virtual void speak() {
        cout << "Animal is speaking..." << endl;
    }
};

class Dog : public Animal {
public:
    void speak() override {
        cout << "Dog is barking..." << endl;
    }
};



int main() {
    Animal* animalPtr = new Dog();
    animalPtr->speak(); // 输出 "Dog is barking..."
    return 0;
}
