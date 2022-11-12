#include <stdio.h>

#define MaxSize 10

struct Node {
    int data;
    int next;
};

typedef struct {    // 静态链表结构类型的定义
    int data;   // 数据元素
    int next;   //  下一个元素的数组下标
} StaticLinkList[MaxSize];

int main() {
    struct Node x;
    printf("sizeX=%lu\n", sizeof(x));

    struct Node a[MaxSize];
    printf("sizeX=%lu\n", sizeof(a));

    StaticLinkList b;
    printf("sizeX=%lu\n", sizeof(b));
}
