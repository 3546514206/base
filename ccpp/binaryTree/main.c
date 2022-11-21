#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define max_size 100

//// 顺序存储结构：存在大量数组元素闲置，虽然能实现随机读
//typedef struct {
//    int value;
//    // 完全二叉树，空节点也占位，is_empty 作为逻辑标识
//    bool is_empty;
//} tree_node_seq;
//
////
//tree_node_seq tree[max_size];
//
//// 初始化顺序存储的二叉树，第一个数组元素不存储任何节点
//void init_seq_binary_tree(){
//    for (int i = 1; i < max_size; ++i) {
//        tree[i].is_empty = false;
//        tree[i].value = -9999999;
//    }
//}


// 链式存储
typedef struct tree_node {
    int data;
    struct tree_node *left_child, *right_child;
} tree_node, *tree;


int main() {

//    init_seq_binary_tree();
    tree binary_tree = NULL;
    tree_node *root = NULL;
    root = (tree_node *) malloc(sizeof(tree_node));
    // 二叉树指向根节点
    binary_tree = root;

    root->left_child = NULL;
    root->right_child = NULL;

    return 0;
}
