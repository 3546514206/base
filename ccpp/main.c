//
// Created by 杨海波 on 2022/11/12.
//

#include <stdio.h>
#include <stdlib.h>

#include "testStaticStructDef.h"

typedef struct ElemType {
    int x;
} ElemType;

typedef struct Node {
    ElemType data;
    struct Node *prior, *next;
} Node, *List;

void test(){
    List list = (List) malloc(sizeof(struct Node));
    list->next = NULL;
    list->prior = NULL;
    for (int i = 0; i < 5; ++i) {
        Node *node = (List) malloc(sizeof(struct Node));

        ElemType elem = *(ElemType *) malloc(sizeof(struct ElemType));
        elem.x = i;
        node->data = elem;

        node->next = NULL;
        node->prior = list;

        list->next = node;
        list = node;
        node->prior = list->prior;
    }
    printf("test");
}

int main() {




}