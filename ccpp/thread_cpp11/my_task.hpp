//
// Created by 杨海波 on 2022/11/29.
//

#ifndef THREAD_CPP11_MY_TASK_HPP
#define THREAD_CPP11_MY_TASK_HPP

#include <iostream>

namespace task_def_space {

    template<typename T>
    class sample_task {
    public:
        T do_something();

        sample_task(T & data);

    protected:
    private:
        T data;
    };
}


#endif //THREAD_CPP11_MY_TASK_HPP
