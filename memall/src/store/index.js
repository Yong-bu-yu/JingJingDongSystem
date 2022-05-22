import {createStore} from 'vuex'
import persistedState from "./persistedstate";
import user from "./user/user"
import admin from "./admin/admin"

import getters from "./getters";

export default createStore({
    modules: {
        user,
        admin
    },
    getters,
    plugins: [persistedState]
})
