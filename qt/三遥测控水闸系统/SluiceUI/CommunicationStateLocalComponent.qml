import QtQuick 2.0
import QtQuick.Controls 1.2
import Communication 1.0

Item {
    id: localRoot
    anchors.fill: parent
    property bool receivedRun: true
    property bool sendRun: true
    property LocalCommunicationHandler localHandler: pageLocalHandler

    property string recevieStr: localHandler.newReceiveData
    property string sendStr: localHandler.newSendData

    onRecevieStrChanged: recvTextArea.append(recevieStr);
    onSendStrChanged: sendTextArea.append(sendStr);

/***************************************************************************
 * 接收数据部分
 */
    Item {
        height: parent.height / 2 - 8
        anchors.top: parent.top
        anchors.left: parent.left
        anchors.right: parent.right

        Text {
            id: recvText
            height: 36
            verticalAlignment: Text.AlignVCenter
            anchors.left: parent.left
            font.pointSize: 12
            text: "接收数据:"
        }
        Image {
            id: pauseBtn1
            width: 72
            height: recvText.height
            anchors.right: recvTextArea.right
            source: "qrc:///general/general/button_glass.png"
            Rectangle {
                id: rect1
                anchors.fill: parent
                color: "#00CCF8"
                radius: 4
                opacity: 0.0
                NumberAnimation {
                    id: rectAnim1
                    target: rect1
                    property: "opacity"
                    from: 0.5
                    to: 0.0
                    duration: 1000
                }
            }

            Text {
                id: recvBtn
                anchors.centerIn: parent
                font.pointSize: 12
                text: "暂停"
            }
            MouseArea {
                anchors.fill: parent
                onClicked: {
                    tinkAudio.play()
                    rectAnim1.start()
                    if ( recvBtn.text === "暂停" ) {
                        receivedRun = false;
                        recvBtn.text = "继续"
                    }else {
                        receivedRun = true;
                        recvBtn.text = "暂停"
                    }
                }
            }
        }
        TextArea {
            id: recvTextArea
            height: parent.height / 3
            anchors.left: parent.left
            anchors.top: recvText.bottom
            anchors.right: parent.right
            anchors.bottom: parent.bottom
            anchors { topMargin: 4; bottomMargin: 4; rightMargin: 4 }
        }
    }

/***************************************************************************
 * 发送数据部分
 */
    Item {
        height: parent.height / 2 - 8
        anchors.bottom: parent.bottom
        anchors.left: parent.left
        anchors.right: parent.right

        Text {
            id: sendText
            height: 36
            verticalAlignment: Text.AlignVCenter
            anchors.left: parent.left
            font.pointSize: 12
            text: "发送数据:"
        }
        Image {
            id: pauseBtn2
            width: 72
            height: sendText.height
            anchors.right: sendTextArea.right
            source: "qrc:///general/general/button_glass.png"
            Rectangle {
                id: rect2
                anchors.fill: parent
                color: "#00CCF8"
                radius: 4
                opacity: 0.0
                NumberAnimation {
                    id: rectAnim2
                    target: rect2
                    property: "opacity"
                    from: 0.5
                    to: 0.0
                    duration: 1000
                }
            }

            Text {
                id: sendBtn
                anchors.centerIn: parent
                font.pointSize: 12
                text: "暂停"
            }
            MouseArea {
                anchors.fill: parent
                onClicked: {
                    tinkAudio.play()
                    rectAnim2.start()
                    if ( sendBtn.text === "暂停" ) {
                        sendRun = false;
                        sendBtn.text = "继续"
                    }else {
                        sendRun = true;
                        sendBtn.text = "暂停"
                    }
                }
            }
        }
        TextArea {
            id: sendTextArea
            height: parent.height / 3
            anchors.left: parent.left
            anchors.top: sendText.bottom
            anchors.right: parent.right
            anchors.bottom: parent.bottom
            anchors { topMargin: 4; bottomMargin: 4; rightMargin: 4 }
        }
    }
}

