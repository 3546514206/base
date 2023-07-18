#include <iostream>

#include <iostream>

enum Color {
    RED,     // 0
    GREEN,   // 1
    BLUE     // 2
};

int main() {
    Color color = GREEN;

    if (color == GREEN) {
        std::cout << "The color is green." << std::endl;
    }

    return 0;
}
