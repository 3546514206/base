import QtQuick 2.0

Image {
    id: chart
    width: ListView.view.width
    height: ListView.view.height
    source: "qrc:///general/general/frame.png"

    property string title: "环境数据曲线"
    property int count: 30
    property var startTime
    property var endTime
    property string activeChart: "30Mins"            // 设置表格显示的时间跨度
    property int gridSize: 8                      // 每个网格宽度为4px
    property real gridStep: gridSize ? (width - canvas.tickMargin) / gridSize : canvas.xGridStep

    function update() {                           // 更新函数
        if (chart.activeChart === "15Mins") {                 // 显示15条数据
            chart.count = 15;
            chart.gridSize = 0;
        }
        else if (chart.activeChart === "30Mins") {          // 显示30条数据
            chart.count = 30;
            gridSize = 0;                // gridSize为0时，采用canvas中定义的网格宽度计算。使每个数据都绘制在坐标线上
        }
        else if (chart.activeChart === "45Mins") {           // 显示45条数据
            chart.count = 45;
            gridSize = 9;
        }
        else {
            chart.count = 60;
            gridSize = 12;
        }
        canvas.requestPaint();                            // 当更新时需要调用画布的这个函数
    }

    Text {
        id: idText
        y: 20
        anchors.horizontalCenter: parent.horizontalCenter
        font.pointSize: 16
        text: title
    }

    onCountChanged: sluiceModel.modelUpdate();

    SluiceModel{
        id: sluiceModel
        Component.onCompleted: modelUpdate();

        function modelUpdate() {
            requestEnvironmentalData(count)
        }
    }

    DataCurveSettingPanel {
        id: settingPanel
        anchors.verticalCenter: parent.verticalCenter
        anchors.right: Canvas.left
        height: parent.height / 2
        width: parent.width / 5
        onDrawUpstreamChanged: chart.update()
        onDrawDownstreamChanged: chart.update();
        onDrawRainfallChanged: chart.update();
        onDrawFlowChanged: chart.update();
    }

    Row {                                       // Row布局自定义的按钮
        id: activeChartRow
        anchors.left: settingPanel.right
        anchors.right: chart.right
        anchors.top: chart.top
        anchors.topMargin: chart.height / 6
        spacing: 50
        onWidthChanged: {                    // 该函数保证宽度变化时优先压缩spacing，且不会造成按钮重叠
            var buttonsLen = firstButton.width + secondButton.width + thirdButton.width + fourthButton.width;
            var space = (width - buttonsLen) / 3;
            spacing = Math.max(space, 10);
        }

        CustomButton {
            id: firstButton
            text: "15Mins"
            buttonEnabled: chart.activeChart === "15Mins"
            onClicked: {
                chart.activeChart = "15Mins";  // 改变当前图表显示模式
                chart.update();                     // 更新
            }
        }
        CustomButton {
            id: secondButton
            text: "30Mins"
            buttonEnabled: chart.activeChart === "30Mins"
            onClicked: {
                chart.activeChart = "30Mins";
                chart.update();
            }
        }
        CustomButton {
            id: thirdButton
            text: "45Mins"
            buttonEnabled: chart.activeChart === "45Mins"
            onClicked: {
                chart.activeChart = "45Mins";
                chart.update();
            }
        }
        CustomButton {
            id: fourthButton
            text: "60Mins"
            buttonEnabled: chart.activeChart === "60Mins"
            onClicked: {
                chart.activeChart = "60Mins";
                chart.update();
            }
        }
    }

    Canvas {
        id: canvas
        renderTarget: Canvas.FramebufferObject  // 渲染到OpenGL的帧缓冲
        renderStrategy: Canvas.Threaded        // 渲染工作在一个私有渲染线程中进行

        anchors.top: activeChartRow.bottom
        anchors.left: settingPanel.right
        anchors.right: parent.right
        anchors.bottom: parent.bottom
        anchors.margins: 12

        property int pixelSkip: 1
        property int numPoints: 1           // 存储数据点的个数
        property int tickMargin: 32         // 右边格的宽度

        property real xGridStep: (width - tickMargin) / numPoints // 网格宽度
        property real yGridOffset: height / 26                // y方向向下的偏移度，用在水平线的绘制
        property real yGridStep: height / 12                  // 网格高度

        function drawBackground(ctx) {
            ctx.save();                                     // 保存之前绘制内容
            ctx.fillStyle = "#ffffff";
            ctx.fillRect(0, 0, canvas.width, canvas.height);
            ctx.strokeStyle = "#d7d7d7";
            ctx.beginPath();
            // 水平网格线
            for (var i = 0; i < 12; i++) {
                ctx.moveTo(0, canvas.yGridOffset + i * canvas.yGridStep);
                ctx.lineTo(canvas.width, canvas.yGridOffset + i * canvas.yGridStep);
            }

            // 垂直网格线
            var height = 335 * canvas.height / 36;            // 垂直线的高度为画布高度的 35/36
            var yOffset = canvas.height - height;            // 垂直线离顶部距离为高度的 1/36
            var xOffset = 0;
            for (i = 0; i < chart.gridSize; i++) {
                ctx.moveTo(xOffset + i * chart.gridStep, yOffset);
                ctx.lineTo(xOffset + i * chart.gridStep, height);
            }
            ctx.stroke();                                    // 描线

            // 右方以及下方颜色较深的那几根线
            ctx.strokeStyle = "#666666";
            ctx.beginPath();
            var xStart = canvas.width - tickMargin;          // x = 画布宽度 - 标尺部分宽度
            ctx.moveTo(xStart, 0);
            ctx.lineTo(xStart, canvas.height);               // 向下画直线
            for (i = 0; i < 12; i++) {
                ctx.moveTo(xStart, canvas.yGridOffset + i * canvas.yGridStep);       // 12根短横线
                ctx.lineTo(canvas.width, canvas.yGridOffset + i * canvas.yGridStep);
            }
            ctx.moveTo(0, canvas.yGridOffset + 9 * canvas.yGridStep);           // 移动绘制点到第九根横线左端
            ctx.lineTo(canvas.width, canvas.yGridOffset + 9 * canvas.yGridStep);  // 向右绘制横线
            ctx.closePath();                                                     // 完成路径
            ctx.stroke();                                                      // 描边

            ctx.restore();                                                     // 载入保存的内容
        }

        function drawScales(ctx, high, low, vol)         // 绘制标尺函数
        {
            ctx.save();
            ctx.strokeStyle = "#888888";
            ctx.font = "10px Arial"
            ctx.beginPath();

            // prices on y-axis
            var x = canvas.width - tickMargin + 3;       // 离右边实线3px
            var priceStep = (high - low) / 9.0;          // 相隔最高与最低的差值除以9
            for (var i = 0; i < 10; i += 2) {            // 隔一级显示
                var price = parseFloat(high - i * priceStep).toFixed(1);
                ctx.text(price, x, canvas.yGridOffset + i * yGridStep - 2); // 绘制text的坐标在文字的左下角
            }

            // 最高流量
            ctx.text(vol, 0, canvas.yGridOffset + 9 * yGridStep + 12);

            ctx.closePath();
            ctx.stroke();
            ctx.restore();
        }

        function drawCurve(ctx, from, to, color, degree, points, highest, lowest)     // 数据曲线绘制
        {
            ctx.save();
            ctx.globalAlpha = 0.7;                  // 透明度
            ctx.strokeStyle = color;                // color由StockSettingPanel指定
            ctx.lineWidth = 3;
            ctx.beginPath();

            var end = points.length;                // 数据长度

            var range = highest - lowest;          // 取值范围
            if (range == 0) {
                range = 1;                         // range作为被除数不能为0
            }

            for (var i = 0; i < end; i += pixelSkip) {
                var x = points[i].x;
                var y = points[i][degree];           // 取出对应设置的数据
                var h = 9 * yGridStep;              // 最大绘制高度为九倍的网格高度,对应数据最大值

                y = h * (lowest - y)/range + h + yGridOffset; // lowest - y为非正数,h + yGridOffset为曲线绘制的底部

                if (i == 0) {
                    ctx.moveTo(x, y);               // 移动到初始点
                } else {
                    ctx.lineTo(x, y);               // 向后绘制
                }
            }
            ctx.stroke();
            ctx.restore();
        }

        function drawVolume(ctx, from, to, color, degree, points, highest)   // 成交量绘制函数
        {
            ctx.save();
            ctx.fillStyle = color;
            ctx.globalAlpha = 0.8;
            ctx.lineWidth = 0;                 // 由于线宽影响绘制边界(参考HTML),这里将线宽设置为0
            ctx.beginPath();

            var end = points.length;
            var margin = 0;

            if (chart.activeChart === "15Mins" || chart.activeChart === "30Mins") {
                margin = 6;
                ctx.shadowOffsetX = 4;             // x方向的阴影
                ctx.shadowBlur = 3.5;              // 模糊效果
                ctx.shadowColor = Qt.darker(color);
            }

            // 由于柱状图的宽度限制，柱状图比实际的数据少一个
            for (var i = 1; i < end; i += pixelSkip) {
                var x = points[i - 1].x;
                var y = points[i][degree];
                y = canvas.height * (y / highest);
                y = 3 * y / 12;                               // 柱状图高度占画布的1/4
                ctx.fillRect(x, canvas.height - y + yGridOffset,
                             canvas.xGridStep - margin, y);    // "15Mins"与"30Mins"时有间隔，其他则没有
            }

            ctx.stroke();
            ctx.restore();
        }

        onPaint: {                                            // 绘制入口
            if (!sluiceModel.ready) {                          // 等待数据完成
                return;
            }

            if ( sluiceModel.model.count < count )
                count = sluiceModel.model.count;

            numPoints = count;           // 数据数
            startTime = sluiceModel.model.get(count - 1).time
            endTime = sluiceModel.model.get(0).time

            if (chart.gridSize == 0)
                chart.gridSize = numPoints                    // 使得gridStep绑定网格计算

            var ctx = canvas.getContext("2d");                // 创建ctx
            ctx.globalCompositeOperation = "source-over";     // 混合模式
            ctx.lineWidth = 1;

            drawBackground(ctx);                              // 背景绘制

            var highestLevel = 0;
            var highestVolume = 0;
            var lowestLevel = 0;
            var points = [];                                  // 创建一个数组
            for (var i = count - 1, j = 0; i >= 0 ; i -= pixelSkip, j += pixelSkip) {   // pixelSkip被定义为 1
                var degree = sluiceModel.model.get(i);
                if (parseFloat(highestLevel) < parseFloat(degree.upstream))  // 曲线表最大值
                    highestLevel = degree.upstream;
                if (parseFloat(highestLevel) < parseFloat(degree.downstream))
                    highestLevel = degree.downstream;
                if (parseFloat(highestLevel) < parseFloat(degree.flow))
                    highestLevel = degree.flow;
                if (parseFloat(highestLevel) < parseFloat(degree.rainfall))
                    highestLevel = degree.rainfall;

                if (parseFloat(highestVolume) < parseFloat(degree.flow))    // 柱状表最大值
                    highestVolume = degree.flow;

                points.push({                           // 插入数据，它类似于Model，但多了一个 x 的坐标值
                                x: j * xGridStep,
                                upstream: degree.upstream,
                                downstream: degree.downstream,
                                rainfall: degree.rainfall,
                                flow: degree.flow
                            });
            }

            if (settingPanel.drawUpstream)                 // 判断StockSettingPanel中相应的选项是否被勾选，然后绘制数据线段
                drawCurve(ctx, 0, numPoints, settingPanel.upColor, "upstream", points, highestLevel, lowestLevel);
            if (settingPanel.drawDownstream)
                drawCurve(ctx, 0, numPoints, settingPanel.downColor, "downstream", points, highestLevel, lowestLevel);
            if (settingPanel.drawRainfall)
                drawCurve(ctx, 0, numPoints, settingPanel.rainfallColor, "rainfall", points, highestLevel, lowestLevel);
            if (settingPanel.drawFlow)
                drawCurve(ctx, 0, numPoints, settingPanel.flowColor, "flow", points, highestLevel, lowestLevel);

            drawVolume(ctx, 0, numPoints, settingPanel.volumeColor, "flow", points, highestVolume);   // 流量绘制
            drawScales(ctx, highestLevel, lowestLevel, highestVolume);            // 标尺绘制
        }
    }

    Text {                                    // 起始时间显示
        id: fromDate
        color: "#000000"
        font.pointSize: 8
        anchors.left: canvas.left
        anchors.top: canvas.top
        text: "| " + startTime
    }

    Text {                                    // 结束时间显示
        id: toDate
        color: "#000000"
        font.pointSize: 8
        anchors.right: canvas.right
        anchors.rightMargin: canvas.tickMargin
        anchors.top: canvas.top
        text: endTime + " |"
    }

}


