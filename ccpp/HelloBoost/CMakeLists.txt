cmake_minimum_required(VERSION 3.21)
project(HelloBoost)

include_directories(/opt/homebrew/opt/boost@1.76/include)
link_directories(/opt/homebrew/opt/boost@1.76/lib)

set(CMAKE_CXX_STANDARD 11)

add_executable(HelloBoost main.cpp)
