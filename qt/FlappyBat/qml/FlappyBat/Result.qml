import QtQuick 2.0

Rectangle {
    id:r
    property alias text: txt.text
    width: parent.width*3/4
    height: r.width/2
    opacity: 0.5
    color: "gray"
    radius: r.height/20
    Text{
        id:txt
        text:"持续时间:0"
        color:"#FFFFFF"
        font.pixelSize: parent.width/10
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.top:parent.top
        anchors.margins: parent.height/10
    }
    Rectangle{//重新开始按钮
        id:reStart
        width: parent.width/3
        height:reStart.width/2
        radius: reStart.height/10
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.bottom: parent.bottom
        anchors.margins: 10
        MouseArea{
            anchors.fill: parent
            onClicked: {
                mainWin.state="ready";
            }
        }
        Text{
            text:"ReStart"
            font.pixelSize: parent.width/5
            anchors.centerIn: parent
            color:"black"
        }
    }
}
