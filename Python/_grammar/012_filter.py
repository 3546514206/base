"""
Python内建的filter()函数用于过滤序列。
和map()类似，filter()也接收一个函数和一个序列。和map()不同的是，filter()把传入的函数依次作用于每个元素，然后根据返回值是True还是False决定保留还是丢弃该元素。
"""
import string


def is_odd(a: int) -> bool:
    """过滤出奇数"""
    return a % 2 == 1


def not_empty(s: string) -> bool:
    """过滤空字符串"""
    return s and s.strip()


def main():
    arr = [2, 11, 34, 2, 7, 15]
    arr_ret = filter(is_odd, arr)
    print(list(arr_ret))

    arr_str = ['A', '', 'test', '', '   ', '     nin ']
    arr_str_list = list(filter(not_empty, arr_str))
    print(arr_str_list)


if __name__ == '__main__':
    main()
