import QtQuick 2.0
import Box2D 1.1

Body {
    bodyType: Body.Static
    fixtures: Box { anchors.fill: parent }
    Image {
        id: img
        anchors.fill: parent
        source: "img/process.png"
    }
}
