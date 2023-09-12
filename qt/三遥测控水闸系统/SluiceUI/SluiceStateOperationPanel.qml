import QtQuick 2.0
import QtMultimedia 5.0
import QtQuick.Controls 1.2
import QtQuick.Controls.Styles 1.2
import Arcs 1.0

Item {
    id: operationPanel
    width: 230; height: 220
    property int sluiceId: 1
    property string title: "1号闸机"
    property var getopeningvalue
    property var getupperlimit
    property var getlowerlimit
    property var range
    property real getratio
    property var localHandler: root.localHandler

    Timer {
        interval: 1000;
        running: true;
        repeat: true
        onTriggered:{
            sluiceModel.update();
        }
    }

    SluiceModel{
        id: sluiceModel
        sluiceId: operationPanel.sluiceId
        Component.onCompleted: update();

        function update() {
            requestSluiceData(1, sluiceId);
            getopeningvalue = model.get(0).openingvalue;
            getupperlimit = model.get(0).upperlimit;
            getlowerlimit = model.get(0).lowerlimit;
            range = getupperlimit - getlowerlimit
            getratio = range > 0 ? getopeningvalue / ( getupperlimit - getlowerlimit ) : 0.0
        }
    }

    // 滑块触底声音
    Audio {
        id: tinkAudio
        source: "qrc:///sound/sluice_state/tink.wav"
    }
    FontLoader { id: localFont; source: "qrc:///res/ttf/luggage.ttf" }

    Image {
        id: container
        anchors.fill: parent
        source: "qrc:///res/sluice_state/panel_background.png"

        Text {
            id: panelTitle
            width: parent.width
            y: 4
            horizontalAlignment: Text.Center
            color: "white"
            font { pointSize: 12; }
            text: "操作面板"
        }

        Text {
            id: sluiceText
            width: parent.width
            anchors.top: panelTitle.bottom
            anchors.topMargin: 6
            horizontalAlignment: Text.Center
            color: "white"
            font { pointSize: 10; }
            text: title
        }

        Rectangle {
            id: sliderRect
            y: container.height / 4
            width: container.width - 2
            height: 64
            anchors.horizontalCenter: container.horizontalCenter
            radius: 2
            border { width: 1; color: "#333333" }
            color: "#10FFFAF0"

            Image {
                id: customSlider
                y: 5
                height: parent.height / 2
                width: parent.width - 12
                anchors.horizontalCenter: sliderRect.horizontalCenter
                source: "qrc:///res/sluice_state/empty_bar.png"

                Image {
                    id: fillbar
                    height: parent.height / 6
                    anchors.left: parent.left
                    anchors.right: key.horizontalCenter
                    anchors.verticalCenter: parent.verticalCenter
                    source: "qrc:///res/sluice_state/fill_bar.png"
                }

                Image {
                    id: key
                    x: ( customSlider.width - 24 ) * getratio
                    width: 24; height: 24
                    anchors.verticalCenter: parent.verticalCenter
                    source: "qrc:///res/sluice_state/key.png"

                    // 滑块拖动设置
                    MouseArea {
                        id: keyArea
                        anchors.fill: parent
                        drag { target: key; axis: Drag.XAxis; minimumX: 0; maximumX: customSlider.width - 24 }
                    }

                    onXChanged: {
                        if ( key.x == keyArea.drag.minimumX || key.x == keyArea.drag.maximumX )
                            tinkAudio.play();
                    }
                }
            }

            Text {
                id: sliderTextLeft
                anchors.top: customSlider.bottom
                anchors.left: sliderRect.left
                anchors.margins: 4
                color: key.x == keyArea.drag.minimumX ? "orange" : "white";
                font.pointSize: 12
                text: "全关"
            }

            Text {
                id: sliderTextRight
                anchors.top: customSlider.bottom
                anchors.right: sliderRect.right
                anchors.margins: 4
                color: key.x == keyArea.drag.maximumX ? "orange" : "white";
                font.pointSize: 12
                text: "全开"
            }
        }

        Rectangle {
            id: inputRect
            anchors.top: sliderRect.bottom
            anchors.topMargin: 4
            width: container.width - 2
            height: 64
            anchors.horizontalCenter: container.horizontalCenter
            radius: 2
            border { width: 1; color: "#333333" }
            color: "#10FFFAF0"

            Text {
                id: texter
                anchors.left: inputRect.left
                anchors.verticalCenter: inputRect.verticalCenter
                anchors.margins: 4
                color: "white";
                font.pointSize: 12
                text: "精确开度: "
            }

            // 实际发送的开度设置
            TextField {
                id: textField
                anchors.left: texter.right
                anchors.right: indicatorBtn.left
                anchors.rightMargin: 10
                anchors.verticalCenter: inputRect.verticalCenter
                horizontalAlignment: Text.HorizontalFit
                text: ( key.x  * parseFloat(range) / keyArea.drag.maximumX + parseFloat(getlowerlimit) ).toFixed(2)

                style: touchStyle
                readOnly: false
            }
            Component {
                id: touchStyle
                TextFieldStyle {
                    textColor: "white"
                    font { family: localFont.name; pointSize: 14 }
                    background: Item {
                        BorderImage {
                            source: "qrc:///res/sluice_state/textinput.png"
                            border.left: 4
                            border.right: 4
                            anchors.bottom: parent.bottom
                            anchors.left: parent.left
                            anchors.right: parent.right
                        }
                    }
                }
            }

            Item {
                id: indicatorBtn
                anchors.verticalCenter: inputRect.verticalCenter
                anchors.right: inputRect.right
                anchors.margins: 10
                width: 36; height: 36

                Timer {
                    id: turnbackTimer
                    interval: 3000
                    repeat: false
                    onTriggered: img.source = "qrc:///res/sluice_state/normal_btn.png"
                }

                Image {
                    id: img
                    anchors.fill: parent
                    source: "qrc:///res/sluice_state/normal_btn.png"
                    Arc {
                        id: arc
                        anchors.fill: parent
                        color: "orange"
                        angle: 0
                        Behavior on angle {
                            NumberAnimation { duration: 1000 }
                        }
                        onAngleChanged:  {
                            if ( angle >= 360 ) {
                                arc.visible = false;
                                angle = 0;
                                img.source = "qrc:///res/sluice_state/complete_btn.png"
                                turnbackTimer.restart();
                            }
                        }
                    }
                }

                MouseArea {
                    id: btnArea
                    anchors.fill: parent
                    onClicked: {
                        // 向底层写入数据,并保存此时开度值
                        if ( textField.text !== getopeningvalue ) {
                            localHandler.sluiceOpenControl(sluiceId, parseFloat(textField.text));
                            arc.visible = true;
                            arc.angle = 360;
                        }
                    }
                }
            }
        }
    }
}
