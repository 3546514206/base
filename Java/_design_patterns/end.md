# 和 lvgo 一起学习设计模式.PDF

大家好，我是 lvgo（小米），刚刚用了60天时间学完了 `设计模式`，输出了 23 份关于设计模式的学习文章，我为了自己复习方便，把它整理成了一本 PDF 。现在我想把它分享出去，**这份文档内容很纯粹**
，因为它是由“草根”程序员整理出来的，所以我觉得它更适合更多的开发者！

## 前言

国庆的时候，开始了对设计模式这个知识点的学习。

因为是系统的学习，所以我同时阅读了**大量**关于设计模式的资料，

其中包括

- GOF 的原著《设计模式.可复用面向对象软件的基础》
- 国内口碑较高的《大话设计模式》程杰著，
- 秦小波《设计模式之禅（第2版）》
- 很多博客网站等等（详情见文末）
- .......

![books](https://i.loli.net/2020/12/20/9uokIFMYTgbKJle.png)

看这么多资料的原因嘛，有两点

1.

目前市面知识要么多是凑数，要么多是营销，在这个人们称为的“知识付费”的时代，已经很难找到从“根本传授知识的“（这也是自己沉淀输出知识的一个原因，希望自己能做到一个朴实无华，作为一个技术人员的角度来真真正正的把自己对这个领域的所见所得输出出去。）

2.

另外一方面就是方便自己学习理解、提高对知识的接受程度，讲真你很难从一个地方学到更多东西，所以我无论学哪个知识点都是会搜罗很多资料去学习，其实在资料选择这一块就是一个非常耗时的过程，因为有很多东西别人说好，但是他不一定适合你。技术这东西更是如此，因为每个人的技术起点不同，只有选取合适自己的“高度”才能学到东西，这也算是“仁者见仁智者见智”的一种体现吧。

## 部分截图

![部分截图](https://i.loli.net/2020/12/20/jZ8ArtpGDR2cXQN.png)

## 过程

### 设计模式的学习问题⁉❔

我一直都在想如何学会一个新东西，和掌握一个旧东西🤔，有很多前辈给过一些建议和意见📑，（当然不是直接给我，都是看大佬的文章或者书籍当中。）先去用，再去学。嗯，我想是的，这样肯定是个很正常的学习过程。但是对于一些你暂时无法使用的东西，你如何去掌握它呢？我认为应该抛开表象去了解本质，通过本质的类比去掌握那些暂时无法使用的东西。设计模式就是这种情况，在平常的开发中，常用的设计模式就那么几种，其它的那些没有机会去接触，干学，如何才能掌握呢？🗃

我从小就是一个好为人师的家伙🤓，然而自己学习却一直都不怎么样😢。人太实在，也不懂的包装，我只知道我学习这么差的能学会的东西，讲给别人应该差不哪去。😊

在学习算法的时候，学到一个特别巧妙而且很好用的解题思路或者说逻辑思维``”分而治之“``，还有之前和网友讨论的一个叫做``”复杂度守恒定律“``的东西。这两个东西放在一起，可以说是很”矛盾“⚔🛡了。

说了这么多我想说的就是，设计模式这个知识点，真的很简单又很难。简单是因为每种设计模式的定义拿出来都能看的懂，难是难在如何，何时的应用。而我希望我写的这个设计模式系列是去应用化的，就是单纯的把设计模式的思想记录下来。我所理解的设计模式就该如此。

GOF的 **《设计模式.可复用面向对象软件的基础》** 通过一个应用案例的实现，串起了23种设计模式，我想在系列更新完之后也更新一个应用，将 23
种设计模式尽可能的应用到一个应用案例中去，而不是每个设计模式都涉及具体的应用，因为我觉得这可能会带的人们更关注应用的实现，而不是设计模式的应用，这是我的一些想法。

**
不要纠结每种模式的具体实现，把它们抽象出来，你能够清楚的描述每种模式是为了解决什么问题而存在的时候，就已经掌握它了，就可以把它用在任何当你需要的时候。而不是问什么时候需要它，这可能有点绕，不过它是真的。不要问我设计模式可以解决哪些问题，把问题给我，我告诉你用什么设计模式可以解决它！**

### 返璞归真

我认为学习设计模式的过程就像拿到一台游戏机，玩到最后，我都会拆开看看里面是什么，而早已不关心游戏好不好玩了。

知识也是一样，知其然而知其所以然。

**我们为了学会使用某种东西看他的操作手册就可以了；**

**我们如果想要学会修某种东西就需要看他的设计手册；**

**当我们想要创造某种东西，你就需要掌握很多很多设计手册，将他们的经验进行吸收、消化、提炼。才能有更好的结果。**

*当然，如果你只想会用，有一份差不多的操作指南也就够了。*

## 收获

60 天说长不长，说短不短，我完成了这一部分知识点的学习，并将其整理成为对应的 23 篇 **原创** 文章提交到了 git 仓库*（这个参考包括了涉及的源码、文章原文、素材、UML类图这些内容）*。

同时也将他们发表在了 `星尘的一个朋友` 这个公众号上。公众号我也只认为是一个分享知识的地方，我没有营销他的想法，所以就像平常发表文章一样在上面写写记记，能帮到一个人也是极好的。

越来越多的时候想将自己在技术上的所见所得与人分享或交流，当然能有机会和大佬交谈学到东西就更好了，这样我就可以将知识传承下去了。写《和 lvgo 一起学设计模式》这个系列文章让我学到了很多，感谢所有！

![源码](https://i.loli.net/2020/12/20/WrRjE9wYtc7bhKp.png)

## 如何获取

大家可以关注公众号回复：`设计模式` 获得这本《和 lvgo 一起学习设计模式》 PDF 以及这个设计模式的 `github 仓库` ，*如果可以，希望你给个 **star** 支持一下我！*

### 写在最后

保持热爱，奔赴山海。
