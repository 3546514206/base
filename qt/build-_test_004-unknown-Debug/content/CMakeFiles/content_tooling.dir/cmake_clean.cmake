file(REMOVE_RECURSE
  "../qml/content/App.qml"
  "../qml/content/Screen01.ui.qml"
  "../qml/content/fonts/fonts.txt"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/content_tooling.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
