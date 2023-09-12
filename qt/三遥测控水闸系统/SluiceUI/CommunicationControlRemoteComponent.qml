import QtQuick 2.0
import QtQml.Models 2.1
import Communication 1.0

ListView {
    id: listView
    anchors.fill: parent
    property LocalCommunicationHandler remoteHandler: pageRemoteHandler

    model: ObjectModel {
        CustomSelectionBox { title: "通讯网络: "; boxNames: "WLAN"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                //remoteHandler.setPortName(itemText);
            }
        }
        CustomSelectionBox { title: "通讯协议: "; boxNames: "Udp/IP"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                //remoteHandler.setPortName(itemText);
            }
        }
        CustomSelectionBox { title: "本机IPv4地址: "; boxNames: "239.255.43.21"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                //remoteHandler.setInterval(itemText);
            }
        }
        CustomSelectionBox { title: "接收端口号: "; boxNames: "45454"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                //remoteHandler.setBaudRate(itemText);
            }
        }
        CustomSelectionBox { title: "发送端口号: "; boxNames: "45455"; checkedIndex: 0
            onItemChecked: {
                console.log(itemText);
                //remoteHandler.setDataBits(itemText);
            }
        }
    }
}
