cmake_minimum_required(VERSION 3.21)
project(_hello_cuda CUDA)

set(CMAKE_CUDA_STANDARD 11)

add_executable(_hello_cuda main.cu)

set_target_properties(_hello_cuda PROPERTIES
        CUDA_SEPARABLE_COMPILATION ON)
