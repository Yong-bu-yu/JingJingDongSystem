import {getToken, setToken} from "../../utils/token"
import {userLogin} from "../../utils/public/login"
import store from '../index'

const login = {
    state: {
        token: getToken()
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        }
    },
    actions: {
        async Login({commit}, userInfo) {
            await userLogin(userInfo).then(r => {
                let data = r.data
                let token = data.data
                setToken(token.Authentication)
                store.dispatch('Info')
                commit('SET_TOKEN', token.Authentication)
            })
        }
    }
}

export default login
