// 系统工具类
export default {
    isLogin: function () {
        let islogin = localStorage.getItem("islogin");
        if (islogin){
            return true;
        }
        return false;
    },
    setLogin: function (){
        localStorage.setItem("islogin","1");
    },
    clearLogin: ()=>{
        localStorage.removeItem("islogin")
    },

    // 设置登录后跳转的路由
    setToUrl: (url)=>{
        localStorage.setItem("toUrl",url);
    },
    getToUrl: ()=>{
        let url = localStorage.getItem("toUrl");
        localStorage.removeItem("toUrl");
        return url ? url : "/";
    }
}