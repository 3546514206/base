//
// Created by 杨海波 on 2022/11/30.
//

#include "tester.hpp"
#include "leet_code_1_50.hpp"
#include <iostream>

void test_leet_code_1() {
    vector<int> a = {2, 7, 11, 15};
    vector<int> &a_ref = a;
    leetcode_00001::Solution solution;
    vector<int> rz = solution.twoSum(a_ref, 9);
    std::cout << "break point" << std::endl;
}