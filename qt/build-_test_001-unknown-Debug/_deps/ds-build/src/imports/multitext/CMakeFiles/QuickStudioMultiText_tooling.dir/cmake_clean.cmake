file(REMOVE_RECURSE
  "../../../../../qml/QtQuick/Studio/MultiText/MultiTextElement.qml"
  "../../../../../qml/QtQuick/Studio/MultiText/MultiTextException.qml"
  "../../../../../qml/QtQuick/Studio/MultiText/MultiTextItem.qml"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/QuickStudioMultiText_tooling.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
