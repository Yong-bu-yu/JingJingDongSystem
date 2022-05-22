import axios from 'axios'
import {getToken} from "./token";
import store from '../store'

const server = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 50000, // 请求超时uploadBuildExcel
    responseType: 'json',
    headers: {
        // 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        'token': getToken(),
        'Authorization': getToken()
    },
})

server.interceptors.request.use(config=>{
    // if (config.method === 'get') {
    //     //  给data赋值以绕过if判断
    //     config.data = true
    // }
    if(store.getters.token)
        config.headers['Authentication'] = store.getters.token
    // console.log(config.headers)
    return config;
})

// server.interceptors.response.use(
// //     res=>{
// //     console.log(res)
// // },
//         error => {
//     const code = error.toString().match(/\s\d{3}(\s|$)/)?.[0]?.trim()
//     console.log(code)
// })

export default server
