import {userInfo} from '../../utils/info'

const info = {
    state: {
        id: '',
        name: '',
        role: ''
    },
    mutations: {
        SET_ID(state, id){
            state.id = id
        },
        SET_NAME: (state, name) => {
            state.name = name
        },
        SET_ROLE: (state, role) => {
            state.role = role
        }
    },
    actions: {
        async Info({commit}) {
            await userInfo().then(r => {
                let data = r.data.data
                if (r.status == 200) {
                    commit('SET_ID', data.id)
                    commit('SET_NAME', data.principal)
                    commit('SET_ROLE', data.authorities)
                }
            })
        }
    }
}

export default info
