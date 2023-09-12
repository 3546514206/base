import QtQuick 2.2
import QtQuick.Controls 1.2
import QtMultimedia 5.0

Item {
    id: root
    width: 360
    height: 360
    property url currentPage
    property bool closedFlag: true

    signal drawerBtnClicked

    Audio {
        id: drawerAudio
        source: "qrc:///sound/drawer/drawer.wav"
    }

    Image {
        id: container
        width: root.width
        height: root.height
        source: "qrc:///res/drawer/drawer_background.png"

        Image {
            id: upRect
            x: width / 12
            y: 5
            width: container.width * 6 / 7
            height: container.height / 9
            source: "qrc:///res/drawer/ribbons.png"
        }

        ExclusiveGroup { id: btnExclusiveGroup }

        ListView {
            id: listview
            width: container.width * 3 / 4
            height: container.height * 3 / 4
            anchors.verticalCenter: container.verticalCenter
            anchors.horizontalCenter: container.horizontalCenter
            anchors.margins: 20
            clip: true

            model: ListModel {
                ListElement {
                    page: "qrc:///qml/SluiceStatePage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn1_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn1_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn1_active.png"
                }
                ListElement {
                    page: "qrc:///qml/SluiceControlPage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn2_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn2_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn2_active.png"
                }
                ListElement {
                    page: "qrc:///qml/CommunicationStatePage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn3_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn3_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn3_active.png"
                }
                ListElement {
                    page: "qrc:///qml/CommunicationControlPage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn4_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn4_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn4_active.png"
                }
                ListElement {
                    page: "qrc:///qml/DataTablePage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn5_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn5_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn5_active.png"
                }
                ListElement {
                    page: "qrc:///qml/DataCurvePage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn6_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn6_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn6_active.png"
                }
                ListElement {
                    page: "qrc:///qml/OperationRecordPage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn7_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn7_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn7_active.png"
                }
                ListElement {
                    page: "qrc:///qml/PowerManagerPage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn8_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn8_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn8_active.png"
                }
                ListElement {
                    page: "qrc:///qml/SystemSettingPage.qml";
                    source1: "qrc:///res/drawer_btn/drawer_btn9_init.png";
                    source2: "qrc:///res/drawer_btn/drawer_btn9_pressed.png";
                    source3: "qrc:///res/drawer_btn/drawer_btn9_active.png"
                }
            }

            delegate: btnDelegate
            spacing: 8

            Component {
                id: btnDelegate
                DrawerBtn {
                    width: container.width / 2
                    height: container.height / 10
                    initSource: source1
                    pressedSource: source2
                    activeSource: source3
                    exclusiveGroup: btnExclusiveGroup
                    onActived: root.currentPage = page;
                }
            }
        }

        Rectangle {
            id: drawerBtn
            width: container.width / 10
            height: container.height / 10
            anchors.verticalCenter: container.verticalCenter
            anchors.right: container.right

            radius: 2
            border { width: 2; color: "azure" }

            color: "lightGrey"

            Image {
                id: arrowImage
                anchors.fill: parent
                source: "qrc:///res/drawer/arrow_right.png"
            }

            MouseArea {
                anchors.fill: parent
                onPressed: {
                    drawerBtn.color = "#FF6000"
                }
                onReleased: {
                    root.drawerBtnClicked();
                    drawerAudio.play();
                    drawerBtn.color = "lightGrey"
                    if ( closedFlag === true ) {
                        arrowImage.source = "qrc:///res/drawer/arrow_left.png";
                        closedFlag = !closedFlag;
                    }else {
                        arrowImage.source = "qrc:///res/drawer/arrow_right.png";
                        closedFlag = !closedFlag;
                    }

                }
            }
        }
    }
}
