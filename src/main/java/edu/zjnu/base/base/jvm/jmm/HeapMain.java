package edu.zjnu.base.base.jvm.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 堆区测试
 * @author: 杨海波
 * @date: 2021-09-14
 **/
public class HeapMain {

    public static void main(String[] args) {
        List<HeapObject> objects = new ArrayList<>();
        // 所有的 HeapObject 都被 objects 引用，不会被回收
        while (true) {
            //1B
            objects.add(new HeapObject(new byte[1]));
        }
    }


}

class HeapObject {

    private byte[] a;

    public HeapObject(byte[] a) {
        this.a = a;
    }
}