#include <vector>
#include <map>

using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int> &nums, int target) {
        map<int, int> a;
        vector<int> rz(2, -1);
        for (int i = 0; i < nums.size(); i++)
            a.insert(map<int, int>::value_type(nums[i], i));
        for (int i = 0; i < nums.size(); i++) {
            if (a.count(target - nums[i]) > 0 && (a[target - nums[i]] != i)) {
                rz[0] = i;
                rz[1] = a[target - nums[i]];
                break;
            }
        }
        return rz;

    }
};

int main() {
    vector<int> nums;
    nums.push_back(2);
    nums.push_back(7);
    nums.push_back(11);
    nums.push_back(15);

    auto *solution = new Solution;
    vector<int> rz = solution->twoSum(nums, 9);

    return 0;
}
