cmake_minimum_required(VERSION 3.21)
project(_huffman_tree)

set(CMAKE_CXX_STANDARD 11)

add_executable(_huffman_tree main.cpp)

# 设置输出目录
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)

# 将 input.txt 复制到输出目录
configure_file(${CMAKE_SOURCE_DIR}/input.txt ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/input.txt COPYONLY)