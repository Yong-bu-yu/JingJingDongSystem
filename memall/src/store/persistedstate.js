import createPersistedState from "vuex-persistedstate";

const persistedState = createPersistedState({
    storage: window.sessionStorage,
    reducer(val){
        return{
            user: {
                info: val.user.info,
                token: val.user.login.token
            }
        }
    }
})

export default persistedState
