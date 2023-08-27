cmake_minimum_required(VERSION 3.21)
project(single)

set(CMAKE_CXX_STANDARD 11)

add_executable(single main.cpp singleton.cpp singleton.h)
