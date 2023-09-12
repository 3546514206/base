import QtQuick 2.0
import QtQml.Models 2.1

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
                text: "闸 机 控 制"
            }
        }
        Image {
            id: arrowLeft
            anchors.top: parent.top
            anchors.topMargin: 64
            anchors.left: parent.left
            anchors.leftMargin: 30
            source: "qrc:///general/general/arrow_left.png"
            MouseArea {
                anchors.fill: parent
                onClicked: listView.decrementCurrentIndex()
            }
        }
        Image {
            id: arrowRight
            anchors.top: parent.top
            anchors.topMargin: 64
            anchors.right: parent.right
            anchors.rightMargin: 30
            source: "qrc:///general/general/arrow_right.png"
            MouseArea {
                anchors.fill: parent
                onClicked: listView.incrementCurrentIndex()
            }
        }

        ListView {
            id: listView
            anchors.fill: parent
            anchors.topMargin: container.height / 6
            anchors.bottomMargin: container.height / 12
            anchors.leftMargin: container.width / 8
            anchors.rightMargin: container.width / 8

            currentIndex: 0
            snapMode: ListView.SnapToItem
            orientation: ListView.Horizontal
            spacing: container.width / 4
            highlightMoveDuration: 500

            model: ObjectModel {
                SluiceControlView {
                    title: "状态检测面板"
                }
            }
        }
    }
}

