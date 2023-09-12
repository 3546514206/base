import QtQuick 2.2
import QtMultimedia 5.0
import QtQuick.Controls 1.2
import QtQuick.Controls.Styles 1.2

Item {
    id: root
    height: 240; width: 220
    FontLoader { id: localFont; source: "qrc:///res/ttf/digital.ttf" }

    Item {
        id: upRect
        height: 48
        width: root.width

        Audio {
            id: calendarAudio
            source: "qrc:///sound/drawer/drawer.wav"
        }

        Timer {
            interval: 1000; running: true; repeat: true
            onTriggered: timer.text = Qt.formatDateTime(new Date(), "yyyy-MM-dd hh:mm:ss")
        }

        Text {
            id: timer
            anchors.horizontalCenter: parent.horizontalCenter
            anchors.verticalCenter: parent.verticalCenter
            width: parent.width - 32
            font { family: localFont.name; pixelSize: 18 }
        }

        Image {
            id: image
            width: 48; height: width
            anchors.right: parent.right

            source: "qrc:///res/sluice_state/calendar.png"

            MouseArea {
                anchors.fill: parent
                onClicked:  {
                    calendarAudio.play();
                    calendar.opacity == 0.0 ? calendar.opacity = 1.0 : calendar.opacity = 0.0;
                }
            }
        }
    }

    Calendar {
        id: calendar
        width: parent.width
        height: parent.height - 48
        anchors.top: upRect.bottom
        opacity: 0.0

        style: CalendarStyle {
            gridVisible: false
            dayDelegate: Rectangle {
                gradient: Gradient {
                    GradientStop {
                        position: 0.00
                        color: styleData.selected ? "#111" : (styleData.visibleMonth && styleData.valid ? "#444" : "#666");
                    }
                    GradientStop {
                        position: 1.00
                        color: styleData.selected ? "#444" : (styleData.visibleMonth && styleData.valid ? "#111" : "#666");
                    }
                    GradientStop {
                        position: 1.00
                        color: styleData.selected ? "#777" : (styleData.visibleMonth && styleData.valid ? "#111" : "#666");
                    }
                }

                Label {
                    text: styleData.date.getDate()
                    anchors.centerIn: parent
                    color: styleData.valid ? "white" : "grey"
                }

                Rectangle {
                    width: parent.width
                    height: 1
                    color: "#555"
                    anchors.bottom: parent.bottom
                }

                Rectangle {
                    width: 1
                    height: parent.height
                    color: "#555"
                    anchors.right: parent.right
                }
            }
        }
    }
}
