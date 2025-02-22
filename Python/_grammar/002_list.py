"""
Python 中的线性表
"""

import os

from PIL.ImageSequence import Iterator


def main():
    """案例"""
    # base()
    # comprehensions()
    generator()


def base():
    """基础用法"""
    classmates = ['Michael', 'Bob', "Tracy"]
    print(classmates)
    print(len(classmates))
    print(classmates[0])
    print(classmates[-1])
    print(classmates.pop())
    classmates.append('Lisa')
    print(classmates)
    classmates.insert(2, 'Jack')
    print(classmates)
    print(classmates.pop())
    print(classmates)
    # print(classmates[4])


def comprehensions():
    """列表生成式案例"""
    l1 = list(range(1, 11))
    print(l1)

    l2 = []
    for x in range(1, 11):
        l2.append(x)
    print(l2)

    l3 = [x for x in range(1, 11)]
    print(l3)

    l4 = [x for x in range(1, 11) if x % 2 == 0]
    print(l4)

    l5 = [a + b for a in 'XYZ' for b in 'OPQ']
    print(l5)

    # 先判断当前的目录是否是可迭代目录
    if isinstance(os.listdir('.'), Iterator):
        cur_dir = [d for d in os.listdir('.')]
        # 打印当前的目录文件
        print(cur_dir)


def generator():
    """列表生成器
    过列表生成式，我们可以直接创建一个列表。但是，受到内存限制，列表容量肯定是有限的。而且，创建一个包含100万个元素的列表，不仅占用很大的
    存储空间，如果我们仅仅需要访问前面几个元素，那后面绝大多数元素占用的空间都白白浪费了。所以，如果列表元素可以按照某种算法推算出来，那我
    们是否可以在循环的过程中不断推算出后续的元素呢？这样就不必创建完整的list，从而节省大量的空间。在Python中，这种一边循环一边计算的机制
    ，称为生成器：generator。
    """
    g = (x for x in range(10))
    print(g)
    print(next(g))
    print(next(g))

    if isinstance(g, Iterator):
        """列表生成器也是一个可遍历对象"""
        for n in g:
            print(n)

    # 普通函数：一次性生成所有平方数
    squares_list = get_squares_list(5)
    print("List:", squares_list)

    # 生成器函数：逐个生成平方数
    squares_gen = get_squares_gen(5)
    print("Generator:", list(squares_gen))


def get_squares_list(n):
    """普通函数返回一个列表"""
    result = []
    for i in range(n):
        result.append(i * i)
    return result


def get_squares_gen(n):
    """生成器函数返回一个生成器对象"""
    for i in range(n):
        yield i * i


if __name__ == '__main__':
    main()
