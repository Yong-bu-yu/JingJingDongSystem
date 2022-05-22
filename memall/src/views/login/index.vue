<template>
    <el-container>
        <el-header>
            <h1 style="margin-top: 95px">京京东电子商城后台系统</h1>
        </el-header>
        <el-main>
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px" style="margin-top: 10%;">
                <el-row type="flex" justify="center" align="top">
                    <el-col :span="12">
                        <el-form-item label="用户名" prop="username" style="color: #fff;">
                            <el-input v-model="formData.username" placeholder="请输入用户名" show-word-limit clearable
                                      prefix-icon='el-icon-user-solid' :style="{width: '100%'}"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row type="flex" justify="center" align="top">
                    <el-col :span="12">
                        <el-form-item label="密码" prop="password" style="color: #fff;">
                            <el-input v-model="formData.password" placeholder="请输入密码" clearable
                                      prefix-icon='el-icon-lock'
                                      show-password :style="{width: '100%'}"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row type="flex" justify="center" align="top">
                    <el-col :span="12">
                        <el-form-item>
                            <el-button type="primary" @click="submitForm" :icon="btn_icon" :disabled="disabled">登录</el-button>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item>
                            <el-button @click="resetForm" icon="el-icon-refresh-right" >重置</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </el-main>
        <Footer></Footer>
    </el-container>
</template>

<script>
    import Footer from "@/components/footer/index.vue"
    import qs from "qs"
    import {mapActions} from "vuex"
    import store from "../../store"

    export default {
        name: "Login",
        components: {
            Footer
        },
        data() {
            return {
                formData: {
                    username: "",
                    password: ""
                },
                rules: {
                    username: [{
                        required: true,
                        message: '请输入用户名',
                        trigger: 'blur'
                    }],
                    password: [{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    }],
                },
                disabled:false,
                btn_icon:"el-icon-position",
            }
        },
        methods: {
            ...mapActions(['Login','Info']),
            submitForm() {
                this.$refs['elForm'].validate(valid => {
                    if (!valid)
                        return
                    // TODO 提交表单
                    new Promise(((resolve) => {
                        this.Login(qs.stringify(this.formData))
                        this.disabled = true
                        this.btn_icon = "el-icon-loading"
                        resolve("表单提交成功")
                    })).then(r =>{
                        console.log(r)
                        setTimeout(()=>{
                            this.disabled = false
                            this.btn_icon = "el-icon-position"
                            let role = store.getters.role.toString().replace('ROLE_','');
                            if(role == '用户' || role == '')
                                this.$message({
                                    type: 'error',
                                    message: '登录失败'
                                })
                            else
                                this.$message({
                                    type: 'success',
                                    message: '登录成功，当前用户权限为：' + role
                                })
                            this.$router.push('/system')
                        },1000)
                    })
                })
            },
            resetForm() {
                this.$refs['elForm'].resetFields()
            }
        }
    }
</script>

<style scoped>

    .el-container {
        margin: 0;
        left: 0;
        right: 0;
        bottom: 0;
        top: 0;
        position: absolute;

        background-color: #ced9e0;
        opacity: 1;
        background-image:  linear-gradient(135deg, #b4c3d3 25%, transparent 25%), linear-gradient(225deg, #b4c3d3 25%, transparent 25%), linear-gradient(45deg, #b4c3d3 25%, transparent 25%), linear-gradient(315deg, #b4c3d3 25%, #ced9e0 25%);
        background-position:  40px 0, 40px 0, 0 0, 0 0;
        background-size: 80px 80px;
        background-repeat: repeat;
    }
</style>
