#include <iostream>

class fraction {
public:
    fraction(double num, double den = 1) : m_numerator(num), m_denominator(den) {

    }

    fraction operator+(const fraction &f) {
        this->m_numerator = this->m_numerator + f.m_numerator;
        this->m_denominator = this->m_denominator + f.m_denominator;
        return *this;
    }

    double print() {
        return m_denominator / m_numerator;
    }

private:
    double m_numerator;
    double m_denominator;
};


int main() {

    fraction f1(2, 1);
    fraction f2 = f1 + 4;

    std::cout << f2.print() << std::endl;

    return 0;
}
