# ch18_9.py
from tkinter import *
from tkinter.ttk import *


def treeSelect(event):
    widgetObj = event.widget  # 取得控件
    itemselected = widgetObj.selection()[0]  # 取得选项
    col1 = widgetObj.item(itemselected, "text")  # 取得图标栏内容
    # print("########  ",col1) # ########   江苏
    col2 = widgetObj.item(itemselected, "values")[0]  # 取得第0索引栏位内容
    # print(widgetObj.item(itemselected,"values"))   # ('南京',)
    str = "{0} : {1}".format(col1, col2)  # 取得所选项目内容
    var.set(str)  # 设置状态栏内容


root = Tk()
root.title("ch18_9")

stateCity = {"伊利诺": "芝加哥", "加州": "洛杉矶",
             "德州": "休斯敦", "华盛顿州": "西雅图",
             "江苏": "南京", "山东": "青岛",
             "广东": "广州", "福建": "厦门"}
# 建立Treeview
tree = Treeview(root, columns=("cities"), selectmode=BROWSE)
# 建立栏标题
tree.heading("#0", text="State")
tree.heading("cities", text="City")
# 格式栏位
tree.column("cities", anchor=CENTER)
# 建立内容，行号从1算起偶数行是浅蓝色底
tree.tag_configure("evenColor", background="lightblue")
rowCount = 1
for state in stateCity.keys():
    if (rowCount % 2 == 1):
        tree.insert("", index=END, text=state, values=stateCity[state])
    else:
        tree.insert("", index=END, text=state,
                    values=stateCity[state], tags=("evenColor"))
        rowCount += 1

tree.bind("<<TreeviewSelect>>", treeSelect)
tree.pack()
var = StringVar()
label = Label(root, textvariable=var, relief="groove")
label.pack(fill=BOTH, expand=True)

root.mainloop()
