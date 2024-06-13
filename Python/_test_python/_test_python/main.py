a = float(input('请输入身高：(单位为米)'))  # a为身高
b = float(input('请输入体重：(单位为千克)'))  # b为体重
BIM = b / (a * a)
print("BIM指数：" + str(BIM))
if BIM < 18.5:
    print("体重过轻")
if BIM >= 18.5 and BIM < 24.9:
    print("体重正常")
if BIM >= 24.9 and BIM < 29.9:
    print("体重过重")
if BIM >= 29.9:
    print("肥胖")
