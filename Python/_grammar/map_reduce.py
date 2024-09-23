"""
map 和 reduce 的抽象规则
"""


def f(x):
    return x * x


def test_map():
    """map()作为高阶函数，事实上它把运算规则抽象了，因此，我们不但可以计算简单的f(x)=x2，还可以计算任意复杂的函数"""
    r = map(f, [1, 2, 3, 4, 5, 6, 7, 8, 9])
    print(r)
    print(list(r))


def main():
    test_map()


if __name__ == '__main__':
    main()
