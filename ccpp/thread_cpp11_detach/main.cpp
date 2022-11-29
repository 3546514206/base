#include <iostream>
#include <thread>
#include <unistd.h>

void thread_1_function(int n){
    sleep(5);
    std::cout<< "thread is running!" << "n= " << std::endl;
}

int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
