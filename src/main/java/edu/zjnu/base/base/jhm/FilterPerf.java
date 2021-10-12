package edu.zjnu.base.base.jhm;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-12
 **/
@State(Scope.Thread)
@Warmup(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FilterPerf {

    private Integer[] collections = new Integer[1000 * 1000];

    @Setup
    public void InitCollection() {
        for (int i = 0; i < collections.length; i++) {
            collections[i] = i;
        }
    }

    @Benchmark
    public void measureStreamFilterOneByOne() {
        Arrays.stream(collections)
                .filter(i -> i % 2 == 0)
                .filter(i -> i % 5 == 0)
                .filter(i -> i % 3 == 0)
                .toArray();
    }

    @Benchmark
    public void measureStreamFilterOnlyOne() {
        Arrays.stream(collections).filter(i -> i % 5 == 0 && i % 2 == 0 && i % 3 == 0).toArray();
    }
}