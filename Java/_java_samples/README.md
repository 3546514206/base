# 1 Java8 tutorial
示例代码：com.java8
入门教程链接： http://winterbe.com/posts/2014/03/16/java-8-tutorial/

# 2 Machine learning

https://developers.google.com/machine-learning/crash-course/

# 3 日志级别

日志记录器（Logger）的行为是分等级的。如下表所示：

分为OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL或者您定义的级别。
Log4j建议只使用四个级别，优先级从高到低分别是 ERROR、WARN、INFO、DEBUG。
通过在这里定义的级别，您可以控制到应用程序中相应级别的日志信息的开关。
比如在这里定义了INFO级别，则应用程序中所有DEBUG级别的日志信息将不被打印出来，也是说大于等于的级别的日志才输出。


# 4 HashMap的putIfAbsent()方法
示例代码：com.java8.map.PutIfAbsentTest
## 4.1 来源
在此方法出现在HashMap里面之前，JDK给出的解决方案是ConcurrentMap的putIfAbsent()方法。
出现在HashMap里面的这个putIfAbsent()方法与之前的解决方法具有相同的功能，

## 4.2 特点
1.当value为null的时候，putIfAbsent()方法会覆盖null值，直到value不为null为止
2.当value初始值不为null的时候，putIfAbsent()保证返回值始终是唯一的，并且是多线程安全的
3.putIfAbsent()是有返回值的，应该对他的返回值进行非空判断
4.2和3主要应用在单例模式中

# 5 ConcurrentHashMap中的putIfAbsent方法

putIfAbsent方法主要是在向ConcurrentHashMap中添加键—值对的时候，它会先判断该键值对是否已经存在。

1.如果不存在（新的entry），那么会向map中添加该键值对，并返回null。

2.如果已经存在，那么不会覆盖已有的值，直接返回已经存在的值。
相当于：
```
 V v = map.get(key);
 if (v == null)
     v = map.put(key, value);
 return v;
```

返回值：

（1）如果是新的记录，那么会向map中添加该键值对，并返回null。

（2）如果已经存在，那么不会覆盖已有的值，直接返回已经存在的值。

# 6 自定义注解
示例代码： com.annatation

# 7 Date API

Java 8 contains a brand new date and time API under the package java.time.
The new Date API is comparable with the Joda-Time library, however it's not the same.
The following examples cover the most important parts of this new API.

Unlike java.text.NumberFormat the new DateTimeFormatter is immutable and thread-safe.

# 8 Annotations

Annotations in Java 8 are repeatable. Let's dive directly into an example to figure that out.

First, we define a wrapper annotation which holds an array of the actual annotations:

# 9 Java读取/写入Yaml配置文件

yaml配置文件格式规范：- 表示sequence(list列表结构)，: 表示map键值对

# 10 Mockito教程

https://www.cnblogs.com/Ming8006/p/6297333.html

# 11 maven profile动态选择配置文件

 profile可以让我们定义一系列的配置信息，然后指定其激活条件。这样我们就可以定义多个profile，然后每个profile对应不同的激活条件和配置信息，从而达到不同环境使用不同配置信息的效果。
 
## 11.1 profile定义的位置
 1. 针对于特定项目的profile配置我们可以定义在该项目的pom.xml中。
 2. 针对于特定用户的profile配置，我们可以在用户的settings.xml文件中定义profile。该文件在用户家目录下的“.m2”目录下。
 3. 全局的profile配置。全局的profile是定义在Maven安装目录下的“conf/settings.xml”文件中的。

## 11.2 动态配置注意点
 注入顺序如下，如果没找到相应的值，则再找下一个配置文件中的相应配置
 1. 全局配置文件conf/setting.xml
 2. 用户目录下的setting.xml
 3. 当前工程的pom.xml
 4. resources目录下的配置文件(本例为${profiles.active}/test.yml)

## 11.3 参考链接
 1. https://www.cnblogs.com/0201zcr/p/6262762.html
 2. http://blog.csdn.net/lihe2008125/article/details/50443491
 3. https://www.cnblogs.com/harvey2017/p/7762286.html


# 12 SLF4J门面日志框架
## 12.1 门面模式
slf4j是门面模式的典型应用，门面模式，其核心为外部与一个子系统的通信必须通过一个统一的外观对象进行，使得子系统更易于使用
门面模式的核心为Facade即门面对象，门面对象核心为几个点：
1.知道所有子角色的功能和责任
2.将客户端发来的请求委派到子系统中，没有实际业务逻辑
3.不参与子系统内业务逻辑的实现

## 12.2 为什么使用slf4j
我们为什么要使用slf4j，举个例子：
```
我们自己的系统中使用了logback这个日志系统
我们的系统使用了A.jar，A.jar中使用的日志系统为log4j
我们的系统又使用了B.jar，B.jar中使用的日志系统为slf4j-simple
这样，我们的系统就不得不同时支持并维护logback、log4j、slf4j-simple三种日志框架，非常不便。
```

解决这个问题的方式就是引入一个适配层，由适配层决定使用哪一种日志系统，而调用端只需要做的事情就是打印日志而不需要关心如何打印日志，slf4j或者commons-logging就是这种适配层，slf4j是本文研究的对象。
从上面的描述，我们必须清楚地知道一点：slf4j只是一个日志标准，并不是日志系统的具体实现。理解这句话非常重要，slf4j只做两件事情：
1. 提供日志接口
2. 提供获取具体日志对象的方法

slf4j-simple、logback都是slf4j的具体实现，log4j并不直接实现slf4j，但是有专门的一层桥接slf4j-log4j12来实现slf4j。

## 12.3 SLF4j 和 common-logging
https://blog.csdn.net/xydds/article/details/51606010

## 12.4 配置Slf4j依赖，桥接各种多个日志组件（排除commons-logging依赖的影响)
https://www.cnblogs.com/gsyun/p/6814696.html

## 12.5 日志组件slf4j介绍及配置详解
https://blog.csdn.net/foreverling/article/details/51385128

# 13 日期间隔计算

在java 编程中，不可避免用到计算时间差。前面我写过几篇文章，关于java 时间计算的，还有timezone 转换的文章，但没有这么具体到相差到天数，小时，分钟，秒数都列出来的情况，所以这里再总结下。
1. 用JDK 自带API 实现。
2. 利用 joda time library 来实现.

# 14 从jar包中读取资源Class对象

com.utils.PackageUtil

# 15 JAVA 泛型通配符 ? EXTENDS SUPER 的用法

```
<? extends T>和<? super T>是Java泛型中的“通配符（Wildcards）”和“边界（Bounds）”的概念。
<? extends T>：是指 “上界通配符（Upper Bounds Wildcards）
<? super T>：是指 “下界通配符（Lower Bounds Wildcards）
```

## 15.1 <? extends Hero>

ArrayList heroList<? extends Hero> 表示这是一个Hero泛型或者其子类泛型
heroList 的泛型可能是Hero
heroList 的泛型可能是APHero
heroList 的泛型可能是ADHero
所以 可以确凿的是，从heroList取出来的对象，一定是可以转型成Hero的


但是，不能往里面放东西，因为
放APHero就不满足<ADHero>
放ADHero又不满足<APHero>

## 15.2 <? super Hero>

ArrayList heroList<? super Hero> 表示这是一个Hero泛型或者其父类泛型
heroList的泛型可能是Hero
heroList的泛型可能是Object

可以往里面插入Hero以及Hero的子类
但是取出来有风险，因为不确定取出来是Hero还是Object

## 15.3 泛型通配符?

泛型通配符? 代表任意泛型
既然?代表任意泛型，那么换句话说，这个容器什么泛型都有可能

所以只能以Object的形式取出来
并且不能往里面放对象，因为不知道到底是一个什么泛型的容器

## 15.4 总结

频繁往外读取内容的，适合用上界Extends。
经常往里插入的，适合用下界Super。

# 16 mybatis 拼接动态表名、字段名

## 16.1 案例

需要根据不同的需求，传入不同的表名进行查询。

```
<select id="loadRequestsForProcess" resultType="com.landy.Request" statementType="STATEMENT">
    <include refid="loadRequestsForProcessSql" />
</select>

<sql id="loadRequestsForProcessSql">
    <![CDATA[
    SELECT
        r.*
    FROM
        xhf_request r
    WHERE r.process_date <= SYSDATE
      AND r.request_status_id<>2
      AND r.update_workflow_id = ${updateWorkflowId}
      AND EXISTS (
        SELECT
            1
        FROM
            ${xhfDetailTableName} pd,
            xhf_update_result ur
        WHERE pd.request_id = r.request_id
          AND pd.update_result_id = ur.update_result_id
          AND ur.process_status = 1 )
    ORDER BY request_id
    ]]>
</sql>

```

注意：
1. 需要加入statementType="STATEMENT"
2. 动态表名用${}表示

## 16.2 原理

动态SQL是mybatis的强大特性之一，mybatis在对sql语句进行预编译之前，会对sql进行动态解析，解析为一个BoundSql对象，也是在此处对动态sql进行处理。

在动态sql解析过程，#{}与${}的效果是不一样的：


### 16.2.1 #{} 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符。


如以下sql语句:

```
select * from user where name = #{name};
```

会被解析为：

```
select * from user where name = ?;
```

可以看到#{}被解析为一个参数占位符？。

### 16.2.2 ${} 仅仅为一个纯碎的 string 替换，在动态 SQL 解析阶段将会进行变量替换

如以下sql语句：

```
select * from user where name = ${name};
```
当我们传递参数“sprite”时，sql会解析为：
```
select * from user where name = "sprite";
```

可以看到预编译之前的sql语句已经不包含变量name了。

综上所得， ${} 的变量的替换阶段是在动态 SQL 解析阶段，而 #{}的变量的替换是在 DBMS 中。

## 16.3 总结

#{}与${}的区别可以简单总结如下：

1. #{}将传入的参数当成一个字符串，会给传入的参数加一个双引号
2. ${}将传入的参数直接显示生成在sql中，不会添加引号
3. #{}能够很大程度上防止sql注入，***${}无法防止sql注入***

   ${}在预编译之前已经被变量替换了，这会存在sql注入的风险。如下sql

```
select * from ${tableName} where name = ${name}
```

如果传入的参数tableName为user; delete user; --，那么sql动态解析之后，预编译之前的sql将变为：

```
select * from user; delete user; -- where name = ?;
```

--之后的语句将作为注释不起作用，顿时我和我的小伙伴惊呆了！！！看到没，本来的查询语句，竟然偷偷的包含了一个删除表数据的sql，是删除，删除，删除！！！重要的事情说三遍，可想而知，这个风险是有多大。

注意点：

1. ${}一般用于传输数据库的表名、字段名等
2. 能用#{}的地方尽量别用${}


## 16.4 动态替换表名/字段名

进入正题，通过上面的分析，相信大家可能已经对如何动态调用表名和字段名有些思路了。示例如下：

```
<select id="getUser" resultType="java.util.Map" parameterType="java.lang.String" statementType="STATEMENT">
    select
        ${columns}
    from ${tableName}
        where COMPANY_REMARK = ${company}
  </select>
```

要实现动态调用表名和字段名，就不能使用预编译了，需添加statementType="STATEMENT" 。

```
statementType：STATEMENT（非预编译），PREPARED（预编译）或CALLABLE中的任意一个，这就告诉 MyBatis 分别使用Statement，PreparedStatement或者CallableStatement。默认：PREPARED。这里显然不能使用预编译，要改成非预编译。
```

其次，sql里的变量取值是${xxx},不是#{xxx}。

因为${}是将传入的参数直接显示生成sql，如${xxx}传入的参数为字符串数据，需在参数传入前加上引号，如：

```
 String name = "sprite";
 name = "'" + name + "'";
```

这样，sql就变成：

```
select * from user where name = 'sprite';
```

# 17 mybatis 中的 <![CDATA[ ]]>

在使用mybatis 时我们sql是写在xml 映射文件中，如果写的sql中有一些特殊的字符的话，在解析xml文件的时候会被转义，但我们不希望他被转义，所以我们要使用<![CDATA[ ]]>来解决。

<![CDATA[   ]]> 是什么，这是XML语法。在CDATA内部的所有内容都会被解析器忽略。

如果文本包含了很多的"<"字符 <=和"&"字符——就象程序代码一样，那么最好把他们都放到CDATA部件中。

但是有个问题那就是 <if test="">   </if>   <where>   </where>  <choose>  </choose>  <trim>  </trim> 等这些标签都不会被解析，所以我们只把有特殊字符的语句放在 <![CDATA[   ]]>  尽量缩小 <![CDATA[  ]]> 的范围。

```
<select id="allUserInfo" parameterType="java.util.HashMap" resultMap="userInfo1">
  <![CDATA[
  SELECT * FROM user WHERE 1=1  AND  news_day > #{startTime} AND news_day <= #{endTime}
  ]]>
  <if test="editName!=''">
   AND newsEdit=#{editName}
  </if>
 </select>
```

因为这里有 ">"  "<=" 特殊字符所以要使用 <![CDATA[   ]]> 来注释，但是有<if> 标签，所以把<if>等 放外面.

# 18 算法链接

https://www.cs.usfca.edu/~galles/visualization/Algorithms.html

# 19 添加BeanUtil工具类

对日期进行特殊处理（增强处理）

# 20 阿里云tomcat启动慢

查看tomcat日志，看看是否此问题
以Centos7为例，
```
1.yum install rngd-tools  或者rng-tools
2.systemctl start rngd启动服务
3.systemctl daemon-reload重新载入服务
4.systemctl restart rngd重启服务
```
我们可以测试一下随机数的生成速度
```
watch -n 1 cat /proc/sys/kernel/random/entropy_avail
```

# 21 添加如何访问https协议接口

# 22 java 获取当月第一天和最后一天 获取前一个月第一天和最后一天

# 23 java语法糖

https://segmentfault.com/a/1190000016048454

https://www.cnblogs.com/Gyoung/p/8663395.html 
https://www.jianshu.com/p/fb6731ee53d2

# 24 Redis
http://doc.redisfans.com/
https://blog.csdn.net/wxx151556/article/details/78493147

# 25 Spring init
https://blog.csdn.net/koflance/article/details/59304090
https://www.cnblogs.com/fyx158497308/p/3977391.html

# 26 Java Collections
https://howtodoinjava.com/java/collections/hashmap/performance-comparison-of-different-ways-to-iterate-over-hashmap/
https://howtodoinjava.com/java/collections/performance-comparison-of-different-for-loops-in-java/

# 27 网站全变灰色解决方案

```html
html { 
    -webkit-filter: grayscale(100%);
    -moz-filter: grayscale(100%);
    -ms-filter: grayscale(100%);
    -o-filter: grayscale(100%);
    filter: grayscale(100%);
}
```
