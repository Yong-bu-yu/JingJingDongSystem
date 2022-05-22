import {Pages} from "../../utils/admin/getPages";

const getPages = {
    state: {
        data:[]
    },
    mutations: {
        GET_PAGES: (state, data) => {
            state.records = data
        }
    },
    actions: {
        async GetPages({commit},param){
            await Pages(param).then(r => {
                let data = r.data.data
                commit('GET_PAGES', data)
            })
        }
    }
}

export default getPages
