# B+ Tree Node class
class BPlusTreeNode:
    def __init__(self, leaf=False):
        self.leaf = leaf
        self.keys = []
        self.children = []

class BPlusTree:
    def __init__(self, t):
        self.root = BPlusTreeNode(True)
        self.t = t  # Minimum degree (t)

    # Search function to find a key in the B+ tree
    def search(self, node, key):
        i = 0
        while i < len(node.keys) and key > node.keys[i]:
            i += 1

        # If we reach a leaf node
        if node.leaf:
            if i < len(node.keys) and node.keys[i] == key:
                return True
            return False

        # Move down to the child
        return self.search(node.children[i], key)

    # Insert a key into the B+ tree
    def insert(self, key):
        root = self.root
        # If root is full, split it
        if len(root.keys) == 2 * self.t - 1:
            new_root = BPlusTreeNode()
            new_root.children.append(self.root)
            self.split_child(new_root, 0, self.root)
            self.root = new_root

        # Insert into non-full node
        self.insert_non_full(self.root, key)

    # Insert a key into a non-full node
    def insert_non_full(self, node, key):
        i = len(node.keys) - 1

        if node.leaf:
            node.keys.append(None)
            while i >= 0 and key < node.keys[i]:
                node.keys[i + 1] = node.keys[i]
                i -= 1
            node.keys[i + 1] = key
        else:
            while i >= 0 and key < node.keys[i]:
                i -= 1
            i += 1
            if len(node.children[i].keys) == 2 * self.t - 1:
                self.split_child(node, i, node.children[i])
                if key > node.keys[i]:
                    i += 1
            self.insert_non_full(node.children[i], key)

    # Split the child node of a parent
    def split_child(self, parent, i, full_child):
        t = self.t
        new_child = BPlusTreeNode(full_child.leaf)
        parent.children.insert(i + 1, new_child)
        parent.keys.insert(i, full_child.keys[t - 1])

        new_child.keys = full_child.keys[t:(2 * t - 1)]
        full_child.keys = full_child.keys[0:(t - 1)]

        if not full_child.leaf:
            new_child.children = full_child.children[t:(2 * t)]
            full_child.children = full_child.children[0:(t)]

    # Print the B+ Tree structure
    def print_tree(self, node, level=0):
        print("Level", level, ":", node.keys)
        if not node.leaf:
            for child in node.children:
                self.print_tree(child, level + 1)

# Usage
if __name__ == '__main__':
    BPlusTree = BPlusTree(3)  # Minimum degree t=3

    # Inserting keys
    for i in [10, 20, 5, 6, 12, 30, 7, 17]:
        BPlusTree.insert(i)

    # Printing the B+ tree structure
    BPlusTree.print_tree(BPlusTree.root)

    # Searching for keys
    print("Search 6:", BPlusTree.search(BPlusTree.root, 6))  # Should return True
    print("Search 15:", BPlusTree.search(BPlusTree.root, 15))  # Should return False
