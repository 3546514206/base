import QtQuick 2.0
import QtMultimedia 5.0
import Communication 1.0

Rectangle {
    id: root
    width: 900; height: 600
    property LocalCommunicationHandler pageLocalHandler: localCommunicationHandler

    Audio {
        id: tinkAudio
        source: "qrc:///sound/sluice_state/tink.wav"
    }

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
                text: "通 信 控 制"
            }
        }

        Image {
            id: frame
            anchors.fill: parent
            anchors.topMargin: container.height / 6
            anchors.bottomMargin: container.height / 12
            anchors.leftMargin: container.width / 8
            anchors.rightMargin: container.width / 8
            source: "qrc:///general/general/frame.png"

            Text {
                id: idText
                y: 20
                anchors.horizontalCenter: parent.horizontalCenter
                font.pointSize: 16
                text: "通信控制"
            }

            /***********************************************************
             * 通讯控制不区分闸机,使用Loader加载右方组件
             * 组件由单独的qml文件构建
             */
            Loader {
                id: loader
                anchors.top: parent.top
                anchors.left: verticalLine.right
                anchors.right: parent.right
                anchors.bottom: parent.bottom
                anchors.margins: 24
                anchors.topMargin: parent.height / 5
                source: "CommunicationControlLocalComponent.qml"
            }

            // 现地通讯与远程通讯切换面板
            Item {
                id: leftPanel
                anchors.left: parent.left
                anchors.top: parent.top
                width: parent.width / 4
                height: parent.height

                Item {
                    id: lowerComputer
                    width: parent.width
                    height: 64
                    anchors.top: parent.top
                    anchors.topMargin: parent.height / 4
                    anchors.horizontalCenter: parent.horizontalCenter

                    Image {
                        id: lightImg
                        anchors.horizontalCenter: parent.horizontalCenter
                        anchors.verticalCenter: parent.verticalCenter
                        fillMode: Image.PreserveAspectCrop
                        opacity: 0.5
                        source: "qrc:///general/general/light.png"
                    }

                    Image {
                        id: icon1
                        anchors.left: parent.left
                        anchors.leftMargin: 24
                        anchors.verticalCenter: parent.verticalCenter
                        width: 48; height: 48
                        source: "qrc:///res/communication/lowerComputer.png"
                    }

                    Text {
                        anchors.left: icon1.right
                        anchors.leftMargin: 10
                        anchors.verticalCenter: icon1.verticalCenter
                        color: "white"
                        font.pointSize: 14
                        text: "现地通信设置"
                    }
                    MouseArea {
                        anchors.fill: parent
                        onClicked: {
                            tinkAudio.play()
                            lightImg.parent = parent;
                            loader.source = "CommunicationControlLocalComponent.qml"
                        }
                    }
                }

                Item {
                    id: remoteComputer
                    width: parent.width
                    height: 64
                    anchors.top: lowerComputer.bottom
                    anchors.topMargin: 48
                    anchors.horizontalCenter: parent.horizontalCenter

                    Image {
                        id: icon2
                        anchors.left: parent.left
                        anchors.leftMargin: 24
                        anchors.verticalCenter: parent.verticalCenter
                        width: 48; height: 48
                        source: "qrc:///res/communication/remoteComputer.png"
                    }

                    Text {
                        anchors.left: icon2.right
                        anchors.leftMargin: 10
                        anchors.verticalCenter: icon2.verticalCenter
                        color: "white"
                        font.pointSize: 14
                        text: "远程通信设置"
                    }
                    MouseArea {
                        anchors.fill: parent
                        onClicked: {
                            tinkAudio.play()
                            lightImg.parent = parent;
                            loader.source = "CommunicationControlRemoteComponent.qml"
                        }
                    }
                }
            }

            Image {
                id: verticalLine
                anchors.left: leftPanel.right
                anchors.leftMargin: 24
                anchors.top: parent.top
                anchors.topMargin: parent.height / 5
                anchors.bottom: parent.bottom
                anchors.bottomMargin: 12
                source: "qrc:///general/general/line_blue_vertical.png"
            }
        }
    }
}

