import QtQuick 2.2

Rectangle{
    id: root
    property alias text: label.text
    signal clicked

    width: 116; height: 26
    border.width: 2
    border.color: "slategrey"
    radius: 4
    color: "lightsteelblue"

    Text{
        id: label
        anchors.centerIn: parent
        text: "Start"
    }

    MouseArea{
        anchors.fill: parent
        onClicked: {
            root.clicked()
        }
    }
}
