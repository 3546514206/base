import QtQuick 2.0

Rectangle {
    id: button
    signal clicked
    property alias text: txt.text
    property bool buttonEnabled: false
    width: Math.max(64, txt.width + 16)
    height: 32
    color: "transparent"
    MouseArea {
        anchors.fill: parent
        onClicked: button.clicked()
    }
    Text {
        anchors.centerIn: parent
        font.family: "OCR A Std"
        font.pointSize: 14
        font.weight: Font.DemiBold
        color: button.buttonEnabled ? "#14aaff" : "#000000"
        id: txt
    }
}
