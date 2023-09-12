package edu.zjnu.base.base.objectlayout;

import edu.zjnu.base.base.LogInterFace;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: JOL分析对象布局
 * @author: 杨海波
 * @date: 2021-07-23
 **/
public class JOL implements LogInterFace {

    public static void main(String[] args) {
        log.info(VM.current().details());
        log.info(args[0]);

        // 分析外部引用情况
        Map<String, String> map = new HashMap();
        map.put("flydean", "www.flydean.com");
        log.info(GraphLayout.parseInstance(map).toPrintable());

    }
}
