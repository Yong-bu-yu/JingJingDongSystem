<template>
    <div class="header">
        <el-row>
            <el-col :span="8">
                <div style="line-height: 20px;">
                    <router-link to="/system/home" style="text-decoration: none; color: #fff;">
                        <h1 style="">京东东电子商城系统后台</h1>
                    </router-link>
                </div>
            </el-col>
            <el-col :span="13">
                <div>
                </div>
            </el-col>
            <el-col :span="3">
                <div>
                    <el-menu background-color="#b4c3d3" text-color="#fff" mode="horizontal" @select="handleSelect">
                        <el-submenu index="1" :popper-append-to-body="true" style="width: 15%;">
                            <template v-slot:title>
                                <el-avatar style="background-color: white;color: #b4c3d3">{{username}}</el-avatar>
                            </template>
                            <el-menu-item index="1-1">回到首页</el-menu-item>
                            <el-menu-item index="1-2">修改密码</el-menu-item>
                            <el-menu-item index="1-3">退出</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </div>
            </el-col>
        </el-row>
        <update ref="update"/>
    </div>
</template>

<script>
    import update from "../../views/user/update";
    import store from "../../store"
    import {mapActions} from "vuex"

    export default {
        name: "Header",
        components:{
            update
        },
        data(){
            return{
                username: ''
            }
        },
        methods: {
            ...mapActions(['Logout']),
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
                switch (key) {
                    case '1-1':
                        this.$router.push('/system')
                        break;
                    case '1-2':
                        // eslint-disable-next-line no-case-declarations
                        let userInfo = {
                            id: store.getters.id,
                            name: store.getters.name,
                            type: store.getters.role,
                            password: null
                        }
                        this.$refs.update.show(userInfo,null)
                        break;
                    case '1-3':
                        new Promise(resolve => {
                            this.Logout()
                            window.sessionStorage.clear()
                            resolve('退出成功')
                        }).finally(()=>{
                            setTimeout(()=>{
                                this.$message({
                                    type: 'success',
                                    message: '退出成功'
                                })
                                this.$router.push('/login')
                            },500)
                        })
                        break;
                }
            }
        },
        mounted() {
            this.username = store.getters.name
        }
    }
</script>

<style scoped>
    .header {
        background-color: #b4c3d3;
        height: 100%;
    }
</style>
