class Node {
    def value
    def forward = []

    Node(value, level) {
        this.value = value
        this.forward = new Object[level + 1]
    }
}

class SkipList {
    def MAX_LEVEL = 4  // 最大层级

    def level = 0
    def header = new Node(null, MAX_LEVEL)

    // 随机生成层级
    def randomLevel() {
        def level = 0
        while (Math.random() < 0.5 && level < MAX_LEVEL) {
            level++
        }
        return level
    }

    // 插入元素
    def insert(value) {
        def update = new Object[MAX_LEVEL + 1]
        def current = header

        for (def i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i]
            }
            update[i] = current
        }

        def newLevel = randomLevel()
        if (newLevel > level) {
            for (def i = level + 1; i <= newLevel; i++) {
                update[i] = header
            }
            level = newLevel
        }

        def newNode = new Node(value, newLevel)
        for (def i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i]
            update[i].forward[i] = newNode
        }
    }

    // 查找元素
    def contains(value) {
        def current = header
        for (def i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i]
            }
        }
        current = current.forward[0]
        return current != null && current.value == value
    }

    // 打印跳表
    def printSkipList() {
        for (def i = level; i >= 0; i--) {
            def current = header
            print("Level $i: ")
            while (current.forward[i] != null) {
                print("${current.forward[i].value} -> ")
                current = current.forward[i]
            }
            println("null")
        }
    }
}

// 示例
def skipList = new SkipList()

skipList.insert(3)
skipList.insert(6)
skipList.insert(7)
skipList.insert(9)
skipList.insert(12)
skipList.insert(19)
skipList.insert(17)
skipList.insert(26)
skipList.insert(21)
skipList.insert(25)

skipList.printSkipList()

def searchValue = 19
if (skipList.contains(searchValue)) {
    println("Skip List contains $searchValue.")
} else {
    println("Skip List does not contain $searchValue.")
}
