<template>
  <div class="hello">
      <!--<h1>{{ msg }}</h1>-->
      <h3>这里是首页</h3>
      <router-link to="/request">发送请求页面</router-link>
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
