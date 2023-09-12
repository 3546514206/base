import QtQuick 2.0
import QtMultimedia 5.0
import Communication 1.0


Item {
    id: root
    width: 1000; height: 600
    property int duration: 1000
    property LocalCommunicationHandler localHandler: localCommunicationHandler

    Audio {
        id: recoveryAudio
        source: "qrc:///sound/sluice_state/recovery.wav"
    }
    Audio {
        // XXX: 这里突然不能使用原先的tock.wav了,换了音频也没有作用,原因不明
        id: knockAudio
        source: "qrc:///sound/sluice_state/tink.wav"
    }


    Image {
        id: container
        anchors.fill: parent
        source: "qrc:///res/sluice_state/background.jpg"

/* // 水流的动态效果
//        Image {
//            id: water
//            height: 600
//            anchors.bottom: parent.bottom
//            anchors.left: parent.left
//            anchors.right: parent.right
//            source: "qrc:///res/sluice_state/water.png"
//            ShaderEffect {
//                        anchors.fill: parent
//                        property variant source: water
//                        property real amplitude: 0.001

//                        property real frequency: 5
//                        property real time: 0
//                        NumberAnimation on time { loops: Animation.Infinite; from: 0; to: Math.PI * 2; duration: 1000 }

//                        fragmentShader: "
//                            uniform float amplitude;
//                            uniform float frequency;
//                            uniform float time;

//                            uniform sampler2D source;
//                            uniform lowp float qt_Opacity;
//                            varying vec2 qt_TexCoord0;

//                            void main()
//                            {
//                                vec2 uv = qt_TexCoord0.xy;
//                                vec2 tc = qt_TexCoord0;
//                                vec2 p = sin(time + frequency * qt_TexCoord0);
//                                tc += amplitude * vec2(p.y, -p.x);
//                                gl_FragColor = qt_Opacity * texture2D(source, tc);
//                            }"
//                    }
//        }
*/
        /*****************************upRect**********************/
        Image {
            id: upRect
            width: parent.width
            height: 48
            opacity: 0.95
            source: "qrc:///general/general/title_banner.png"

            Image {
                id: viewImg1
                x: parent.width / 5
                y: 0
                height: 36; width: 36
                anchors.verticalCenter: upRect.verticalCenter
                source: "qrc:///res/sluice_state/view.png"
                MouseArea {
                    anchors.fill: parent
                    onClicked: startAnim1.start()
                }
            }

            Image {
                id: settingImg1
                anchors.left: viewImg1.left
                anchors.leftMargin: 125
                height: 36; width: 36
                anchors.verticalCenter: upRect.verticalCenter
                source: "qrc:///res/sluice_state/setting.png"
                MouseArea {
                    anchors.fill: parent
                    onClicked: startAnim2.start()
                }
            }

            Image {
                id: viewImg2
                anchors.left: settingImg2.left
                anchors.leftMargin: - 125
                height: 36; width: 36
                anchors.verticalCenter: upRect.verticalCenter
                source: "qrc:///res/sluice_state/view.png"
                MouseArea {
                    anchors.fill: parent
                    onClicked: startAnim3.start()
                }
            }

            Image {
                id: settingImg2
                x: parent.width * 4 / 5 - 48
                height: 36; width: 36
                anchors.verticalCenter: upRect.verticalCenter
                source: "qrc:///res/sluice_state/setting.png"
                MouseArea {
                    anchors.fill: parent
                    onClicked: startAnim4.start()
                }
            }
        }
        /************************upRect Done******************************/

        CustomCalendar {
            anchors.right: container.right
            anchors.top: container.top
            width: 200; height: width + 50
        }

        /************************MonitorPanel 1******************************/
        SluiceStateMonitorPanel {
            id: monitorPanel1
            width: 230; height: 300;
            transformOrigin: Item.TopLeft
            scale: 0.0
            sluiceId: 1
            title: "一号闸机"

            MouseArea {
                id: mouseArea1
                enabled: false
                anchors.fill: parent
                anchors.bottomMargin: parent.height - 36
                drag {
                    target: monitorPanel1;
                    axis: Drag.XAndYAxis;
                }
                onReleased: {
                    if ( monitorPanel1.y < container.height / 6 && monitorPanel1.x < container.width / 2 )
                        recoveryAnim1.start();
                    else
                        dragAnim1.start();
                }
            }

            // 拖拽动画
            NumberAnimation {
                id: dragAnim1
                target: monitorPanel1
                properties: "y"
                to: container.height - monitorPanel1.height
                duration: 500
                easing.type: Easing.InCubic
                onStopped: knockAudio.play()
            }

            // 回收动画
            ParallelAnimation {
                id: recoveryAnim1
                NumberAnimation {
                    target: monitorPanel1
                    properties: "scale"
                    from: 1.0
                    to: 0.0
                    duration: 500
                }
                NumberAnimation {
                    target: monitorPanel1
                    properties: "x"
                    to: viewImg1.x + 18
                    duration: 500
                }
                NumberAnimation {
                    target: monitorPanel1
                    properties: "y"
                    to: 18
                    duration: 500
                    easing.type: Easing.InQuint
                }
                onStopped: {
                    recoveryAudio.play();
                    mouseArea1.enabled = false;
                }
            }

            // 初始动画
            ParallelAnimation {
                id: startAnim1
                NumberAnimation {
                    target: monitorPanel1
                    properties: "scale"
                    from: 0.0
                    to: 1.0
                    duration: root.duration
                }
                NumberAnimation {
                    target: monitorPanel1
                    properties: "x"
                    from: viewImg1.x + 18
                    to: 36
                    duration: root.duration
                }
                NumberAnimation {
                    target: monitorPanel1
                    properties: "y"
                    from: 18
                    to: container.height - monitorPanel1.height
                    duration: root.duration
                    easing.type: Easing.OutBack
                }
                onStopped: {
                    mouseArea1.enabled = true;
                    knockAudio.play()
                }
            }
        }
        /************************MonitorPanel 1 Done******************************/

        /************************OperationPanel 1******************************/
        SluiceStateOperationPanel {
            id: operationPanel1
            width: 230; height: 220;
            transformOrigin: Item.TopLeft
            scale: 0.0
            sluiceId: 1
            title: "一号闸机"

            MouseArea {
                id: mouseArea2
                anchors.fill: parent
                anchors.bottomMargin: parent.height - 36
                enabled: false
                drag {
                    target: operationPanel1;
                    axis: Drag.XAndYAxis;
                }
                onReleased: {
                    if ( operationPanel1.y < container.height / 6 && operationPanel1.x < container.width / 2 ) {
                        recoveryAnim2.start();
                    }
                    else
                        dragAnim2.start();
                }
            }

            NumberAnimation {
                id: dragAnim2
                target: operationPanel1
                properties: "y"
                to: container.height - operationPanel1.height
                duration: 500
                easing.type: Easing.InCubic
                onStopped: knockAudio.play()
            }
            ParallelAnimation {
                id: recoveryAnim2
                NumberAnimation {
                    target: operationPanel1
                    properties: "scale"
                    from: 1.0
                    to: 0.0
                    duration: 500
                }
                NumberAnimation {
                    target: operationPanel1
                    properties: "x"
                    to: settingImg1.x + 18
                    duration: 500
                }
                NumberAnimation {
                    target: operationPanel1
                    properties: "y"
                    to: 18
                    duration: 500
                    easing.type: Easing.InQuint
                }
                onStopped: {
                    recoveryAudio.play();
                    mouseArea2.enabled = false;
                }
            }

            ParallelAnimation {
                id: startAnim2
                NumberAnimation {
                    target: operationPanel1
                    properties: "scale"
                    from: 0.0
                    to: 1.0
                    duration: root.duration
                }
                NumberAnimation {
                    target: operationPanel1
                    properties: "x"
                    from: settingImg1.x + 18
                    to: container.width / 4
                    duration: root.duration
                }
                NumberAnimation {
                    target: operationPanel1
                    properties: "y"
                    from: 18
                    to: container.height - operationPanel1.height
                    duration: root.duration
                    easing.type: Easing.OutBack
                }
                onStopped: {
                    mouseArea2.enabled = true;
                    knockAudio.play()
                }
            }
        }
        /************************OperationPanel 1 Done******************************/

        /************************MonitorPanel 2******************************/
        SluiceStateMonitorPanel {
            id: monitorPanel2
            width: 230; height: 300;
            transformOrigin: Item.TopLeft
            scale: 0.0
            sluiceId: 2
            title: "二号闸机"

            MouseArea {
                id: mouseArea3
                anchors.fill: parent
                anchors.bottomMargin: parent.height - 36
                enabled: false
                drag {
                    target: monitorPanel2
                    axis: Drag.XAndYAxis;
                }
                onReleased: {
                    if ( monitorPanel2.y < container.height / 6 && monitorPanel2.x > container.width / 2 )
                        recoveryAnim3.start();
                    else
                        dragAnim3.start();
                }
            }

            NumberAnimation {
                id: dragAnim3
                target: monitorPanel2
                properties: "y"
                to: container.height - monitorPanel2.height
                duration: 500
                easing.type: Easing.InCubic
                onStopped: knockAudio.play()
            }
            ParallelAnimation {
                id: recoveryAnim3
                NumberAnimation {
                    target: monitorPanel2
                    properties: "scale"
                    from: 1.0
                    to: 0.0
                    duration: 500
                }
                NumberAnimation {
                    target: monitorPanel2
                    properties: "x"
                    to: viewImg2.x + 18
                    duration: 500
                }
                NumberAnimation {
                    target: monitorPanel2
                    properties: "y"
                    to: 18
                    duration: 500
                    easing.type: Easing.InQuint
                }
                onStopped: {
                    recoveryAudio.play();
                    mouseArea3.enabled = false;
                }
            }

            ParallelAnimation {
                id: startAnim3
                NumberAnimation {
                    target: monitorPanel2
                    properties: "scale"
                    from: 0.0
                    to: 1.0
                    duration: root.duration
                }
                NumberAnimation {
                    target: monitorPanel2
                    properties: "x"
                    from: viewImg2.x + 18
                    to: container.width / 2 + 36
                    duration: root.duration
                }
                NumberAnimation {
                    target: monitorPanel2
                    properties: "y"
                    from: 18
                    to: container.height - monitorPanel2.height
                    duration: root.duration
                    easing.type: Easing.OutBack
                }
                onStopped: {
                    mouseArea3.enabled = true;
                    knockAudio.play()
                }
            }
        }
        /************************MonitorPanel 2 Done******************************/

        /************************OperationPanel 2******************************/
        SluiceStateOperationPanel {
            id: operationPanel2
            width: 230; height: 220;
            transformOrigin: Item.TopLeft
            scale: 0.0
            sluiceId: 2
            title: "二号闸机"

            MouseArea {
                id: mouseArea4
                anchors.fill: parent
                anchors.bottomMargin: parent.height - 36
                enabled: false
                drag {
                    target: operationPanel2;
                    axis: Drag.XAndYAxis;
                }
                onReleased: {
                    if ( operationPanel2.y < container.height / 6 && operationPanel2.x > container.width / 2 ) {
                        recoveryAnim4.start();
                    }
                    else
                        dragAnim4.start();
                }
            }

            NumberAnimation {
                id: dragAnim4
                target: operationPanel2
                properties: "y"
                to: container.height - operationPanel2.height
                duration: 500
                easing.type: Easing.InCubic
                onStopped: knockAudio.play()
            }
            ParallelAnimation {
                id: recoveryAnim4
                NumberAnimation {
                    target: operationPanel2
                    properties: "scale"
                    from: 1.0
                    to: 0.0
                    duration: 500
                }
                NumberAnimation {
                    target: operationPanel2
                    properties: "x"
                    to: settingImg2.x + 18
                    duration: 500
                }
                NumberAnimation {
                    target: operationPanel2
                    properties: "y"
                    to: 18
                    duration: 500
                    easing.type: Easing.InQuint
                }
                onStopped: {
                    recoveryAudio.play();
                    mouseArea4.enabled = false;
                }
            }

            ParallelAnimation {
                id: startAnim4
                NumberAnimation {
                    target: operationPanel2
                    properties: "scale"
                    from: 0.0
                    to: 1.0
                    duration: root.duration
                }
                NumberAnimation {
                    target: operationPanel2
                    properties: "x"
                    from: settingImg2.x + 18
                    to: container.width - 250
                    duration: root.duration
                }
                NumberAnimation {
                    target: operationPanel2
                    properties: "y"
                    from: 18
                    to: container.height - operationPanel2.height
                    duration: root.duration
                    easing.type: Easing.OutBack
                }
                onStopped: {
                    mouseArea4.enabled = true;
                    knockAudio.play()
                }
            }
        }
    }
}
/************************OperationPanel 2 Done******************************/
