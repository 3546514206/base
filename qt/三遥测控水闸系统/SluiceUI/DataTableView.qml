import QtQuick 2.0
import QtQuick.Controls 1.2

Image {
    id: frame
    width: ListView.view.width
    height: ListView.view.height
    source: "qrc:///general/general/frame.png"

    property string databaseName
    property string title: "一号闸机"

    Text {
        id: idText
        y: 20
        anchors.horizontalCenter: parent.horizontalCenter
        font.pointSize: 16
        text: title
    }

    Component {
        id: delegate
        Item {
            clip: true
            Text {
                width: parent.width
                anchors.margins: 4
                anchors.left: parent.left
                anchors.verticalCenter: parent.verticalCenter
                horizontalAlignment: Text.AlignHCenter

                elide: styleData.elideMode
                text: styleData.value !== undefined  ? styleData.value : ""
                color: styleData.textColor
            }
        }
    }
    SluiceModel{
        id: sluiceModel
        databaseName: frame.databaseName
        Component.onCompleted: {
            //console.log(databaseName);
            if ( databaseName === "sluicedata" ){
                requestSluiceData(60, 99);
                upstreamColumn.visible = false;
                downstreamColumn.visible = false;
                flowColumn.visible = false;
                rainfallColumn.visible = false;
                winddirectColumn.visible = false;
            }
            else if ( databaseName === "sluicesensor" ){
                requestSluiceSensor(60, 99);
                upstreamColumn.visible = false;
                downstreamColumn.visible = false;
                flowColumn.visible = false;
                rainfallColumn.visible = false;
                winddirectColumn.visible = false;
            }
            else if ( databaseName === "environmentaldata" ){
                requestEnvironmentalData(60);
                nobColumn.visible = false;
                upperlimitColumn.visible = false;
                lowerlimitColumn.visible = false;
                openingvalueColumn.visible = false;
            }
            else if ( databaseName === "environmentalsensor" ){
                requestEnvironmentalSensor(60);
                nobColumn.visible = false;
                upperlimitColumn.visible = false;
                lowerlimitColumn.visible = false;
                openingvalueColumn.visible = false;
            }
            else;
       }
    }

    TableView {
        anchors.margins: 16
        anchors.top: parent.top
        anchors.topMargin: parent.height / 6
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom

        model: sluiceModel.model
        itemDelegate: delegate;

        TableViewColumn {
            id: timeColumn
            role: "time"
            title: "记录时间"
            width: 120
        }
        TableViewColumn {
            id: nobColumn
            role: "number"
            title: "闸门编号"
            width: 120
        }
        TableViewColumn {
            id: upperlimitColumn
            role: "upperlimit"
            title: "闸门上限"
            width: 120
        }
        TableViewColumn {
            id: lowerlimitColumn
            role: "lowerlimit"
            title: "闸门下限"
            width: 120
        }
        TableViewColumn {
            id: openingvalueColumn
            role: "openingvalue"
            title: "闸门开度"
            width: 120
        }
        TableViewColumn {
            id: upstreamColumn
            role: "upstream"
            title: "上游水位"
            width: 120
        }
        TableViewColumn {
            id: downstreamColumn
            role: "downstream"
            title: "下游水位"
            width: 120
        }
        TableViewColumn {
            id: flowColumn
            role: "flow"
            title: "实时流量"
            width: 120
        }
        TableViewColumn {
            id: rainfallColumn
            role: "rainfall"
            title: "雨量"
            width: 120
        }
        TableViewColumn {
            id: winddirectColumn
            role: "winddirect"
            title: "风向"
            width: 120
        }

        headerDelegate: BorderImage{
            source: "qrc:///data/datatable/header.png"
            border{left:2;right:2;top:2;bottom:2}
            Text {
                text: styleData.value
                anchors.centerIn:parent
                color:"#333"
            }
        }

        rowDelegate: Rectangle {
            height: styleData.selected ? 40 : 30
            Behavior on height{ NumberAnimation{} }

            color: styleData.selected ? "#448" : (styleData.alternate? "#eee" : "#fff")
            BorderImage{
                id: selected
                anchors.fill: parent
                source: "qrc:///data/datatable/selectedrow.png"
                visible: styleData.selected
                border{left:2; right:2; top:2; bottom:2}
                SequentialAnimation {
                    running: true; loops: Animation.Infinite
                    NumberAnimation { target:selected; property: "opacity"; to: 1.0; duration: 900}
                    NumberAnimation { target:selected; property: "opacity"; to: 0.5; duration: 900}
                }
            }
        }
    }
}

