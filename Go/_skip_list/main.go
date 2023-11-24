package main

import (
	"fmt"
	"math/rand"
)

const (
	maxLevel = 16
)

// Node represents a node in the skip list
type Node struct {
	key     int
	value   interface{}
	forward []*Node
}

// SkipList represents the skip list data structure
type SkipList struct {
	header *Node
	level  int
}

// NewNode creates a new node with the given key, value, and level
func NewNode(key int, value interface{}, level int) *Node {
	return &Node{
		key:     key,
		value:   value,
		forward: make([]*Node, level+1),
	}
}

// NewSkipList creates a new skip list
func NewSkipList() *SkipList {
	header := NewNode(0, nil, maxLevel)
	return &SkipList{header, 0}
}

// randomLevel generates a random level for a new node
func randomLevel() int {
	level := 1
	for rand.Float64() < 0.5 && level < maxLevel {
		level++
	}
	return level
}

// Insert inserts a key-value pair into the skip list
func (sl *SkipList) Insert(key int, value interface{}) {
	update := make([]*Node, maxLevel+1)
	current := sl.header

	for i := sl.level; i >= 0; i-- {
		for current.forward[i] != nil && current.forward[i].key < key {
			current = current.forward[i]
		}
		update[i] = current
	}

	level := randomLevel()

	if level > sl.level {
		for i := sl.level + 1; i <= level; i++ {
			update[i] = sl.header
		}
		sl.level = level
	}

	newNode := NewNode(key, value, level)

	for i := 0; i <= level; i++ {
		newNode.forward[i] = update[i].forward[i]
		update[i].forward[i] = newNode
	}
}

// Search searches for a key in the skip list and returns its value if found
func (sl *SkipList) Search(key int) (interface{}, bool) {
	current := sl.header

	for i := sl.level; i >= 0; i-- {
		for current.forward[i] != nil && current.forward[i].key < key {
			current = current.forward[i]
		}
	}

	if current.forward[0] != nil && current.forward[0].key == key {
		return current.forward[0].value, true
	}

	return nil, false
}

// Display prints the elements of the skip list at each level
func (sl *SkipList) Display() {
	for i := sl.level; i >= 0; i-- {
		fmt.Printf("Level %d: ", i)
		current := sl.header.forward[i]
		for current != nil {
			fmt.Printf("%d ", current.key)
			current = current.forward[i]
		}
		fmt.Println()
	}
}

func main() {
	skipList := NewSkipList()

	keys := []int{3, 6, 9, 12, 16, 19, 22, 25}
	values := []interface{}{"A", "B", "C", "D", "E", "F", "G", "H"}

	for i := range keys {
		skipList.Insert(keys[i], values[i])
	}

	fmt.Println("Skip List:")
	skipList.Display()

	searchKey := 16
	if result, found := skipList.Search(searchKey); found {
		fmt.Printf("Key %d found, Value: %s\n", searchKey, result)
	} else {
		fmt.Printf("Key %d not found\n", searchKey)
	}
}
