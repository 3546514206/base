let object_name = {
    key1: "value1", // 标量
    key2: "value",
    key3: function () {
        // 函数
        console.log(this.key1);
    },
    key4: ["content1", "content2"] //集合
}

object_name.key3();