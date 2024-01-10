// 定义跳表节点
class Node {
    constructor(value, level) {
        this.value = value;
        this.forward = new Array(level + 1);
    }
}

// 定义跳表
class SkipList {
    constructor(maxLevel) {
        this.maxLevel = maxLevel;
        this.level = 0;
        this.head = new Node(-1, maxLevel);
    }

    // 生成随机层数
    randomLevel() {
        let level = 0;
        while (Math.random() < 0.5 && level < this.maxLevel) {
            level++;
        }
        return level;
    }

    // 插入节点
    insert(value) {
        const update = new Array(this.maxLevel + 1);
        let current = this.head;

        for (let i = this.level; i >= 0; i--) {
            while (current.forward[i] && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        const newLevel = this.randomLevel();
        if (newLevel > this.level) {
            for (let i = this.level + 1; i <= newLevel; i++) {
                update[i] = this.head;
            }
            this.level = newLevel;
        }

        const newNode = new Node(value, newLevel);

        for (let i = 0; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    // 查找节点
    search(value) {
        let current = this.head;

        for (let i = this.level; i >= 0; i--) {
            while (current.forward[i] && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }

        const foundNode = current.forward[0];

        if (foundNode && foundNode.value === value) {
            return foundNode;
        } else {
            return null;
        }
    }

    // 删除节点
    delete(value) {
        const update = new Array(this.maxLevel + 1);
        let current = this.head;

        for (let i = this.level; i >= 0; i--) {
            while (current.forward[i] && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        const targetNode = current.forward[0];

        if (targetNode && targetNode.value === value) {
            for (let i = 0; i <= this.level; i++) {
                if (update[i].forward[i] !== targetNode) {
                    break;
                }
                update[i].forward[i] = targetNode.forward[i];
            }

            // 更新跳表的层数
            while (this.level > 0 && this.head.forward[this.level] === null) {
                this.level--;
            }
        }
    }

    // 打印跳表
    print() {
        for (let i = this.level; i >= 0; i--) {
            let row = "Head -> ";
            let current = this.head.forward[i];
            while (current) {
                row += current.value + " -> ";
                current = current.forward[i];
            }
            console.log(row + "null");
        }
    }
}

// 使用示例
const skipList = new SkipList(4);

skipList.insert(3);
skipList.insert(6);
skipList.insert(7);
skipList.insert(9);
skipList.insert(12);
skipList.insert(19);
skipList.insert(17);
skipList.insert(26);
skipList.insert(21);
skipList.insert(25);

console.log("Original Skip List:");
skipList.print();

const searchResult = skipList.search(19);
console.log("\nSearch Result (Node with value 19):", searchResult);

skipList.delete(19);
console.log("\nSkip List after deleting node with value 19:");
skipList.print();
