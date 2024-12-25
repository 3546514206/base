package main

import (
	"container/heap"
	"fmt"
)

// 坐标结构体
type Point struct {
	x, y int
}

// 节点结构体
type Node struct {
	point      Point // 节点坐标
	f, g, h, i int   // f = g + h，i 为节点在堆中的索引
	parent     *Node
}

// 优先队列，用于实现最小堆
type PriorityQueue []*Node

func (pq PriorityQueue) Len() int           { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool { return pq[i].f < pq[j].f }
func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].i, pq[j].i = i, j
}

// Push 添加元素到堆中
func (pq *PriorityQueue) Push(x interface{}) {
	node := x.(*Node)
	node.i = len(*pq)
	*pq = append(*pq, node)
}

// Pop 从堆中弹出元素
func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	node := old[n-1]
	node.i = -1 // for safety
	*pq = old[0 : n-1]
	return node
}

// 计算曼哈顿距离
var abs = func(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

// A*算法实现
func AStar(grid [][]int, start, end Point) []Point {
	rows, cols := len(grid), len(grid[0])

	// 判断某个点是否在地图内
	isValid := func(p Point) bool {
		return p.x >= 0 && p.x < rows && p.y >= 0 && p.y < cols
	}

	// 计算启发式函数h
	calculateH := func(p1, p2 Point) int {
		return abs(p1.x-p2.x) + abs(p1.y-p2.y)
	}

	// 定义方向数组，表示上、下、左、右四个方向
	directions := []Point{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

	// 初始化起点和终点
	startNode := &Node{point: start}
	endNode := &Node{point: end}

	// 初始化优先队列（最小堆）
	openSet := make(PriorityQueue, 0)
	heap.Init(&openSet)

	// 初始化关闭集合
	closedSet := make(map[Point]bool)

	// 将起点加入优先队列
	heap.Push(&openSet, startNode)

	for openSet.Len() > 0 {
		// 弹出优先队列中f值最小的节点
		currentNode := heap.Pop(&openSet).(*Node)

		// 判断是否达到终点
		if currentNode.point == endNode.point {
			// 回溯路径
			path := make([]Point, 0)
			for currentNode != nil {
				path = append(path, currentNode.point)
				currentNode = currentNode.parent
			}
			// 反转路径
			reversePath(path)
			return path
		}

		// 将当前节点加入关闭集合
		closedSet[currentNode.point] = true

		// 遍历当前节点的邻居
		for _, dir := range directions {
			neighbor := Point{currentNode.point.x + dir.x, currentNode.point.y + dir.y}

			// 判断邻居是否在地图内且可达
			if isValid(neighbor) && grid[neighbor.x][neighbor.y] == 0 {
				// 判断邻居是否在关闭集合中
				if closedSet[neighbor] {
					continue
				}

				// 计算g值
				g := currentNode.g + 1

				// 判断邻居是否在优先队列中
				found := false
				neighborNode := &Node{}
				for _, node := range openSet {
					if node.point == neighbor {
						found = true
						neighborNode = node
						break
					}
				}

				// 如果邻居不在优先队列中或者新路径的g值更小，则更新邻居节点信息
				if !found || g < neighborNode.g {
					// 更新g、h和f值
					neighborNode.g = g
					neighborNode.h = calculateH(neighbor, end)
					neighborNode.f = g + neighborNode.h

					// 更新父节点
					neighborNode.parent = currentNode

					// 如果邻居不在优先队列中，将其加入队列
					if !found {
						heap.Push(&openSet, neighborNode)
					}
				}
			}
		}
	}

	// 如果优先队列为空，表示没有找到路径
	return nil
}

// 反转路径
func reversePath(path []Point) {
	for i, j := 0, len(path)-1; i < j; i, j = i+1, j-1 {
		path[i], path[j] = path[j], path[i]
	}
}

func main() {
	// 定义地图
	grid := [][]int{
		{0, 0, 0, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 1, 0, 0, 0},
		{0, 1, 0, 1, 0},
		{0, 0, 0, 0, 0},
	}

	// 定义起点和终点
	start := Point{0, 0}
	end := Point{4, 4}

	// 调用A*算法寻找路径
	path := AStar(grid, start, end)

	// 打印路径
	if path != nil {
		fmt.Println("找到路径：", path)
	} else {
		fmt.Println("未找到路径")
	}
}
