#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define MaxSize 20

typedef struct {
    int data;
} ElemType;

typedef struct {
    ElemType elems[MaxSize];
    int top;
} SeqStack;

/**
 * 初始化栈
 * @param stack_p
 */
void initStack(SeqStack *stack_p) {
    for (int i = 0; i < MaxSize; ++i) {
        (stack_p->elems)[i].data = 0;
    }

    // -2 栈顶指针初始值为下标 -1 ，表示顺序栈为空
    stack_p->top = -1;
}

bool push(SeqStack *s, ElemType e) {
    if (s->top == MaxSize - 1) {
        return false;
    }

    s->top = s->top + 1;
    (s->elems)[s->top] = e;
    return true;
}

// *x 表示 x 是一个指针变量，指针变量指向内存的一个地址，
// 比如 *stack 中，指针变量 stack 指向了某个顺序栈的内存地址
// 所以可以认为，stack 就是一个地址。
bool pop(SeqStack *stack, ElemType *e) {
    if (stack->top == -1) {
        return false;
    }
    // 只是对局部变量重新赋值，没有操作内存数据，最终对运行结果不会发生影像
    //    e = &stack->elems[stack->top];
    e->data = stack->elems[stack->top--].data;
    return true;
}

int main() {
    // 使用一个顺序栈（对于当前数据文件的访问，随机读写的形式更多，故而采用顺序栈）
    SeqStack stack;
    // 初始化该顺序栈
    initStack(&stack);

    ElemType e1 = *(ElemType *) malloc(sizeof(ElemType));
    e1.data = 4;
    ElemType e2 = *(ElemType *) malloc(sizeof(ElemType));
    e2.data = 5;
    push(&stack, e1);
    push(&stack, e2);

    ElemType *e3 = malloc(sizeof(ElemType));
    ElemType *e4 = malloc(sizeof(ElemType));

    pop(&stack, e3);
    pop(&stack, e4);

    printf("length=%lu", sizeof(stack));

    return 0;
}

