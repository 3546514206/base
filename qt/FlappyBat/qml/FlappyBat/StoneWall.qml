import QtQuick 2.0
import Box2D 1.1
import "img"
Body{
    id:st
    bodyType: Body.Static
    fixtures: Box {anchors.fill: parent}
    Image {
        anchors.fill: parent
        source: "img/stone.png"
    }
}
