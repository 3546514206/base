import QtQuick 2.0
import RestartHandler 1.0

Image {
    id: container
    source: victory === true ? "qrc:///images/victory.png" : "qrc:///images/failed.png"

    property int steps: 0
    property bool victory: true

    RestartHandler {
        id: restartHandler
    }

    FontLoader { id: localFont; source: "qrc:///ttf/MFDingDing.otf" }

    Text {
        id: text
        x: 20
        y: 130
        font { family: localFont.name; pixelSize: 35 }
        color: "orangered"

        text: victory === true ? "你真棒! 一共用了 " + steps + " 步!" : "你没有抓住神经猫 ~(^(OO)^)~"
    }

    Image {
        anchors.bottom: parent.bottom
        anchors.right: parent.right
        anchors.margins: 40

        width: 150
        height: 80

        source: "qrc:///images/replay.png"

        MouseArea {
            anchors.fill: parent
            onClicked: restartHandler.restart();
        }
    }
}
