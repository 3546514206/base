# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.26

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake

# The command to remove a file.
RM = /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug

# Utility rule file for QuickStudioMultiText_tooling.

# Include any custom commands dependencies for this target.
include _deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/compiler_depend.make

# Include the progress variables for this target.
include _deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/progress.make

qml/QtQuick/Studio/MultiText/MultiTextElement.qml: /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext/MultiTextElement.qml
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Generating ../../../../../qml/QtQuick/Studio/MultiText/MultiTextElement.qml"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E copy /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext/MultiTextElement.qml /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/QtQuick/Studio/MultiText/MultiTextElement.qml

qml/QtQuick/Studio/MultiText/MultiTextItem.qml: /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext/MultiTextItem.qml
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Generating ../../../../../qml/QtQuick/Studio/MultiText/MultiTextItem.qml"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E copy /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext/MultiTextItem.qml /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/QtQuick/Studio/MultiText/MultiTextItem.qml

qml/QtQuick/Studio/MultiText/MultiTextException.qml: /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext/MultiTextException.qml
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Generating ../../../../../qml/QtQuick/Studio/MultiText/MultiTextException.qml"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E copy /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext/MultiTextException.qml /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/QtQuick/Studio/MultiText/MultiTextException.qml

QuickStudioMultiText_tooling: qml/QtQuick/Studio/MultiText/MultiTextElement.qml
QuickStudioMultiText_tooling: qml/QtQuick/Studio/MultiText/MultiTextException.qml
QuickStudioMultiText_tooling: qml/QtQuick/Studio/MultiText/MultiTextItem.qml
QuickStudioMultiText_tooling: _deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/build.make
.PHONY : QuickStudioMultiText_tooling

# Rule to build all files generated by this target.
_deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/build: QuickStudioMultiText_tooling
.PHONY : _deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/build

_deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/clean:
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/multitext && $(CMAKE_COMMAND) -P CMakeFiles/QuickStudioMultiText_tooling.dir/cmake_clean.cmake
.PHONY : _deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/clean

_deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/depend:
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/multitext /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/multitext /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : _deps/ds-build/src/imports/multitext/CMakeFiles/QuickStudioMultiText_tooling.dir/depend
