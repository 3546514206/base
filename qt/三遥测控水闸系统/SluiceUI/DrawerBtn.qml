import QtQuick 2.0
import QtQuick.Controls 1.2
import QtMultimedia 5.0

Item {
    id: root
    width: 100
    height: 40
    signal actived()

    property url initSource
    property url pressedSource
    property url activeSource
    property bool checked: false
    property ExclusiveGroup exclusiveGroup: null
    onExclusiveGroupChanged: {
        if (exclusiveGroup)
            exclusiveGroup.bindCheckable(root)
    }
    onCheckedChanged: {
        if ( checked == true ) {
            img.source = activeSource;
            root.actived()
        }
        else
            img.source = initSource;
    }

    Image {
        id: img
        anchors.fill: parent
        source: initSource

        MouseArea {
            anchors.fill: parent
            onPressed: {
                playMusic.play();
                img.source = pressedSource;
            }
            onReleased: {
                checked = !checked;
            }
        }
    }

    Audio {
        id: playMusic
        source: "qrc:///sound/drawer/btn_pressed.wav"
    }
}
