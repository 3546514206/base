import QtQuick 2.0
import Box2D 1.1
import "img"
Body {
    property alias playing: batImg.playing
    property alias source: batImg.source
    sleepingAllowed: false
	bodyType: Body.Dynamic
    fixtures: Box {
        id:b
        width:parent.width*2/3
        height:parent.height/3
        anchors.centerIn: parent
        density: 1;
        friction: 0.3;
        restitution: 0.2;
    }

    AnimatedImage {
        id:batImg
        anchors.fill: parent
        source: "img/bat.gif"
    }
    Connections{
        target: b
        onBeginContact : {
            mainWin.state="result"
            console.log("re")
        }
    }
}
