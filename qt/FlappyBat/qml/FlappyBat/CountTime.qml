import QtQuick 2.0

Rectangle {
    width: 100
    height: 62
    property alias text: t.text
    color: "transparent"
    Text{
        id:t
        width: parent.width
        height: parent.height
        font.pixelSize:parent.width/3
        anchors.centerIn: parent
        text:"0"
        color: "#CCCCFF"
    }
}
