import QtQuick 2.0

Image {
    id: container
    source: "qrc:///images/bg.jpg"
    width: 480
    height: 640

    property int steps
    property int catIndex: 40
    property var activeRoundArray: []

    EndPanel {
        id: endPanel
        width: container.width - 120
        height: container.height / 2
        scale: 0.0
        z: 99
        steps: container.steps

        Behavior on scale {
            NumberAnimation { duration: 400 }
        }
        Behavior on x {
            NumberAnimation { duration: 400 }
        }
        Behavior on y {
            NumberAnimation { duration: 400 }
        }
    }

    Column {
        id: column
        anchors.bottom: parent.bottom
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.leftMargin: 20
        anchors.bottomMargin: 20
        anchors.rightMargin: 40

        spacing: 8

        Repeater {
            id: columnRepeater
            model: 9

            Row {
                id: row
                spacing: 8
                property int columnIndex: index

                Item {
                    id: stretch
                    width: ( columnIndex % 2 ) * 16;    // 16 + 8 = 24 = ( 40 + 8 ) / 2
                    height: width
                }

                Repeater {
                    id: rowRepeater
                    model: 9

                    Rectangle {
                        id: round
                        height: 40
                        width:  height
                        radius: height / 2
                        color: active === true ? 'orangered' : 'lightgrey'

                        property int roundIndex: columnIndex * 9 + index
                        property bool active: roundIndex !== 40 && Math.random() * 100 < 10 ? true : false

                        MouseArea {
                            anchors.fill: parent
                            onClicked: {
                                if ( round.active === false ) {
                                    round.active = true;
                                    container.update();
                                }
                            }
                        }

                        onActiveChanged: {
                            if ( round.active === true )
                                activeRoundArray.push(roundIndex)

                            //console.log(activeRoundArray)
                        }
                    }
                }
            }
        }
    }  // Column End

    SpriteSequence {
        id: spriteCat
        x: container.width / 2 - 40
        y: container.height / 2
        Behavior on x {
            NumberAnimation{ duration: 200 }
        }
        Behavior on y {
            NumberAnimation{ duration: 200 }
        }

        width: sprite.frameWidth
        height: sprite.frameHeight
        interpolate: false

        sprites: [
            Sprite {
                id: sprite
                name: "cat"
                source: "qrc:///images/stay.png"
                frameWidth: 61
                frameHeight: 93
                frameCount: 16
                frameDuration: 120
            },
            Sprite {
                name: "weizhu"
                source: "qrc:///images/weizhu.png"
                frameWidth: 64
                frameHeight: 91
                frameCount: 15
                frameDuration: 80
            }

        ]
    }

    function update() {
        var oddCompensate;                // 奇数行补偿值
        if ( Math.floor(catIndex / 9) % 2 === 0 )
            oddCompensate = 0;
        else
            oddCompensate = 1;

        var directionArray = [];          // 方位数组,从左边顺时针绕圆,为[0-5]
        if ( activeRoundArray.indexOf(catIndex - 1) === -1 )
            directionArray.push(0);
        if ( activeRoundArray.indexOf(catIndex - 10 + oddCompensate) === -1 )
            directionArray.push(1);
        if ( activeRoundArray.indexOf(catIndex - 9 + oddCompensate) === -1 )
            directionArray.push(2);
        if ( activeRoundArray.indexOf(catIndex + 1) === -1 )
            directionArray.push(3);
        if ( activeRoundArray.indexOf(catIndex + 9 + oddCompensate) === -1 )
            directionArray.push(4);
        if ( activeRoundArray.indexOf(catIndex + 8 + oddCompensate) === -1 )
            directionArray.push(5);

        var randomNum = Math.floor(Math.random() * directionArray.length);

        //console.log("array:" + directionArray + " randomNum:" + randomNum + " catIndex:" + catIndex)

        if ( directionArray[randomNum] === 0 )
            left();
        else if ( directionArray[randomNum] === 1 )
            leftUp();
        else if ( directionArray[randomNum] === 2 )
            rightUp();
        else if ( directionArray[randomNum] === 3 )
            right();
        else if ( directionArray[randomNum] === 4 )
            rightDown();
        else if ( directionArray[randomNum] === 5 )
            leftDown();

        // 神经猫被围住
        else if ( directionArray.length == 0 ) {
            spriteCat.jumpTo("weizhu");
            endPanel.victory = true
            endPanel.scale = 1.0;
            endPanel.x = 60
            endPanel.y = container.height / 3
        }

        // 神经猫逃脱
        if ( catIndex < 9 || catIndex > 72 || catIndex % 9 === 0 || ( catIndex + 1 ) % 9 === 0 ) {
            endPanel.victory = false
            endPanel.scale = 1.0;
            endPanel.x = 60
            endPanel.y = container.height / 3
        }

        steps += 1;
    }

    // 运动方向函数
    function left() {
        spriteCat.x -= 48;
        catIndex -= 1;
    }
    function leftUp() {
        spriteCat.x -= 24;
        spriteCat.y -= 48;
        if ( Math.floor(catIndex / 9) % 2 === 0 ) // 偶数行向左上运动减10,奇数行减9
            catIndex -= 10;
        else
            catIndex -= 9;
    }
    function rightUp() {
        spriteCat.x += 24;
        spriteCat.y -= 48;
        if ( Math.floor(catIndex / 9) % 2 === 0 ) // 偶数行向右上运动减9,奇数行减8
            catIndex -= 9;
        else
            catIndex -= 8;
    }
    function right() {
        spriteCat.x += 48;
        catIndex += 1;
    }
    function rightDown() {
        spriteCat.x += 24;
        spriteCat.y += 48;
        if ( Math.floor(catIndex / 9) % 2 === 0 ) // 偶数行向右下运动加9,奇数行加10
            catIndex += 9;
        else
            catIndex += 10;
    }
    function leftDown() {
        spriteCat.x -= 24;
        spriteCat.y += 48;
        if ( Math.floor(catIndex / 9) % 2 === 0 ) // 偶数行向左下运动加8,奇数行加9
            catIndex += 8;
        else
            catIndex += 9;
    }
}
