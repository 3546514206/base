file(REMOVE_RECURSE
  "../../qml/_test_001/Constants.qml"
  "../../qml/_test_001/DirectoryFontLoader.qml"
  "../../qml/_test_001/EventListModel.qml"
  "../../qml/_test_001/EventListSimulator.qml"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/_test_001_tooling.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
