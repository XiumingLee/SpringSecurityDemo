import service from "../utils/request";

const AuthorityService = {

    doNeedAdmin:(params = {})=>{
        return service({
            method: 'get',
            url: `/needAdmin`,
            params: params
        });
    },

    doNeedRoot:(params = {})=>{
        return service({
            method: 'post',
            url: `/needRoot`,
            data: params
        });
    },


}

export default AuthorityService