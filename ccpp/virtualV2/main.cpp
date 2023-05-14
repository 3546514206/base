#include <iostream>

using namespace std;

class Animal {
public:
     void speak() {
        cout << "Animal is speaking..." << endl;
    }
};

class Dog : public Animal {
public:
    void speak() {
        cout << "Dog is barking..." << endl;
    }
};


int main() {
    Animal* animalPtr = new Dog();
    // 输出 "Dog is barking..."
    animalPtr->speak();

    return 0;
}
