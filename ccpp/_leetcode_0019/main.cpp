#include <iostream>

using namespace std;

struct ListNode {
    int val;

    ListNode *next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int x) : val(x), next(nullptr) {}

    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode *removeNthFromEnd(ListNode *head, int n);

    ListNode *removeNthFromEndV2(ListNode *head, int n);

private:
    int getLength(ListNode *head);
};

int Solution::getLength(ListNode *head) {
    int length = 0;
    while (head) {
        ++length;
        head = head->next;
    }


    return length;
}

ListNode *Solution::removeNthFromEnd(ListNode *head, int n) {
    ListNode *dummy = new ListNode(-1, head);

    int length = getLength(head);
    ListNode *cur = dummy;
    for (int i = 1; i < length - n + 1; ++i) {
        cur = cur->next;
    }


    return dummy->next;
}

ListNode *Solution::removeNthFromEndV2(ListNode *head, int n) {}

int main() {


    return 0;
}
