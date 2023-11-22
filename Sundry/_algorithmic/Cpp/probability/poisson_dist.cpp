/**
 * @file
 * @brief [Poisson
 * statistics](https://en.wikipedia.org/wiki/Poisson_distribution)
 *
 * The Poisson distribution counts how many
 * events occur over a set time interval.
 */
#include <cmath>
#include <iostream>

/**
 * poisson rate:\n
 * calculate the events per unit time\n
 * e.g 5 dollars every 2 mins = 5 / 2 = 2.5
 */
double poisson_rate(double events, double timeframe) {
    return events / timeframe;
}

/**
 *  calculate the expected value over a time
 * e.g rate of 2.5 over 10 mins = 2.5 x 10 = 25
 */
double poisson_expected(double rate, double time) { return rate * time; }

/**
 * Compute factorial of a given number
 */
double fact(double x) {
    double x_fact = x;
    for (int i = x - 1; i > 0; i--) {
        x_fact *= i;
    }

    if (x_fact <= 0) {
        x_fact = 1;
    }
    return x_fact;
}

/**
 * Find the probability of x successes in a Poisson dist.
 * \f[p(\mu,x) = \frac{\mu^x e^{-\mu}}{x!}\f]
 */
double poisson_x_successes(double expected, double x) {
    return (std::pow(expected, x) * std::exp(-expected)) / fact(x);
}

/**
 * probability of a success in range for Poisson dist (inclusive, inclusive)
 * \f[P = \sum_i p(\mu,i)\f]
 */
double poisson_range_successes(double expected, double lower, double upper) {
    double probability = 0;
    for (int i = lower; i <= upper; i++) {
        probability += poisson_x_successes(expected, i);
    }
    return probability;
}

/**
 * main function
 */
int main() {
    double rate, expected;
    rate = poisson_rate(3, 1);
    std::cout << "Poisson rate : " << rate << std::endl;

    expected = poisson_expected(rate, 2);
    std::cout << "Poisson expected : " << expected << std::endl;

    std::cout << "Poisson 0 successes : " << poisson_x_successes(expected, 0)
              << std::endl;
    std::cout << "Poisson 0-8 successes : "
              << poisson_range_successes(expected, 0, 8) << std::endl;

    return 0;
}
