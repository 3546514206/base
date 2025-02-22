"""
提供一个list作为输入，从而创建一个集合
"""


def set_form_list():
    s = set([1, 2, 3])
    print(s)


def normal():
    s = {4, 5, 6}
    print(s)


def main():
    normal()
    set_form_list()


if __name__ == "__main__":
    main()
