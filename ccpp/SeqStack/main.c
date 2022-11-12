#include <stdio.h>

#define MaxSize 20

typedef struct {
    int data;
}ElemType;

typedef struct {
    ElemType elems[MaxSize];
    int top;
}SeqStack;

int main() {
    SeqStack stack;

    for (int i = 0; i < 80; ++i) {
        stack.elems[i].data = 0;
    }

    printf("length=%lu",sizeof (stack));
}
