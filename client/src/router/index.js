import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

// 静态路由
const constantRouterMap = [
    {
        path:'/',
        component:()=>import('@/views/home/Home.vue'),
        meta:{ title:'首页' },
        hidden: true
    },
    {
        path:'/login',
        component:()=>import('@/views/login/login'),
        meta:{ title:'登录页面' },
        hidden: true
    },
    {
        path:'/authority',
        component:()=>import('@/views/authority/authority'),
        meta:{ title:'权限测试页面' },
        hidden: true
    },

]

export default new Router({
    routes: constantRouterMap
})