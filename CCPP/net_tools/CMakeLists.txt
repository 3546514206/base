cmake_minimum_required(VERSION 3.29)
project(net_tools)

set(CMAKE_CXX_STANDARD 11)

# 查找 Boost
find_package(Boost REQUIRED)

add_executable(net_tools main.cpp
        src/scanner/scanner.cpp
        src/scanner/scanner.h)

if (Boost_FOUND)
    message("Boost found: ${Boost_VERSION}")
    include_directories(${Boost_INCLUDE_DIRS}) # 添加头文件路径
    target_link_libraries(net_tools ${Boost_LIBRARIES}) # 链接 Boost 库
else ()
    message(FATAL_ERROR "Boost not found!")
endif ()
