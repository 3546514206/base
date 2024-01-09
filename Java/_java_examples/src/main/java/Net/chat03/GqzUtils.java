package com.gqz.chat03;

import java.io.Closeable;

/**
 * 工具类
 *
 * @author ganquanzhong
 * @ClassName: GqzUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午4:25:10
 */
public class GqzUtils {
    /*
     * 释放资源
     */

    public static void close(Closeable... targets) {
        for (Closeable target : targets) {
            try {
                if (null != target) {
                    target.close();
                }
            } catch (Exception e) {
            }
        }
    }

}
