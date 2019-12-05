<template>
  <div class="hello">
      <h3>这里是首页，需要登录后才能访问</h3>
      <router-link to="/request">Request页面</router-link>
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

</style>
