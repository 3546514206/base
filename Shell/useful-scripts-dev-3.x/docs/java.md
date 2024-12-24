🐌 `Java`相关脚本
====================================

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [🍺 show-busy-java-threads](#-show-busy-java-threads)
    - [用法](#%E7%94%A8%E6%B3%95)
    - [示例](#%E7%A4%BA%E4%BE%8B)
    - [贡献者](#%E8%B4%A1%E7%8C%AE%E8%80%85)
- [🍺 show-duplicate-java-classes](#-show-duplicate-java-classes)
    - [用法](#%E7%94%A8%E6%B3%95-1)
        - [`JDK`开发场景使用说明](#jdk%E5%BC%80%E5%8F%91%E5%9C%BA%E6%99%AF%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E)
            - [对于一般的工程](#%E5%AF%B9%E4%BA%8E%E4%B8%80%E8%88%AC%E7%9A%84%E5%B7%A5%E7%A8%8B)
            - [对于`Web`工程](#%E5%AF%B9%E4%BA%8Eweb%E5%B7%A5%E7%A8%8B)
        - [`Android`开发场景使用说明](#android%E5%BC%80%E5%8F%91%E5%9C%BA%E6%99%AF%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E)
    - [示例](#%E7%A4%BA%E4%BE%8B-1)
    - [贡献者](#%E8%B4%A1%E7%8C%AE%E8%80%85-1)
- [🍺 find-in-jars](#-find-in-jars)
    - [用法](#%E7%94%A8%E6%B3%95-2)
    - [示例](#%E7%A4%BA%E4%BE%8B-2)
    - [运行效果](#%E8%BF%90%E8%A1%8C%E6%95%88%E6%9E%9C)
    - [参考资料](#%E5%8F%82%E8%80%83%E8%B5%84%E6%96%99)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

-------------------------------

关于`Java`排错与诊断，力荐️`Arthas`： ❤️

- `Arthas`用户文档： https://arthas.aliyun.com/doc/quick-start.html
- GitHub Repo： [alibaba/arthas: Alibaba Java诊断利器](https://github.com/alibaba/arthas)

`Arthas`功能异常(😜)强劲，且在阿里巴巴线上支持使用多年。我自己也常用，一定要看看用用！

`Arthas`是通过`Agent`方式来连接运行的`Java`进程、主要通过交互式来完成功能，与之对应的脚本方式也有其优势，如：

1. 可以在进程不能启动的情况下完成诊断（如依赖中的重复类分析、`ClassPath`上的资源或类查找）
1. 开销少；简单少依赖（就纯文本的一个脚本文件）
1. 方便与（已有的）工具（如`awk`、`sed`、`cron`）、流程或设施集成，进一步编程/自动化

请按需按场景选用。

-------------------------------

<a id="beer-show-busy-java-threadssh"></a>
<a id="beer-show-busy-java-threads"></a>

🍺 [show-busy-java-threads](../bin/show-busy-java-threads)
----------------------

用于快速排查`Java`的`CPU`性能问题(`top us`值过高)，自动查出运行的`Java`进程中消耗`CPU`多的线程，并打印出其线程栈，从而确定导致性能问题的方法调用。  
目前只支持`Linux`。原因是`Mac`、`Windows`的`ps`命令不支持列出进程的线程`id`，更多信息参见 [#33](https://github.com/oldratlee/useful-scripts/issues/33)，欢迎提供解法。

PS，如何操作可以参见[`@bluedavy`](http://weibo.com/bluedavy)的[《分布式Java应用》](https://book.douban.com/subject/4848587/)的【5.1.1 `CPU`消耗分析】一节，说得很详细：

1. `top`命令找出消耗`CPU`高的`Java`进程及其线程`id`：
    1. 开启线程显示模式（`top -H`，或是打开`top`后按`H`）
    1. 按`CPU`使用率排序（`top`缺省是按`CPU`使用降序，已经合要求；打开`top`后按`P`可以显式指定按`CPU`使用降序）
    1. 记下`Java`进程`id`及其`CPU`高的线程`id`
1. 查看消耗`CPU`高的线程栈：
    1. 用进程`id`作为参数，`jstack`出有问题的`Java`进程
    1. 手动转换线程`id`成十六进制（可以用`printf %x 1234`）
    1. 在`jstack`输出中查找十六进制的线程`id`（可以用`vim`的查找功能`/0x1234`，或是`grep 0x1234 -A 20`）
1. 查看对应的线程栈，分析问题

查问题时，会要多次上面的操作以分析确定问题，这个过程**太繁琐太慢了**。  
期望整合上面的过程成一个脚本，这样一行命令就可以自动化地搞定。

### 用法

```bash
show-busy-java-threads
# 从所有运行的Java进程中找出最消耗CPU的线程（缺省5个），打印出其线程栈

# 缺省会自动从所有的Java进程中找出最消耗CPU的线程，这样用更方便
# 当然你可以通过 -p 选项 手动指定要分析的Java进程Id，以保证只会显示你关心的那个Java进程的信息
show-busy-java-threads -p <指定的Java进程Id>
show-busy-java-threads -p 42
show-busy-java-threads -p 42,47

show-busy-java-threads -c <要展示示的线程栈个数>

show-busy-java-threads <重复执行的间隔秒数> [<重复执行的次数>]
# 多次执行；这2个参数的使用方式类似vmstat命令

show-busy-java-threads -a <运行输出的记录到的文件>
# 记录到文件以方便回溯查看

show-busy-java-threads -S <存储jstack输出文件的目录>
# 指定jstack输出文件的存储目录，方便记录以后续分析

##############################
# 注意：
##############################
# 如果Java进程的用户 与 执行脚本的当前用户 不同，则jstack不了这个Java进程
# 为了能切换到Java进程的用户，需要加sudo来执行，即可以解决：
sudo show-busy-java-threads

show-busy-java-threads -s <指定jstack命令的全路径>
# 对于sudo方式的运行，JAVA_HOME环境变量不能传递给root，
# 而root用户往往没有配置JAVA_HOME且不方便配置，不能找到jstack命令。
# 这时显式指定jstack命令的路径就反而显得更方便了

# -m 选项：执行jstack命令时加上 -m 选项，显示上Native的栈帧，一般应用排查不需要使用
show-busy-java-threads -m
# -F 选项：执行jstack命令时加上 -F 选项（如果直接jstack无响应时，用于强制jstack），一般情况不需要使用
show-busy-java-threads -F
# -l 选项：执行jstack命令时加上 -l 选项，显示上更多相关锁的信息，一般情况不需要使用
# 注意：和 -m -F 选项一起使用时，可能会大大增加jstack操作的耗时
show-busy-java-threads -l

# 帮助信息
$ show-busy-java-threads -h
Usage: show-busy-java-threads [OPTION]... [delay [count]]
Find out the highest cpu consumed threads of java processes,
and print the stack of these threads.

Example:
  show-busy-java-threads       # show busy java threads info
  show-busy-java-threads 1     # update every 1 second, (stop by eg: CTRL+C)
  show-busy-java-threads 3 10  # update every 3 seconds, update 10 times

Output control:
  -p, --pid <java pid(s)>   find out the highest cpu consumed threads from
                            the specified java process.
                            support pid list(eg: 42,47).
                            default from all java process.
  -c, --count <num>         set the thread count to show, default is 5.
                            set count 0 to show all threads.
  -a, --append-file <file>  specifies the file to append output as log.
  -S, --store-dir <dir>     specifies the directory for storing
                            the intermediate files, and keep files.
                            default store intermediate files at tmp dir,
                            and auto remove after run. use this option to keep
                            files so as to review jstack/top/ps output later.
  delay                     the delay between updates in seconds.
  count                     the number of updates.
                            delay/count arguments imitates the style of
                            vmstat command.

jstack control:
  -s, --jstack-path <path>  specifies the path of jstack command.
  -F, --force               set jstack to force a thread dump.
                            use when jstack does not respond (process is hung).
  -m, --mix-native-frames   set jstack to print both java and
                            native frames (mixed mode).
  -l, --lock-info           set jstack with long listing.
                            prints additional information about locks.

CPU usage calculation control:
  -i, --cpu-sample-interval specifies the delay between cpu samples to get
                            thread cpu usage percentage during this interval.
                            default is 0.5 (second).
                            set interval 0 to get the percentage of time spent
                            running during the *entire lifetime* of a process.

Miscellaneous:
  -h, --help                display this help and exit.
  -V, --version             display version information and exit.
```

### 示例

```bash
$ show-busy-java-threads
[1] Busy(57.0%) thread(23355/0x5b3b) stack of java process(23269) under user(admin):
"pool-1-thread-1" prio=10 tid=0x000000005b5c5000 nid=0x5b3b runnable [0x000000004062c000]
   java.lang.Thread.State: RUNNABLE
    at java.text.DateFormat.format(DateFormat.java:316)
    at com.xxx.foo.services.common.DateFormatUtil.format(DateFormatUtil.java:41)
    at com.xxx.foo.shared.monitor.schedule.AppMonitorDataAvgScheduler.run(AppMonitorDataAvgScheduler.java:127)
    at com.xxx.foo.services.common.utils.AliTimer$2.run(AliTimer.java:128)
    at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:886)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:908)
    at java.lang.Thread.run(Thread.java:662)

[2] Busy(26.1%) thread(24018/0x5dd2) stack of java process(23269) under user(admin):
"pool-1-thread-2" prio=10 tid=0x000000005a968800 nid=0x5dd2 runnable [0x00000000420e9000]
   java.lang.Thread.State: RUNNABLE
    at java.util.Arrays.copyOf(Arrays.java:2882)
    at java.lang.AbstractStringBuilder.expandCapacity(AbstractStringBuilder.java:100)
    at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:572)
    at java.lang.StringBuffer.append(StringBuffer.java:320)
    - locked <0x00000007908d0030> (a java.lang.StringBuffer)
    at java.text.SimpleDateFormat.format(SimpleDateFormat.java:890)
    at java.text.SimpleDateFormat.format(SimpleDateFormat.java:869)
    at java.text.DateFormat.format(DateFormat.java:316)
    at com.xxx.foo.services.common.DateFormatUtil.format(DateFormatUtil.java:41)
    at com.xxx.foo.shared.monitor.schedule.AppMonitorDataAvgScheduler.run(AppMonitorDataAvgScheduler.java:126)
    at com.xxx.foo.services.common.utils.AliTimer$2.run(AliTimer.java:128)
    at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:886)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:908)
    at java.lang.Thread.run(Thread.java:662)

......
```

上面的线程栈可以看出，`CPU`消耗最高的2个线程都在执行`java.text.DateFormat.format`，业务代码对应的方法是`shared.monitor.schedule.AppMonitorDataAvgScheduler.run`。可以基本确定：

- `AppMonitorDataAvgScheduler.run`调用`DateFormat.format`次数比较频繁。
- `DateFormat.format`比较慢。（这个可以由`DateFormat.format`的实现确定。）

多执行几次`show-busy-java-threads`，如果上面情况高概率出现，则可以确定上面的判定。  
因为调用越少代码执行越快，则出现在线程栈的概率就越低。  
脚本有自动多次执行的功能，指定 重复执行的间隔秒数/重复执行的次数 参数。

分析`shared.monitor.schedule.AppMonitorDataAvgScheduler.run`实现逻辑和调用方式，以优化实现解决问题。

### 贡献者

- [silentforce](https://github.com/silentforce) 改进此脚本，增加对环境变量`JAVA_HOME`的判断。 [#15](https://github.com/oldratlee/useful-scripts/pull/15)
- [liuyangc3](https://github.com/liuyangc3)
    - 发现并解决`jstack`非当前用户`Java`进程的问题。 [#50](https://github.com/oldratlee/useful-scripts/pull/50)
    - 优化性能，通过`read -a`简化反复的`awk`操作。 [#51](https://github.com/oldratlee/useful-scripts/pull/51)
- [superhj1987](https://github.com/superhj1987) / [lirenzuo](https://github.com/lirenzuo)
    - 提出/实现了多次执行的功能 [superhj1987/awesome-scripts#1](https://github.com/superhj1987/awesome-scripts/issues/1)
- [xiongchen2012](https://github.com/xiongchen2012) 提出并解决了长用户名截断的Bug [#62](https://github.com/oldratlee/useful-scripts/pull/62)
- [qsLI](https://github.com/qsLI) / [sdslnmd](https://github.com/sdslnmd)
    - 发现并提交Issue：show-busy-java-threads支持top来获取cpu占用率，ps的cpu占用率非实时 [#67](https://github.com/oldratlee/useful-scripts/issues/67)
- [geekMessi](https://github.com/geekMessi)
    - 发现并提交Issue：在`top v3.2`下提取不正确的Bug [#71](https://github.com/oldratlee/useful-scripts/issues/71)
    - 发现并提交Issue：support command name jsvc to find java process [#72](https://github.com/oldratlee/useful-scripts/issues/72)

🍺 [show-duplicate-java-classes](../bin/show-duplicate-java-classes)
----------------------

找出`Java Lib`（`Java`库，即`Jar`文件）或`Class`目录（类目录）中的重复类。  
全系统支持（`Python 3`实现，安装`Python 3`即可），如`Linux`、`Mac`、`Windows`。

`Java`开发的一个麻烦的问题是`Jar`冲突（即多个版本的`Jar`），或者说重复类。会出`NoSuchMethod`等的问题，还不见得当时出问题。找出有重复类的`Jar`，可以防患未然。

### 用法

- 通过脚本参数 指定 `Libs`目录，查找目录下`Jar`文件，收集`Jar`文件中`Class`文件以分析重复类。可以指定多个`Libs`目录。
    - 缺省只会查找指定`Lib`目录下`Jar`文件，不会收集`Lib`目录的子目录下`Jar`文件。
        - 因为`Libs`目录一般不会用子目录再放`Jar`，也避免把去查找不期望的`Jar`文件。
        - 可以通过 `-L`选项 设置 收集`Lib`子目录下的`Jar`文件；这样可以简化`Lib`目录的设置，不需要指定完整的`Lib`目录路径。
    - 对于找到的`Jar`文件，缺省不会进一步收集包含在`Jar`文件中的`Jar`。
        - 即`FatJar`/`UberJar`的场景，随着像`SpringBoot`的广泛使用，`FatJar`/`UberJar`也比较常见。
        - 可以通过 `-J`选项 设置 收集包含在`Jar`文件中的`Jar`。
- 通过`-c`选项 指定 `Class`目录，直接收集这个目录下的`Class`文件以分析重复类。可以多次指定多个`Class`目录。

```bash
# 查找当前目录下所有Jar中的重复类
show-duplicate-java-classes

# 查找多个指定目录下所有Jar中的重复类
show-duplicate-java-classes path/to/lib_dir1 /path/to/lib_dir2
# 通过 -L 选项，收集子目录中的Jar文件
show-duplicate-java-classes -L path/to/lib_dir1
# 通过 -J 选项，收集包含在Jar文件中的Jar文件（即 收集包含在FatJar/UberJar中的Jar）
show-duplicate-java-classes -J path/to/lib_dir1

# 查找多个指定Class目录下的重复类。 Class目录 通过 -c 选项指定
show-duplicate-java-classes -c path/to/class_dir1 -c /path/to/class_dir2

# 查找指定Class目录和指定目录下所有Jar中的重复类的Jar
show-duplicate-java-classes path/to/lib_dir1 /path/to/lib_dir2 -c path/to/class_dir1 -c path/to/class_dir2

# 帮助信息
$ show-duplicate-java-classes -h
Usage: show-duplicate-java-classes [OPTION]... [-c class-dir1 [-c class-dir2] ...] [lib-dir1|jar-file1 [lib-dir2|jar-file2] ...]
Find duplicate classes among java lib dirs and class dirs.

Examples:
  show-duplicate-java-classes  # search jars from current dir
  show-duplicate-java-classes path/to/lib_dir1 /path/to/lib_dir2
  show-duplicate-java-classes -c path/to/class_dir1 -c /path/to/class_dir2
  show-duplicate-java-classes -c path/to/class_dir1 path/to/lib_dir1
  show-duplicate-java-classes -L path/to/lib_dir1
  show-duplicate-java-classes -J path/to/lib_dir1

Options:
  --version             show program's version number and exit
  -h, --help            show this help message and exit
  -L, --recursive-lib   search jars in the sub-directories of lib dir
  -J, --recursive-jar   search jars in the jar file
  -c CLASS_DIRS, --class-dir=CLASS_DIRS
                        add class dir
  -R, --no-find-progress
                        do not display responsive find progress
```

#### `JDK`开发场景使用说明

以`Maven`作为构建工程示意过程。

##### 对于一般的工程

```sh
# 在项目模块目录下执行，拷贝依赖Jar到目录target/dependency下
$ mvn dependency:copy-dependencies -DincludeScope=runtime
...
# 检查重复类
$ show-duplicate-java-classes target/dependency
...
```

##### 对于`Web`工程

对于`Web`工程，即`war` `maven`模块，会打包生成`war`文件。

```sh
# 在war模块目录下执行，生成war文件
$ mvn install
...
# 解压war文件，war文件中包含了应用的依赖的Jar文件
$ unzip target/*.war -d target/war
...
# 检查重复类
$ show-duplicate-java-classes -c target/war/WEB-INF/classes target/war/WEB-INF/lib
...
```

#### `Android`开发场景使用说明

`Android`开发，有重复类在编译打包时会报`[Dex Loader] Unable to execute dex: Multiple dex files define Lorg/foo/xxx/Yyy`。

但只会给出一个重复类名，如果重复类比较多时，上面打包/报错/排查会要进行多次，而`Android`的打包比较费时，这个过程比较麻烦，希望可以一次把所有重复类都列出来，一起排查掉。

以`Gradle`作为构建工程示意过程。

在`App`的`build.gradle`中添加拷贝库到目录`build/dependencies`下。

```groovy
task copyDependencies(type: Copy) {
    def dest = new File(buildDir, "dependencies")

    // clean dir
    dest.deleteDir()
    dest.mkdirs()

    // fill dir with dependencies
    from configurations.compile into dest
}
```

```sh
# 拷贝依赖
$ ./gradlew app:copyDependencies
...
# 检查重复类
$ show-duplicate-java-classes app/build/dependencies
...
```

### 示例

```bash
$ show-duplicate-java-classes WEB-INF/lib
COOL! No duplicate classes found!

================================================================================
Find in 150 class paths:
================================================================================
  1: (contain   9 classes) WEB-INF/lib/aopalliance-1.0.jar
  2: (contain  25 classes) WEB-INF/lib/asm-5.0.4.jar
  3: (contain 313 classes) WEB-INF/lib/aviator-5.0.0.jar
  4: (contain 687 classes) WEB-INF/lib/cassandra-0.6.1.jar
...

$ show-duplicate-java-classes -c WEB-INF/classes WEB-INF/lib
Found 1272 duplicate classes in 345 class paths and 9 class path sets:
[1] found 188(100%) duplicate classes in 3 class paths:
    1: (contain 188 classes) WEB-INF/lib/jdom-2.0.2.jar
    2: (contain 195 classes) WEB-INF/lib/jdom2-2.0.6.jar
    3: (contain 195 classes) WEB-INF/lib/jdom2-2.0.8.jar
[2] found 150(33.8%) duplicate classes in 2 class paths:
    1: (contain 1385 classes) WEB-INF/lib/netty-all-4.0.35.Final.jar
    2: (contain  444 classes) WEB-INF/lib/netty-common-4.1.31.Final.jar
[3] found 148(55.4%) duplicate classes in 2 class paths:
    1: (contain 1385 classes) WEB-INF/lib/netty-all-4.0.35.Final.jar
    2: (contain  267 classes) WEB-INF/lib/netty-handler-4.1.31.Final.jar
[4] found 103(82.4%) duplicate classes in 2 class paths:
    1: (contain 125 classes) WEB-INF/lib/hessian-3.0.14.bugfix.jar
    2: (contain 275 classes) WEB-INF/lib/hessian-4.0.38.jar
...

================================================================================
Duplicate classes detail info:
================================================================================
[1] found 188 duplicate classes in 3 class paths WEB-INF/lib/jdom-2.0.2.jar WEB-INF/lib/jdom2-2.0.6.jar WEB-INF/lib/jdom2-2.0.8.jar :
      1: org/jdom2/Attribute.class
      2: org/jdom2/AttributeList$1.class
      3: org/jdom2/AttributeList$ALIterator.class
      4: org/jdom2/AttributeList.class
      5: org/jdom2/AttributeType.class
      ...
[2] found 150 duplicate classes in 2 class paths WEB-INF/lib/netty-all-4.0.35.Final.jar WEB-INF/lib/netty-common-4.1.31.Final.jar :
      1: io/netty/util/AbstractReferenceCounted.class
      2: io/netty/util/Attribute.class
      3: io/netty/util/AttributeKey.class
      4: io/netty/util/AttributeMap.class
      5: io/netty/util/CharsetUtil.class
      ...
...

================================================================================
Find in 232 class paths:
================================================================================
  1: (contain  42 classes) WEB-INF/classes
  2: (contain  70 classes) WEB-INF/lib/HikariCP-2.7.8.jar
  3: (contain  13 classes) WEB-INF/lib/accessors-smart-1.2.jar
  4: (contain   9 classes) WEB-INF/lib/aopalliance-1.0.jar
  5: (contain  25 classes) WEB-INF/lib/asm-5.0.4.jar
  6: (contain 313 classes) WEB-INF/lib/aviator-5.0.0.jar
...
```

### 贡献者

[tgic](https://github.com/tg123) 提供此脚本。友情贡献者的链接 [commandlinefu.cn](http://commandlinefu.cn/) | [微博linux命令行精选](http://weibo.com/u/2674868673)

<a id="beer-find-in-jarssh"></a>
<a id="beer-find-in-jars"></a>

🍺 [find-in-jars](../bin/find-in-jars)
----------------------

在当前目录下所有`jar`文件里，查找类或资源文件。  
支持`Linux`、`Mac`、`Windows`（`cygwin`、`MSSYS`）。

### 用法

```bash
# 在当前目录下所有`jar`文件里，查找类或资源文件。
find-in-jars 'log4j\.properties'
find-in-jars 'log4j\.xml$'
find-in-jars log4j\\.xml$ # 和上面命令一样，Shell转义的不同写法而已
find-in-jars 'log4j\.(properties|xml)$'

# -d选项 指定 查找目录（覆盖缺省的当前目录）
find-in-jars 'log4j\.properties$' -d /path/to/find/directory
# 支持多个查找目录，多次指定这个选项即可
find-in-jars 'log4j\.properties' -d /path/to/find/directory1 -d /path/to/find/directory2

# -e选项 指定 查找`zip`文件的扩展名，缺省是`jar`
find-in-jars 'log4j\.properties' -e zip
# 支持多种查找扩展名，多次指定这个选项即可
find-in-jars 'log4j\.properties' -e jar -e zip

# -a选项 指定 查找结果中的Jar文件使用绝对路径
# 分享给别人时，Jar文件路径是完整的，方便别人找到文件
find-in-jars 'log4j\.properties' -a

# -s选项 指定 查找结果中的Jar文件和Jar文件里的查找Entry间分隔符，缺省是『!』
# 方便你喜欢的人眼查看，或是与工具脚本如`awk`的处理
find-in-jars 'log4j\.properties' -s ' <-> '
find-in-jars 'log4j\.properties' -s ' ' | awk '{print $2}'

# -l选项 指定 只列出Jar文件，不显示Jar文件内匹配的文件列表
# 列出 包含log4j.xml文件的Jar文件：
find-in-jars -l 'log4j\.xml$'

# 帮助信息
$ find-in-jars -h
Usage: find-in-jars [OPTION]... PATTERN

Find files in the jar files under specified directory,
search jar files recursively(include subdirectory).
The pattern default is *extended* regex.

Example:
  find-in-jars 'log4j\.properties'
  # search file log4j.properties/log4j.xml at zip root
  find-in-jars '^log4j\.(properties|xml)$'
  find-in-jars 'log4j\.properties$' -d /path/to/find/directory
  find-in-jars '\.properties$' -d /path/to/find/dir1 -d path/to/find/dir2
  find-in-jars 'Service\.class$' -e jar -e zip
  find-in-jars 'Mon[^$/]*Service\.class$' -s ' <-> '

Find control:
  -d, --dir              the directory that find jar files.
                         default is current directory. this option can specify
                         multiply times to find in multiply directories.
  -e, --extension        set find file extension, default is jar. this option
                         can specify multiply times to find multiply extension.
  -E, --extended-regexp  PATTERN is an extended regular expression (*default*)
  -F, --fixed-strings    PATTERN is a set of newline-separated strings
  -G, --basic-regexp     PATTERN is a basic regular expression
  -P, --perl-regexp      PATTERN is a Perl regular expression
  -i, --ignore-case      ignore case distinctions

Output control:
  -a, --absolute-path    always print absolute path of jar file
  -s, --separator        specify the separator between jar file and zip entry.
                         default is `!'.
  -L, --files-not-contained-found
                         print only names of JAR FILEs NOT contained found
  -l, --files-contained-found
                         print only names of JAR FILEs contained found
  -R, --no-find-progress do not display responsive find progress

Miscellaneous:
  -h, --help             display this help and exit
  -V, --version          display version information and exit
```

注意，Pattern缺省是`grep`的 **扩展**正则表达式。

### 示例

```bash
# 在当前目录下的所有Jar文件中，查找出 log4j.properties文件
$ find-in-jars 'log4j\.properties$'
./hadoop-core-0.20.2-cdh3u3.jar!log4j.properties
......

# 查找出 以Service结尾的类，Jar文件路径输出成绝对路径
$ find-in-jars 'Service.class$' -a
/home/foo/deploy/app/WEB-INF/libs/spring-2.5.6.SEC03.jar!org/springframework/stereotype/Service.class
/home/foo/deploy/app/WEB-INF/libs/rpc-hello-0.0.1-SNAPSHOT.jar!com/taobao/biz/HelloService.class
......

# 在指定的多个目录的Jar文件中，查找出 properties文件
$ find-in-jars '\.properties$' -d WEB-INF/lib -d ../deploy/lib | grep -v '/pom\.properties$'
WEB-INF/lib/aspectjtools-1.6.2.jar!org/aspectj/ajdt/ajc/messages.properties
WEB-INF/lib/aspectjweaver-1.8.8.jar!org/aspectj/weaver/XlintDefault.properties
../deploy/lib/groovy-all-1.1-rc-1.jar!groovy/ui/InteractiveShell.properties
../deploy/lib/httpcore-4.3.3.jar!org/apache/http/version.properties
../deploy/lib/javax.servlet-api-3.0.1.jar!javax/servlet/http/LocalStrings_es.properties
......

# 列出 包含properties文件的Jar文件
$ find-in-jars '\.properties$' -l -d WEB-INF/lib
WEB-INF/lib/aspectjtools-1.6.2.jar
WEB-INF/lib/aspectjweaver-1.8.8.jar
WEB-INF/lib/javax.servlet-api-3.0.1.jar
......
```

### 运行效果

支持彩色输出，文件名中的匹配部分以`grep`的高亮方式显示。

![find-in-jar screenshot](https://user-images.githubusercontent.com/1063891/33545067-9eb66072-d8a2-11e7-8a77-d815c0979e5e.gif)

### 参考资料

[在多个Jar(Zip)文件查找Log4J配置文件的Shell命令行](http://oldratlee.github.io/458/tech/shell/find-file-in-jar-zip-files.html)
