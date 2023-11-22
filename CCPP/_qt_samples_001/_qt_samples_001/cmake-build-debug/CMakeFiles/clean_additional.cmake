# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "CMakeFiles/_qt_samples_001_autogen.dir/AutogenUsed.txt"
  "CMakeFiles/_qt_samples_001_autogen.dir/ParseCache.txt"
  "_qt_samples_001_autogen"
  )
endif()
