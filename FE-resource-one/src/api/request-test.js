import { get,formPost } from "../utils/request";
import Config from '@/config';

export function getRequest(url){
    return get(url,null);
}

export function getToken(code) {
    let params = {
        code,
        client_id:Config.client_id,
        client_secret:Config.client_secret,
        scope:Config.scope,
        grant_type:'authorization_code',
        redirect_uri:'http://127.0.0.1:3000/auth_code'
    }
    return formPost(Config.sso_auth_token,params);
}

