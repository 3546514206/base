//
// Created by 杨海波 on 2022/11/25.
//

#include "thread_sample.hpp"

static void do_something() {
    std::cout << "thread is running";
}

static void test_simple_case() {

    boost::thread my_thread(&do_something);
    my_thread.join();
}

void thread_testing_space::test_thread() {

    // 测试最简单的使用
//    test_simple_case();
}