#include <iostream>
#include <thread>

using namespace std;

void threadFun1(int n) {
    cout << "---thread1 running\n";
    cout << "n=" << n << endl;
}

void threadFun2(const char *url) {
    cout << "---thread2 running\n";
    cout << "url=" << url << endl;
}

int main() {
    //调用第 1 种构造函数
    thread thread1(threadFun1, 10);
    //调用移动构造函数
    thread thread2 = std::thread(threadFun2, "http://c.biancheng.net");
    //阻塞主线程，等待 thread1 线程执行完毕
    thread1.join();
    //阻塞主线程，等待 thread2 线程执行完毕
    thread2.join();
    return 0;
}