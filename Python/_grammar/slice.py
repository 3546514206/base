"""slice综合案例"""


def trim(source: str) -> str:
    if not isinstance(source, str):
        raise TypeError("str must be a string")

    start = 0
    end = len(source)
    for ch in source:
        if ch == " ":
            start += 1
        else:
            break
    for ch in source[::-1]:
        if ch == " ":
            end -= 1
        else:
            break
    return source[start:end]


def main():
    # 示例 1: 去掉两端空格
    result = trim("  hello  ")
    # 输出: "hello"
    print(result)

    # 示例 2: 没有空格
    result = trim("world")
    # 输出: "world"
    print(result)

    # 示例 3: 全是空格
    result = trim("   ")
    # 输出: ""
    print(result)

    # 示例 4: 参数不是字符串
    try:
        trim(123)
    except TypeError as e:
        # 输出: str must be a string
        print(e)


if __name__ == "__main__":
    main()
