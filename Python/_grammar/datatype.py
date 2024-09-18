# 整数测试
import math

# 整形数测试
def test_int():
    print(10_000_000_000 == 10000000000)

# 测试浮点数
def test_float():
    print(1.2e-5)

# 测试布尔值
def test_bool():
    print(True or False)

# 字符串测试
def test_str():
    print('I\'m learning\nPython.')

    # 多行内容
    print('''line1
    ... line2
    ... line3''')

# 测试变量
def test_variable():
    # 声明一个整型变量
    x = 10

    # 输出变量的值和类型
    print(x, type(x))  # 输出: 10 <class 'int'>

    # 将一个字符串赋给同一个变量
    x = "Hello, World!"

    # 再次输出变量的值和类型
    print(x, type(x))  # 输出: Hello, World! <class 'str'>

# 测试常量
def test_constant():
    print(math.pi)

# 内置类型
def test_built_in_data_types():
    # 整型
    x = 10
    print(x)

    # 浮点型
    y = 3.14
    print(y)

    # 复数型
    z = 2 + 3j
    print(z)

    # 字符串
    s = "Hello, World!"
    print(s)

    # 列表
    lst = [1, 2, 3, 4, 5]
    print(lst)

    # 元组
    tup = (1, 2, 3)
    print(tup)

    # 字典
    dic = {"name": "Alice", "age": 25}
    print(dic)

    # 集合
    st = {1, 2, 3, 4, 5}
    print(st)

    # 布尔
    flag = True
    print(flag)

    # 字节
    b = b"Hello"
    print(b)

    # 字节数组
    ba = bytearray(b"Hello")
    print(ba)

    # NoneType
    n = None
    print(n)


def main():
    # test_int()
    # test_str()
    # test_float()
    # test_variable()
    # test_constant()
    test_built_in_data_types()


if __name__ == "__main__":
    main()
