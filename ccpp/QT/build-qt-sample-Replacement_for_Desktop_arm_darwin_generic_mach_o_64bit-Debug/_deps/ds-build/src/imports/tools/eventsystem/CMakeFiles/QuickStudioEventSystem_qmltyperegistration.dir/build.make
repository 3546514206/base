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

# Utility rule file for QuickStudioEventSystem_qmltyperegistration.

# Include any custom commands dependencies for this target.
include _deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/compiler_depend.make

# Include the progress variables for this target.
include _deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/progress.make

_deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration: _deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp
_deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration: qml/QtQuick/Studio/EventSystem/QuickStudioEventSystem.qmltypes

_deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp: _deps/ds-build/src/imports/tools/eventsystem/qmltypes/QuickStudioEventSystem_foreign_types.txt
_deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp: _deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json
_deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp: /opt/homebrew/share/qt/libexec/qmltyperegistrar
_deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp: /opt/homebrew/share/qt/metatypes/qt6qml_release_metatypes.json
_deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp: /opt/homebrew/share/qt/metatypes/qt6core_release_metatypes.json
_deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp: /opt/homebrew/share/qt/metatypes/qt6network_release_metatypes.json
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Automatic QML type registration for target QuickStudioEventSystem"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem && /opt/homebrew/share/qt/libexec/qmltyperegistrar --generate-qmltypes=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/qml/QtQuick/Studio/EventSystem/QuickStudioEventSystem.qmltypes --import-name=QtQuick.Studio.EventSystem --major-version=6 --minor-version=4 --past-major-version 1 @/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/qmltypes/QuickStudioEventSystem_foreign_types.txt -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E make_directory /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/.generated
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E touch /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/.generated/QuickStudioEventSystem.qmltypes

qml/QtQuick/Studio/EventSystem/QuickStudioEventSystem.qmltypes: _deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp
	@$(CMAKE_COMMAND) -E touch_nocreate qml/QtQuick/Studio/EventSystem/QuickStudioEventSystem.qmltypes

_deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json.gen: /opt/homebrew/share/qt/libexec/moc
_deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json.gen: _deps/ds-build/src/imports/tools/eventsystem/meta_types/QuickStudioEventSystem_json_file_list.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Running moc --collect-json for target QuickStudioEventSystem"
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem && /opt/homebrew/share/qt/libexec/moc -o /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json.gen --collect-json @/Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/meta_types/QuickStudioEventSystem_json_file_list.txt
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem && /opt/homebrew/Cellar/cmake/3.26.4/bin/cmake -E copy_if_different /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json.gen /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json

_deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json: _deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json.gen
	@$(CMAKE_COMMAND) -E touch_nocreate _deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json

QuickStudioEventSystem_qmltyperegistration: _deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration
QuickStudioEventSystem_qmltyperegistration: _deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json
QuickStudioEventSystem_qmltyperegistration: _deps/ds-build/src/imports/tools/eventsystem/meta_types/qt6quickstudioeventsystem_debug_metatypes.json.gen
QuickStudioEventSystem_qmltyperegistration: _deps/ds-build/src/imports/tools/eventsystem/quickstudioeventsystem_qmltyperegistrations.cpp
QuickStudioEventSystem_qmltyperegistration: qml/QtQuick/Studio/EventSystem/QuickStudioEventSystem.qmltypes
QuickStudioEventSystem_qmltyperegistration: _deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/build.make
.PHONY : QuickStudioEventSystem_qmltyperegistration

# Rule to build all files generated by this target.
_deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/build: QuickStudioEventSystem_qmltyperegistration
.PHONY : _deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/build

_deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/clean:
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem && $(CMAKE_COMMAND) -P CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/cmake_clean.cmake
.PHONY : _deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/clean

_deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/depend:
	cd /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/setsunayang/Documents/learning/base/ccpp/QT/qt-sample /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-src/src/imports/tools/eventsystem /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem /Users/setsunayang/Documents/learning/base/ccpp/QT/build-qt-sample-Replacement_for_Desktop_arm_darwin_generic_mach_o_64bit-Debug/_deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : _deps/ds-build/src/imports/tools/eventsystem/CMakeFiles/QuickStudioEventSystem_qmltyperegistration.dir/depend
