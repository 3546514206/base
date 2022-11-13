#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define MaxSize 10

typedef struct {
    int datas[MaxSize];
    // rear 队尾元素下标 + 1
    int front, rear;
} SeqQueue;

void initSeqQueue(SeqQueue *s) {
    // 队头指针与队尾指针重合表示队列为空，只有不重合才指向了队头元素
    s->front = 0;
    // 队尾指针表示下一个入队的位置，队尾指针 - 1 得到队尾元素的下标
    s->rear = 0;
}

bool enQueue(SeqQueue *queue, int e) {
    // 队列满的条件：队尾指针 + 1 等于队头指针
    if ((queue->rear + 1) % MaxSize == queue->front) {
        return false;
    }
    (queue->datas)[queue->rear] = e;
    queue->rear = (queue->rear + 1) % MaxSize;
    return true;
}

bool deQueue(SeqQueue *queue, int *e) {
    // 队列为空，出队失败
    if (queue->front == queue->rear) {
        return false;
    }
    *e = queue->datas[queue->front];
    queue->front = (queue->front + 1) % MaxSize;
    return true;
}


int main() {
    SeqQueue s;
    initSeqQueue(&s);
    for (int i = 9; i < 19; ++i) {
        if (i == 14) {
            int *d = malloc(sizeof(int));
            if (deQueue(&s, d)) {
                printf("数据：d=%d 出队成功\n", *d);
            } else {
                printf("数据：d=%d 出队失败\n", -9999);
            }
        }
        if (enQueue(&s, i)) {
            printf("数据：i=%d 入队成功\n", i);
        } else {
            // 牺牲了最后一个位置，当下一个入队位置为 9 的时候
            // 假如入队成功，rear 指针将指向队头指针，rear front 重合
            printf("数据：i=%d 入队失败\n", i);
        }


    }

    return 0;
}
