import QtQuick 2.4
/*
Rectangle {
    width: 120; height: 300
    color: "white"

    ListView {
        anchors.fill: parent
        anchors.margins: 20

        clip: true
        model: 100

        delegate: numberDelegate
        spacing: 5

        focus: true
    }
    Component {
        id: numberDelegate
        Rectangle {
            width: ListView.view.width
            height: 40

            color: ListView.isCurrentItem? "lightBlue": "lightGreen"
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
    width: 500; height: 300
    color: "white"

    ListModel {
        id: theModel

        ListElement {number: 0 }
        ListElement {number: 1 }
        ListElement {number: 2 }
        ListElement {number: 3 }
        ListElement {number: 4 }
        ListElement {number: 5 }
        ListElement {number: 6 }
        ListElement {number: 7 }
        ListElement {number: 8 }
        ListElement {number: 9 }
    }
    Rectangle {
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom
        anchors.margins: 20

        height: 40
        color: "darkGreen"

        Text {
            anchors.centerIn: parent
            text: "Add Item!"
        }
        MouseArea {
            anchors.fill: parent
            onClicked: {
                theModel.append({"number": ++parent.count});
            }
        }
        property int count: 9
    }

    GridView {
        anchors.fill: parent
        anchors.margins: 20
        anchors.bottomMargin: 80

        clip: true
        model: theModel

        cellHeight: 45
        cellWidth: 45

        delegate: numberDelegate
    }

    Component {
        id: numberDelegate
        Rectangle {
            id:wrapper
            width: 40; height: 40
            color: "lightGreen"
            Text {
                anchors.centerIn: parent
                font.pixelSize: 10
                text: number
            }
            MouseArea {
                anchors.fill: parent
                onClicked: {
                    if (!wrapper.GridView.delayRemove)
                        theModel.remove(index)
                }
            }
            GridView.onRemove: SequentialAnimation {
                PropertyAction {
                    target: wrapper; property: "GridView.delayRemove"; value:true
                }
                NumberAnimation {
                    target: wrapper; property: "scale"; to: 0; duration: 250; easing.type: Easing.InOutQuad
                }

                PropertyAction {
                    target: wrapper; property: "GridView.delayRemove"; value: false
                }
            }
            GridView.onAdd: SequentialAnimation {
                NumberAnimation {
                    target: wrapper; property: "scale"; from: 0; to: 1; duration: 250; easing.type: Easing.InOutQuad
                }
            }
        }

    }
}
*/

Item {
    width: 300; height: 400

    ListView {
        id: listView
        anchors.fill: parent
        delegate: datailsDelegate
        model: planets
    }
    ListModel {
        id: planets

        ListElement {
            name: "Mercury";
            imageSource: "images/mercury.jpeg";
            facts: "Mercury is the smallest planet in the Solar System. It is the closest planet to the sun. It makes one trip around the Sun once every 87.969 days."
        }
        ListElement {
            name: "Venus";
            imageSource: "images/venus.jpeg";
            facts: "Venus is the second planet from the Sun. It is a terrestrial planet because it has a solid, rocky surface. The other terrestrial planets are Mercury, Earth and Mars. Astronomers have known Venus for thousands of years."
        }
        ListElement {
            name: "Earth";
            imageSource: "images/earth.jpeg";
            facts: "The Earth is the third planet from the Sun. It is one of the four terrestrial planets in our Solar System. This means most of its mass is solid. The other three are Mercury, Venus and Mars. The Earth is also called the Blue Planet, 'Planet Earth', and 'Terra'."
        }
        ListElement {
            name: "Mars";
            imageSource: "images/mars.jpeg";
            facts: "Mars is the fourth planet from the Sun in the Solar System. Mars is dry, rocky and cold. It is home to the largest volcano in the Solar System. Mars is named after the mythological Roman god of war because it is a red planet, which signifies the colour of blood."
        }
    }
    Component {
        id: datailsDelegate
        Item{
            id: wrapper
            width: listView.width; height: 30
            Rectangle {
                anchors.left: parent.left
                anchors.right: parent.right
                anchors.top: parent.top
                height: 30
                color: "#ffaa00"
                Text {
                    anchors.left: parent.left
                    anchors.verticalCenter: parent.verticalCenter
                    anchors.leftMargin: 4
                    font.pixelSize: parent.height - 4
                    text: name
                }
            }
            Rectangle {
                id: image
                width: 26; height: 26
                color: "black"

                anchors.right: parent.right
                anchors.top: parent.top
                anchors.rightMargin: 2
                anchors.topMargin: 2

                Image {
                    anchors.fill: parent
                    fillMode: Image.PreserveAspectFit
                    source: imageSource
                }
            }
            MouseArea {
                anchors.fill: parent
                onClicked: parent.state = "expanded"
            }
            Item {
                id: factsView
                anchors.top: image.bottom
                anchors.left: parent.left
                anchors.right: parent.right
                anchors.bottom: parent.bottom
                opacity: 0
                Rectangle{
                    anchors.fill: parent
                    color: "#cccccc"
                    Text {
                        anchors.fill: parent
                        anchors.margins: 5
                        clip: true
                        wrapMode: Text.WordWrap
                        font.pixelSize: 12
                        color: '#1f1f21'
                        text: facts
                    }
                }
            }
            Rectangle {
                id: closeButton
                width: 26; height: 26
                anchors.right: parent.right
                anchors.top: parent.top
                anchors.rightMargin: 2
                anchors.topMargin: 2

                color: "red"
                opacity: 0
                MouseArea {
                    anchors.fill: parent
                    onClicked: wrapper.state = ""
                }
            }
            states: [
                State {
                    name: "expanded"

                    PropertyChanges {
                        target: wrapper;
                        height: listView.height
                    }
                    PropertyChanges {
                        target: image;
                        width: listView.width;
                        height: listView.width;
                        anchors.rightMargin: 0;
                        anchors.topMargin: 30
                    }
                    PropertyChanges {
                        target: factsView;
                        opacity: 1
                    }
                    PropertyChanges {
                        target: closeButton;
                        opacity: 1
                    }
                    PropertyChanges {
                        target: wrapper.ListView.view;
                        contentY: wrapper.y;
                        interactive: false
                    }
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
    }
}
