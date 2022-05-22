import {createRouter, createWebHashHistory} from 'vue-router'
import store from '../store'

import Login from '../views/login/index.vue'
import System from '../views/system/index.vue'

import {getToken} from '../utils/token'
import jwtDecode from 'jwt-decode'

const routes = [
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
        meta: {
            title: '关于'
        }
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: {
            title: '登录',
            requireAuth: true
        }
    },
    {
        path: '/system',
        name: 'System',
        component: System,
        meta: {
            title: '系统',
            role: '管理员'
        },
        children: [{
            path: '/system/user/insert',
            name: 'insert',
            component: () => import('../views/user/insert.vue'),
            meta: {
                title: '用户添加'
            }
        }, {
            path: '/system/user/management',
            name: 'UserManagement',
            component: () => import('../views/user/UserManagement.vue'),
            meta: {
                title: '用户查询'
            }
        }, {
            path: '/system/home',
            name: 'home',
            component: () => import('../views/Home.vue')
        }]
    }, {
        path: '/404',
        name: '404',
        meta: {
            title: '系统'
        },
        component: () => import('../views/404.vue')
    },

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
    if (to.path == '/login'){
        next()
        return
    }
    let cookiesToken = getToken()
    let storeToken = store.getters.token;
    let role = store.getters.role.toString().replace('ROLE_', '')
    // eslint-disable-next-line no-unused-vars
    let storeExp
    if (storeToken == undefined) {
        storeExp = Date.now() + 1000
    } else storeExp = jwtDecode(storeToken).exp * 1000
    let nowExp = Date.now()
    if (storeExp < nowExp) {
        cookiesToken = undefined
        storeToken = undefined
    }
    if (to.path == '/' || to.path == '/system')
        next('/system/home')
    if (cookiesToken == storeToken &&
        cookiesToken != undefined && storeToken != undefined &&
        role != '用户' && role != '' && role == '管理员')
        next()
    else if (to.path == '/login')
        next()
    else
        next('/login')
})
