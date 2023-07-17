#include <iostream>
#include <string>
#include <unordered_map>
#include <stack>

using namespace std;

class Solution {
public:
    bool isValid(string s);
};

bool Solution::isValid(string s) {
    int n = s.size();
    if (n % 2 == 1) {
        return false;
    }

    unordered_map<char, char> pairs = {
            {')', '('},
            {']', '['},
            {'}', '{'}};

    stack<char> stak;
    for (char ch: s) {
        if (pairs.count(ch)) {
            if (stak.empty() || stak.top() != pairs[ch]) {
                return false;
            }

            stak.pop();
        } else {
            stak.push(ch);
        }
    }

    return stak.empty();
}

int main() {
    return 0;
}
