import QtQuick 2.4
/*
Background {
    id: root
    PathView {
        anchors.fill: parent
        delegate: flipCardDelegate
        model: 10
        path: Path {
            startX: root.width/2
            startY: 0
            PathAttribute {
                name: "itemZ"
                value: 0
            }
            PathAttribute {
                name: "itemAngle"
                value: -90.0
            }
            PathAttribute {
                name: "itemScale"
                value: 0.5
            }
            PathLine {
                x: root.width/2
                y: root.height*0.4
            }
            PathPercent {
                value: 0.48
            }
            PathLine {
                x: root.width/2
                y: root.height*0.5
            }
            PathAttribute {
                name: "itemAngle"
                value: 0.0
            }
            PathAttribute {
                name: "itemScale"
                value: 1.0
            }
            PathAttribute {
                name: "itemZ"
                value: 100
            }
            PathLine {
                x: root.width/2
                y: root.height*0.6
            }
            PathPercent {
                value: 0.52
            }
            PathLine {
                x: root.width/2
                y: root.height
            }
            PathAttribute {
                name: "itemAngle"
                value: 90.0
            }
            PathAttribute {
                name: "itemScale"
                value: 0.5
            }
            PathAttribute {
                name: "itemZ"
                value: 0
            }
        }
        pathItemCount: 16

        preferredHighlightBegin: 0.5
        preferredHighlightEnd: 0.5
    }

    Component {
        id: flipCardDelegate
        Item {
            id: wrapper
            width: 64; height: 64
            visible: PathView.onPath
            scale: PathView.itemScale
            z: PathView.itemZ
            property variant rotX: PathView.itemAngle
            transform: Rotation {
                axis {
                    x: 1
                    y: 0
                    z: 0
                }
                angle: wrapper.rotX
                origin {
                    x: 32
                    y: 32
                }
            }
            Rectangle {
                anchors.fill: parent
                color: "DeepSkyBlue"
                border.color: "white"
                border.width: 3
            }
            Text {
                anchors.centerIn: parent
                text: index
                font.pixelSize: 30
            }
        }
    }

}
*/

