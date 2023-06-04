#include <iostream>
#include <stdexcept>

class DivideByZeroException : public std::runtime_error {
public:
    // 在这个特定的构造函数中，它继承自std::runtime_error的构造函数，
    // 并通过调用基类std::runtime_error的构造函数来设置异常对象的错误消息为"Divide by zero exception"。
    DivideByZeroException() : std::runtime_error("Divide by zero exception") {}
};

double divide(double a, double b) {
    if (b == 0.0) {
        throw DivideByZeroException();
    }
    return a / b;
}

double calculateAverage(const double *const numbers, int size) {
    if (numbers == nullptr || size == 0) {
        throw std::invalid_argument("Invalid arguments");
    }

    double sum = 0.0;
    for (int i = 0; i < size; i++) {
        sum += numbers[i];
    }
    return divide(sum, size);
}

int main() {
    int size;
    std::cout << "Enter the number of elements: ";
    std::cin >> size;

    try {
        auto *numbers = new double[size];
        std::cout << "Enter the numbers: ";
        for (int i = 0; i < size; i++) {
            std::cin >> numbers[i];
        }

        double average = calculateAverage(numbers, size);
        std::cout << "Average: " << average << std::endl;

        delete[] numbers;
    } catch (const DivideByZeroException &ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
    } catch (const std::exception &ex) {
        std::cerr << "Error: " << ex.what() << std::endl;
    } catch (...) {
        std::cerr << "Unknown error occurred" << std::endl;
    }

    return 0;
}
