cmake_minimum_required(VERSION 3.21)
project(_opencv_sample)

set(CMAKE_CXX_STANDARD 11)

INCLUDE_DIRECTORIES(/opt/homebrew/Cellar/opencv/4.8.1_2/include/opencv4/)

#CMake通过此命令自动寻找OpenCV的依赖
find_package(OpenCV REQUIRED)

add_executable(_opencv_sample main.cpp)
