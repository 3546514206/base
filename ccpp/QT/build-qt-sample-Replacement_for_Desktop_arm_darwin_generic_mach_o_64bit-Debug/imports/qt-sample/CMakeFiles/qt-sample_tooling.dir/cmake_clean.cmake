file(REMOVE_RECURSE
  "../../qml/qt-sample/Constants.qml"
  "../../qml/qt-sample/DirectoryFontLoader.qml"
  "../../qml/qt-sample/EventListModel.qml"
  "../../qml/qt-sample/EventListSimulator.qml"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/qt-sample_tooling.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
