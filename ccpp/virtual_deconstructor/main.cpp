#include <iostream>


using namespace std;

class GrandFather {
public:
    GrandFather() {
        cout << "GrandFather created!" << endl;
    }

    virtual void fun() {
        cout << "GrandFather call function!" << endl;
    }

    virtual ~GrandFather() {
//         ~GrandFather() {
        cout << "GrandFather destruction!" << endl;
    }
};


class Father : public GrandFather {
public:
    Father() {
        cout << "Father created!" << endl;
    }

    void fun() {
        cout << "Father call function!" << endl;
    }

    ~Father() {
        cout << "Father destruction!" << endl;
    }
};

class Son : public Father {
public:
    Son() {
        cout << "Son created!" << endl;
    }

    void fun() {
        cout << "Son call function!" << endl;
    }

    ~Son() {
        cout << "Son destruction!" << endl;
    }
};

void print(GrandFather *p) {
    p->fun();
}

int main() {
    Father *pfather = new Son;
    delete pfather;
    return 0;
}