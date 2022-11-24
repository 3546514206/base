#include <iostream>

class binary_tree_node {
public:

    binary_tree_node *left_child;

    binary_tree_node *right_child;

    binary_tree_node(char data);

    char get_data();

    void set_data(char data);

private:

    char data;
};

char binary_tree_node::get_data() {
    return this->data;
}

void binary_tree_node::set_data(char data) {
    this->data = data;
}

binary_tree_node::binary_tree_node(char data) : data(data), left_child(NULL), right_child(NULL) {
}

class binary_tree {
public:
    binary_tree_node *root;

    explicit binary_tree(binary_tree_node *root);

    void pre_order(binary_tree_node *root) {
        if (root == NULL) {
            return;
        }
        std::cout << root->get_data() << std::endl;
        if (root->left_child != NULL) {
            pre_order(root->left_child);
        }
        if (root->right_child != NULL) {
            pre_order(root->right_child);
        }
    }

    void mid_order(binary_tree_node *root) {
        if (root == NULL) {
            return;
        }

        if (root->left_child != NULL) {
            mid_order(root->left_child);
        }
        std::cout << root->get_data() << std::endl;
        if (root->right_child != NULL) {
            mid_order(root->right_child);
        }
    }

    void after_order(binary_tree_node *root) {
        if (root == NULL) {
            return;
        }

        if (root->left_child != NULL) {
            after_order(root->left_child);
        }
        if (root->right_child != NULL) {
            after_order(root->right_child);
        }

        std::cout << root->get_data() << std::endl;
    }
};

binary_tree::binary_tree(binary_tree_node *root) : root(root) {

}

//                  A
//                /   \
//               B     C
//             /  \   /
//            D   E  F
//             \
//              G
int main() {

    binary_tree_node *node_a = new binary_tree_node('a');
    binary_tree_node *node_b = new binary_tree_node('b');
    binary_tree_node *node_c = new binary_tree_node('c');
    binary_tree_node *node_d = new binary_tree_node('d');
    binary_tree_node *node_e = new binary_tree_node('e');
    binary_tree_node *node_f = new binary_tree_node('f');
    binary_tree_node *node_g = new binary_tree_node('g');

    node_a->left_child = node_b;
    node_a->right_child = node_c;
    node_b->left_child = node_d;
    node_b->right_child = node_e;
    node_c->left_child = node_f;
    node_d->right_child = node_g;

    binary_tree *tree = new binary_tree(node_a);

//    tree->pre_order(tree->root);
//    tree->mid_order(tree->root);
    tree->after_order(tree->root);

    return 0;
}
