import QtQuick 2.4

/*
Canvas{
    id: root
    width: 200; height: 200
    onPaint: {
        var ctx = getContext("2d")
        ctx.lineWidth = 4
        ctx.strokeStyle = "blue"
        ctx.fillStyle = "steelblue"
        ctx.beginPath()
        ctx.moveTo(50,50)
        ctx.lineTo(150,50)
        ctx.lineTo(150,150)
        ctx.lineTo(50,150)
        ctx.closePath()
        ctx.fill()
        ctx.stroke()
    }
}
*/

/*
Canvas {
    id: root
    onPaint: {
        var ctx = getContext("2d")
        ctx.strokeStyle = "red"
        ctx.beginPath()
        ctx.moveTo(50,50)
        ctx.lineTo(150,50)
        ctx.stroke()
    }
}
*/

/*
Canvas {
    id: root
    width: 120; height: 120
    onPaint: {
        var ctx = getContext("2d")
        ctx.fillStyle = "green"
        ctx.strokeStyle = "blue"
        ctx.lineWidth = 4

        ctx.fillRect(20,20,80,80)
        ctx.clearRect(30,30,60,60)
        ctx.strokeRect(20,20,40,40)
    }
}
*/

/*
Canvas {
    width: 200; height: 200
    id: root
    onPaint: {
        var ctx = getContext("2d")
        var gradient = ctx.createLinearGradient(100,0,100,200)
        gradient.addColorStop(0,"blue")
        gradient.addColorStop(0.5,"lightsteelblue")
        ctx.fillStyle = gradient
        ctx.fillRect(50,50,100,100)
    }
}
*/

/*
Canvas {
    id: canvas
    //width: 100; height: 100
    onPaint: {
        var ctx = getContext("2d")
        ctx.strokeStyle = "#333"
        ctx.fillRect(0,0,canvas.width,canvas.height)

        ctx.shadowColor = "blue"
        ctx.shadowOffsetX = 2
        ctx.shadowOffsetY = 2
        ctx.font = "Bold 80px Ubuntu"
        ctx.fillStyle = "#33a9ff"
        ctx.fillText("Earth",60,80)
    }
}
*/

/*
Canvas {
    id: root
    width: 200; height: 100
    onPaint: {
        var ctx = getContext("2d")
        ctx.drawImage("ball.png",10,10)
        ctx.save()

        ctx.strokeStyle = "red"
        ctx.beginPath()
        ctx.moveTo(110,10)
        ctx.lineTo(160,10)
        ctx.lineTo(135,50)
        ctx.closePath()
        ctx.translate(100,0)		//移动
        ctx.clip()
        ctx.drawImage("ball.png",10,10)
        ctx.stroke()
        ctx.restore()
    }
    Component.onCompleted: {
        loadImage("ball.png")
    }
}
*/


Canvas {
    id: root
    width: 120; height: 120
    onPaint: {
        var ctx = getContext("2d")
        ctx.strokeStyle = "blue"
        ctx.lineWidth = 4

        ctx.beginPath()
        ctx.rect(40,40,40,40)

        ctx.stroke()

        ctx.translate(60,60)
        ctx.strokeStyle = "green"
        ctx.rotate(Math.PI/4)
        ctx.stroke()
    }
}

