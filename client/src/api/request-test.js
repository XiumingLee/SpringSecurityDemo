import request from '@/utils/request';
import { formPost } from "../utils/request";

export function jsonRequest(params){
    return request({
        url: '/jsonRequest',
        method: 'post',
        data:params
    })
}

export function formRequest(params){
    return formPost('/formRequest',params);
}
