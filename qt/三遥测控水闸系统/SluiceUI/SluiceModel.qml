/************************************************************
 * 该组件用来向数据库请求数据,并将数据分离到ListModel中存储
 * 只负责读取,该组件并不了解数据库的变动信息
 * 因为实际操作数据库的databaseManager与该组件内的并不是同一个对象
 ***********************************************************/
import QtQuick 2.0
import Database 1.0

Item {
    id: sluiceModel
    property ListModel model: listModel
    property string databaseName
    property int sluiceId: 1                // ID

    property bool ready: false              // 标志位
    signal dataReady                        // 数据就绪信号

    DatabaseManager{
        id: dm
    }

    ListModel {
        id: listModel
    }

    // 创建闸门数据模型
    function createSluiceDataModel(r) {
        return {
            "number": r[0],
            "time": r[1],
            "upperlimit": r[2],
            "lowerlimit": r[3],
            "openingvalue": r[4]
        };
    }
    // 创建闸门传感器模型
    function createSluiceSensorModel(r) {
        return {
            "number": r[0],
            "time": r[1],
            "upperlimit": r[2],
            "lowerlimit": r[3],
            "openingvalue": r[4]
        };
    }
    // 创建环境数据模型
    function createEnvironmentalDataModel(r) {
        return {
            "time": r[0],
            "upstream": r[1],
            "downstream": r[2],
            "flow": r[3],
            "rainfall": r[4],
            "winddirect": r[5]
        };
    }
    // 创建环境传感器模型
    function createEnvironmentalSensorModel(r) {
        return {
            "time": r[0],
            "upstream": r[1],
            "downstream": r[2],
            "flow": r[3],
            "rainfall": r[4],
            "winddirect": r[5]
        };
    }

    function requestSluiceData(count, sluiceId) {
        var dataString = dm.requestSluiceData(count, sluiceId);
/*返回数据调试口
        console.log("sluicedata is " + dataString);
/**/
        listModel.clear();
        var dataRecords = dataString.split('\n');
        for (var i = 0; i < dataRecords.length - 1; i++ ) {
            var r = dataRecords[i].split(',');
            listModel.append(createSluiceDataModel(r));
        }
        if (listModel.count > 0) {
            model = listModel;
            sluiceModel.ready = true;
            sluiceModel.dataReady();
        }
    }

    function requestSluiceSensor(count, sluiceId) {
        var dataString = dm.requestSluiceSensor(count, sluiceId);
/*返回数据调试口
        console.log("sluicesensor is " + dataString);
/**/
        listModel.clear();
        var dataRecords = dataString.split('\n');
        for (var i = 0; i < dataRecords.length - 1; i++ ) {
            var r = dataRecords[i].split(',');
            listModel.append(createSluiceSensorModel(r));
        }
        if (listModel.count > 0) {
            model = listModel;
            sluiceModel.ready = true;
            sluiceModel.dataReady();
        }
    }

    function requestEnvironmentalData(count) {
        var dataString = dm.requestEnvironmentalData(count);
/*返回数据调试口
        console.log("environmentaldata is " + dataString);
/**/
        listModel.clear();
        var dataRecords = dataString.split('\n');
        for (var i = 0; i < dataRecords.length - 1; i++ ) {
            var r = dataRecords[i].split(',');
            listModel.append(createEnvironmentalDataModel(r));
        }

        if (listModel.count > 0) {
            model = listModel;
            sluiceModel.ready = true;
            sluiceModel.dataReady();
        }
    }
    function requestEnvironmentalSensor(count) {
        var dataString = dm.requestEnvironmentalSensor(count);
/*返回数据调试口
        console.log("environmentalsensor is " + dataString);
/**/
        listModel.clear();
        var dataRecords = dataString.split('\n');
        for (var i = 0; i < dataRecords.length - 1; i++ ) {
            var r = dataRecords[i].split(',');
            listModel.append(createEnvironmentalSensorModel(r));
        }
        if (listModel.count > 0) {
            model = listModel;
            sluiceModel.ready = true;
            sluiceModel.dataReady();
        }
    }
}
