"""
tuple 一旦初始化就不能修改。
不可变的 tuple 有什么意义？因为 tuple 不可变，所以代码更安全。如果可能，能用 tuple 代替 list 就尽量用 tuple。
tuple 的陷阱：当你定义一个 tuple 时，在定义的时候，tuple 的元素就必须被确定下来。
"""


def main():
    t1 = (1, 2)
    print(t1)

    t2 = ()
    print(t2)
    print(type(t2))

    # t3 = (1)
    t3 = (1,)
    print(t3)

    t4 = (1, 2, 3,)
    print(t4)

    # 一个“可变”的元组
    t5 = ('a', 'b', ['A', 'B'])
    t5[2][0] = 'X'
    t5[2][0] = 'Y'
    print(t5)


if __name__ == "__main__":
    """list和tuple是Python内置的有序集合，一个可变，一个不可变。根据需要来选择使用它们。"""
    main()
