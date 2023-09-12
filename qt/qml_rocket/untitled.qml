import QtQuick 2.2
/*
Rectangle{
    id: root
    //color:Qt.rgba(0.5,0.5,0.5,0.9)
    width: 120;height: 240
    color: "#D8D8D8"
    Image{
        id: rocket
        x: (parent.width - width)/2;y: 20
        fillMode: Image.PreserveAspectCrop
        //Image元素使用PreserveAspectCrop属性开启裁剪图片数据避免图片在界面之外显示
        clip: true
        source: 'images/rocket.png'
    }
    Text{
        y: rocket.y + rocket.height + 20
        width: root.width
        horizontalAlignment: Text.AlignHCenter
        text: 'Rocket'
    }
}
*/

//transformation.qml
Item{
    width: bg.width; height: bg.height

    Image{
        id: bg
        source: "assets/background.png"
    }

    MouseArea{
        id: backgroundClicker
        anchors.fill: parent
        onClicked: {
            rocket1.x=20
            rocket2.rotation=0
            rocket3.rotation=0
            rocket3.scale=1.0
        }
    }
    ClickableImage{
        id: rocket1
        x: 20;y: 80
        source: "assets/rocket.png"
        onClicked: {
            x+=5
        }
    }
    ClickableImage{
        id: rocket2
        x: 140; y: 80
        source: "assets/rocket.png"
        smooth: true
        onClicked: {
            rotation += 5
        }
    }
    ClickableImage{
        id: rocket3
        x: 240; y: 80
        source: "assets/rocket.png"
        smooth: true
        onClicked: {
            rotation += 5
            scale -= 0.05
        }
    }
}
