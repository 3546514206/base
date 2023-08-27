//
// Created by 杨海波 on 2023/8/25.
//

#ifndef SINGLE_SINGLETON_H
#define SINGLE_SINGLETON_H

#include <mutex>

class singleton {
public:
    static singleton & get_singleton();

    singleton(const singleton &) = delete;

    singleton &operator=(const singleton &) = delete;

    void do_something();

private:
    singleton();

    static std::mutex mutex;

    static singleton *instance;

};

#endif //SINGLE_SINGLETON_H
