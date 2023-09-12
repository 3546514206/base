import QtQuick 2.4
/*
Image{
    source: "assets/background.png"

    Image {
        x: 60;y: 80
        source: "assets/rocket.png"

        NumberAnimation on x {
            to: 240
            duration: 4000
            loops: Animation.Infinite
        }
        RotationAnimation on rotation {
            to: 360
            duration: 4000
            loops: Animation.Infinite
        }
    }
}
*/
/*
Item{
    id: root
    width: 400; height: 420

    Image {
        anchors.centerIn: parent
        source: "assets/background.png"
    }

    MouseArea {
        anchors.fill: parent
        onClicked: {
            rocket1.y = rocket2.y = rocket3.y = 205
        }
    }

    ClickableImageV2{
        id: rocket1
        x: 40; y: root.height-height
        source: "assets/rocket.png"
        text: "animotion on property"
        NumberAnimation on y{
            to: 40;
            duration: 4000
        }
    }
    ClickableImageV2{
        id: rocket2
        x: 152; y: root.height-height
        source: "assets/rocket.png"
        text: "behavior on property"
        Behavior on y{
            NumberAnimation{
                duration: 4000
            }
        }
        onClicked: y=40
        //onClicked: y = 40 + Math.random()*(205-40)
    }
    ClickableImageV2{
        id: rocket3
        x: 264; y: root.height-height
        source: "assets/rocket.png"
        onClicked: anim.start()
        text: "standalone animation"

        NumberAnimation {
            id: anim
            target: rocket3
            properties: "y"
            from: 205
            to: 40
            duration: 4000
        }
    }
}
*/
/*
DarkSquare{
    id: root
    width: 600; height: 340

    property variant easings: [
        "Linear","InQuad","OutQuad","InOutQuad",
        "InCubic","InSine","InCirc","InElastic",
        "InBack","InBounce"]
    Grid{
        id: container
        anchors.top: parent.top
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.margins: 16
        height: 200
        columns: 5
        spacing: 16
        Repeater{
            model: easings
            ClickableImageV3{
                frame: true
                text: modelData
                source: "curves/" + modelData + ".png"
                onClicked: {
                    anim.easing.type = modelData
                    anim.restart()
                }
            }
        }
    }

    GreenSquare{
        id: square
        x: 40; y: 260
    }

    NumberAnimation{
        id: anim
        target: square
        from: 40; to: root.width - 40 - square.width
        properties: "x"
        duration: 2000
    }
}
*/
/*
//parallelanimation.qml
BrightSquare{
    id: root
    width: 280; height: 350
    property int duration: 3000

    ClickableImageV3{
        id: rocket
        x: 20; y: 120
        source: "assets/rocket.png"
        onClicked: anim.restart()
    }

    ParallelAnimation{
        id: anim
        NumberAnimation{
            target: rocket
            properties: "y"
            to: 20
            duration: root.duration
        }
        NumberAnimation{
            target: rocket
            properties: "x"
            to: 160
            duration: root.duration
        }
    }
}
*/
/*
//sequentialanimation
BrightSquare{
    id: root
    width: 280
    height: 350
    property int duration: 3000
    ClickableImageV3{
        id: rocket
        x: 20; y: 120
        source: "assets/rocket.png"
        onClicked: anim.restart()
    }

    SequentialAnimation{
        id: anim
        NumberAnimation{
            target: rocket
            properties: "y"
            to: 20
            duration: root.duration * 0.6
        }
        NumberAnimation{
            target: rocket
            properties: "x"
            to: 160
            duration: root.duration
        }
    }
}
*/
/*
Item{
    id: root
    width: 480; height: 300
    property int duration: 3000

    Rectangle{
        id: sky
        width: parent.width
        height: 200
        gradient: Gradient{
            GradientStop{ position: 0.0; color: "#0080FF" }
            GradientStop{ position: 1.0; color: "#66ccff" }
        }
    }
    Rectangle{
        id: ground
        anchors.top: sky.bottom
        anchors.bottom: root.bottom
        width: parent.width
        gradient: Gradient{
            GradientStop {position: 0.0; color: "#00ff00" }
            GradientStop {position: 1.0; color: "#00803f" }
        }

    }
    Image{
        id: ball
        x: 20; y: 240
        source: "assets/soccer_ball.png"

        MouseArea {
            anchors.fill: parent
            onClicked: {
                ball.x = 20; ball.y = 240
                anim.restart()
            }
        }
    }

    ParallelAnimation {
        id: anim
        SequentialAnimation{
            //首先使用一个连续的动画来播放两次的y轴变换
            NumberAnimation {
                target: ball
                properties: "y"
                to: 20
                duration: root.duration * 0.4
                easing.type: Easing.OutCirc
            }
            NumberAnimation {
                target: ball
                properties: "y"
                to: 240
                duration: root.duration * 0.6
                easing.type: Easing.OutBounce
            }
        }
        NumberAnimation {
            target: ball
            properties: "x"
            to: 400
            duration: root.duration
        }
        RotationAnimation{
            target: ball
            properties: "rotation"
            to: 720
            duration: root.duration * 1.1
        }
    }
}
*/


Item{
    id: root
    width: 150; height: 250
    state: "stop"
    states: [
        State{
            name: "stop"
            PropertyChanges {
                target: light1;
                color: "red"
            }
            PropertyChanges {
                target: light2;
                color: "black"
            }
        },
        State{
            name: "go"
            PropertyChanges {
                target: light1;
                color: "black"
            }
            PropertyChanges {
                target: light2;
                color: "green"
            }
        }

    ]
    Rectangle{
        id: light1
        x: 25; y: 15
        width: 100; height: width
        radius: width / 2
        color: "black"
        border.color: Qt.lighter(color,1.1)
    }
    Rectangle {
        id: light2
        x: 25; y: 135
        width: 100; height: width
        radius: width/2
        color: "black"
        border.color: Qt.lighter(color,1.1)
    }
    MouseArea {
        anchors.fill: parent
        onClicked: parent.state = (parent.state == "stop"? "go" : "stop")
    }
    transitions: [
        Transition {
            from: "stop"
            to: "go"

            ColorAnimation {
                target: light1;
                properties: "color";
                duration: 2000
            }
            ColorAnimation {
                target: light2;
                properties: "color"
                duration: 2000
            }
        }
    ]
}
