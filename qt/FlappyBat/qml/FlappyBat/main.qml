import QtQuick 2.0
import Box2D 1.1
import QtSensors 5.0
import "img"
import "fns.js" as Fns
Rectangle {
    id:mainWin
    width: 480
    height: 800
    color: "black"
    Image {
        id: back
        anchors.fill: parent
        source: "img/back.png"
    }
    World {
        id: world;
        anchors.fill: parent
        Bat {//蝙蝠
            id: bat
            width:mainWin.width/4
            height:mainWin.width/4
            x:mainWin.width*3/5
            y:mainWin.height*2/5
            playing : false
        }
        Wall {//天花板
            id: ceiling
            height: 4
            anchors { left: parent.left; right: parent.right; top: parent.top }
        }
        Wall {//地面
            id: ground
            height: mainWin.height/8
            anchors { left: parent.left; right: parent.right; bottom: parent.bottom }
        }
        Wall {//左边墙
            id: l_wall
            height: mainWin.height
            width: 4
            anchors { left: parent.left; top: parent.top; bottom: parent.bottom }
        }
        Wall {//右边墙
            id: r_wall
            height: mainWin.height
            width: 4
            anchors { right: parent.right; top: parent.top; bottom: parent.bottom }
        }
        StoneWall{//第一对石头的上面一个
            id:s1
            x:-width
            y:ceiling.height
            width:parent.width/6
            height:parent.height-ceiling.height-ground.height
            NumberAnimation {//绑定在石头上的动画，让石头平滑的移动
                id:xAni1
                target: s1;
                property :"x"
                from: -s1.width;
                to: mainWin.width;
                duration: 3000
                // running: false
            }
        }
        StoneWall{//第一对石头的下面那个
            id:s1_2
            x:s1.x
            y:ceiling.height+s1.height+parent.height/3
            width:s1.width
            height:parent.height-ceiling.height-ground.height-s1.height-parent.height/3
        }
        StoneWall{
            id:s2
             x:-3*width
             y:ceiling.height
             width:parent.width/6
             height:world.height*(Math.floor(5+Math.random()*(50-5))/100);
            NumberAnimation {
                id:xAni2
                target: s2;
                property :"x"
                from: -s2.width;
                to: mainWin.width;
                duration: 3000
                running: false
            }
        }
        StoneWall{
            id:s2_2
            x:s2.x
            y:ceiling.height+s2.height+parent.height/3
            width:s2.width
            height:parent.height-ceiling.height-ground.height-s2.height-parent.height/3
        }
    }

    //显示飞行持续时间的label
    CountTime{
        id:time
        width: parent.width/5
        height: time.width/3
        anchors.top: parent.top
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.topMargin: 30
        visible: false
    }
    MouseArea{//点击屏幕时执行的动作
        anchors.fill: parent
        onClicked: {
            if(mainWin.state=="ready")//初始准备状态
            {
                mainWin.state="fly";
                 bat.setLinearVelocity(0,-600);
            }else if(mainWin.state=="fly")
            {
                bat.setLinearVelocity(0,-600)//给bat一个向上的速度
            }else
            {

            }
        }
    }
    //显示结果与历史成绩的界面，发现这个result需要放在上边MouseArea后，result的鼠标区域才有效……
    Result{
        id:result
        anchors.centerIn: parent
        visible: false
        text:"持续时间："+time.text+"秒"
    }
    states: [//共三个状态，准备、飞行、结果显示。
        State {
            name: "ready"//准备状态
            PropertyChanges { target: bat; playing: false }
            PropertyChanges { target: bat; x:mainWin.width*3/5 }
            PropertyChanges { target: bat; y:mainWin.height*2/5 }
            PropertyChanges { target: time; visible:false}
            PropertyChanges { target: result; visible:false}

            PropertyChanges { target: s1; x:-s1.width}
            PropertyChanges { target: s2; x:-s2.width*3}

        },
        State {
            name: "fly"//飞行状态
            PropertyChanges { target: bat; playing: true }
            PropertyChanges { target: time; visible:true}
            PropertyChanges { target: result; visible:false}

        },
        State {
            name: "result"//结束时显示结果
            PropertyChanges { target: bat; source:"img/deadBat.png" }
            PropertyChanges { target: time; visible:false}
            PropertyChanges { target: result; visible:true}
        }
    ]
    onStateChanged: {//状态变化时，该显示的图层及执行的动作
        if(mainWin.state==="ready")
        {
            world.gravity = Qt.point(0, 0)//准备阶段，让重力为0，使蝙蝠不下落
            bat.angularVelocity=0;//使其角速度为0
            bat.rotation=0;
            bat.linearVelocity=Qt.point(0,0);//使其线速度为0，防止重新启动游戏时蝙蝠在x或y上有初速度
        }else if(mainWin.state==="fly")
        {
            t1.start();//飞行时，启动定时装置，让石头运动
            t2.start();
            world.gravity = Qt.point(0, -35)
            Fns.s=0;

        }else
        {
            xAni1.stop();
            xAni2.stop();
            t1.stop();
            t2.stop();

        }
    }
    Timer {//用于检测石头是否已冲出屏幕右方
        id:t1
        interval: 100; repeat: true
        running: false
        onTriggered:{
            if(xAni1.running===false)
            {
                xAni1.restart()//重启动画，让石头再次从左方运动到右方
                s1.height=world.height*(Math.floor(5+Math.random()*(50-5))/100);

            }
            if(xAni2.running===false && s1.x>mainWin.width/2-s1.width/2 )
            {
                xAni2.restart()
                s2.height=world.height*(Math.floor(5+Math.random()*(50-5))/100);
            }
            Fns.s= Fns.s+1;
            time.text=Math.round(Fns.s)/10;
        }

    }
    Timer {//用于隔2秒后启动另一个石头
        id:t2
        interval: 1600;
        repeat: false
        running: false
        onTriggered:{
            xAni2.start()
        }

    }
    Component.onCompleted: {
        mainWin.state="ready";

    }
}
