import QtQuick 2.4

/*
Rectangle{
    width: 200;height: 80
    color: "linen"

    TLineEdit{
        id: input1
        x: 8;y: 8
        width: 96;height: 20
        focus: true
        text: "Text Input 1"
        KeyNavigation.tab: input2
        //KeyNavigation（按键向导）附加属性可以预先设置一个元素id绑定切换焦点的按键。
    }

    TLineEdit{
        id: input2
        x: 8;y: 36
        width: 96;height: 20
        text: "Text Input 2"
    }
}
*/
/*
Rectangle{
    width: 136;height: 120
    color: "linen"

    TTextEdit{
        id: input
        x: 8;y: 8
        width: 120;height: 104
        focus: true
        text: "Text Edit"
    }
}
*/
/*
DarkSquare{
    width: 400;height: 200

    GreenSquare{
        id: square
        x: 8;y: 8
    }
    focus: true
    Keys.onLeftPressed: square.x -= 8
    Keys.onRightPressed: square.x += 8
    Keys.onUpPressed: square.y -= 8
    Keys.onDownPressed: square.y += 8
    Keys.onPressed: {
        switch(event.key){
        case Qt.key_Plus:
            square.scale += 0.2
            break;
        case Qt.key_Minus:
            square.scale -= 0.2
            break;
        }
    }
}
*/
