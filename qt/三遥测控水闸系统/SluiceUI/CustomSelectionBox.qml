import QtQuick 2.0
import QtQuick.Controls 1.2

Item {
    id: wrapper
    width: ListView.view.width
    height: 36
    property string title
    property string boxNames
    property int checkedIndex

    signal itemChecked(string itemText)

    Image {
        id: titleImg
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.top: parent.top
        height: 36
        source: "qrc:///general/general/button_glass.png"

        Text {
            id: titleText
            anchors.left: parent.left
            anchors.verticalCenter: parent.verticalCenter
            anchors.leftMargin: 8

            font.pointSize: 12
            text: title
        }

        Text {
            id: checkedText
            anchors.left: titleText.right
            anchors.verticalCenter: parent.verticalCenter
            anchors.leftMargin: 12
            font.pointSize: 12
            text: boxNames.split(",")[checkedIndex]
        }
    }

    Image {
        id: arrowImg
        anchors.right: parent.right
        anchors.top: parent.top
        anchors.rightMargin: 2
        anchors.topMargin: 2

        width: 32
        height: 32
        source: "qrc:///general/general/arrow_down.png"
    }

    MouseArea {
        anchors.fill: parent
        onClicked: {
            if ( parent.state == "" )
                parent.state = "expanded";
            else
                parent.state = "";
        }
    }

    Item {
        id: checkBoxView
        anchors.top: titleImg.bottom
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom

        opacity: 0
        ExclusiveGroup { id: checkboxExclusiveGroup }

        Grid {
            anchors.fill: parent
            columns: 3
            rowSpacing: 2
            columnSpacing: 40
            Repeater {
                model: boxNames.split(",")

                CustomCheckBox {
                    exclusiveGroup: checkboxExclusiveGroup
                    txt: modelData
                    onCheckedChanged: {
                        if ( checked == true ){
                            checkedText.text = txt;
                            itemChecked(txt);
                        }
                    }
                }
            }
        }
    }

    states: [
        State {
            name: "expanded"

            PropertyChanges { target: wrapper; height: 108 }
            PropertyChanges { target: arrowImg; source: "qrc:///general/general/arrow_up.png" }
            PropertyChanges { target: checkBoxView; opacity: 1 }
            PropertyChanges { target: wrapper.ListView.view; contentY: wrapper.y; interactive: false }
        }
    ]

    transitions: [
        Transition {
            NumberAnimation {
                duration: 200;
                properties: "height,width,anchors.rightMargin,anchors.topMargin,opacity,contentY"
            }
        }
    ]
}

