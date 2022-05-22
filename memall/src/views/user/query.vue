<template>
    <el-dialog @close="onClose" title="多条件查询" :model-value="dialogVisible">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-form-item label="用户ID" prop="id">
                <el-input v-model="formData.id" placeholder="请输入用户ID" clearable :style="{width: '100%'}" type="number"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="formData.name" placeholder="请输入姓名" clearable suffix-icon='el-icon-user'
                          :style="{width: '100%'}"></el-input>
            </el-form-item>
            <el-form-item label="类型" prop="type">
                <el-select v-model="formData.type" placeholder="请选择类型" clearable :style="{width: '100%'}">
                    <el-option v-for="(item, index) in typeOptions" :key="index" :label="item.label" :value="item.value"
                               ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="formData.password" placeholder="请输入密码" clearable show-password disabled
                          :style="{width: '100%'}"></el-input>
            </el-form-item>
            <el-row type="flex" justify="space-between" align="top">
                <el-form-item label="当前页标" prop="pageNum">
                    <el-select v-model="formData.pageNum" placeholder="请选择当前页标" clearable :style="{width: '100%'}"
                               filterable
                               allow-create default-first-option>
                        <el-option v-for="index in 10" :key="index" :label="index"
                                   :value="index"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="当前总页数" prop="pageSize">
                    <el-select v-model="formData.pageSize" placeholder="请选择当前总页数" clearable :style="{width: '100%'}"
                               filterable
                               allow-create default-first-option>
                        <el-option v-for="index in 100" :key="index" :label="index"
                                   :value="index"></el-option>
                    </el-select>
                </el-form-item>
            </el-row>
        </el-form>
        <template v-slot:footer>
            <el-button @click="close">取消</el-button>
            <el-button type="primary" @click="handelConfirm">查询</el-button>
        </template>
    </el-dialog>
</template>

<script>
    import {mapActions} from "vuex"

    export default {
        name: "query",
        data() {
            return {
                formData: {
                    id: null,
                    name: null,
                    type: "用户",
                    password: null,
                    pageNum: 1,
                    pageSize: 10,
                },
                rules: {
                    id: [{
                        required: false,
                        message: '请输入用户ID',
                        trigger: 'blur'
                    }],
                    name: [{
                        required: false,
                        message: '请输入姓名',
                        trigger: 'blur'
                    }],
                    type: [{
                        required: false,
                        message: '请选择类型',
                        trigger: 'change'
                    }],
                    password: [{
                        required: false,
                        message: '请输入密码',
                        trigger: 'blur'
                    }],
                    pageNum: [{
                        required: true,
                        message: '请选择当前页标',
                        trigger: 'change'
                    }],
                    pageSize: [{
                        required: true,
                        message: '请选择当前总页数',
                        trigger: 'change'
                    }],
                },
                typeOptions: [{
                    "label": "用户",
                    "value": "用户"
                }, {
                    "label": "管理员",
                    "value": "管理员"
                }],
                dialogVisible: false
            }
        },
        methods: {
            ...mapActions(['GetPages']),
            onClose() {
                this.$refs['elForm'].resetFields()
                this.dialogVisible = false
            },
            close() {
                this.$refs['elForm'].resetFields()
                this.dialogVisible = false
            },
            handelConfirm() {
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    if (this.formData.type == '') {
                        this.formData.type = null
                    }
                    this.GetPages(this.formData)
                    this.close()
                })
            },
            show(){
                this.dialogVisible = true
            }
        },
        mounted() {
            this.dialogVisible = false
        }
    }
</script>

<style scoped>

</style>
