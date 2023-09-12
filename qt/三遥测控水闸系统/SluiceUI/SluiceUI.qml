import QtQuick 2.2
import QtQuick.Window 2.1
import Database 1.0
import Communication 1.0

Window {
    id: windowRoot
    visible: true
    width: 1000; height: 600

    LocalCommunicationHandler {
        id: localCommunicationHandler

        Component.onCompleted: {
            init();
        }
    }

    RemoteCommunicationHandler {
        id: remoteCommunicationHandler

        Component.onCompleted: {
            startMonitor();
        }
    }

    Loader {
        id: pageLoader
        anchors.fill: parent
        source: "qrc:///qml/SluiceStatePage.qml"
    }

    Drawer {
        id: drawer
        height: parent.height
        width: parent.width / 3
        x: - width * 9 / 10
        onCurrentPageChanged: {
            if ( pageLoader.source != currentPage )
                pageLoader.source = currentPage;
        }

        onDrawerBtnClicked: {
            if ( drawer.state === "" ) {
                drawer.state = "opened";
            }
            else {
                drawer.state = "";
            }
        }
        states: State {
                name: "opened"
                PropertyChanges { target: drawer; x: 0 }
            }
        transitions: [
            Transition {
                from: ""
                to: "opened"
                NumberAnimation { target: drawer; property: "x";
                    duration: 500; easing.type:  Easing.OutExpo }
            },
            Transition {
                from: "opened"
                to: ""
                NumberAnimation { target: drawer; property: "x";
                    duration: 500; easing.type:  Easing.OutExpo }
            }
        ]
    }
}
