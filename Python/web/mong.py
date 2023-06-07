import pymongo

# 连接到 MongoDB 从节点
# uri = 'mongodb://<用户名>:<密码>@<从节点主机名>:<端口号>/'
client = pymongo.MongoClient('mongodb://root:root@localhost:27017')

# 选择数据库和集合
db = client['local']
collection = db['startup_log']

# 查询数据
result = collection.find()

# 遍历查询结果
for doc in result:
    print(doc)
