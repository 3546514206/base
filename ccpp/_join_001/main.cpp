#include <iostream>
#include <thread>

void myFunction() {
    for (int i = 0; i < 5; ++i) {
        std::cout << "Thread executing... " << i << std::endl;
    }
}

int main() {
    std::thread myThread(myFunction); // 创建一个新线程，并开始执行myFunction函数
    myThread.join(); // 主线程等待myThread线程执行完成
    std::cout << "Thread execution completed." << std::endl;
    return 0;
}
