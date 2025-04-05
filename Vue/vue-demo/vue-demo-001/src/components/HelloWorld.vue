<template>
  <div class="hello">
    <div>消息体：{{ msg }}</div>
    <button @click="counter++">计数变化了:{{ counter }}</button>
  </div>

  <div class="world">
    <div>欢迎，{{ userName }}！</div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    userName: {
      // 新增 userName 属性
      type: String,
      default: "Vue开发者"
    }
  },
  data() {
    return {
      msg: "Vue 学习教程",
      counter: 0
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
