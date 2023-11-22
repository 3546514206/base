cmake_minimum_required(VERSION 3.21)
project(_hello_qt)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

set(CMAKE_PREFIX_PATH "/opt/homebrew/Cellar/qt/6.6.0/bin")

find_package(Qt6 COMPONENTS
        Core
        REQUIRED)

add_executable(_hello_qt main.cpp)
target_link_libraries(_hello_qt
        Qt::Core
        )

