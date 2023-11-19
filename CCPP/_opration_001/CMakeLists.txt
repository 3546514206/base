# 指定CMake的最低版本要求
cmake_minimum_required(VERSION 3.12...3.21)

# 定义项目名称
project(_cmake_sample)

# 添加可执行文件，并指定源文件
add_executable(_cmake_sample main.cpp)
