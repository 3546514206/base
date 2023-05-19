# 图与网络优化Qt

#### 项目介绍

使用Qt工具将一些图论的算法可视化，目前支持的算法有Bellman，Floyd算法，网络单纯形法求解最小费用流。之后可能会添加网络单纯形法求解最大流的算法

#### 软件架构

使用Qt5.11.0开发，编译环境为MinGW5.3.0 32bit，部署后可以在Windows系统（x86）<del>以及Linux系统（未测试)</del>下运行。

## 注意

由于增加了输出Floyd算法数据到Excel，该功能使用了QAxContainer组件。<br>
根据Qt文档：The QAxContainer module is a Windows-only extension for accessing ActiveX controls and COM objects. 所以，应该不支持Linux系统。感谢@Think的提醒。

#### 移除QAxContainer来实现可能运行在Linux系统的目的

1. 删除工程文件project.pro中的 <code>axcontainer</code> 模块<br><pre>QT       += core gui <del>axcontainer</del></pre>
2. 删除有关Floyd输出到Excel方法

#### 使用说明

鼠标右键拖动改变显示区域。左键拖动单个顶点，也可以多选顶点整体拖动。滚轮缩放视图大小。

##### 如何构造边

最短路径界面在编辑模式下按住Ctrl键选中一个顶点点击并拖动可以构造边，如下图所示
![如何构造边](https://gitee.com/uploads/images/2018/0625/211051_ce99ca21_1729038.png "屏幕截图.png")

边上的标签在编辑模式下可以用鼠标左键拖动

##### 网络单纯形法旋转顶点上的标签

顶点上的标签是无法拖动的，但是在编辑模式下可以点击顶点旋转标签（上右下左）。

##### 观察结果

* Bellman算法 在运行模式（非编辑模式）下将鼠标指针移动到节点的上面，可以观察从节点1到该节点的最短路径。

![bellman](https://gitee.com/uploads/images/2018/0625/214606_b2f8cb5e_1729038.png "屏幕截图.png")

* Floyd算法 在运行模式下，首先点击一个节点作为起始节点，然后再将鼠标指针移动到一个节点上面，可以观察从起始节点到该节点的最短路径。

![floyd](https://gitee.com/uploads/images/2018/0625/214641_0fbee288_1729038.png "屏幕截图.png")

##### 其他

* 运行模式所有节点不可以移动，边上的标签不可以移动。
* 但使用两阶段法的网络单纯形法的第一阶段的dummy节点（淡蓝色的节点）可以移动，如下图所示。

![dummy节点](https://gitee.com/uploads/images/2018/0625/213233_84b85a43_1729038.png "在这里输入图片标题")

项目地址 [https://gitee.com/zhaoziqiu1995/graph_and_network_optimization_qt.git](https://gitee.com/zhaoziqiu1995/graph_and_network_optimization_qt.git "项目地址（Gitee)")



