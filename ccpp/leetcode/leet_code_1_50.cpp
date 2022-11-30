//
// Created by 杨海波 on 2022/11/30.
//

#include "leet_code_1_50.hpp"

// 力扣第一题
vector<int> leetcode_00001::Solution::twoSum(vector<int> &nums, int target) {
    int index_1 = -1;
    int index_2 = -1;
    for (int i = 0; i < nums.size(); ++i) {
        for (int j = i + 1; j < nums.size(); ++j) {
            if (nums.at(i) + nums.at(j) == target) {
                index_1 = i;
                index_2 = j;
                break;
            }
        }
    }

    vector<int> rz = vector<int>();
    rz.push_back(index_1);
    rz.push_back(index_2);
    return rz;
}