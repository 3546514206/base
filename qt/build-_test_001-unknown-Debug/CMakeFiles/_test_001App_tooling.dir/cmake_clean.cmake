file(REMOVE_RECURSE
  "qml/Main/main.qml"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/_test_001App_tooling.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
