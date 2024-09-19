"""
Python 中的线性表
"""


def main():
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


if __name__ == '__main__':
    main()
