<template>
    <el-config-provider :locale="locale">
        <router-view/>
    </el-config-provider>
</template>

<script>
    import {ElConfigProvider} from 'element-plus'
    import zhCn from 'element-plus/lib/locale/lang/zh-cn'

    export default {
        name: "app",
        components:{
            [ElConfigProvider.name]: ElConfigProvider
        },
        data(){
            return{
                locale: zhCn
            }
        }
    };
</script>

<style>
    body {
        margin: 0;
        padding: 0;
    }

    #app {
        font-family: "Avenir", Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        /*margin-top: 60px;*/
        padding: 0;
        margin: 0;
        height: 100%;
        width: 100%;
        position: absolute;
    }
</style>
