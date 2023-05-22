#include <iostream>
#include <thread>
#include <tuple>

struct Data {
    int x;
    int y;
    std::string name;
};

void printData(const Data& data) {
    std::cout << "Name: " << data.name << ", x: " << data.x << ", y: " << data.y << std::endl;
}

int main() {
    Data data = { 1, 2, "John" };
    std::thread t(printData, std::ref(data));
    t.join();
    return 0;
}
