![工厂模式](https://i.loli.net/2020/10/17/miCrgRXSDIOyP3l.png)

# 工厂模式 🏗

> 定义一个用于创建产品的接口，由子类决定生产什么产品。

大家可能都知道工厂模式，可真正理解应用的又有多少呢？此文本着能让大家彻底了解和何时适合使用工厂模式的原则来书写，希望能对你有所帮助，点个关注，一起开启新的思维来学习设计模式。

## 概念 👀

首先我们要知道的是，工厂模式是**<u>创建型</u>**设计模式分类下的一种，用来创建对象时选择使用。而还有一种看似功能一样，但实际的实现却大相径庭的方式叫做 *简单工厂模式或（静态工厂模式）*
。要注意这两者区别。即使他们完成的工作都是 <u>**创建对象**</u>

- 简单工厂模式（静态工厂模式）：通过一个工厂类完成所有对象的创建工作；
- 工厂方法模式：如引用说明 **定义一个用于创建产品的接口，由子类决定生产什么产品**；

## 简单工厂模式 😉

让我们先来看一下简单工厂模式，这个模式‘人如其名’，非常简单。

### 类图 🖌

![通过 idea UML 工具构建](https://i.loli.net/2020/10/13/RbNwGdVjuQAnkY9.png)

### 具体代码 📄

> 完整代码及单元测试结果 [https://github.com/lvgocc/java-design-patterns/tree/main/factory](https://github.com/lvgocc/java-design-patterns/tree/main/factory)

```java
/**
 * 抽象图形类
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 21:33
 * @since 1.0.0
 */
public abstract class AbstractGraphical {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
```

```java
/**
 * 圆形
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 21:55
 * @since 1.0.0
 */
public class Circular extends AbstractGraphical {
}
```

```java
/**
 * 矩形
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 22:10
 * @since 1.0.0
 */
public class Rectangle extends AbstractGraphical {
}
```

```java
/**
 * 三角形
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 22:10
 * @since 1.0.0
 */
public class Triangle extends AbstractGraphical {
}
```

```java
/**
 * 图形工厂
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 22:05
 * @since 1.0.0
 */
public class GraphicalFactory {

    public static final int CIRCULAR = 0;
    public static final int RECTANGLE = 1;
    public static final int TRIANGLE = 2;


    public static AbstractGraphical create(int type) {
        switch (type) {
            case CIRCULAR:
                return new Circular();
            case RECTANGLE:
                return new Rectangle();
            case TRIANGLE:
                return new Triangle();
            default:
                throw new IllegalStateException("please check param， range 0 - 2");
        }
    }
}
```

### 使用时机

当我们所要创建的<u>对象个数较少</u>，<u>创建过程较复杂</u>，<u>使用较频繁</u>
可以通过简单工厂模式将创建对象的过程封装起来，这样可以提高代码可读性，业务代码更专注于业务本身（当然案例代码中没有模拟构建复杂对象的情景）同时为了便于使用，将方法定义为静态。故也称之为静态工厂模式。

❗这里在强调一下，同时解释一下使用时机

1. 对象个数少：指的是需要通过这种方式创建的对象个数，通常为不变个数。因为如果对象个数迭代频繁，个数较多，在这种方法的维护上会出现一个很大的问题，即每新增加一个 class （一种图形，比如在增加一个正方形）就要调整一次
   GraphicalFactory 类的代码。同样，即违反了开闭原则。
2. 创建过程较复杂：通过反向推理可知，如果创建对象过程不复杂，我选择直接 new。
3. 使用较频繁：同上可得，如果使用不频繁，我选择直接 new。不会考虑相对较复杂的设计模式。

## 工厂方法模式 😊

当我们将上面的简单工厂模式中的创建图形的方法抽象出来，将创建的过程延迟到子类中。满足了开闭原则的时候，那这就是工厂方法模式了。

**工厂方法模式怎么理解呢，顾名思义，通过工厂的方法来创建对象，每个对象都由一个工厂来创建，怎么创建这个工厂说了算**。理解工厂方法模式对后面的抽象工厂理解可以说是“很精彩”

### 类图 🖌

**工厂方法模式**的类图

![](https://i.loli.net/2020/10/13/jdoIiGvwtP2JCFY.png)

**简单工厂模式**的类图

![通过 idea UML 工具构建](https://i.loli.net/2020/10/13/RbNwGdVjuQAnkY9.png)

通过类图的比较我们发现。简单工厂的工厂类抽象成了一个抽象工厂，而工厂方法模式中多了3个工厂。这就是工厂模式的定义诠释

> **定义一个用于创建产品的接口，由子类决定生产什么产品**

### 具体代码 📄

> 避免篇幅过长，完整代码及单元测试结果点击查看 [https://github.com/lvgocc/java-design-patterns/tree/main/factory](https://github.com/lvgocc/java-design-patterns/tree/main/factory)

```java
/**
 * 图形类接口
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 21:33
 * @since 1.0.0
 */
public interface Graphical {

    /**
     * 图形描述
     */
    void description();
}
```

```java
/**
 * 圆形
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 21:55
 * @since 1.0.0
 */
public class Circular implements Graphical {

    @Override
    public void description() {
        LOGGER.info("circular");
    }
}
```

```java
/**
 * 抽象工厂
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 23:16
 * @since 1.0.0
 */
public abstract class AbstractGraphicalFactory {

    /**
     * 创建一个图形
     *
     * @return 具体图形
     */
    public abstract Graphical creat();
}
```

```java
/**
 * 圆形工厂
 *
 * @author lvgorice@gmail.com
 * @date 2020/10/8 23:27
 * @since 1.0.0
 */
public class CircularFactory extends AbstractGraphicalFactory {
    /**
     * 将创建复杂的圆形过程封装到工厂里。
     * 1. 选定圆形位置；
     * 2. 指定圆形半径；
     * 3. 设置绘制图形所用的画笔；
     * 4. 选择图形的颜色；
     * 5. 。。。。。
     *
     * @return 一个复杂的圆形
     */
    @Override
    public Graphical creat() {
        return new Circular();
    }
}
```

### 使用时机

其实这里我们通过与上面的简单工厂模式比较就可以看出，工厂方法模式适合在<u>**对象可能存在新增的情况，而且数量不定。创建对象过程复杂，使用频繁**</u>的场景。

### JDK中的工厂设计模式示例

> 案例来源：https://www.journaldev.com/1392/factory-design-pattern-in-java

1. java.util.Calendar，ResourceBundle和NumberFormat`getInstance()`方法使用Factory模式。
2. `valueOf()` 包装器类（例如Boolean，Integer等）中的方法。

## 总结 🐱‍👤

当我们所要创建的<u>对象个数较少且不会在新增</u>，<u>创建过程较复杂</u>，<u>使用较频繁</u> 可以通过简单工厂模式将创建对象。如不满足以上 3 种情况，建议直接 new。

**当我们所需要创建的对象使用频繁，创建过程较复杂，可能增加对象个数时，这无疑选择使用工厂方法模式。**

当我们试图用上面的3个原则去选择使用工厂模式的时候应该要思考几个问题。如

1. 对象个数很少，创建不复杂。（new 关键字）

2. 创建过程虽然复杂，但是很少使用。（建造者模式）

3. 使用虽然很频繁，但只有1个对象就满足了需要。（单例模式）

等等诸如以上对象与使用使机的权衡都需要我们自己去仔细的设计和衡量，设计模式只提供了一种思想，你可以将一些思想整合使用，也可以使用一个方法来解决你的所有问题。

以上的几个问题，分别可以考虑单例模式和后面要讲到的建造者模式来实现，并不一定非要用工厂模式，活学活用才是我们的宗旨。



> *千万不要搞骚操作，为了用设计模式而用，否则岂不是 new 个 String 对象也要工厂来创建？😢*

----
<div align="center">
    <b>亦或繁星、亦或尘埃。星尘✨，为了梦想，学习技术，不要抱怨、坚持下去💪。</b>
    <p>关注<b style='color:blue'>星尘的一个朋友</b>获取源码、加群一起交流学习🤓。</p>
    <img alt='星尘的一个朋友' src='https://i.loli.net/2020/10/22/7swJfMCPrThebVI.png'/>
</div>
