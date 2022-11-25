#include <iostream>
#include <csignal>
#include <unistd.h>

// 信号处理测试
void signal_handler(int sig_num) {
    std::cout << "Interrupt signal (" << sig_num << ") received.\n";

    // 清理并关闭
    // 终止程序

    exit(sig_num);
}

int main() {
    // 向操作系统注册信号处理函数
    signal(SIGFPE, signal_handler);
    int i = 0;

    while (true) {
        i++;
        std::cout << "Going to sleep...." << std::endl;
        if (i == 5) {
            int j = i / 0;
            std::cout << "division by zero" << std::endl;
        }
        sleep(1);
    }

    return 0;
    return 0;
}
