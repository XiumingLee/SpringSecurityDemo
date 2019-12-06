import { formPost } from "../utils/request";

export function doLogin(params){
    return formPost('/login',params);
}