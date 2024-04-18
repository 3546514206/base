# ch19_28.py
from tkinter import *
from tkinter import messagebox
from random import *
import time

cntL = 1
cntR = 1


class Ball:  # class Ball(): # 这样写是一样的
    def __init__(self, canvas, color, winW, winH, racket):
        self.canvas = canvas
        self.racket = racket
        self.id = canvas.create_oval(0, 0, 20, 20, fill=color)  # 建立球对象
        self.canvas.move(self.id, winW / 2, winH / 2)  # 设置球最初位置
        startPos = [-4, -3, -2, -1, 1, 2, 3, 4]  # 球最初x轴位移的随机数
        shuffle(startPos)  # 打乱排序
        self.x = startPos[0]  # 球最初水平移动单位
        self.y = -step  # 垂直移动单位
        self.notTouchBottom = True

    def hitRacket(self, ballPos):
        racketPos = self.canvas.coords(self.racket.id)
        if ballPos[2] >= racketPos[0] and ballPos[0] <= racketPos[2]:
            if ballPos[3] >= racketPos[1] and ballPos[3] <= racketPos[3]:
                return True
        return False

    def ballMove(self):
        self.canvas.move(self.id, self.x, self.y)  # step是正值表示往下移动
        ballPos = self.canvas.coords(self.id)
        if ballPos[0] <= 0:  # 侦测球是否超过画布左方
            self.x = step
        if ballPos[1] <= 0:  # 侦测球是否超过画布上方
            self.y = step
        if ballPos[2] >= winW:  # 侦测球是否超过画布右方
            self.x = -step
        if ballPos[3] >= winH:  # 侦测球是否超过画布下方
            self.y = -step
        if self.hitRacket(ballPos):  # 侦测是否撞到球拍
            self.y = -step
        if ballPos[3] >= winH:  # 侦测球是否超过画布下方
            self.notTouchBottom = False


class Racket():
    def __init__(self, canvas, color):
        self.canvas = canvas
        self.id = canvas.create_rectangle(0, 0, 100, 15, fill=color)  # 球拍对象
        self.canvas.move(self.id, 270, 400)
        self.x = 0
        self.canvas.bind_all('<KeyPress-Right>', self.moveRight)  # 绑定按住右键 ##########################
        self.canvas.bind_all('<KeyPress-Left>', self.moveLeft)  # 绑定按住左键

    def racketMove(self):
        self.canvas.move(self.id, self.x, 0)
        racketPos = self.canvas.coords(self.id)
        # self.x = 0
        if racketPos[0] <= 0:
            self.x = 0
        elif racketPos[2] >= winW:
            self.x = 0

    def moveLeft(self, event):
        global cntL
        print("按下左键", cntL)
        cntL += 1
        self.x = -3

    def moveRight(self, event):
        global cntR
        print("按下右键", cntR)
        cntR += 1
        self.x = 3


winW = 640  # 定义画布宽度
winH = 480  # 定义画布高度
step = 3  # 定义速度可想成位移步长
speed = 0.01  # 设置移动速度

tk = Tk()
tk.title("    弹球小游戏  ----  " + time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))  # 游戏窗口标题
tk.wm_attributes('-topmost', 1)  # 确保游戏窗口在屏幕最上层
canvas = Canvas(tk, width=winW, height=winH, bg='black')  # 建立画布
canvas.pack()
tk.update()

# 以下两行代码执行顺序决定了两者重合时哪一个对象被遮挡
racket = Racket(canvas, 'purple')  # 定义紫色球拍
ball = Ball(canvas, 'yellow', winW, winH, racket)  # 定义球对象

while ball.notTouchBottom:
    try:
        ball.ballMove()
    except:
        print("单击关闭按钮终止程序执行")
        break
    racket.racketMove()
    tk.update()
    time.sleep(speed)  # 可以控制移动速度

messagebox.showinfo("Game Over", "游戏结束！！！")
# tk.mainloop()
