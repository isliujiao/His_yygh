import request from '@/utils/request'

const api_name = `/api/user`

export default {
    //手机登录
    // login(userInfo) {
    //     return request({
    //         url: `${api_name}/login`,
    //         method: `post`,
    //         data: userInfo
    //     })
    // },
    //邮箱登录
    login(userInfo) {
        return request({
            url: `${api_name}/login/email`,
            method: `post`,
            data: userInfo
        })
    },
    //用户认证
    saveUserAuth(userAuth) {
        return request({
            url: `${api_name}/auth/userAuth`,
            method: `post`,
            data: userAuth
        })
    },
    //根据userid获取用户信息
    getUserInfo(userAuth) {
        return request({
            url: `${api_name}/auth/getUserInfo`,
            method: `get`
        })
    }

}