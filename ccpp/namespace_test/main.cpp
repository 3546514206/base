
#include <iostream>

using namespace std;

// 第一个命名空间
namespace first_space {
    void func() {
        cout << "Inside first_space" << endl;
    }
}
// 第二个命名空间
namespace second_space {
    void func() {
        cout << "Inside second_space" << endl;
    }

    // 第二个命名空间
    namespace third_space {
        void func() {
            cout << "Inside third_space" << endl;
        }
    }
}

int main() {

    // 调用第一个命名空间中的函数
    first_space::func();

    // 调用第二个命名空间中的函数
    second_space::func();

    second_space::third_space::func();

    return 0;
}