#include <iostream>
#include <vector>
#include <queue>

using namespace std;

#define INF 1e9

// 图的邻接表表示
vector<vector<pair<int, int>>> graph;

vector<int> dijkstra(int start, int n) {
    vector<int> dist(n, INF);  // 存储起始点到每个顶点的最短距离
    vector<bool> visited(n, false);  // 记录顶点是否被访问过

    // 优先队列，按照距离从小到大排序
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    dist[start] = 0;  // 起始点到自身的距离为0
    pq.push(make_pair(0, start));

    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        if (visited[u]) {
            continue;
        }

        visited[u] = true;

        for (auto edge : graph[u]) {
            int v = edge.first;
            int weight = edge.second;

            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                pq.push(make_pair(dist[v], v));
            }
        }
    }

    return dist;
}

int main() {
    int n, m;
    cout << "Enter the number of vertices: ";
    cin >> n;
    cout << "Enter the number of edges: ";
    cin >> m;

    // 初始化图的邻接表
    graph.resize(n);

    cout << "Enter the edges and their weights:" << endl;
    for (int i = 0; i < m; i++) {
        int u, v, weight;
        cin >> u >> v >> weight;
        graph[u].push_back(make_pair(v, weight));
        graph[v].push_back(make_pair(u, weight));
    }

    int start;
    cout << "Enter the starting vertex: ";
    cin >> start;

    vector<int> dist = dijkstra(start, n);

    cout << "Shortest distances from vertex " << start << ":" << endl;
    for (int i = 0; i < n; i++) {
        cout << "Vertex " << i << ": " << dist[i] << endl;
    }

    return 0;
}
