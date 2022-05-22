<template>
    <div style="width: 100%;padding: 15px;overflow-y: auto">
        <el-card shadow="hover">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>系统</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/system' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>用户管理</el-breadcrumb-item>
                <el-breadcrumb-item>用户添加</el-breadcrumb-item>
            </el-breadcrumb>
        </el-card>
        <el-card shadow="hover" style="margin-top: 15px;">

            <upload ref="upload"/>
            <el-button type="primary" style="margin-bottom: 15px;" @click="upload">点击此处上传</el-button>

            <el-form :model="formData" :rules="rules" label-width="100px" ref="elForm" size="medium">
                <el-form-item label="用户名" prop="name">
                    <el-input :style="{width: '100%'}" clearable placeholder="请输入名用户名" suffix-icon='el-icon-user-solid'
                              v-model="formData.name"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type">
                    <el-select v-model="formData.type" placeholder="请选择类型" :style="{width: '100%'}">
                        <el-option v-for="(item, index) in typeOptions" :key="index" :label="item.label" :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input :style="{width: '100%'}" clearable placeholder="请输入密码" show-password
                              v-model="formData.password"></el-input>
                </el-form-item>
                <el-form-item size="large">
                    <el-button @click="submitForm" type="primary" icon="el-icon-s-promotion">提交</el-button>
                    <el-button @click="resetForm" icon="el-icon-refresh-right">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
    import {mapActions} from "vuex"
    import upload from "./upload.vue";
    // eslint-disable-next-line no-unused-vars
    import qs from "qs"

    export default {
        name: "insert",
        components:{
            upload
        },
        data() {
            return {
                formData: {
                    name: undefined,
                    type: "用户",
                    password: undefined,
                },
                rules: {
                    name: [{
                        required: true,
                        message: '请输入名用户名',
                        trigger: 'blur'
                    }],
                    type: [{
                        required: true,
                        message: '请至少选择一个类型',
                        trigger: 'change'
                    }],
                    password: [{
                        required: true,
                        message: '没有密码或密码太短或太长了，密码长度在6到24位之间',
                        trigger: 'blur',
                        min: 6,
                        max: 24
                    }],
                },
                typeOptions: [{
                    "label": "用户",
                    "value": "用户",
                    "id": 101
                }, {
                    "label": "管理员",
                    "value": "管理员",
                    "id": 100
                }]
            }
        },
        methods: {
            ...mapActions(['AdminInsert']),
            submitForm() {
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    this.AdminInsert(qs.stringify(this.formData))
                })
            },
            resetForm() {
                this.$refs['elForm'].resetFields()
            },
            upload(){
                this.$refs.upload.show()
            }
        }
    }
</script>

<style scoped>

</style>
