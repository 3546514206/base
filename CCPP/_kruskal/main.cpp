#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 定义边的结构体
struct edge {
    int src, dest, weight;
};

// 定义并查集的数据结构
class union_find {
public:
    union_find(int n) {
        parent.resize(n);
        rank.resize(n, 0);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    // 查找节点的根
    int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    // 合并两个集合
    void merge(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);

        if (rank[root_x] > rank[root_y]) {
            parent[root_y] = root_x;
        } else {
            parent[root_x] = root_y;
            if (rank[root_x] == rank[root_y]) {
                rank[root_y]++;
            }
        }
    }

private:
    vector<int> parent;
    vector<int> rank;
};

// Kruskal 算法实现
vector<edge> kruskal(const vector<edge> &edges, int num_vertices) {
    vector<edge> minimum_spanning_tree;
    // 将边按权重升序排序
    vector<edge> sorted_edges = edges;
    sort(sorted_edges.begin(), sorted_edges.end(), [](const edge &a, const edge &b) {
        return a.weight < b.weight;
    });

    // 初始化并查集
    union_find uf(num_vertices);

    // 遍历排序后的边
    for (const edge &edge: sorted_edges) {
        int rootSrc = uf.find(edge.src);
        int rootDest = uf.find(edge.dest);

        // 如果边的两个端点不在同一个集合中，加入最小生成树，并合并集合
        if (rootSrc != rootDest) {
            minimum_spanning_tree.push_back(edge);
            uf.merge(rootSrc, rootDest);
        }
    }

    return minimum_spanning_tree;
}

int main() {
    // 例子：图的边集合
    vector<edge> edges = {
            {0, 1, 4},
            {0, 7, 8},
            {1, 2, 8},
            {1, 7, 11},
            {2, 3, 7},
            {2, 8, 2},
            {2, 5, 4},
            {3, 4, 9},
            {3, 5, 14},
            {4, 5, 10},
            {5, 6, 2},
            {6, 7, 1},
            {6, 8, 6},
            {7, 8, 7}
    };

    // 节点数
    int num_vertices = 9;

    // 执行 Kruskal 算法
    vector<edge> minimum_spanning_tree = kruskal(edges, num_vertices);

    // 输出最小生成树的边
    for (const edge &edge: minimum_spanning_tree) {
        cout << edge.src << " - " << edge.dest << " : " << edge.weight << endl;
    }

    return 0;
}
