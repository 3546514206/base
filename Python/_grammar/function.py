"""
返回 5
"""


def my_func():
    a = 1
    b = 4
    return a + b


def build_in_def():
    print(help(abs))
    print(abs(-0.99))
    print(help(my_func))
    print(max(2, 3, 1, -5))


def main():
    build_in_def()


"""
函数
"""
if __name__ == "__main__":
    main()
