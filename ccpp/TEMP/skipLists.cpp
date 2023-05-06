#include <iostream>   // 输入输出流
#include <vector>     // 动态数组
#include <cstdlib>    // rand(), srand()
#include <ctime>      // time()
#include <climits>    // INT_MIN

using namespace std;



struct Node 
{
    int value;
    vector<Node*> next;
    Node(int val, int level) 
    {
        value = val;
        next.resize(level);
    }
};


class SkipList 
{
public:
    SkipList() 
    {
        head = new Node(INT_MIN, MAX_LEVEL);
    }
  
    void insert(int val) 
	{
        int level = randomLevel();
        Node* newNode = new Node(val, level);
        Node* cur = head;
        vector<Node*> update(level + 1, nullptr);
        for (int i = level; i >= 0; --i)
        {
            while (cur->next[i] && cur->next[i]->value < val) 
			{
                cur = cur->next[i];
            }
            update[i] = cur;
        }
        for (int i = 0; i <= level; ++i) 
		{
            newNode->next[i] = update[i]->next[i];
            update[i]->next[i] = newNode;
        }
    }

    bool search(int target) 
	{
        Node* cur = head;
        for (int i = MAX_LEVEL; i >= 0; --i) 
		{
            while (cur->next[i] && cur->next[i]->value < target) 
			{
                cur = cur->next[i];
            }
        }
        return cur->next[0] && cur->next[0]->value == target;
    }

    void del(int val) 
	{
        Node* cur = head;
        vector<Node*> update(MAX_LEVEL + 1, nullptr);
        for (int i = MAX_LEVEL; i >= 0; --i) 
		{
            while (cur->next[i] && cur->next[i]->value < val) 
			{
                cur = cur->next[i];
            }
            update[i] = cur;
        }
        if (!cur->next[0] || cur->next[0]->value != val) 
		{
            return;
        }
        Node* nodeToDelete = cur->next[0];
        for (int i = nodeToDelete->next.size() - 1; i >= 0; --i) 
		{
            if (update[i]->next[i] != nodeToDelete) 
			{
                break;
            }
            update[i]->next[i] = nodeToDelete->next[i];
        }
        delete nodeToDelete;
    }
    
private:
    int randomLevel() 
	{
        int level = 0;
        while (rand() % 2 == 0 && level < MAX_LEVEL) 
		{
            level++;
        }
        return level;
    }
  
    Node* head;
    const int MAX_LEVEL = 15;
};


int main() 
{
    SkipList skipList;
    skipList.insert(10);
    skipList.insert(7);
    skipList.insert(20);
    skipList.insert(8);
    cout << skipList.search(7) << endl;
    cout << skipList.search(9) << endl;
    skipList.del(7);
    cout << skipList.search(7) << endl;
    return 0;
}
