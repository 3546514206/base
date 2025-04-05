package edu.zjun;

import edu.zjun.scanner.WifiScanner;

import java.io.IOException;
import java.util.List;

/**
 * @Description：扫描 wifi
 * @Author：杨海波
 * @Date：2025-02-24-20:56
 */
public class App {

    public static void main(String[] args) {
        try {
            List<String> wifiList = new WifiScanner().scanWifiNetworks();
            System.out.println("发现 " + wifiList.size() + " 个WiFi网络：");
            wifiList.forEach(System.out::println);
        } catch (IOException | InterruptedException e) {
            System.err.println("扫描失败: " + e.getMessage());
        }
    }

}
