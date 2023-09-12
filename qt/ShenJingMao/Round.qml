import QtQuick 2.0

Rectangle {
    id: round
    height: 40
    width:  height
    radius: height / 2
    color: active === true ? 'orangered' : 'lightgrey'

    property int index
    property bool active: Math.random() * 100 < 10 ? true : false

    signal clicked

    MouseArea {
        anchors.fill: parent
        onClicked: {
            round.active = true;
            round.clicked();
        }
    }
}
