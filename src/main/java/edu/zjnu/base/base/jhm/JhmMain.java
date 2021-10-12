package edu.zjnu.base.base.jhm;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @description: JHM性能测试
 * @author: 杨海波
 * @date: 2021-10-12
 **/
public class JhmMain {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FilterPerf.class.getName()) // 指定需要执行的文件或者匹配的文件
                .forks(1) // 启动1个进程来执行测试
                .build();

        new Runner(opt).run();
    }
}
