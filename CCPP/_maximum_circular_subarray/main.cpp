/**
* @file
* @brief C++ program for maximum contiguous circular sum problem using [Kadane's Algorithm](https://en.wikipedia.org/wiki/Maximum_subarray_problem)
* @details
* The idea is to modify Kadane’s algorithm to find a minimum contiguous subarray sum and the maximum contiguous subarray sum,
* then check for the maximum value between the max_value and the value left after subtracting min_value from the total sum.
* For more information, check [Geeks For Geeks](https://www.geeksforgeeks.org/maximum-contiguous-circular-sum/) explanation page.
*/

#include <cassert>     /// for assert
#include <iostream>   /// for IO operations
#include <vector>     /// for std::vector


/**
 * @namespace dynamic_programming
 * @brief Dynamic Programming algorithms
 */
namespace dynamic_programming {

    int maxCircularSum(std::vector<int> &arr) {
        if (arr.size() == 1)
            return arr[0];

        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr[i];
        }

        int current_max = arr[0], max_so_far = arr[0], current_min = arr[0], min_so_far = arr[0];

        for (int i = 1; i < arr.size(); i++) {

            current_max = std::max(current_max + arr[i], arr[i]);
            max_so_far = std::max(max_so_far, current_max);


            current_min = std::min(current_min + arr[i], arr[i]);
            min_so_far = std::min(min_so_far, current_min);
        }

        if (min_so_far == sum)
            return max_so_far;

        return std::max(max_so_far, sum - min_so_far);
    }
}

/**
 * @brief
 * @returns
 */
static void test() {


    int n = 7; // size of the array
    std::vector<int> arr = {8, -8, 9, -9, 10, -11, 12};
    assert(dynamic_programming::maxCircularSum(arr) == 22);

    arr = {8, -8, 10, -9, 10, -11, 12};
    assert(dynamic_programming::maxCircularSum(arr) == 23);

    std::cout << "All tests have successfully passed!\n";
}

/**
 * @brief
 * @returns
 */
int main(int argc, char *argv[]) {
    test();  // run self-test implementations
    return 0;
}