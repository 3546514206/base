cmake_minimum_required(VERSION 3.21)
project(namespace)

set(CMAKE_CXX_STANDARD 14)

add_executable(namespace main.cpp name_space_1.hpp name_space_2.hpp)
