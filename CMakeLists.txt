cmake_minimum_required(VERSION 3.17)
project(learning C)

set(CMAKE_C_STANDARD 99)

include_directories(.)
include_directories(ccpp)

add_executable(ccpp ccpp/main.c ccpp/testStaticStructDef.c ccpp/testStaticStructDef.h)

