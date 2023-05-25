#include <iostream>

using namespace std;

// 函数宏配合 # 使用，#将函数参数转换为用引号引起来的字符串。
#define MKSTR(x) #x

int main() {
    cout << MKSTR(HELLO C++) << endl;

    return 0;
}