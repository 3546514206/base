#include <iostream>

#include <pthread.h>

#define NUM_THREADS 5

struct param {

    int a;

    char b;
};

void *say_hello(void *args) {

    auto *p = (param *) args;

    std::cout << "hello " << p->b << std::endl;

    return p;
}

int main() {

    pthread_t tids[NUM_THREADS];
    param param_t;
    param_t.b = 2;
    param_t.a = 3;


    for (int i = 0; i < NUM_THREADS; ++i) {
        int ret = pthread_create(&tids[i], NULL, say_hello, &param_t);
        if (ret != 0) {
            {
                std::cout << "pthread_create error: error_code=" << ret << std::endl;
            }
        }
    }

    //等各个线程退出后，进程才结束，否则进程强制结束了，线程可能还没反应过来；
    pthread_exit(NULL);
}
