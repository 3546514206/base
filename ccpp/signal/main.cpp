#include <iostream>
#include <csignal>
#include <unistd.h>

using namespace std;

void signal_handler(int signum) {
    cout <<"\n";
    cout << "Interrupt signal (" << signum << ") received.\n";

    // 清理并关闭
    // 终止程序

    exit(signum);

}

int main() {
    // 注册信号 SIGINT 和信号处理程序
    signal(SIGINT, signal_handler);

    while (1) {
        cout << "Going to sleep...." << endl;
        sleep(1);
    }

    return 0;
}