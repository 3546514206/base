cmake_minimum_required(VERSION 3.21)
project(cinatra)

set(CMAKE_CXX_STANDARD 17)

find_package(Boost 1.77.0 COMPONENTS system filesystem REQUIRED)
include_directories(${Boost_INCLUDE_DIRS})
include_directories(${BOOST_LIBS_DIR})



add_executable(cinatra main.cpp)
