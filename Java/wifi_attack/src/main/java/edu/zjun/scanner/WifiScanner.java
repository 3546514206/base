package edu.zjun.scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description：WifiScanner
 * @Author：杨海波
 * @Date：2025-02-24-21:10
 */
public class WifiScanner {

    public List<String> scanWifiNetworks()
            throws IOException, InterruptedException {
        List<String> results = new ArrayList<>();
        String os = System.getProperty("os.name").toLowerCase();
        Process process;

        if (os.contains("win")) {
            process = Runtime.getRuntime()
                    .exec("netsh wlan show networks mode=bssid");
        } else if (os.contains("nix") || os.contains("nux")) {
            process = Runtime.getRuntime()
                    .exec("nmcli dev wifi list");
        } else {
            throw new UnsupportedOperationException("暂不支持此操作系统");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("SSID") || line.contains("BSSID")) { // Windows
                    results.add(line.trim());
                } else if (line.matches("^\\*?\\s+\\w+.*")) { // Linux
                    results.add(line.trim());
                }
            }
        }
        process.waitFor();
        return results;
    }
}
