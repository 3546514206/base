#include <iostream>
#include <vector>
#include <queue>

using namespace std;

// 边的结构体
struct Edge {
    int src, dest, weight;
};

// 最小生成树算法
void primMST(vector<vector<int>> &graph) {
    int V = graph.size();

    // 创建存储边的优先队列，按照权重从小到大排序
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    // 创建标记数组，用于记录顶点是否已经加入最小生成树
    vector<bool> visited(V, false);

    // 创建存储最小生成树的数组，用于存储边的信息
    vector<Edge> MST(V - 1);

    // 标记第一个顶点，并将其加入优先队列
    visited[0] = true;
    for (int i = 1; i < V; ++i) {
        if (graph[0][i] != 0) {
            pq.push(make_pair(graph[0][i], i));
        }
    }

    int index = 0; // 最小生成树数组的索引

    // 遍历优先队列，直到最小生成树数组填满
    while (!pq.empty() && index < V - 1) {
        int u = pq.top().second; // 获取队列中最小权重边的目标顶点
        pq.pop();

        // 如果目标顶点已经访问过，则跳过该顶点
        if (visited[u]) {
            continue;
        }

        // 将边的信息存入最小生成树数组
        MST[index].src = u;
        MST[index].dest = pq.top().second;
        MST[index].weight = pq.top().first;
        ++index;

        // 标记目标顶点，并将目标顶点的相邻边加入优先队列
        visited[u] = true;
        for (int v = 0; v < V; ++v) {
            if (!visited[v] && graph[u][v] != 0) {
                pq.push(make_pair(graph[u][v], v));
            }
        }
    }

    // 输出最小生成树的边
    cout << "Minimum Spanning Tree Edges:" << endl;
    for (int i = 0; i < V - 1; ++i) {
        cout << MST[i].src << " - " << MST[i].dest << "  Weight: " << MST[i].weight << endl;
    }
}

int main() {
    // 创建一个邻接矩阵表示的图
    vector<vector<int>> graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
    };

    primMST(graph);

    return 0;
}
