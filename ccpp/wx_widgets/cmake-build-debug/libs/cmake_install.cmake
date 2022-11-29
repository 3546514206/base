# Install script for directory: /Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/build/cmake/lib

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/usr/local")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Debug")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

# Is this installation the result of a crosscompile?
if(NOT DEFINED CMAKE_CROSSCOMPILING)
  set(CMAKE_CROSSCOMPILING "FALSE")
endif()

# Set default install directory permissions.
if(NOT DEFINED CMAKE_OBJDUMP)
  set(CMAKE_OBJDUMP "/Library/Developer/CommandLineTools/usr/bin/objdump")
endif()

if(NOT CMAKE_INSTALL_LOCAL_ONLY)
  # Include the install script for each subdirectory.
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/base/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/net/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/core/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/adv/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/aui/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/html/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/propgrid/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/ribbon/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/richtext/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/webview/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/stc/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/xrc/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/media/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/gl/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/qa/cmake_install.cmake")
  include("/Users/setsunayang/Documents/learning/learning/ccpp/wx_widgets/cmake-build-debug/libs/xml/cmake_install.cmake")

endif()

