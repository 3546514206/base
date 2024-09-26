"""
Python内建的filter()函数用于过滤序列。
和map()类似，filter()也接收一个函数和一个序列。和map()不同的是，filter()把传入的函数依次作用于每个元素，然后根据返回值是True还是False决定保留还是丢弃该元素。
"""


def is_odd(a: int) -> bool:
    """过滤出奇数"""
    return a % 2 == 1


def main():
    arr = [2, 11, 34, 2, 7, 15]
    arr_ret = filter(is_odd, arr)
    print(list(arr_ret))


if __name__ == '__main__':
    main()
