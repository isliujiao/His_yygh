import request from '@/utils/request'

const api_name = `/api/msm`

export default {
    //手机登录
    // sendCode(mobile) {
    //     return request({
    //         url: `${api_name}/send/${mobile}`,
    //         method: `get`
    //     })
    // },
    //邮箱登录
    sendEmailCode(mobile) {
        return request({
            url: `${api_name}/sendEmailCode/${mobile}`,
            method: `get`
        })
    }
}