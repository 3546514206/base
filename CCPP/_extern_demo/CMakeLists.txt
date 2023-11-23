cmake_minimum_required(VERSION 3.21)
project(_extern_demo)

set(CMAKE_CXX_STANDARD 11)

add_executable(_extern_demo main.cpp helper.cpp helper.h)
