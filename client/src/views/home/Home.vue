<template>
  <div class="hello">
      <h3>这里是首页，需要登录后才能访问</h3>
      <router-link class="rl" to="/login">登录页</router-link>
      <router-link class="rl" to="/authority">权限测试页</router-link>
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {
      msg: `Welcome to Xiuming's Vue2.x-Template`,
    }
  }
}
</script>

<style scoped>
.rl{
    margin-left: 5px;
}
</style>
