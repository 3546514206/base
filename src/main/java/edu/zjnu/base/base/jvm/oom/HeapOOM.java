package edu.zjnu.base.base.jvm.oom;

/**
 * @author: 杨海波
 * @date: 2022-12-30 16:56:07
 * @description: todo
 */

import java.util.ArrayList;
import java.util.List;

/**
 * VM参数： -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}

