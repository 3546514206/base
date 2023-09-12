import QtQuick 2.0
import QtQml.Models 2.1
import Communication 1.0

ListView {
    id: listView
    anchors.fill: parent
    property LocalCommunicationHandler localHandler: pageLocalHandler

    model: ObjectModel {
        CustomSelectionBox { title: "通讯端口: "; boxNames: "COM1,COM2,COM3,COM4,COM5,COM6"; checkedIndex: 0;
            onItemChecked: {
                console.log(itemText);
                localHandler.setPortName(itemText);
            }
        }
        CustomSelectionBox { title: "接收间隔: "; boxNames: "30s,60s,90s"; checkedIndex: 1
            onItemChecked: {
                console.log(itemText);
                localHandler.setInterval(itemText);
            }
        }
        CustomSelectionBox { title: "波特率: "; boxNames: "4800,9600,19200,38400,57600,115200"; checkedIndex: 5
            onItemChecked: {
                console.log(itemText);
                localHandler.setBaudRate(itemText);
            }
        }
        CustomSelectionBox { title: "数据位: "; boxNames: "5,6,7,8"; checkedIndex: 3
            onItemChecked: {
                console.log(itemText);
                localHandler.setDataBits(itemText);
            }
        }
        CustomSelectionBox { title: "校验位: "; boxNames: "无校验,偶校验,奇校验"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                localHandler.setParity(itemText);
            }
        }
        CustomSelectionBox { title: "流控制: "; boxNames: "无流控,硬流控,软流控"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                localHandler.setFlowControl(itemText);
            }
        }
        CustomSelectionBox { title: "停止位: "; boxNames: "1,1.5,2"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                localHandler.setStopBits(itemText);
            }
        }
    }
}


