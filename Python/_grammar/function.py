"""
返回 5
"""
import math


def my_func():
    a = 1
    b = 4
    return a + b


def build_in_def():
    print(help(abs))
    print(abs(-0.99))
    print(help(my_func))
    print(max(2, 3, 1, -5))


def move(x, y, step, angle=0.0):
    nx = x + step * math.cos(angle)
    ny = y - step * math.sin(angle)
    return nx, ny


def multi_return():
    x, y = move(100, 100, 60, math.pi / 6.0)
    print(x)
    print(y)


def add_end(L=[]):
    L.append('END')
    return L


def default_var(L=[]):
    """默认参数最大的坑"""
    # 没问题
    print(add_end([1, 2, 3]))
    # 还是没问题
    print(add_end(['x', 'y', 'z']))
    # 一开始也没问题
    print(add_end())
    # 再调一次就有问题了
    print(add_end())
    # 函数似乎每次都“记住了”上次添加了'END'后的list。原因解释如下：
    # Python函数在定义的时候，默认参数L的值就被计算出来了，即[]，因为默认参数L也是一个变量，它指向对象[]，每次调用该函数，都会修改这个对象
    # 的内容，也就是说，这个对象是“全局的”。定义默认参数要牢记一点：默认参数必须指向不变对象！
    print(add_end())


def variadic_arguments(*numbers):
    """可变参数：定义可变参数仅仅在参数前面加了一个*号。在函数内部，参数numbers接收到的是一个tuple。调用该函数时，可以传入任意个参数，包括0个参数："""
    sum_of_num = 0
    for num in numbers:
        sum_of_num = sum_of_num + num * num

    return sum_of_num


def keyword_arguments(name, age, **kw):
    """关键字参数:可变参数允许你传入0个或任意个参数，这些可变参数在函数调用时自动组装为一个tuple。而关键字参数允许你传入0个或任意个含参数名的参数，这些关键字参数在函数内部自动组装为一个dict。"""
    print('name:', name, 'age:', age, 'other:', kw)


def main():
    # build_in_def()
    # multi_return()
    # default_var()
    # print(variadic_arguments(100))

    my_list = [1, 3, 5, 8]
    print(variadic_arguments(*my_list))

    my_tuple = (2, 3)
    print(variadic_arguments(*my_tuple))

if __name__ == "__main__":
    main()
