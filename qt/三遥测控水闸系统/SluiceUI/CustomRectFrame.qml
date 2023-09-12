import QtQuick 2.0

Rectangle {
    id: root
    width: 200
    height: 36
    radius: 2
    border { width: 1; color: "#333333" }
    color: "#10FFFAF0"

    property string name
    property bool pilotlight
    property alias value: texter.text

    FontLoader { id: localFont; source: "qrc:///res/ttf/luggage.ttf" }

    Image {
        id: pilotLight
        x: 10
        width: root.height > 24 ? 24 : root.height;
        height: root.height > 24 ? 24 : root.height;
        anchors.verticalCenter: root.verticalCenter
        source: root.pilotlight === true ? "qrc:///res/sluice_state/green.png" : "qrc:///res/sluice_state/red.png"
    }

    Text {
        id: typer
        height: parent.height
        anchors.left: pilotLight.right
        anchors.leftMargin: 10
        verticalAlignment: Text.AlignVCenter
        color: "white"
        font.pointSize: 12
        text: name
    }

    Image {
        id: line
        anchors.left: typer.right
        anchors.right: root.right
        anchors.bottom: root.bottom
        anchors.margins: 2

        source: "qrc:///res/sluice_state/textinput.png"
    }
    Text {
        id: texter
        anchors.horizontalCenter: line.horizontalCenter
        anchors.verticalCenter: root.verticalCenter
        color: "white"
        font { family: localFont.name; pointSize: 14 }
    }
}
