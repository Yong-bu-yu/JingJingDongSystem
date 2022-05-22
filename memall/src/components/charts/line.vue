<template>
    <el-card body-style="width: 100%;height: 100%" shadow="hover">
        <div ref="line" style="height: 100%;width: 100%"></div>
    </el-card>
</template>

<script>
    export default {
        name: "lines",
        props:{
            title:null,
            colTitle: null,
            data: null
        },
        mounted() {
            let data = this.$echarts.init(this.$refs.line)
            data.setOption({
                title: {
                    text: this.title,
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: this.colTitle
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: this.data,
                        type: 'line',
                        areaStyle: {},
                        label: {
                            show: true,
                            position: 'top',
                            color: '#000'
                        }
                    }
                ],
                dataZoom: [
                    {
                        type: 'inside'
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
                }
            })
        }
    }
</script>

<style scoped>

</style>
