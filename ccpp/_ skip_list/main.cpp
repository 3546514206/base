#include <iostream>
#include <cstdlib>
#include <ctime>
#include <climits>

using namespace std;

// 最大层数
const int MAX_LEVEL = 10;

// 跳表节点
struct SkipNode
{
    int key;
    int value;
    // 每层的指针数组
    SkipNode **forward;

    SkipNode(int level, int key, int value)
    {
        this->key = key;
        this->value = value;
        forward = new SkipNode *[level + 1];
        memset(forward, 0, sizeof(SkipNode *) * (level + 1));
    }

    ~SkipNode()
    {
        delete[] forward;
    }
};

// 跳表
class SkipList
{
public:
    SkipList();

    ~SkipList();

    void insert(int key, int value);

    void remove(int key);

    int search(int key);

    void display();

private:
    int randomLevel();

    SkipNode *createNode(int level, int key, int value);

private:
    SkipNode *header;

    int level;
};

SkipList::SkipList()
{
    header = new SkipNode(MAX_LEVEL, INT_MIN, 0);
    level = 0;
}

SkipList::~SkipList()
{
    SkipNode *current = header->forward[0];
    while (current)
    {
        SkipNode *next = current->forward[0];
        delete current;
        current = next;
    }
    delete header;
}

int SkipList::randomLevel()
{
    int level = 0;

    while (rand() % 2 == 0 && level < MAX_LEVEL)
    {
        level++;
    }

    return level;
}

SkipNode *SkipList::createNode(int level, int key, int value)
{
    SkipNode *newNode = new SkipNode(level, key, value);
    return newNode;
}

void SkipList::insert(int key, int value)
{
    SkipNode *update[MAX_LEVEL + 1];
    memset(update, 0, sizeof(SkipNode *) * (MAX_LEVEL + 1));

    SkipNode *current = header;
    for (int i = level; i >= 0; i--)
    {
        while (current->forward[i] && current->forward[i]->key < key)
        {
            current = current->forward[i];
        }
        update[i] = current;
    }
    current = current->forward[0];

    if (current && current->key == key)
    {
        current->value = value;
    }
    else
    {
        int newLevel = randomLevel();
        if (newLevel > level)
        {
            for (int i = level + 1; i <= newLevel; i++)
            {
                update[i] = header;
            }
            level = newLevel;
        }

        SkipNode *newNode = createNode(newLevel, key, value);
        for (int i = 0; i <= newLevel; i++)
        {
            newNode->forward[i] = update[i]->forward[i];
            update[i]->forward[i] = newNode;
        }
    }
}

void SkipList::remove(int key)
{
    SkipNode *update[MAX_LEVEL + 1];
    memset(update, 0, sizeof(SkipNode *) * (MAX_LEVEL + 1));

    SkipNode *current = header;
    for (int i = level; i >= 0; i--)
    {
        while (current->forward[i] && current->forward[i]->key < key)
        {
            current = current->forward[i];
        }
        update[i] = current;
    }
    current = current->forward[0];

    if (current && current->key == key)
    {
        for (int i = 0; i <= level; i++)
        {
            if (update[i]->forward[i] != current)
            {
                break;
            }
            update[i]->forward[i] = current->forward[i];
        }
        delete current;

        while (level > 0 && header->forward[level] == nullptr)
        {
            level--;
        }
    }
}

int SkipList::search(int key)
{
    SkipNode *current = header;
    for (int i = level; i >= 0; i--)
    {
        while (current->forward[i] && current->forward[i]->key <= key)
        {
            if (current->forward[i]->key == key)
            {
                return current->forward[i]->value;
            }
            current = current->forward[i];
        }
    }
    return -1;
}

void SkipList::display()
{
    for (int i = level; i >= 0; i--)
    {
        SkipNode *current = header->forward[i];
        cout << "Level " << i << ": ";
        while (current)
        {
            cout << current->key << " ";
            current = current->forward[i];
        }
        cout << endl;
    }
}

int main()
{
    // 设置随机种子
    srand(time(0));

    SkipList skipList;
    skipList.insert(3, 10);
    skipList.insert(7, 25);
    skipList.insert(2, 15);
    skipList.insert(5, 30);
    skipList.insert(1, 20);

    cout << "Initial Skip List:" << endl;
    skipList.display();

    cout << "Value for key 5: " << skipList.search(5) << endl;
    cout << "Value for key 10: " << skipList.search(10) << endl;

    skipList.remove(3);
    skipList.remove(7);

    cout << "Skip List after deletion:" << endl;
    skipList.display();

    return 0;
}
