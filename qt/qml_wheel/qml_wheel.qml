import QtQuick 2.2
import QtGraphicalEffects 1.0

Image{
    id: root
    source: "images/background.png"
    property int blurRadius: 0

    Image {
        id: pole
        source: "images/pole.png"
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.bottom: parent.bottom
    }
    Image {
        id: wheel
        anchors.centerIn: parent
        source: "images/wheel.png"
        Behavior on rotation {
            NumberAnimation {
                duration: 250
            }
        }
        layer.effect: FastBlur {
            id: blur
            radius: root.blurRadius
            Behavior on radius {
                NumberAnimation {
                    duration: 250
                }
            }
        }
        MouseArea {
            anchors.fill: parent
            onClicked: wheel.rotation += 90
        }
        layer.enabled: true
    }

}
