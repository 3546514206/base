/************************************************************
 * 选择绘制线条的面板
 ***********************************************************/
import QtQuick 2.0

Rectangle {
    id: root
    width: 240
    height: 400
    color: "transparent"

    property bool drawUpstream: upButton.checked      // 对外的标志位
    property bool drawDownstream: downButton.checked
    property bool drawFlow: flowButton.checked
    property bool drawRainfall: rainfallButton.checked

    property string upColor: "#face20"        // 各曲线的颜色设置
    property string downColor: "#14aaff"
    property string flowColor: "#f30000"
    property string rainfallColor: "#80c342"


    property string volumeColor: "#14aaff"

    property real spacing: height  > 300 ? ( height - 300 ) / 4 : 30

    CustomCheckBox {
        id: upButton
        txt: "上游水位"
        checked: false
        anchors.top: parent.top
        anchors.left: parent.left
        anchors.margins: 24
    }

    Rectangle {
        height: 4
        anchors.left: upButton.left
        anchors.right: parent.right
        anchors.top: upButton.bottom
        anchors.margins: 12
        color: upColor
    }

    CustomCheckBox {
        id: downButton
        txt: "下游水位"
        checked: false
        anchors.top: upButton.bottom
        anchors.topMargin: spacing
        anchors.left: parent.left
        anchors.leftMargin: 24
    }
    Rectangle {
        height: 4
        anchors.left: downButton.left
        anchors.right: parent.right
        anchors.top: downButton.bottom
        anchors.margins: 12
        color: downColor
    }

    CustomCheckBox {
        id: flowButton
        txt: "实时流量"
        checked: true
        anchors.top: downButton.bottom
        anchors.topMargin: spacing
        anchors.left: parent.left
        anchors.leftMargin: 24
    }

    Rectangle {
        height: 4
        anchors.left: flowButton.left
        anchors.right: parent.right
        anchors.top: flowButton.bottom
        anchors.margins: 12
        color: flowColor
    }

    CustomCheckBox {
        id: rainfallButton
        txt: "雨量"
        checked: false
        anchors.top: flowButton.bottom
        anchors.topMargin: spacing
        anchors.left: parent.left
        anchors.leftMargin: 24
    }

    Rectangle {
        height: 4
        anchors.left: rainfallButton.left
        anchors.right: parent.right
        anchors.top: rainfallButton.bottom
        anchors.margins: 12
        color: rainfallColor
    }
}
