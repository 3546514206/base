import QtQuick 2.0

Item {
    id: root
    width: 250; height: 300
    property int sluiceId: 1
    property string title: "1号闸机"
    property bool pilotlight: true          // 正常状态指示灯标志位

    property var getupstream
    property var getdownstream
    property var getopeningvalue
    property var getflow
    property var getrainfall
    property var getwinddirect

    Timer {
        interval: 1000;
        running: true;
        repeat: true
        onTriggered:{
            sluiceModel.update();
        }
    }

    SluiceModel {
        id: sluiceModel
        sluiceId: root.sluiceId
        Component.onCompleted: update()

        function update() {
            requestSluiceData(1, sluiceId);
            getopeningvalue = model.get(0).openingvalue;

            requestEnvironmentalData(1);
            getupstream = model.get(0).upstream;
            getdownstream = model.get(0).downstream;
            getflow = model.get(0).flow;
            getrainfall = model.get(0).rainfall;
            getwinddirect = model.get(0).winddirect;
        }
    }

    Image {
        id: container
        anchors.fill: parent
        source: "qrc:///res/sluice_state/panel_background.png"

        Text {
            id: panelTitle
            width: parent.width
            y: 8
            horizontalAlignment: Text.Center
            color: "white"
            font { pointSize: 12; }
            text: "工作状态面板"
        }

        // 闸机Id
        Text {
            id: sluiceText
            width: parent.width
            anchors.top: panelTitle.bottom
            anchors.topMargin: 10
            horizontalAlignment: Text.Center
            color: "white"
            font { pointSize: 10; }
            text: title
        }

        CustomRectFrame {
            id: farme1
            y: container.height / 5
            width: container.width - 2
            height: 36
            anchors.horizontalCenter: container.horizontalCenter
            name: "上游水位"; value: getupstream;
            pilotlight: true
        }
        CustomRectFrame {
            id: farme2
            anchors.top: farme1.bottom
            anchors.topMargin: 4
            width: farme1.width
            height: 36
            anchors.horizontalCenter: container.horizontalCenter
            name: "下游水位"; value: getdownstream;
            pilotlight: true
        }
        CustomRectFrame {
            id: farme3
            anchors.top: farme2.bottom
            anchors.topMargin: 4
            width: farme1.width
            height: 36
            anchors.horizontalCenter: container.horizontalCenter
            name: "闸门开度"; value: getopeningvalue;
            pilotlight: true
        }
        CustomRectFrame {
            id: farme4
            anchors.top: farme3.bottom
            anchors.topMargin: 4
            width: farme1.width
            height: 36
            anchors.horizontalCenter: container.horizontalCenter
            name: "当前流量"; value: getflow
            pilotlight: true
        }
        CustomRectFrame {
            id: farme5
            anchors.top: farme4.bottom
            anchors.topMargin: 4
            width: farme1.width
            height: 36
            anchors.horizontalCenter: container.horizontalCenter
            name: "当前雨量"; value: getrainfall
            pilotlight: true
        }
        CustomRectFrame {
            id: farme6
            anchors.top: farme5.bottom
            anchors.topMargin: 4
            width: farme1.width
            height: 36
            anchors.horizontalCenter: container.horizontalCenter
            name: "当前风向"; value: getwinddirect
            pilotlight: true
        }
    }
}
