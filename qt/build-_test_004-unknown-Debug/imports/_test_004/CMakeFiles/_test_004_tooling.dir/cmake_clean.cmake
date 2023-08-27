file(REMOVE_RECURSE
  "../../qml/_test_004/Constants.qml"
  "../../qml/_test_004/DirectoryFontLoader.qml"
  "../../qml/_test_004/EventListModel.qml"
  "../../qml/_test_004/EventListSimulator.qml"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/_test_004_tooling.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
