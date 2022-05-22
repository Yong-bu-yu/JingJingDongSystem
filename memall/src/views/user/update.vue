<template>
    <el-dialog @open="onOpen" @close="onClose" title="更新用户信息" :model-value="dialogVisible">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-form-item label="用户ID" prop="id">
                <el-input v-model="formData.id" placeholder="请输入用户ID" readonly :disabled='true' clearable
                          :style="{width: '100%'}"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="formData.name" placeholder="请输入姓名" clearable suffix-icon='el-icon-user' :disabled="formData.name == '永不语'"
                          :style="{width: '100%'}"></el-input>
            </el-form-item>
            <el-form-item label="类型" prop="type">
                <el-select v-model="formData.type" placeholder="请选择类型" clearable :style="{width: '100%'}" :disabled="formData.name == '永不语'">
                    <el-option v-for="(item, index) in typeOptions" :key="index" :label="item.label" :value="item.value"
                               :disabled="item.disabled"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="formData.password" placeholder="请输入密码" clearable show-password
                          :style="{width: '100%'}"></el-input>
            </el-form-item>
        </el-form>
        <template v-slot:footer>
            <el-button @click="close">取消</el-button>
            <el-button type="primary" @click="handelConfirm">确定</el-button>
        </template>
    </el-dialog>
</template>

<script>
    import qs from "qs"
    import {mapActions} from "vuex"

    export default {
        name: "update",
        data(){
            return {
                formData: {
                    id: undefined,
                    name: undefined,
                    type: "用户",
                    password: undefined,
                },
                rules: {
                    id: [{
                        required: true,
                        message: '请输入用户ID',
                        trigger: 'blur'
                    }],
                    name: [{
                        required: true,
                        message: '请输入姓名',
                        trigger: 'blur'
                    }],
                    type: [{
                        required: true,
                        message: '请选择类型',
                        trigger: 'change'
                    }],
                    password: [{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    }],
                },
                typeOptions: [{
                    "label": "用户",
                    "value": "用户"
                }, {
                    "label": "管理员",
                    "value": "管理员"
                }],
                dialogVisible:false,
                tableParams: {
                    id: null,
                    name: null,
                    type: null,
                    password: null,
                    pageNum: 1,
                    pageSize: 10
                }
            }
        },
        methods:{
            ...mapActions(['AdminUpdate','GetPages']),
            show(param,tableParams){
                this.dialogVisible = true
                this.formData = qs.parse(qs.stringify(param))
                this.tableParams = tableParams
                // console.log(param)
            },
            onOpen() {},
            onClose() {
                this.dialogVisible = false
                this.$refs['elForm'].resetFields()
            },
            close() {
                this.dialogVisible = false
            },
            handelConfirm() {
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    this.AdminUpdate(qs.stringify(this.formData))
                    setTimeout(()=>{
                        this.GetPages(this.tableParams)
                    },100)
                    this.close()
                })
            },
        }
    }
</script>

<style scoped>

</style>
