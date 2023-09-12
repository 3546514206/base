import QtQuick 2.0

Rectangle {
    id: root
    width: 900; height: 600

    Image {
        id: container
        anchors.fill: parent
        source: "qrc:///general/general/background.png"

        Image {
            id: titleBar
            height: 48
            width: parent.width
            anchors.top: parent.top
            source: "qrc:///general/general/title_banner.png"

            Text {
                anchors.centerIn: parent
                font { pointSize: 28; weight: Font.Black }
                color: "white"
                style: Text.Outline;
                styleColor: "#00CCFC"
                text: "操 作 记 录"
            }
        }
        Image {
            id: arrowLeft
            anchors.top: parent.top
            anchors.topMargin: 64
            anchors.left: parent.left
            anchors.leftMargin: 30
            source: "qrc:///general/general/arrow_left.png"
        }
        Image {
            id: arrowRight
            anchors.top: parent.top
            anchors.topMargin: 64
            anchors.right: parent.right
            anchors.rightMargin: 30
            source: "qrc:///general/general/arrow_right.png"
        }

        Image {
            id: frame
            width: container.width * 3 / 4
            height: container.height * 3 / 4
            anchors.horizontalCenter: parent.horizontalCenter
            anchors.verticalCenter: parent.verticalCenter
            anchors.verticalCenterOffset: 30
            source: "qrc:///general/general/frame.png"

            Text {
                id: idText
                y: 20
                anchors.horizontalCenter: parent.horizontalCenter
                font.pointSize: 16
                text: "操作记录"
            }
        }
    }
}
