#include <iostream>
#include <pthread.h>

#define NUM_OF_THREAD 5

void * do_something(void *args) {
    std::cout << (int *) args << std::endl;
}

int main() {
    pthread_t tread_ids[NUM_OF_THREAD];
    int params[] = {9, 8, 7, 6, 0};
    for (int i = 0; i < NUM_OF_THREAD; ++i) {
        int ret = pthread_create(&tread_ids[i], NULL, do_something, &params[i]);
        if (ret != 0) {
            std::cout << "pthread_create error: error_code=" << ret << std::endl;
        }
    }


    //等各个线程退出后，进程才结束，否则进程强制结束了，线程可能还没反应过来；
    pthread_exit(NULL);
}
