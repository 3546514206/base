"""
在 Python 中，yield 关键字用于定义生成器（generator），它使得一个函数能够返回一个迭代器。
与普通函数不同，使用 yield 的函数在调用时并不会立即执行，而是返回一个生成器对象，只有在迭代时才逐步执行函数体。
"""


def fibonacci(n):
    """
    迭代的时候，每次迭代会执行一次 fibonacci 生成器函数，执行函数体，通过 yield 指令产生当前的迭代对象。
    换句话说：如果不迭代，那么 生成器对象就不会产生斐波那契数。因此生成器对象又被看做一种“惰性对象”。
    显而易见：生成器对象必定是保存有 fibonacci 参数 n 的当前值和上一次的执行位置，否则不可能每次惰性调
    用生成器函数的时候，还能够从上次执行函数体的下一行指令重新执行。
    """
    a, b = 0, 1
    for _ in range(n):
        # 产生当前斐波那契数
        yield a
        # 更新为下一个数
        a, b = b, a + b


def main():
    # 使用生成器
    gen = fibonacci(10)
    for number in gen:
        print(number)


if __name__ == '__main__':
    main()
