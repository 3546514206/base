cmake_minimum_required(VERSION 3.21)
project(_move_constructor)

set(CMAKE_CXX_STANDARD 11)

add_executable(_move_constructor main.cpp my_string.cpp my_string.h)
