cmake_minimum_required(VERSION 3.21)
project(_db_demo)


include_directories(/usr/local/mysql-connector-c++-8.0.33/include/jdbc)
link_directories(/usr/local/mysql-connector-c++-8.0.33/lib64)


set(CMAKE_CXX_STANDARD 20)

add_executable(_db_demo main.cpp)
