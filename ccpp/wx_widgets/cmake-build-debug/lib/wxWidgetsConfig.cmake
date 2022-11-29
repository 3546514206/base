
####### Expanded from @PACKAGE_INIT@ by configure_package_config_file() #######
####### Any changes to this file will be overwritten by the next CMake run ####
####### The input file was wxWidgetsConfig.cmake.in                            ########

get_filename_component(PACKAGE_PREFIX_DIR "${CMAKE_CURRENT_LIST_DIR}/../../../" ABSOLUTE)

macro(set_and_check _var _file)
  set(${_var} "${_file}")
  if(NOT EXISTS "${_file}")
    message(FATAL_ERROR "File or directory ${_file} referenced by variable ${_var} does not exist !")
  endif()
endmacro()

macro(check_required_components _NAME)
  foreach(comp ${${_NAME}_FIND_COMPONENTS})
    if(NOT ${_NAME}_${comp}_FOUND)
      if(${_NAME}_FIND_REQUIRED_${comp})
        set(${_NAME}_FOUND FALSE)
      endif()
    endif()
  endforeach()
endmacro()

####################################################################################

# determine target from compiler, platform and library type
if(WIN32 AND NOT CYGWIN AND NOT MSYS)
    if(${CMAKE_CXX_COMPILER_ID} STREQUAL MSVC)
        set(wxCOMPILER_PREFIX "vc")
    elseif(${CMAKE_CXX_COMPILER_ID} STREQUAL GNU)
        set(wxCOMPILER_PREFIX "gcc")
    elseif(${CMAKE_CXX_COMPILER_ID} STREQUAL Clang)
        set(wxCOMPILER_PREFIX "clang")
    endif()

    if(CMAKE_SIZEOF_VOID_P EQUAL 8)
        set(wxARCH_SUFFIX "_x64")
    endif()

    # use wxWidgets_USE_STATIC to force static libraries, otherwise shared is searched first
    if(NOT wxWidgets_USE_STATIC AND EXISTS "${CMAKE_CURRENT_LIST_DIR}/${wxCOMPILER_PREFIX}${wxARCH_SUFFIX}_dll/wxWidgetsTargets.cmake")
        set(wxPLATFORM_LIB_DIR "/${wxCOMPILER_PREFIX}${wxARCH_SUFFIX}_dll")
    elseif(EXISTS "${CMAKE_CURRENT_LIST_DIR}/${wxCOMPILER_PREFIX}${wxARCH_SUFFIX}_lib/wxWidgetsTargets.cmake")
        set(wxPLATFORM_LIB_DIR "/${wxCOMPILER_PREFIX}${wxARCH_SUFFIX}_lib")
    endif()
endif()

include("${CMAKE_CURRENT_LIST_DIR}${wxPLATFORM_LIB_DIR}/wxWidgetsTargets.cmake")

macro(wx_inherit_property source dest name)
    # property name without _<CONFIG>
    get_target_property(prop ${source} ${name})
    if(prop)
        set_target_properties(${dest} PROPERTIES ${name} "${prop}")
    endif()
    # property name with _<CONFIG>
    get_target_property(configs ${source} IMPORTED_CONFIGURATIONS)
    foreach(cfg ${configs})
        set(name_cfg "${name}_${cfg}")
        get_target_property(prop ${source} ${name_cfg})
        if(prop)
            set_target_properties(${dest} PROPERTIES ${name_cfg} "${prop}")
        endif()
    endforeach()
endmacro()

# for compatibility with FindwxWidgets
set(wxWidgets_LIBRARIES)

# list all available components
set(wxWidgets_COMPONENTS)
foreach(libname wxbase;wxnet;wxcore;wxadv;wxaui;wxhtml;wxpropgrid;wxribbon;wxrichtext;wxwebview;wxstc;wxxrc;wxmedia;wxgl;wxqa;wxxml)
    if(TARGET wx::${libname})
        # remove wx prefix from component name
        string(SUBSTRING ${libname} 2 -1 name)

        # set variables used in check_required_components
        list(APPEND wxWidgets_COMPONENTS ${name})
        set(wxWidgets_${name}_FOUND 1)
        set(wxWidgets_FIND_REQUIRED_${name} 1)

        # use the Release configuration for MinSizeRel and RelWithDebInfo configurations
        # only when Release target exists, and MinSizeRel/RelWithDebInfo doesn't exist
        get_target_property(configs wx::${libname} IMPORTED_CONFIGURATIONS)
        list(FIND configs "RELEASE" idxSrc)
        if(idxSrc GREATER -1)
            list(FIND configs "MINSIZEREL" idxSrc)
            list(FIND CMAKE_CONFIGURATION_TYPES "MinSizeRel" idxDst)
            if(idxSrc EQUAL -1 AND (idxDst GREATER -1 OR CMAKE_BUILD_TYPE STREQUAL "MinSizeRel"))
                set_target_properties(wx::${libname} PROPERTIES MAP_IMPORTED_CONFIG_MINSIZEREL "Release")
            endif()
            list(FIND configs "RELWITHDEBINFO" idxSrc)
            list(FIND CMAKE_CONFIGURATION_TYPES "RelWithDebInfo" idxDst)
            if(idxSrc EQUAL -1 AND (idxDst GREATER -1 OR CMAKE_BUILD_TYPE STREQUAL "RelWithDebInfo"))
                set_target_properties(wx::${libname} PROPERTIES MAP_IMPORTED_CONFIG_RELWITHDEBINFO "Release")
            endif()
        endif()

        # add an alias from wx::<lib> to wx::wx<lib>
        if(CMAKE_VERSION VERSION_LESS "3.18")
            # CMake <3.18 does not support alias to non-global imported target, create a copy of the library
            get_target_property(target_type wx::${libname} TYPE)
            if(target_type STREQUAL STATIC_LIBRARY)
                add_library(wx::${name} STATIC IMPORTED)
            else()
                add_library(wx::${name} SHARED IMPORTED)
            endif()
            wx_inherit_property(wx::${libname} wx::${name} IMPORTED_CONFIGURATIONS)
            wx_inherit_property(wx::${libname} wx::${name} INTERFACE_COMPILE_DEFINITIONS)
            wx_inherit_property(wx::${libname} wx::${name} INTERFACE_INCLUDE_DIRECTORIES)
            wx_inherit_property(wx::${libname} wx::${name} INTERFACE_LINK_LIBRARIES)
            wx_inherit_property(wx::${libname} wx::${name} IMPORTED_LINK_INTERFACE_LANGUAGES)
            wx_inherit_property(wx::${libname} wx::${name} IMPORTED_LOCATION)
            wx_inherit_property(wx::${libname} wx::${name} IMPORTED_IMPLIB)
            wx_inherit_property(wx::${libname} wx::${name} IMPORTED_LINK_DEPENDENT_LIBRARIES)
        else()
            add_library(wx::${name} ALIAS wx::${libname})
        endif()

        # add to FindwxWidgets variable
        list(FIND wxWidgets_FIND_COMPONENTS ${name} idx)
        if(NOT wxWidgets_FIND_COMPONENTS OR idx GREATER -1)
            list(APPEND wxWidgets_LIBRARIES wx::${name})
        endif()
    endif()
endforeach()

# if no components are specified in find_package, check all of them
if(NOT wxWidgets_FIND_COMPONENTS)
    set(wxWidgets_FIND_COMPONENTS ${wxWidgets_COMPONENTS})
endif()

check_required_components("wxWidgets")
