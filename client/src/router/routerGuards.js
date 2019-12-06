// 导航卫士
import router from './index'
import system_utils from '@/utils/system-utils'

// 白名单
const whiteList = ['/login']
// 这里是在路由跳转之前，还有其他的方式，参考官方文档
router.beforeEach((to, from, next) => {
    if (whiteList.indexOf(to.path) !== -1) { // 在白名单中，直接进入
        next();
    } else {
        if (system_utils.isLogin()){
            next();
        }else { // 未登录则记录下跳转的路由，调到转到登录页
            system_utils.setToUrl(to.path);
            next("/login")
        }
    }

    // 动态添加路由.添加的和index.js格式一样即可。路由数组
    // router.addRoutes(routes: RouteConfig[]);

})