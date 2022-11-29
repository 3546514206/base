//
// Created by 杨海波 on 2022/11/29.
//

#include "atomic_sample.hpp"


void atomic_tester::test_sample_case() {
    boost::atomic<int> *atomic_int_var = new boost::atomic<int>(10);
    //    atomic 类型的隐式类型转换
    std::cout << (*atomic_int_var == 10) << std::endl;


    delete atomic_int_var;
}