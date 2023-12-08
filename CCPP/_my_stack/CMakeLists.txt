cmake_minimum_required(VERSION 3.21)
project(_my_stack)

set(CMAKE_CXX_STANDARD 11)

add_executable(_my_stack main.cpp my_stack.cpp my_stack.h)
