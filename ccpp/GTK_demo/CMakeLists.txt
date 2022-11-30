cmake_minimum_required(VERSION 3.9)
project(wxWidgets_test2)

set(CMAKE_CXX_STANDARD 11)
set(SOURCE_FILES main.cpp sample.cpp sample.h sample.hpp sample.cpp sample.hpp)

add_executable(wx_widgets_demo ${SOURCE_FILES})

file(GLOB wxWidgets_LIBRARTIES "/Users/cmzhang/Documents/wxWidgets-3.1.1/wx_build")

find_package(wxWidgets REQUIRED COMPONENTS net core base)
include(${wxWidgets_USE_FILE})
target_link_libraries(wx_widgets_demo ${wxWidgets_LIBRARIES})