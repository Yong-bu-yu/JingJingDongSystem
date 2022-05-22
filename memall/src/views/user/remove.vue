<template>
    <div></div>
</template>

<script>
    import {mapActions} from "vuex"
    import qs from "qs"

    export default {
        name: "remove",
        methods:{
            ...mapActions(['AdminDelete']),
            show(param,index,tableData){
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
                    this.AdminDelete(qs.stringify({id:param.id}))
                    tableData.records.splice(index,1)
                })
                .catch(()=>{
                    this.$message({
                        type: 'info',
                        message: '你取消的删除'
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>
