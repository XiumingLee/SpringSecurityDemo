// 导航卫士
import router from './index'
import Config from '@/config';
import { getToken } from '@/api/request-test'

// 白名单
const whiteList = ['/login','search']
// 这里是在路由跳转之前，还有其他的方式，参考官方文档
router.beforeEach((to, from, next) => {
    if (whiteList.indexOf(to.path) !== -1) { // 在白名单中，直接进入
        next();
    }
    // ### 跳转到单点登录页面
    if (to.path === '/toLogin'){
        // 将之前的页面地址保存起来，方便，单点登录完成后，跳转到指定页面。
        localStorage.setItem("cacheUrl",from.path);
        window.location.href = Config.sso_auth_get_code;
    } else if (to.path === '/auth_code') { // 获取授权码返回的路径，下一步需要获取Token
        let code = to.query.code;
        console.log("授权码是-------------");
        console.log(code);
        console.log("--------------------");
        localStorage.setItem('token',Config.client_basic_token)
        getToken(code).then(res=>{
            console.log(res); //正常
        }).catch(err => {
            console.log(err.response.data.message); //异常
        });
    } else { // 其他处理...
        console.log(`前往的Url：${to.path}`);
        next();
    }
})