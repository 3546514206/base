#include <iostream>

class Strategy {
public:
    virtual void execute() = 0;
};

class StrategyA : public Strategy {
public:
    void execute() override {
        std::cout << "Executing strategy A" << std::endl;
        // 具体策略A的执行逻辑
    }
};

// 具体策略类B
class StrategyB : public Strategy {
public:
    void execute() override {
        std::cout << "Executing strategy B" << std::endl;
        // 具体策略B的执行逻辑
    }
};

// 上下文
class Context {
private:
    Strategy *strategy;
public:
    void setStrategy(Strategy *strategy) {
        this->strategy = strategy;
    }

    void execute() {
        strategy->execute();
    }

};


int main() {

    // 创建策略对象
    StrategyA strategyA;
    StrategyB strategyB;

    // 创建上下文对象
    Context context;

    // 设置具体策略A并执行
    context.setStrategy(&strategyA);
    context.execute();

    // 设置具体策略B并执行
    context.setStrategy(&strategyB);
    context.execute();

    return 0;
}
