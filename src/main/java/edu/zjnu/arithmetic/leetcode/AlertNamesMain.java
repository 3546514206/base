package edu.zjnu.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 杨海波
 * @date: 2023-02-07 10:59:06
 * @description: todo
 */
public class AlertNamesMain {

    public static void main(String[] args) {
        String[] keyName = {"john", "john", "john"};
        String[] keyTime = {"23:58", "23:59", "00:01"};
        List<String> result = new AlertNamesMain().alertNames(keyName, keyTime);
        System.out.println(result);
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<String>> recordsMap = new HashMap<>();

        for (int i = 0; i < keyName.length; i++) {
            if (!recordsMap.containsKey(keyName[i])) {
                List<String> records = new ArrayList<>();
                records.add(keyTime[i]);
                recordsMap.put(keyName[i], records);
                continue;
            }

            recordsMap.get(keyName[i]).add(keyTime[i]);
        }

        List<String> result = new ArrayList<>();
        for (String name : recordsMap.keySet()) {
            if (judge(recordsMap.get(name))) {
                result.add(name);
            }
        }

        result.sort(String::compareTo);

        return result;
    }

    private boolean judge(List<String> strings) {
        for (int i = 2; i < strings.size(); i++) {
            Long t1 = convert(strings.get(i));
            long t2 = convert(strings.get(i - 2));
            if (t1 - t2 <= 60) {
                return true;
            }
        }

        return false;
    }


    public Long convert(String time) {
        String[] t = time.split(":");
        return Long.parseLong(t[0]) * 60 + Long.parseLong(t[1]);
    }
}
