# CMAKE_PREFIX_PATH=/Library/Java/Extensions/qt-6.4/6.4.1/macos

cmake_minimum_required(VERSION 3.21)
project(qt_test)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

set(CMAKE_PREFIX_PATH "/Library/Java/Extensions/qt-6.4/6.4.1/macos/lib/cmake")

find_package(Qt6 COMPONENTS
        Core
        Gui
        Widgets
        REQUIRED)

add_executable(qt_test main.cpp)
target_link_libraries(qt_test
        Qt::Core
        Qt::Gui
        Qt::Widgets
        )

