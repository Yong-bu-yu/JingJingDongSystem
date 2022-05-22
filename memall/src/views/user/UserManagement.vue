<template>
    <div style="width: 100%;padding: 15px;overflow-y: auto">

        <query ref="query"/>
        <update ref="update"/>
        <remove ref="remove"/>

        <el-card shadow="hover">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>系统</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/system' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>用户模块</el-breadcrumb-item>
                <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            </el-breadcrumb>
        </el-card>
        <el-card shadow="hover" style="margin-top: 15px;">
            <el-card shadow="hover">
                <el-row>
                    <el-col :span="2">
                        <el-button type="danger" @click="toggleSelection">删除选中</el-button>
                    </el-col>
                    <el-col :span="2"></el-col>
                    <el-col :span="16">
                        <el-row :gutter="21">
                            <el-col :span="16">
                                <el-input v-model="tableParams.name" placeholder="请输入姓名，不支付模糊查询"></el-input>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
                            </el-col>
                            <el-col :span="4">
                                <el-button type="primary" @click="queryShow()">详细查询</el-button>
                            </el-col>
                        </el-row>
                    </el-col>
                    <el-col :span="2"></el-col>
                    <el-col :span="2">
                        <el-button type="primary" icon="el-icon-plus" @click="insert">添加</el-button>
                    </el-col>
                </el-row>
            </el-card>
            <el-table
                    ref="multipleTable"
                    @selection-change="handleSelectionChange"
                    :data="tableData.records"
                    border
                    height="450"
                    v-loading="isLoading" element-loading-text="正在加载，请稍后..."
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    style="width: 100%">
                <el-table-column
                        type="selection"
                        :selectable="selectEnable"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="用户ID"
                        width="100"
                        >
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="名称"
                        >
                </el-table-column>
                <el-table-column
                        prop="type"
                        label="类型"
                        width="100"
                        :filters="[{ text: '用户', value: '用户' }, { text: '管理员', value: '管理员' }]"
                        :filter-method="filterTag"
                >
                    <template v-slot="scope">
                        <el-tag
                            :type="scope.row.type === '用户' ? 'primary' : 'success' "
                            disable-transitions>{{scope.row.type}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="password"
                        label="密码"
                        >
                </el-table-column>
                <el-table-column label="操作">
                    <template v-slot="scope">
                        <el-button
                                size="mini"
                                type="warning"
                                icon="el-icon-edit"
                                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                @click="handleDelete(scope.$index, scope.row)" :disabled="scope.row.name == userInfo.userName ? true : false">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    v-model:current-page="tableParams.pageNum"
                    :page-sizes="[10, 20, 30]"
                    v-model:page-size="tableParams.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    style="margin-top: 15px;"
                    background
                    :hide-on-single-page="true"
                    :total="tableData.total">
            </el-pagination>
        </el-card>
    </div>
</template>

<script>
    import {mapActions} from "vuex"
    import query from "./query"
    import update from "./update"
    import remove from "./remove"
    import store from "../../store"

    export default {
        name: "UserManagement",
        components:{
            query,
            update,
            remove
        },
        data(){
            return{
                tableData: {},
                tableParams: {
                    id: null,
                    name: null,
                    type: null,
                    password: null,
                    pageNum: 1,
                    pageSize: 10
                },
                isLoading: false,
                tempTableData: null,
                userInfo: null
            }
        },
        methods:{
            ...mapActions(['GetPages','AdminDeleteList']),
            //筛选类型
            filterTag(value, row) {
                return row.type === value;
            },
            // 编辑
            handleEdit(index, row) {
                this.$refs.update.show(row,this.tableParams)
                // console.log(index, row);
            },
            //删除
            handleDelete(index, row) {
                this.$refs.remove.show(row,index,this.tableData)
                // console.log(index, row);
            },
            //选中删除项目
            toggleSelection(){

                this.$messageBox.confirm(
                    '是否删除此项？',
                    '警告',
                    {
                        confirmButtonText: '是',
                        confirmButtonClass: 'el-button--danger',
                        cancelButtonText: '否',
                        type: 'warning'
                    }
                )
                    .then(()=>{
                        this.AdminDeleteList(this.tempTableData)
                        for (let i in this.tempTableData){
                            this.tableData.records.splice(this.tableData.records.findIndex(value => value.id == this.tempTableData[i].id),1)
                        }
                    })
                    .catch(()=>{
                        this.$message({
                            type: 'info',
                            message: '你取消的删除'
                        })
                    })
            },
            //选中事件
            handleSelectionChange(val) {
                this.tempTableData = val
            },
            //pageSize改变
            handleSizeChange(val) {
                this.tableParams.pageSize = val
                this.GetPages(this.tableParams)
                console.log(`每页 ${val} 条`);
            },
            //pageNum改变
            handleCurrentChange(val) {
                this.tableParams.pageNum = val
                this.GetPages(this.tableParams)
                console.log(`当前页: ${val}`);
            },
            insert(){
                this.$router.push('/system/user/insert')
            },
            queryShow(){
                this.$refs.query.show()
                this.tableParams = this.$refs.query.formData
            },
            query(){
                if(this.tableParams.name == '')
                    this.tableParams.name = null
                new Promise(resolve=>{
                    this.isLoading = true
                    resolve('正在查询，请稍后...')
                }).finally(()=>{
                    setTimeout(()=>{
                        this.isLoading = false
                        this.GetPages(this.tableParams)
                    },500)
                })
            },
            // eslint-disable-next-line no-unused-vars
            selectEnable(row, rowIndex){
                // console.log(row,rowIndex)
                if(row.name != this.userInfo.userName)
                    return true
            }
        },
        mounted() {
            this.GetPages(this.tableParams)
            this.userInfo = {
                userName: store.getters.name
            }
        },
        watch:{
            "$store.state.admin.getPages.records":{
                deep: true,
                // eslint-disable-next-line no-unused-vars
                handler:function (nVal,oVal) {
                    this.tableData = nVal
                }
            }
        }
    }
</script>

<style scoped>

</style>
