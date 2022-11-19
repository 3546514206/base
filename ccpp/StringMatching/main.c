#include <stdio.h>

#define MAX_LEN 255

typedef struct {
    char chars[MAX_LEN];
    int length;
} String;

void initString(String *string, char s[], int size) {
    string->length = size;
    for (int i = 1; i <= size; ++i) {
        string->chars[i] = s[i - 1];
    }
}

// 朴素的字符串模式匹配算法
int index(String *string, String *ttring) {

    int k = 1;
    int i = k;
    int j = 1;

    while (i <= string->length && j <= ttring->length) {
        if (string->chars[i] == ttring->chars[j]) {
            i++;
            j++;
        } else {
            k++;
            i = k;  // 主串扫描指针会发生回退， KMP 也是针对此做出优化
            j = j;
        }
    }

    if (j > ttring->length) {
        return k;
    } else {
        return 0;
    }
}

int main() {

    String string;
    char s[] = {'w', 'a', 'n', 'g', 'd', 'a', 'o'};
    initString(&string, s, sizeof(s) / sizeof(s[0]));

    char t[] = {'g', 'd', 'a'};
    String ttring;
    initString(&ttring, t, sizeof(t) / sizeof(s[0]));

    printf("模式串在子串中的位置：%d", index(&string, &ttring));

    return 0;
}
