# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.29

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
CMAKE_COMMAND = /opt/homebrew/Cellar/cmake/3.29.3/bin/cmake

# The command to remove a file.
RM = /opt/homebrew/Cellar/cmake/3.29.3/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/build

# Include any dependencies generated for this target.
include CMakeFiles/_check_prime.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/_check_prime.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/_check_prime.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/_check_prime.dir/flags.make

CMakeFiles/_check_prime.dir/_check_prime.cpp.o: CMakeFiles/_check_prime.dir/flags.make
CMakeFiles/_check_prime.dir/_check_prime.cpp.o: /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/_check_prime.cpp
CMakeFiles/_check_prime.dir/_check_prime.cpp.o: CMakeFiles/_check_prime.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/_check_prime.dir/_check_prime.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/_check_prime.dir/_check_prime.cpp.o -MF CMakeFiles/_check_prime.dir/_check_prime.cpp.o.d -o CMakeFiles/_check_prime.dir/_check_prime.cpp.o -c /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/_check_prime.cpp

CMakeFiles/_check_prime.dir/_check_prime.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/_check_prime.dir/_check_prime.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/_check_prime.cpp > CMakeFiles/_check_prime.dir/_check_prime.cpp.i

CMakeFiles/_check_prime.dir/_check_prime.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/_check_prime.dir/_check_prime.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/_check_prime.cpp -o CMakeFiles/_check_prime.dir/_check_prime.cpp.s

# Object files for target _check_prime
_check_prime_OBJECTS = \
"CMakeFiles/_check_prime.dir/_check_prime.cpp.o"

# External object files for target _check_prime
_check_prime_EXTERNAL_OBJECTS =

_check_prime: CMakeFiles/_check_prime.dir/_check_prime.cpp.o
_check_prime: CMakeFiles/_check_prime.dir/build.make
_check_prime: CMakeFiles/_check_prime.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --bold --progress-dir=/Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable _check_prime"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/_check_prime.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/_check_prime.dir/build: _check_prime
.PHONY : CMakeFiles/_check_prime.dir/build

CMakeFiles/_check_prime.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/_check_prime.dir/cmake_clean.cmake
.PHONY : CMakeFiles/_check_prime.dir/clean

CMakeFiles/_check_prime.dir/depend:
	cd /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/build /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/build /Users/setsunayang/Documents/GitHub/base/CCPP/_check_prime/build/CMakeFiles/_check_prime.dir/DependInfo.cmake "--color=$(COLOR)"
.PHONY : CMakeFiles/_check_prime.dir/depend

