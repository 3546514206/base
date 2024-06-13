# ch18_13.py
from tkinter import *
from tkinter.ttk import *

root = Tk()
root.title("ch18_13")

stateCity = {"Illinois":"芝加哥","California":"洛杉矶",
                "Texas":"休斯敦","Washington":"西雅图",
                "Jiangsu":"南京","Shandong":"青岛",
                "Guangdong":"广州","Fujain":"厦门",
                "Mississippi":"Oxford","Kentucky":"Lexington",
                "Florida":"Miami","Indiana":"West Lafeyette"}

tree = Treeview(root,columns=("cities"))
yscrollbar = Scrollbar(root)
yscrollbar.pack(side=RIGHT,fill=Y)
tree.pack()
# tree.configure(yscrollcommand=yscrollbar.set)
yscrollbar.config(command=tree.yview)
tree.configure(yscrollcommand=yscrollbar.set) # 经过试验，以上两行代码交换顺序后无影响
# 建立栏标题
tree.heading("#0",text="State")     # 图标栏
tree.heading("cities",text="City")
# 格式栏位
tree.column("cities",anchor=CENTER)
# 建立内容
for state in stateCity.keys():
    tree.insert("",index=END,text=state,values=stateCity[state])

root.mainloop()
