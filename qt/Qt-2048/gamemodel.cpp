#include "gamemodel.h"
#include <QDebug>
/*
 * matrix[i][j] 对应i+1行j+1列种的数据
 */


GameModel::GameModel()
{
    matrix = data.matrix;
    clear();
}

void GameModel::init(){
    clear();
    addRamNum();    //任意添加两个数字
    addRamNum();
    emit finishProcess(&data);  //要更新数据 只需要发射这个信号
}
/*
 * 为了能够使用同一个方法处理矩阵，只需要把矩阵旋转到特定的方向
 * 恢复矩阵只需要根据当前旋转角度旋转凑足四次就回复原来的位置了
*/
void GameModel::op(GameOP op){
    switch (op) {
    case LEFT:
        rotate();
        process();
        rotate();
        rotate();
        break;
    case RIGHT:
        rotate();
        rotate();
        rotate();
        process();
        break;
    case UP:
        rotate();
        rotate();
        process();
        rotate();
        break;
    case DOWN:
        process();
        rotate();
        rotate();
        rotate();
        break;
    default:
        return;
    }
    rotate();
    emit finishProcess(&data);
}

void GameModel::clear(){
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++){
            matrix[i][j] = 0;
        }
    data.score = 0;
}
//旋转矩阵的方法
void GameModel::rotate(){
    int r_matrix[4][4];
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++){
            r_matrix[j][3-i] = matrix[i][j];
        }
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++){
            matrix[i][j] = r_matrix[i][j];
        }
}

void GameModel::process(){
    //从下往上处理
    bool isMoved = false;
    for(int i=3; i>=0; i--)
        for(int j=3; j>=0; j--){
            for(int c=j-1; c>=0; c--){
                if(matrix[i][j]==0){
                    if(matrix[i][c]!=0){
                        matrix[i][j] = matrix[i][c];
                        matrix[i][c] = 0;
                        isMoved = true;
                        continue;
                    }
                }else{
                    if(matrix[i][c] != 0){
                        if(matrix[i][j] == matrix[i][c]){
                            matrix[i][j] *= 2;
                            matrix[i][c] = 0;
                            data.score += matrix[i][j];
                            isMoved = true;
                        }
                        break;
                    }
                }
            }
            if(matrix[i][j] == 2048)
                emit win(); //如果出现2048 发射信号 通知界面赢了游戏
        }
    if(isMoved)
        addRamNum(); //添加一个随意数
}

void GameModel::addRamNum(){
    QVector<int *> blanks;
    //将空格（值为0）的矩阵单元压入QVector中
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++){
            if(matrix[i][j] == 0)
                blanks.append(&matrix[i][j]);
        }
    //如果Vector为空 就不执行操作
    if(blanks.length() == 0)
        return;
    //按3：1的比例随机添加2或者4
    *blanks[(int)qrand()%blanks.length()] = qrand()%4 < 3 ? 2 : 4;
    if(blanks.length() == 1){
        //如果只有一个空位 现在就是满的
        for(int i=0; i<=3; i++)
            for(int j=0; j<=3; j++){
                //往右下角判断 是否有相邻相同的数值
                if(j<3 && matrix[i][j] == matrix[i][j+1])
                    return;
                if(i<3 && matrix[i][j] == matrix[i+1][j])
                    return;
            }
        //如果能执行到这里 说明游戏结束
        emit gameover();
    }
}
