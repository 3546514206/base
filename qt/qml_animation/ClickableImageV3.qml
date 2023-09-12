// M1>>
// ClickableImageV2.qml
// Simple image which can be clicked

import QtQuick 2.0

Item {
    id: root
    width: container.childrenRect.width + 16
    height: container.childrenRect.height + 16
    property alias text: label.text
    property alias source: image.source
    signal clicked

    // M1>>
    // ... add a framed rectangle as container
    property bool frame : false

    Rectangle {
        anchors.fill: parent
        color: "white"
        visible: root.frame
    }
    // <<M1

    Column {
        x: 8; y: 8
        id: container
        Image {
            id: image
        }
        Text {
            id: label
            width: image.width
            horizontalAlignment: Text.AlignHCenter
            wrapMode: Text.WordWrap
            color: "#e0e0e0"
        }
    }

    MouseArea {
        anchors.fill: parent
        onClicked: root.clicked()
    }
}

// <<M1
