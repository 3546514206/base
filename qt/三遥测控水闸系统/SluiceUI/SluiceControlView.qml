import QtQuick 2.0
import QtMultimedia 5.0

Image {
    id: frame
    width: ListView.view.width
    height: ListView.view.height
    source: "qrc:///general/general/frame.png"

    property string title: "一号闸机"

    property var getupperlimit
    property var getlowerlimit
    property var getopeningvalue

    property var getupstream
    property var getdownstream
    property var getflow
    property var getrainfall
    property var getwinddirect

    Timer {
        interval: 1000;
        running: true;
        repeat: true
        onTriggered:{
            sluiceModel.update();
        }
    }

    SluiceModel {
        id: sluiceModel
        Component.onCompleted: update()

        function update() {
            requestSluiceSensor(1, 99);
            getopeningvalue = model.get(0).openingvalue;
            getupperlimit = model.get(0).upperlimit;
            getlowerlimit = model.get(0).lowerlimit;

            requestEnvironmentalSensor(1);
            getupstream = model.get(0).upstream;
            getdownstream = model.get(0).downstream;
            getflow = model.get(0).flow;
            getrainfall = model.get(0).rainfall;
            getwinddirect = model.get(0).winddirect;
        }
    }

    Audio {
        id: tinkAudio
        source: "qrc:///sound/sluice_state/tink.wav"
    }

    // 闸机id
    Text {
        id: idText
        y: 20
        anchors.horizontalCenter: parent.horizontalCenter
        font.pointSize: 16
        text: title
    }

    // 界面右方loader
    Loader {
        id: loader
        anchors.top: parent.top
        anchors.left: verticalLine.right
        anchors.right: parent.right
        anchors.bottom: parent.bottom
        anchors.margins: 24
        anchors.topMargin: parent.height / 5
        sourceComponent: controlMethodComponent
    }

    // 控制方式组件
    Component {
        id: controlMethodComponent
        Item {
            id: table
            anchors.fill: parent

            Item {
                id: controlItem
                width: 350; height: 200
                anchors.horizontalCenter: parent.horizontalCenter
                anchors.verticalCenter: parent.verticalCenter

                Text {
                    id: upText
                    font.pointSize: 12
                    anchors.left: parent.left
                    anchors.top: parent.top
                    text: "点击以切换控制方式:"
                }
                Image {
                    id: local
                    width: 150; height: 150
                    anchors.left: parent.left
                    anchors.verticalCenter: parent.verticalCenter
                    source: "qrc:///general/general/button_glass.png"

                    Image {
                        width: 128; height: 128
                        anchors.centerIn: parent
                        source: "qrc:///res/sluice_control/local.png"
                    }

                    Rectangle {
                        id: rect
                        width: 150; height: 150
                        anchors.fill: parent
                        color: "#00CCF8"
                        radius: 4
                        opacity: 0.0
                        NumberAnimation {
                            id: rectAnim
                            target: rect
                            property: "opacity"
                            from: 0.5
                            to: 0.0
                            duration: 1000
                        }
                    }
                    MouseArea {
                        anchors.fill: parent
                        onClicked: {
                            tinkAudio.play();
                            rect.parent = parent;
                            rectAnim.start();
                            changeText.text = "现地控制"

                            //userInterface.setControlMethod(0);
                        }
                    }
                }

                Image {
                    id: remote
                    width: 150; height: 150
                    anchors.right: parent.right
                    anchors.verticalCenter: parent.verticalCenter
                    source: "qrc:///general/general/button_glass.png"

                    Image {
                        width: 128; height: 128
                        anchors.centerIn: parent
                        source: "qrc:///res/sluice_control/remote.png"
                    }

                    MouseArea {
                        anchors.fill: parent
                        onClicked: {
                            tinkAudio.play();
                            rect.parent = parent;
                            rectAnim.start();
                            changeText.text = "远程控制"

                            //userInterface.setControlMethod(1);
                        }
                    }
                }
                Text {
                    id: bottomText
                    font.pointSize: 12
                    anchors.horizontalCenter: parent.horizontalCenter
                    anchors.horizontalCenterOffset: -20
                    anchors.bottom: parent.bottom
                    text: "当前控制方式: "
                }
                Text {
                    id: changeText
                    font.pointSize: 12
                    color: "blue"
                    anchors.left: bottomText.right
                    anchors.bottom: parent.bottom
                    text: "现地控制"
                }
            }
        }
    }

    // 保护继电器组件
    Component {
        id: relayComponent
        Item {
            id: table
            anchors.fill: parent

            Grid {
                anchors.fill: parent
                anchors.topMargin: 24
                anchors.leftMargin: 48
                columns: 2
                spacing: 12
                columnSpacing: 40
                Repeater {
                    model: ["短路保护继电器: ", "过流保护继电器: ", "失压保护继电器: ",
                        "缺相保护继电器: ", "限位保护继电器: ", "过载保护继电器: "]
                    Item {
                        width: 160; height: 40
                        Text {
                            id: txt
                            font.pointSize: 12
                            //color: "white"
                            anchors.verticalCenter: parent.verticalCenter
                            text: modelData
                        }
                        Rectangle {
                            id: light
                            width: 20; height: 20
                            radius: 10
                            anchors.verticalCenter: parent.verticalCenter
                            anchors.right: parent.right
                            color: "#00C0F0"
                            opacity: 0.7
                            gradient: Gradient {
                                GradientStop { position: 0.0; color: "lightskyblue" }
                                GradientStop { position: 1.0; color: "#00C0F0" }
                            }
                        }
                    }
                }
            }
        }
    }
    // 传感器组件
    Component {
        id: sensorComponent
        Item {
            id: table
            anchors.fill: parent

            Grid {
                anchors.fill: parent
                anchors.topMargin: 24
                anchors.leftMargin: 48
                columns: 2
                spacing: 12
                columnSpacing: 40

                Item {
                    id: upperlimitItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "上限传感器"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getupperlimit == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getupperlimit == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
                Item {
                    id: lowerlimitItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "下限传感器"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getlowerlimit == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getlowerlimit == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
                Item {
                    id: openingvalueItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "开度传感器"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getopeningvalue == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getopeningvalue == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
                Item {
                    id: upstreamItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "上游水位传感器"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getupstream == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getupstream == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
                Item {
                    id: downstreamItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "下游水位传感器"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getdownstream == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getdownstream == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
                Item {
                    id: flowItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "流量传感器"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getflow == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getflow == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
                Item {
                    id: rainfallItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "雨量传感器"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getrainfall == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getrainfall == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
                Item {
                    id: winddirectItem
                    width: 160; height: 40
                    Text {
                        font.pointSize: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: "风速风向仪"
                    }
                    Rectangle {
                        width: 20; height: 20
                        radius: 10
                        anchors.verticalCenter: parent.verticalCenter
                        anchors.right: parent.right
                        opacity: 0.7
                        gradient: Gradient {
                            GradientStop { position: 0.0; color: getwinddirect == 0 ? "red" : "lightskyblue" }
                            GradientStop { position: 1.0; color: getwinddirect == 0 ? "#F0C000" : "#00C0F0" }
                        }
                    }
                }
            }
        }
    }

    // 左半部选择面板
    Item {
        id: leftPanel
        anchors.left: parent.left
        anchors.top: parent.top
        width: parent.width / 4
        height: parent.height

        Item {
            id: controlMethod
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
                source: "qrc:///res/sluice_control/method.png"
            }

            Text {
                anchors.left: icon1.right
                anchors.leftMargin: 10
                anchors.verticalCenter: icon1.verticalCenter
                color: "white"
                font.pointSize: 14
                text: "控制方式"
            }
            MouseArea {
                anchors.fill: parent
                onClicked: {
                    tinkAudio.play()
                    lightImg.parent = parent;
                    loader.sourceComponent = controlMethodComponent
                }
            }
        }

        Item {
            id: relay
            width: parent.width
            height: 64
            anchors.top: controlMethod.bottom
            anchors.topMargin: 48
            anchors.horizontalCenter: parent.horizontalCenter

            Image {
                id: icon2
                anchors.left: parent.left
                anchors.leftMargin: 24
                anchors.verticalCenter: parent.verticalCenter
                width: 48; height: 48
                source: "qrc:///res/sluice_control/relay.png"
            }

            Text {
                anchors.left: icon2.right
                anchors.leftMargin: 10
                anchors.verticalCenter: icon2.verticalCenter
                color: "white"
                font.pointSize: 14
                text: "继电器状态"
            }
            MouseArea {
                anchors.fill: parent
                onClicked: {
                    tinkAudio.play()
                    lightImg.parent = parent;
                    loader.sourceComponent = relayComponent
                }
            }
        }
        Item {
            id: sensor
            width: parent.width
            height: 64
            anchors.top: relay.bottom
            anchors.topMargin: 48
            anchors.horizontalCenter: parent.horizontalCenter

            Image {
                id: icon3
                anchors.left: parent.left
                anchors.leftMargin: 24
                anchors.verticalCenter: parent.verticalCenter
                width: 48; height: 48
                source: "qrc:///res/sluice_control/sensor.png"
            }

            Text {
                anchors.left: icon3.right
                anchors.leftMargin: 10
                anchors.verticalCenter: icon3.verticalCenter
                color: "white"
                font.pointSize: 14
                text: "传感器状态"
            }
            MouseArea {
                anchors.fill: parent
                onClicked: {
                    tinkAudio.play()
                    lightImg.parent = parent;
                    loader.sourceComponent = sensorComponent
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

