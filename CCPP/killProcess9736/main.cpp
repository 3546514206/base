#include <iostream>
#include <cstdlib>
#include <string>
#include <sstream>

#ifdef _WIN32
#include <windows.h>
#include <tlhelp32.h>
#else

#include <unistd.h>
#include <signal.h>

#endif

std::string executeCommand(const std::string &command) {
    std::string result;
    char buffer[128];
    FILE *pipe = popen(command.c_str(), "r");
    if (!pipe) {
        std::cerr << "Failed to run command." << std::endl;
        return "";
    }
    while (fgets(buffer, sizeof(buffer), pipe) != nullptr) {
        result += buffer;
    }
    pclose(pipe);
    return result;
}

#ifdef _WIN32
void killProcess(int pid) {
    HANDLE process = OpenProcess(PROCESS_TERMINATE, FALSE, pid);
    if (process != nullptr) {
        TerminateProcess(process, 0);
        CloseHandle(process);
        std::cout << "Process " << pid << " terminated successfully." << std::endl;
    } else {
        std::cerr << "Failed to terminate process " << pid << "." << std::endl;
    }
}
#else

void killProcess(int pid) {
    if (kill(pid, SIGTERM) == 0) {
        std::cout << "Process " << pid << " terminated successfully." << std::endl;
    } else {
        std::cerr << "Failed to terminate process " << pid << "." << std::endl;
    }
}

#endif

int main() {
    int targetPort = 9736;

#ifdef _WIN32
    std::string command = "netstat -ano | findstr :" + std::to_string(targetPort);
#else
    std::string command = "lsof -i :" + std::to_string(targetPort);
#endif

    // Execute the command to find the process using the target port
    std::string output = executeCommand(command);

    if (output.empty()) {
        std::cout << "No process is using port " << targetPort << "." << std::endl;
        return 0;
    }

#ifdef _WIN32
    // Parse PID from output on Windows
    std::istringstream iss(output);
    std::string line;
    int pid = -1;
    while (std::getline(iss, line)) {
        std::istringstream lineStream(line);
        std::string proto, localAddr, foreignAddr, state, pidStr;
        lineStream >> proto >> localAddr >> foreignAddr >> state >> pidStr;
        pid = std::stoi(pidStr);
        break;
    }
#else
    // Parse PID from output on Linux
    std::istringstream iss(output);
    std::string line;
    int pid = -1;
    while (std::getline(iss, line)) {
        std::istringstream lineStream(line);
        std::string command, pidStr;
        lineStream >> command >> pidStr;
        pid = std::stoi(pidStr);
        break;
    }
#endif

    if (pid != -1) {
        std::cout << "Process " << pid << " is using port " << targetPort << "." << std::endl;
        killProcess(pid);
    } else {
        std::cout << "Failed to parse PID for port " << targetPort << "." << std::endl;
    }

    return 0;
}
