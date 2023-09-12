import QtQuick 2.4

//Item{
//Rect {}
//width: 252
//height: 252
/*
    DarkSquare{
        id: rt
        width: 120
        height: 240
        Column{
            id: column
            anchors.centerIn: parent
            spacing: 8
            RedSquare {}
            GreenSquare {width: 96}
            BlueSquare {}
        }
    }
    */
/*
    BrightSquare{
        id:r2ot
        width: 400;height: 120
        Row{
            id: row
            anchors.centerIn: parent
            spacing: 20
            BlueSquare {}
            GreenSquare {}
            RedSquare {}
        }
    }
    */
/*
    BrightSquare{
        id: rot
        width: 160;height: 160
        Grid{
            id: grid
            rows: 2
            columns: 2
            anchors.centerIn: parent
            spacing: 8
            RedSquare {}
            RedSquare {}
            RedSquare {}
            RedSquare {}
        }
    }
    */
/*
    BrightSquare{
        id:rrt
        width: 160;height: 160
        Flow{
            anchors.fill: parent
            anchors.margins: 20
            spacing: 20
            RedSquare {}
            BlueSquare {}
            GreenSquare {}
        }
    }
    */
/*
    DarkSquare{
        id: root
        width: 252;height: 252
        property variant colorArray: ["#00bde3","#67c111","#ea7025"]
        Grid{
            anchors.fill: parent
            anchors.margins: 8
            spacing: 4
            Repeater{
                model: 16
                Rectangle{
                    width: 56;height: 56
                    property int colorIndex: Math.floor(Math.random()*3)
                    color: root.colorArray[colorIndex]
                    border.color: Qt.lighter(color)
                    Text{
                        anchors.centerIn: parent
                        color: "#f0f0f0"
                        text: "Cell " + index
                    }
                }
            }
        }
    }
    */
//}


DarkSquare {
    id: root
    width: 400; height: 240

    Grid{
        anchors.fill: parent
        anchors.margins: 16
        spacing: 8
        columns: 4
        //元素填充它的父元素
        GreenSquare{
            BlueSquare{
                width: 12
                anchors.fill: parent
                anchors.margins: 12
                text: '(1)'
            }
        }
        //元素左对齐它的父元素
        GreenSquare{
            BlueSquare{
                width: 48
                y: 8
                anchors.left: parent.left
                anchors.leftMargin: 8
                text: '(2)'
            }
        }
        //元素的左边与它的父元素的右边对齐
        GreenSquare{
            BlueSquare{
                width: 48
                anchors.left: parent.right
                text: '(3)'
            }
        }
        EmptySquare {}
        //元素中间对齐
        GreenSquare{
            BlueSquare{
                id: blue1
                width: 48;height: 24
                y: 8
                anchors.horizontalCenter: parent.horizontalCenter
            }
            BlueSquare{
                id: blue2
                width: 72;height: 24
                anchors.top: blue1.bottom
                anchors.topMargin: 4
                anchors.horizontalCenter: blue1.horizontalCenter
                text: '(4)'
            }
        }
        //元素在它的父元素居中
        GreenSquare{
            BlueSquare{
                width: 48
                anchors.centerIn: parent
                text: '(5)'
            }
        }
        //元素水平方向居中对齐父元素并向后偏移12像素，垂直方向居中对齐
        GreenSquare{
            BlueSquare{
                width: 48
                anchors.horizontalCenter: parent.horizontalCenter
                anchors.horizontalCenterOffset: -12
                anchors.verticalCenter: parent.verticalCenter
                text: '(6)'
            }
        }
    }
}

/*
Item{
    id:root
    width: 200;height: 120
    Rectangle{
        id: rect1
        x: 12;y: 12
        width: 76;height: 96
        color: "lightsteelblue"
        MouseArea{
            id: area
            width: parent.width
            height: parent.height
            onClicked: rect2.visible= !rect2.visible
        }
    }
    Rectangle{
        id: rect2
        x: 112;y:12
        width: 76;height: 96
        border.color: "lightsteelblue"
        border.width: 4
        radius: 8
    }
}
*/

/*
Rectangle{
    id: rect1
    //x: 12;y: 12
    width: 300;height: 50
    gradient: Gradient{
        GradientStop{
            position: 0.0;color: "lightsteelblue"
        }
        GradientStop{
            position: 1.0;color: "slategray"
        }
    }
    border.width: 10
    border.color: "white"
    Text{
        //width: 300;height: 40
        text: "The quick brown fox"
        color: "#303030"
        font.family: "Ubuntu"
        font.pixelSize: 28
        x: (parent.width - width)/2;y: (parent.height - height)/2
        style: Text.Sunken
        styleColor: '#ff4444'
        //verticalAlignment: Text.AlignTop
        wrapMode: Text.WordWrap
    }
}
*/
/*
Item{
    Rectangle{
        id: button
        x: 12;y: 12
        width: 116;height: 26
        color: "lightsteelblue"
        border.color: "slategrey"
        Text{
            anchors.centerIn: parent
            text: "Start"
        }
        MouseArea{
            anchors.fill: parent
            onClicked: {
                status.text = "Button clicked!"
            }
        }
    }
    Text{
        id: status
        x: 12;y: 76
        width: 116;height: 26
        text: "Waiting ..."
        horizontalAlignment: Text.AlignHCenter
    }
}
*/
/*
Item{
    Button{
        id:button
        x: 12; y: 12
        text: "Stat!"
        onClicked: {
            status.text = "Button clicked"
        }
    }

    Text{
        id: status
        x: 12;y: 76
        width: 116;height: 26
        text: "Waiting ..."
        horizontalAlignment: Text.AlignHCenter
    }
}
*/
/*
Rectangle{
    //圆
    id: root
    width: 120
    height: width
    color: "red"
    radius: width/2
}
*/

