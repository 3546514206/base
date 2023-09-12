#ifndef GAMEDATADEF_H
#define GAMEDATADEF_H
typedef struct{
    int score;  //分数
    int matrix[4][4];   //矩阵
}ViewData;
//操作枚举
enum GameOP{
    LEFT, RIGHT, UP, DOWN
};

#endif // GAMEDATADEF_H
