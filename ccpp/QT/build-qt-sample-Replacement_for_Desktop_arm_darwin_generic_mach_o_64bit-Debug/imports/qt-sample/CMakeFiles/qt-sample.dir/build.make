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

# Include any dependencies generated for this target.
include imports/qt-sample/CMakeFiles/qt-sample.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include imports/qt-sample/CMakeFiles/qt-sample.dir/compiler_depend.make

# Include the progress variables for this target.
include imports/qt-sample/CMakeFiles/qt-sample.dir/progress.make

# Include the compile flags for this target's objects.
include imports/qt-sample/CMakeFiles/qt-sample.dir/flags.make

imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json.gen: /opt/homebrew/share/qt/libexec/moc
imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json.gen: imports/qt-sample/meta_types/qt-sample_json_file_list.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Running moc --collect-json for target qt-sample"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/share/qt/libexec/moc -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json.gen --collect-json @/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/meta_types/qt-sample_json_file_list.txt
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E copy_if_different /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json.gen /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json

imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json: imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json.gen
	@$(CMAKE_COMMAND) -E touch_nocreate imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json

imports/qt-sample/qt-sample_qmltyperegistrations.cpp: imports/qt-sample/qmltypes/qt-sample_foreign_types.txt
imports/qt-sample/qt-sample_qmltyperegistrations.cpp: imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json
imports/qt-sample/qt-sample_qmltyperegistrations.cpp: /opt/homebrew/share/qt/libexec/qmltyperegistrar
imports/qt-sample/qt-sample_qmltyperegistrations.cpp: /opt/homebrew/share/qt/metatypes/qt6qml_release_metatypes.json
imports/qt-sample/qt-sample_qmltyperegistrations.cpp: /opt/homebrew/share/qt/metatypes/qt6core_release_metatypes.json
imports/qt-sample/qt-sample_qmltyperegistrations.cpp: /opt/homebrew/share/qt/metatypes/qt6network_release_metatypes.json
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Automatic QML type registration for target qt-sample"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/share/qt/libexec/qmltyperegistrar --generate-qmltypes=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/qt-sample/qt-sample.qmltypes --import-name=qt-sample --major-version=1 --minor-version=0 @/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qmltypes/qt-sample_foreign_types.txt -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qt-sample_qmltyperegistrations.cpp /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E make_directory /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.generated
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E touch /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.generated/qt-sample.qmltypes

qml/qt-sample/qt-sample.qmltypes: imports/qt-sample/qt-sample_qmltyperegistrations.cpp
	@$(CMAKE_COMMAND) -E touch_nocreate qml/qt-sample/qt-sample.qmltypes

imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp: /opt/homebrew/share/qt/libexec/qmlcachegen
imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp: /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/Constants.qml
imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp: imports/qt-sample/.rcc/qmake_qt-sample.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp: imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp: qml/qt-sample/qt-sample.qmltypes
imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp: qml/qt-sample/qmldir
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Generating .rcc/qmlcache/qt-sample_Constants_qml.cpp"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E make_directory /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/share/qt/libexec/qmlcachegen --bare --resource-path /qt-sample/Constants.qml -I /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/ -I /opt/homebrew/share/qt/qml -i /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/qt-sample/qmldir --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmake_qt-sample.qrc --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/Constants.qml

imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp: /opt/homebrew/share/qt/libexec/qmlcachegen
imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp: /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/DirectoryFontLoader.qml
imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp: imports/qt-sample/.rcc/qmake_qt-sample.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp: imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp: qml/qt-sample/qt-sample.qmltypes
imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp: qml/qt-sample/qmldir
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Generating .rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E make_directory /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/share/qt/libexec/qmlcachegen --bare --resource-path /qt-sample/DirectoryFontLoader.qml -I /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/ -I /opt/homebrew/share/qt/qml -i /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/qt-sample/qmldir --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmake_qt-sample.qrc --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/DirectoryFontLoader.qml

imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp: /opt/homebrew/share/qt/libexec/qmlcachegen
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp: /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/EventListModel.qml
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp: imports/qt-sample/.rcc/qmake_qt-sample.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp: imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp: qml/qt-sample/qt-sample.qmltypes
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp: qml/qt-sample/qmldir
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Generating .rcc/qmlcache/qt-sample_EventListModel_qml.cpp"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E make_directory /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/share/qt/libexec/qmlcachegen --bare --resource-path /qt-sample/EventListModel.qml -I /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/ -I /opt/homebrew/share/qt/qml -i /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/qt-sample/qmldir --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmake_qt-sample.qrc --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/EventListModel.qml

imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp: /opt/homebrew/share/qt/libexec/qmlcachegen
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp: /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/EventListSimulator.qml
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp: imports/qt-sample/.rcc/qmake_qt-sample.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp: imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp: qml/qt-sample/qt-sample.qmltypes
imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp: qml/qt-sample/qmldir
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Generating .rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E make_directory /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /opt/homebrew/share/qt/libexec/qmlcachegen --bare --resource-path /qt-sample/EventListSimulator.qml -I /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/ -I /opt/homebrew/share/qt/qml -i /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/qt-sample/qmldir --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmake_qt-sample.qrc --resource /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qt-sample_raw_qml_0.qrc -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample/EventListSimulator.qml

imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/flags.make
imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o: imports/qt-sample/qt-sample_autogen/mocs_compilation.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Building CXX object imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o -MF CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o.d -o CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o -c /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qt-sample_autogen/mocs_compilation.cpp

imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.i"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qt-sample_autogen/mocs_compilation.cpp > CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.i

imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.s"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qt-sample_autogen/mocs_compilation.cpp -o CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.s

imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/flags.make
imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o: imports/qt-sample/qt-sample_qmltyperegistrations.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_8) "Building CXX object imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o -MF CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o.d -o CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o -c /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qt-sample_qmltyperegistrations.cpp

imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.i"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qt-sample_qmltyperegistrations.cpp > CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.i

imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.s"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/qt-sample_qmltyperegistrations.cpp -o CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.s

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/flags.make
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o: imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_9) "Building CXX object imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o -MF CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o.d -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o -c /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.i"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp > CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.i

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.s"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.s

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/flags.make
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o: imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_10) "Building CXX object imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o -MF CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o.d -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o -c /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.i"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp > CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.i

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.s"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.s

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/flags.make
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o: imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_11) "Building CXX object imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o -MF CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o.d -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o -c /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.i"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp > CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.i

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.s"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.s

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/flags.make
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o: imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o: imports/qt-sample/CMakeFiles/qt-sample.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_12) "Building CXX object imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o -MF CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o.d -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o -c /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.i"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp > CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.i

imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.s"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && /usr/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp -o CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.s

# Object files for target qt-sample
qt__sample_OBJECTS = \
"CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o" \
"CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o" \
"CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o" \
"CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o" \
"CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o" \
"CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o"

# External object files for target qt-sample
qt__sample_EXTERNAL_OBJECTS =

imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_autogen/mocs_compilation.cpp.o
imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/qt-sample_qmltyperegistrations.cpp.o
imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_Constants_qml.cpp.o
imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp.o
imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp.o
imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp.o
imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/build.make
imports/qt-sample/libqt-sample.a: imports/qt-sample/CMakeFiles/qt-sample.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_13) "Linking CXX static library libqt-sample.a"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && $(CMAKE_COMMAND) -P CMakeFiles/qt-sample.dir/cmake_clean_target.cmake
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/qt-sample.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
imports/qt-sample/CMakeFiles/qt-sample.dir/build: imports/qt-sample/libqt-sample.a
.PHONY : imports/qt-sample/CMakeFiles/qt-sample.dir/build

imports/qt-sample/CMakeFiles/qt-sample.dir/clean:
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample && $(CMAKE_COMMAND) -P CMakeFiles/qt-sample.dir/cmake_clean.cmake
.PHONY : imports/qt-sample/CMakeFiles/qt-sample.dir/clean

imports/qt-sample/CMakeFiles/qt-sample.dir/depend: imports/qt-sample/.rcc/qmlcache/qt-sample_Constants_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/depend: imports/qt-sample/.rcc/qmlcache/qt-sample_DirectoryFontLoader_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/depend: imports/qt-sample/.rcc/qmlcache/qt-sample_EventListModel_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/depend: imports/qt-sample/.rcc/qmlcache/qt-sample_EventListSimulator_qml.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/depend: imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json
imports/qt-sample/CMakeFiles/qt-sample.dir/depend: imports/qt-sample/meta_types/qt6qt-sample_debug_metatypes.json.gen
imports/qt-sample/CMakeFiles/qt-sample.dir/depend: imports/qt-sample/qt-sample_qmltyperegistrations.cpp
imports/qt-sample/CMakeFiles/qt-sample.dir/depend: qml/qt-sample/qt-sample.qmltypes
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample/imports/qt-sample /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/imports/qt-sample/CMakeFiles/qt-sample.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : imports/qt-sample/CMakeFiles/qt-sample.dir/depend
