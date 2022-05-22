import {userLogout} from "../../utils/public/logout"
import {setToken} from "../../utils/token"
import store from "../index"
import message from 'element-plus/es/el-message/index'

const logout = {
    state:{},
    mutations:{},
    actions:{
        async Logout(){
            await userLogout().then(r => {
                console.log(r.data)
                setToken(undefined)
                store.dispatch('SET_TOKEN',undefined)
                store.dispatch('Info')
                message({
                    type: 'success',
                    message: '退出成功'
                })
            })
        }
    }
}

export default logout
