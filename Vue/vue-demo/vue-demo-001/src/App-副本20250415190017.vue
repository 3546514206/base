<template>
  <div class="app-container">
    <!-- 导航栏 -->
    <nav class="navbar">
      <router-link
          to="/"
          class="nav-link"
          active-class="active-link"
          exact
      >
        首页
      </router-link>
      <router-link
          to="/about"
          class="nav-link"
          active-class="active-link"
      >
        关于我们
      </router-link>
    </nav>

    <!-- 主要内容区域 -->
    <main class="main-content">
      <router-view></router-view>

      <!-- 交互区域 -->
      <div
          class="interactive-box"
          @click="doSomething"
      >
        <div class="counter-display">
          <span class="label">点击计数：</span>
          <span class="count">{{ count }}</span>
        </div>
        <div class="message-text">{{ msg }}</div>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      count: 0,
      msg: "欢迎体验交互功能"
    }
  },
  methods: {
    doSomething() {
      this.count++
      this.msg = `已进行 ${this.count} 次交互（最后更新：${new Date().toLocaleTimeString()}）`
    }
  }
}
</script>

<style scoped>
.app-container {
  min-height: 100vh;
  background: #f5f7fa;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

.navbar {
  background: #2c3e50;
  padding: 1rem 2rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  gap: 2rem;
}

.nav-link {
  color: #ecf0f1;
  text-decoration: none;
  font-size: 1.1rem;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.3s ease;
}

.nav-link:hover {
  background: #34495e;
  transform: translateY(-2px);
}

.active-link {
  color: #42b983;
  border-bottom: 2px solid #42b983;
}

.main-content {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 2rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.interactive-box {
  margin-top: 2rem;
  padding: 2rem;
  background: #f8f9fa;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
  border: 1px solid #e9ecef;
}

.interactive-box:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  background: #fff;
}

.counter-display {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1rem;
}

.label {
  color: #6c757d;
  font-weight: 500;
}

.count {
  font-size: 1.5rem;
  color: #42b983;
  font-weight: bold;
}

.message-text {
  color: #495057;
  font-size: 1rem;
  line-height: 1.6;
}
</style>