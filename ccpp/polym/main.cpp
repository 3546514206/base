#include <iostream>

class speaker {
public:
    virtual void do_speak() = 0;
};

class dog : public speaker {
public:
    void do_speak() override {
        std::cout << "wang!!" << std::endl;
    }
};

class people : public speaker {
public:
    void do_speak() override {
        std::cout << "hello" << std::endl;
    }
};


int main() {
    speaker *dog_inst_ptr = (speaker *) new dog;
    speaker *people_inst_ptr = (speaker *) new people;
    dog_inst_ptr->do_speak();
    people_inst_ptr->do_speak();

    return 0;
}
