cmake_minimum_required(VERSION 3.21)
project(_hello_qt_001)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)


find_package(Qt6 COMPONENTS
        Core
        Gui
        Widgets
        REQUIRED)

add_executable(_hello_qt_001 main.cpp)
target_link_libraries(_hello_qt_001
        Qt::Core
        Qt::Gui
        Qt::Widgets
        )

