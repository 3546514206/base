#include <iostream>

class animal {
public:
    virtual void speak() const = 0;
};

class cat : public animal {
public:
    void speak() const override;
};

class dog : public animal {
public:
    void speak() const override;
};

int main() {

    animal *cat_ptr = new cat;
    cat_ptr->speak();
    animal *dog_ptr = new dog;
    dog_ptr->speak();

    return 0;
}

void cat::speak() const {
    std::cout << "Meow!" << std::endl;
}

void dog::speak() const {
    std::cout << "Woof!" << std::endl;
}
