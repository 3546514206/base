//
// Created by 杨海波 on 2022/11/29.
//

#ifndef BOOST_ATOMIC_SAMPLE_HPP
#define BOOST_ATOMIC_SAMPLE_HPP

#include <boost/atomic.hpp>
#include <iostream>

class atomic_tester {
public:
    void test_sample_case();

//    指定构造函数或转换函数 (C++11起)为显式, 即它不能用于隐式转换和复制初始化
    explicit atomic_tester(){

    }

protected:
private:
};

#endif //BOOST_ATOMIC_SAMPLE_HPP
