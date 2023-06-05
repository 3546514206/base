#include <iostream>
#include <fstream>
#include <queue>
#include <unordered_map>
#include <bitset>

using namespace std;

// 哈夫曼树节点
struct Node {
    char data;
    unsigned frequency;
    Node* left;
    Node* right;

    Node(char data, unsigned frequency) : data(data), frequency(frequency), left(nullptr), right(nullptr) {}
};

// 优先队列的比较函数，按照节点频率进行排序
struct Compare {
    bool operator()(Node* left, Node* right) {
        return left->frequency > right->frequency;
    }
};

// 生成哈夫曼树
Node* buildHuffmanTree(const unordered_map<char, unsigned>& frequencies) {
    priority_queue<Node*, vector<Node*>, Compare> pq;

    // 创建叶子节点并加入优先队列
    for (const auto& pair : frequencies) {
        pq.push(new Node(pair.first, pair.second));
    }

    // 构建哈夫曼树
    while (pq.size() > 1) {
        Node* left = pq.top();
        pq.pop();
        Node* right = pq.top();
        pq.pop();

        Node* parent = new Node('$', left->frequency + right->frequency);
        parent->left = left;
        parent->right = right;

        pq.push(parent);
    }

    return pq.top();
}

// 生成哈夫曼编码表
void generateHuffmanCodes(Node* root, const string& code, unordered_map<char, string>& codes) {
    if (root == nullptr) {
        return;
    }

    // 叶子节点，保存编码
    if (!root->left && !root->right) {
        codes[root->data] = code;
    }

    generateHuffmanCodes(root->left, code + "0", codes);
    generateHuffmanCodes(root->right, code + "1", codes);
}

// 压缩文件
void compressFile(const string& inputFile, const string& outputFile) {
    // 统计字符频率
    unordered_map<char, unsigned> frequencies;
    ifstream ifs(inputFile, ios::binary);
    char ch;
    while (ifs.get(ch)) {
        frequencies[ch]++;
    }
    ifs.close();

    // 构建哈夫曼树
    Node* root = buildHuffmanTree(frequencies);

    // 生成哈夫曼编码表
    unordered_map<char, string> codes;
    generateHuffmanCodes(root, "", codes);

    // 写入压缩数据
    ofstream ofs(outputFile, ios::binary);
    ifstream ifs2(inputFile, ios::binary);
    bitset<8> bits;
    while (ifs2.get(ch)) {
        string code = codes[ch];
        for (char c : code) {
            bits[7] = c - '0';
            ofs.write(reinterpret_cast<const char*>(&bits), sizeof(char));
            bits <<= 1;
        }
    }
    ifs2.close();
    ofs.close();

    cout << "File compressed successfully!" << endl;
}

int main() {
    string inputFile = "./input.txt";
    string compressedFile = "./compressed.bin";

    compressFile(inputFile, compressedFile);

    return 0;
}
