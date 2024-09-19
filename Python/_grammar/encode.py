"""
python 支持 Unicode 编码
"""


def main():
    print('\u4e2d\u6587')
    print(ord('A'))
    print(chr(25991))
    print(b'ABC'.decode('ascii'))
    print(b'\xe4\xb8\xad\xe6\x96\x87'.decode('utf-8'))
    # 1个中文字符经过UTF-8编码后通常会占用3个字节，而1个英文字符只占用1个字节。
    print(len(b'\xe4\xb8\xad\xe6\x96\x87'))
    print(len(b'ABC'))


if __name__ == "__main__":
    main()
