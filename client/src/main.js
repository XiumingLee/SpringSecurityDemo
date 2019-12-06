import Vue from 'vue';
import App from './App.vue';

Vue.config.productionTip = false;

import router from './router'; // 路由配置
import './router/routerGuards'; // 导航卫士

// ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
// ElementUI - 结束

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
