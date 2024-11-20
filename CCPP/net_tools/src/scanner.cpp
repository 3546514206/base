#include "scanner.h"
#include <iostream>
#include <boost/asio.hpp>
#include <boost/process.hpp>
#include <sstream>

#ifdef _WIN32
#include <winsock2.h>
#include <windows.h>
#else

#endif

Scanner::Scanner() {
    initialize();
}

Scanner::~Scanner() {
    cleanup();
}

void Scanner::initialize() {
#ifdef _WIN32
    // Initialize Winsock for Windows
    WSADATA wsaData;
    WSAStartup(MAKEWORD(2, 2), &wsaData);
#endif
}

void Scanner::cleanup() {
#ifdef _WIN32
    // Cleanup Winsock for Windows
    WSACleanup();
#endif
}

bool Scanner::ping(const std::string &ip, int timeout) {
    std::ostringstream command;

#ifdef _WIN32
    command << "ping -n 1 -w " << timeout << " " << ip;
#else
    command << "ping -c 1 -W " << timeout << " " << ip;
#endif

    boost::process::ipstream outStream; // Capture process output
    boost::process::child process(command.str(), boost::process::std_out > outStream);

    process.wait();
    return process.exit_code() == 0;
}

std::vector<std::string> Scanner::scanNetwork(const std::string &subnet, int timeout) {
    std::vector<std::string> devices;

    for (int i = 1; i <= 254; ++i) {
        std::ostringstream ip;
        ip << subnet << "." << i;

        if (ping(ip.str(), timeout)) {
            devices.push_back(ip.str());
        }
    }

    return devices;
}

void scan_subnet() {
    Scanner scanner;

    std::string subnet;
    int timeout;

    std::cout << "Enter subnet (e.g., 192.168.1): ";
    std::cin >> subnet;

    std::cout << "Enter timeout in milliseconds (default 1000): ";
    std::cin >> timeout;

    std::cout << "Scanning subnet: " << subnet << "..." << std::endl;

    auto devices = scanner.scanNetwork(subnet, timeout);

    std::cout << "Active devices found:" << std::endl;
    for (const auto &device: devices) {
        std::cout << device << std::endl;
    }
}
