import QtQuick 2.4
/*
Column{
    spacing: 2
    Repeater {
        //model: 10
        model: ["Enterprise","Colombia","Challenger","Discovery","Endeavour","Atlantis"]
        Rectangle{
            width: 120; height: 20
            radius: 3
            color: "lightBlue"
            Text{
                anchors.centerIn: parent
                //text: index
                text: modelData + ":(" + index + ")"
            }
        }
    }
}
*/
/*
Column {
    Repeater {
        model: ListModel {
            ListElement {
                name: "Mercury";surfaceColor: "gray"
            }
            ListElement {
                name: "Venus";surfaceColor: "yellow"
            }
            ListElement {
                name: "Earth";surfaceColor: "blue"
            }
            ListElement {
                name: "Mars";surfaceColor: "orange"
            }

            ListElement {
                name: "Jupiter";surfaceColor: "orange"
            }
            ListElement {
                name: "Saturn";surfaceColor: "yellow"
            }
            ListElement {
                name: "Uranus";surfaceColor: "lightBlue"
            }
            ListElement {
                name: "Neptune";surfaceColor: "lightBlue"
            }
        }
        Rectangle {
            width: 200; height: 50
            radius: 3

            color: "lightBlue"

            Text {
                anchors.centerIn: parent
                text: name
            }

            Rectangle {
                anchors.left: parent.left
                anchors.top: parent
                anchors.verticalCenter: parent.verticalCenter
                anchors.leftMargin: 2

                width: 16; height: 16

                radius: 8

                border.color: "black"
                border.width: 1
                color: surfaceColor
            }
        }
    }
}
*/
/*
Rectangle {
    //width: 80; height: 300
    width: 480; height: 80

    color: "white"

    ListView {
        anchors.fill: parent
        anchors.margins: 20

        clip: true

        model: 100

        orientation: ListView.Horizontal

        delegate: numberDelegate
        spacing: 5
    }
    Component {
        id: numberDelegate
        Rectangle {
            width: 50; height: 50
            color: "lightGreen"
            Text {
                anchors.centerIn: parent
                font.pixelSize: 10
                text: index
            }
        }
    }
}
*/
/*
Rectangle {
    width: 240; height: 300
    color: "white"
    ListView {
        anchors.fill: parent
        anchors.margins: 20
        clip: true
        model: 100
        delegate: numberDelegate
        spacing: 5
        highlight: highlightComponent
        focus: true
    }
    Component {
        id: highlightComponent
        Item {
            width: ListView.view.width
            height: ListView.view.currentItem.height
            y: ListView.view.currentItem.y

            Behavior on y{
                SequentialAnimation {
                    PropertyAnimation {
                        target: highlightRectangle;
                        property: "opacity"
                    }
                    NumberAnimation { duration: 1 }
                    PropertyAnimation {
                        target: highlightRectangle;
                        property: "opacity"
                    }
                }
            }
            Rectangle {
                id: highlightRectangle
                anchors.fill: parent
                color: "lightGreen"
            }
        }
    }
    Component {
        id: numberDelegate
        Item {
            width: 40; height: 40
            Text {
                anchors.centerIn: parent
                font.pixelSize: 10
                text: index
            }
        }
    }
}
*/
/*
Rectangle {
    width: 80; height: 300
    color: "white"

    ListView {
        anchors.fill: parent
        anchors.margins: 20

        clip: true
        model: 10

        delegate: numberDelegate
        spacing: 5

        header: headerComponent
        footer: footerComponent
    }
    Component {
        id: headerComponent
        Rectangle {
            width: 40; height: 20
            color: "yellow"
        }
    }
    Component {
        id: footerComponent
        Rectangle {
            width: 40; height: 20
            color: "red"
        }
    }
    Component {
        id: numberDelegate
        Rectangle {
            width: 40; height: 40
            color: "lightGreen"
            Text {
                anchors.centerIn: parent
                font.pixelSize: 10
                text: index
            }
        }
    }
}
*/
/*
Rectangle {
    width: 240; height: 300
    color: "white"

    GridView{
        anchors.fill: parent
        anchors.margins: 20

        clip: true

        model: 100

        cellWidth: 45
        cellHeight: 45

        delegate: numberDelegate
    }
    Component {
        id: numberDelegate
        Rectangle {
            width: 40; height: 40
            color: "lightGreen"
            Text {
                anchors.centerIn: parent
                font.pixelSize: 10
                text: index
            }
        }
    }
}
*/

