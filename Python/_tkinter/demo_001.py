from tkinter import *
from tkinter.ttk import *

import time

scale = 100


def running():  # 开始Progressbar动画
    btn.configure(text="系统忙碌中...", state=DISABLED)
    print("\n" * 2)
    print("执行开始".center(scale + 28, '-'))
    start = time.perf_counter()
    for i in range(scale + 1):
        pb["value"] = i  # 每次更新1
        root.update()  # 更新画面
        a = '*' * i
        b = '.' * (scale - i)
        c = (i / scale) * 100
        t = time.perf_counter() - start
        print("\r任务进度:{:>3.0f}% [{}->{}]消耗时间:{:.2f}s".format(c, a, b, t), end="")
        time.sleep(0.03)
    print("\n" + "执行结束".center(scale + 28, '-'))
    btn.configure(text="重启任务", state=NORMAL)


root = Tk()
root.geometry("300x140+600+300")
root.title("任务进度可视化")

# 使用默认设置创建进度条
pb = Progressbar(root, length=200, mode="determinate", orient=HORIZONTAL)
pb.pack(padx=10, pady=20)
pb["maximum"] = 100
pb["value"] = 0

btn = Button(root, text="启动任务", command=running)
btn.pack(pady=10)

root.mainloop()
