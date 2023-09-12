import QtQuick 2.2

Image{
    id: root
    signal clicked

    MouseArea{
        anchors.fill: parent
        onClicked: root.clicked()
    }
}
