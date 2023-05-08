#include <iostream>

class Line {
public:
    double length;

    void setLength(double len);

    double getLength(void);

private:
    double cray;
};

void Line::setLength(double len) {
    this->length = len;
}

double Line::getLength(void) {
    return this->length;
}

int main() {

    Line line;

    line.setLength(6.0);
    std::cout << "Length of line:" << line.getLength() << std::endl;

    line.length = 10.0;

    std::cout << "Length of line:" << line.getLength() << std::endl;

    Line *linePtr = &line;

    linePtr->length = 20.0;
    std::cout << "Length of line:" << linePtr->length << std::endl;


    return 0;
}
