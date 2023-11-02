#include <stdio.h>
#include <algorithm>
#include <vector>
#include <iostream>
using namespace std;
typedef struct rect
{
    int id;
    int length;
    int width;

    // 对于向量元素是结构体的，可在结构体内部定义比较函数，下面按照id,length,width升序排序。
    bool operator<(const rect &a) const
    {
        if (id != a.id)
            return id < a.id;
        else
        {
            if (length != a.length)
                return length < a.length;
            else
                return width < a.width;
        }
    }
} Rect;

int main()
{
    vector<Rect> vec;
    Rect rect;
    rect.id = 1;
    rect.length = 2;
    rect.width = 3;
    vec.push_back(rect);
    vector<Rect>::iterator it = vec.begin();
    cout << (*it).id << ' ' << (*it).length << ' ' << (*it).width << endl;
    return 0;
}