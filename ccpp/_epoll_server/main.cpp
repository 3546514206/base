#include <iostream>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <sys/event.h>

int main() {
    // 创建监听 socket
    int server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd == -1) {
        perror("Socket creation failed");
        return 1;
    }

    sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(12345); // 选择一个空闲端口
    server_addr.sin_addr.s_addr = INADDR_ANY;

    // 绑定地址
    if (bind(server_fd, (struct sockaddr *) &server_addr, sizeof(server_addr)) == -1) {
        perror("Binding failed");
        return 1;
    }

    // 开始监听
    if (listen(server_fd, 5) == -1) {
        perror("Listening failed");
        return 1;
    }

    std::cout << "Server is listening on port 12345..." << std::endl;

    // 创建 kqueue
    int kq = kqueue();
    if (kq == -1) {
        perror("Kqueue creation failed");
        return 1;
    }

    struct kevent event;
    struct kevent change_list[1];

    // 监听 server_fd
    EV_SET(&event, server_fd, EVFILT_READ, EV_ADD, 0, 0, NULL);
    if (kevent(kq, change_list, 1, NULL, 0, NULL) == -1) {
        perror("Error adding server_fd to kqueue");
        return 1;
    }

    while (true) {
        struct kevent triggered_events[32];
        int event_count = kevent(kq, NULL, 0, triggered_events, 32, NULL);
        if (event_count == -1) {
            perror("Error in kevent");
            return 1;
        }

        for (int i = 0; i < event_count; ++i) {
            int fd = triggered_events[i].ident;

            if (fd == server_fd) {
                // 有新的客户端连接
                struct sockaddr_in client_addr;
                socklen_t client_len = sizeof(client_addr);
                int client_socket = accept(server_fd, (struct sockaddr *) &client_addr, &client_len);
                if (client_socket == -1) {
                    perror("Accept failed");
                } else {
                    std::cout << "New client connected" << std::endl;
                    EV_SET(&event, client_socket, EVFILT_READ, EV_ADD, 0, 0, NULL);
                    kevent(kq, change_list, 1, NULL, 0, NULL);
                }
            } else {
                // 有数据可读
                char buffer[1024];
                int bytes_read = recv(fd, buffer, sizeof(buffer), 0);
                if (bytes_read <= 0) {
                    std::cout << "Client disconnected" << std::endl;
                    close(fd);
                } else {
                    std::cout << "Received: " << std::string(buffer, bytes_read) << std::endl;
                }
            }
        }
    }

    return 0;
}
