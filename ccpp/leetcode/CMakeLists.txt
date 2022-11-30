cmake_minimum_required(VERSION 3.21)
project(leetcode)

set(CMAKE_CXX_STANDARD 11)

add_executable(leetcode main.cpp leet_code_1_50.cpp leet_code_1_50.hpp tester.cpp tester.hpp)
