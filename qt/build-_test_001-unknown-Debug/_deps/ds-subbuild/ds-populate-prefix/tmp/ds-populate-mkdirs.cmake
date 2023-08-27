# Distributed under the OSI-approved BSD 3-Clause License.  See accompanying
# file Copyright.txt or https://cmake.org/licensing for details.

cmake_minimum_required(VERSION 3.5)

file(MAKE_DIRECTORY
  "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-src"
  "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-build"
  "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-subbuild/ds-populate-prefix"
  "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-subbuild/ds-populate-prefix/tmp"
  "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-subbuild/ds-populate-prefix/src/ds-populate-stamp"
  "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-subbuild/ds-populate-prefix/src"
  "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-subbuild/ds-populate-prefix/src/ds-populate-stamp"
)

set(configSubDirs )
foreach(subDir IN LISTS configSubDirs)
    file(MAKE_DIRECTORY "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-subbuild/ds-populate-prefix/src/ds-populate-stamp/${subDir}")
endforeach()
if(cfgdir)
  file(MAKE_DIRECTORY "/Users/setsunayang/Documents/learning/base/qt/build-_test_001-unknown-Debug/_deps/ds-subbuild/ds-populate-prefix/src/ds-populate-stamp${cfgdir}") # cfgdir has leading slash
endif()
