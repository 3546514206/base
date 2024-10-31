let object_name = {
    // 标量
    key1: "value1",
    key2: "value",
    key3: function () {
        // 函数
        console.log(this.key1);
    },
    // 集合
    key4: ["content1", "content2"]
}

object_name.key3();