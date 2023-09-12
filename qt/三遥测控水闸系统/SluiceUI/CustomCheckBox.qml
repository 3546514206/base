import QtQuick 2.2
import QtQuick.Controls 1.2

Item {
    id: root
    width: 150
    height: 36

    property string txt: "CheckBox"
    property bool checked: false
    property ExclusiveGroup exclusiveGroup: null
    onExclusiveGroupChanged: {
        if (exclusiveGroup)
            exclusiveGroup.bindCheckable(root)
    }
    onCheckedChanged: {
        if ( checked == true ) {
            img.source = "qrc:///general/general/checkbox_full.png"
        }
        else
            img.source = "qrc:///general/general/checkbox_empty.png"
    }

    Image {
        id: img
        anchors.left: parent.left
        anchors.top: parent.top
        anchors.margins: 2
        source: "qrc:///general/general/checkbox_empty.png"
    }

    Text {
        id: checkText
        anchors.left: img.right
        anchors.leftMargin: 10
        anchors.verticalCenter: parent.verticalCenter
        font.pointSize: 12
        text: txt
    }

    MouseArea {
        anchors.fill: parent
        onClicked: {
            checked = !checked;
        }
    }
}
