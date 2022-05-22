<template>
    <el-card body-style="width: 100%;height: 100%" shadow="hover">
        <div ref="pie" style="height: 100%;width: 100%"></div>
    </el-card>
</template>

<script>
    export default {
        name: "pie",
        props:{
            title:null,
            data: null
        },
        mounted() {
            let data = this.$echarts.init(this.$refs.pie)
            data.setOption({
                title: {
                    text: this.title,
                    left: 'center'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    top: 20,
                    bottom: 20,
                },
                series: [
                    {
                        type: 'pie',
                        radius: [30, 100],
                        center: ['50%', '50%'],
                        roseType: 'area',
                        itemStyle: {
                            borderRadius: 8
                        },
                        data: this.data,

                    }
                ],
                tooltip: {},
                aria: {
                    enabled: true,
                    decal: {
                        show: true
                    }
                }
            })
        }
    }
</script>

<style scoped>

</style>
