#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
    int searchInsert(vector<int> &nums, int target);
};

int Solution::searchInsert(vector<int> &nums, int target)
{
    int l = 0;
    int r = nums.size() - 1;

    int ans = nums.size();

    while (l <= r)
    {
        int mid = l + (r - l) / 2;
        if (target <= nums[mid])
        {
            ans = mid;
            r = mid - 1;
        }
        else
        {
            l = mid + 1;
        }
    }

    return ans;
}

int main()
{
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
