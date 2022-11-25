#include <iostream>
#include <fstream>

int main() {

    char data[100];

    std::ofstream file;
    file.open("./o.txt", std::ios::app);
    if (file.is_open()) {
        std::cout << "Writing to the file" << std::endl;
        std::cout << "Enter your name: " << std::endl;
        std::cin.getline(data, 100);
        file << data << std::endl;

        file.close();
    }


    return 0;
}
