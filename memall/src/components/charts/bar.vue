<template>
    <el-card body-style="width: 100%;height: 100%" shadow="hover">
        <div ref="bar" style="height: 100%;width: 100%"></div>
    </el-card>
</template>

<script>
    export default {
        name: "bar",
        props:{
            title: null,
            colTitle: null,
            data: null
        },
        mounted() {
            let data = this.$echarts.init(this.$refs.bar)
            data.setOption({
                title: {
                    text: this.title,
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    data: this.colTitle
                },
                yAxis: {
                    type: 'value'
                },
                //数据
                series: [
                    {
                        data: this.data,
                        type: 'bar'
                    }
                ],
                tooltip: {
                    trigger: 'axis'
                },
                aria: {
                    enabled: true,
                    decal: {
                        show: true
                    }
                },
                dataZoom: [
                    {
                        type: 'inside'
                    }
                ]
            })
        }
    }
</script>

<style scoped>

</style>
