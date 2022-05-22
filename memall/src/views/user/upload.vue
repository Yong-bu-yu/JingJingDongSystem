<template>
    <el-dialog @close="onClose" title="文件上传" :model-value="dialogVisible">
        <!--            上传-->
        <el-upload
                drag
                action="http://localhost:8080/admin/upload"
                style="margin-bottom: 15px"
                accept=".xlsx,.xls"
                :http-request="uploadSectionFile"
                ref="upload"
                :auto-upload="false"
                :limit="1"
        >
            <div class="el-icon-upload"></div>
            <div class="el-upload__text">
                请把文件拖拽在这儿 或者 <em>点击此处</em>
            </div>
            <template #tip>
                <div class="el-upload__tip">
                    批量插入用户信息，文件格式为Excel，扩展名为*.xlsx,*.xls
                </div>
            </template>
        </el-upload>
        <el-progress :percentage="percentage" :status="status" style="margin-left: 30px;" v-show="progressShow"/>
        <!---->
        <template v-slot:footer>
            <el-button @click="close">取消</el-button>
            <el-button type="primary" @click="handelConfirm">上传</el-button>
        </template>
    </el-dialog>
</template>

<script>
    import {mapActions} from "vuex"

    export default {
        name: "upload",
        data() {
            return {
                dialogVisible: false,
                percentage: 0,
                status: '',
                progressShow: false
            }
        },
        methods: {
            ...mapActions(['AdminUpload']),
            onClose(){
                this.dialogVisible = false
                this.$refs.upload.clearFiles()
            },
            close(){
                this.dialogVisible = false
                this.$refs.upload.clearFiles()
            },
            handelConfirm() {
                this.$refs.upload.submit()
            },
            show(){
                this.dialogVisible = true
            },
            uploadSectionFile(param){
                let upData = new FormData()
                upData.append('file',param.file)
                new Promise((resolve)=>{
                    this.progressShow = true
                    resolve('提交成功')
                }).then(r =>{
                    this.percentage = 90
                    console.log(r)
                    let s = setInterval(()=>{
                        this.percentage++
                        if(this.percentage >= 100){
                            this.status = 'success'
                            this.AdminUpload(upData)
                            if(this.status == 'success')
                                setTimeout(()=>{
                                    this.progressShow = false
                                },500)
                            clearInterval(s)
                        }
                    },150)
                })
            }
        }
    }
</script>

<style scoped>

</style>
