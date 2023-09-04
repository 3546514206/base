#include <iostream>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

int main() {
    int client_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (client_fd == -1) {
        perror("Socket creation failed");
        return 1;
    }

    sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(12345); // 服务器端口号
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1"); // 服务器 IP 地址

    if (connect(client_fd, (struct sockaddr*)&server_addr, sizeof(server_addr)) == -1) {
        perror("Connection failed");
        return 1;
    }

    std::cout << "Connected to server" << std::endl;

    while (true) {
        std::string message;
        std::cout << "Enter a message to send (or 'q' to quit): ";
        std::cin >> message;

        if (message == "q") {
            break;
        }

        send(client_fd, message.c_str(), message.length(), 0);
    }

    close(client_fd);
    return 0;
}

