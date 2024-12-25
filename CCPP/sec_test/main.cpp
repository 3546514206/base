#include <iostream>
#include <thread>
#include <vector>
#include <string>
#include <cstdlib> // 用于 system()

std::mutex outputMutex;

// 执行 ping 命令并判断目标是否在线
bool ping(const std::string& ip) {
    std::string command = "ping -c 1 -w 1 " + ip + " > /dev/null 2>&1"; // Linux/MacOS
    // std::string command = "ping -n 1 " + ip + " > nul 2>&1"; // Windows
    return system(command.c_str()) == 0;
}

// 扫描单个 IP，并记录在线设备
void scanIP(const std::string& ip, std::vector<std::string>& activeDevices) {
    if (ping(ip)) {
        std::lock_guard<std::mutex> lock(outputMutex);
        activeDevices.push_back(ip);
        std::cout << "[+] Active: " << ip << std::endl;
    }
}

int main() {
    std::string baseIP = "92.168.0."; // 修改为实际内网的子网 IP
    int start = 1, end = 254; // 通常扫描范围
    std::vector<std::string> activeDevices;
    std::vector<std::thread> threads;

    std::cout << "Scanning local network...\n";

    for (int i = start; i <= end; ++i) {
        std::string ip = baseIP + std::to_string(i);
        threads.emplace_back(scanIP, ip, std::ref(activeDevices));
    }

    for (auto& t : threads) {
        if (t.joinable()) {
            t.join();
        }
    }

    std::cout << "\nScan complete. Active devices:\n";
    for (const auto& device : activeDevices) {
        std::cout << "- " << device << std::endl;
    }

    return 0;
}
