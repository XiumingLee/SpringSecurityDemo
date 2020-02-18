/**
 * @description 系统全局配置
 */
export default {

  client_id:'client1',
  client_secret:'123456',
  scope:'all',
  client_basic_token:'Basic Y2xpZW50MToxMjM0NTY=',

  /** 请求超时时间，毫秒（默认2分钟） */
  request_timeout: 1200000,
  /** 请求的baseUrl */
  request_baseUrl: '/api',
  /** 单点登录的地址 */
  sso_auth_get_code: 'http://127.0.0.1:8001/authServer/oauth/authorize?response_type=code&client_id=client1&redirect_uri=http://127.0.0.1:3000/auth_code&scope=all',
  sso_auth_token: 'http://127.0.0.1:8001/authServer/oauth/token',
}
