package com.java8.builtin;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author landyl
 * @create 4:21 PM 03/02/2018
 * As mentioned above streams can be either sequential or parallel.
 * Operations on sequential streams are performed on a single thread while operations on parallel streams are performed concurrent on multiple threads.
 */
public class ParallelStreamsTest {

    public static void main(String[] args) {
        // The following example demonstrates how easy it is to increase the performance by using parallel streams.

        // First we create a large list of unique elements:

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // Now we measure the time it takes to sort a stream of this collection.

        // Sequential Sort

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        // sequential sort took: 899 ms

        // Parallel Sort

        long t00 = System.nanoTime();

        long count1 = values.parallelStream().sorted().count();
        System.out.println(count1);

        long t11 = System.nanoTime();

        long millis1 = TimeUnit.NANOSECONDS.toMillis(t11 - t00);
        System.out.println(String.format("parallel sort took: %d ms", millis1));

        // parallel sort took: 472 ms

        // As you can see both code snippets are almost identical but the parallel sort is roughly 50% faster.
        // All you have to do is change stream() to parallelStream().

        // As already mentioned maps don't support streams.
        // Instead maps now support various new and useful methods for doing common tasks.

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        // The above code should be self-explaining: putIfAbsent prevents us from writing additional if null checks;
        // forEach accepts a consumer to perform operations for each value of the map.

        // This example shows how to compute code on the map by utilizing functions:

        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33

        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null

        map.getOrDefault(42, "not found");  // not found

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat

    }

}
