<template>
    <div style="width: 100%;padding: 15px;overflow-y: auto;">
        <el-card shadow="hover">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>系统</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/system/home' }">首页</el-breadcrumb-item>
            </el-breadcrumb>
        </el-card>
        <el-row :gutter="15">
            <el-col :span="12">
                <bar style="margin-top: 15px;width: 100%;height: 300px;"
                     title="商品储量统计图"
                     :col-title="colTitle"
                     :data="chartsData"
                     v-if="chartsData.length != 0"
                />
            </el-col>
            <el-col :span="12">
                <pie style="margin-top: 15px;width: 100%;height: 300px"
                     title="用户购物倾向图"
                     :data="pieData"
                     v-if="pieData.length != 0"
                />
            </el-col>
        </el-row>
        <el-row :gutter="15">
            <el-col>
                <pie style="margin-top: 15px;width: 100%;height: 300px"
                     title="用户订单已支付商品统计图"
                     :data="payData"
                     v-if="payData.length != 0"
                />
            </el-col>
        </el-row>
        <el-row :gutter="15">
            <el-col>
                <lines style="margin-top: 15px; width: 100%; height: 300px"
                       title="支付统计图"
                       :col-title="lineColTile"
                       :data="lineData"
                       v-if="lineData.length != 0"
                />
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import bar from "../components/charts/bar"
    import pie from "../components/charts/pie"
    import lines from "../components/charts/line"
    import moment from "moment"
    import Random from "../mock"
    import {Pages} from "../utils/commodity/getPages"
    import {getShoppingTendency} from "../utils/shopping_cart/getShoppingTendency";
    import {ordersPages} from "../utils/orders/getPages";
    import {getUserPay} from "../utils/orders/getUserPay";

    export default {
        name: "Home",
        components: {
            lines,
            bar,
            pie
        },
        data() {
            return {
                colTitle: [],
                chartsData: [],
                pieData: [],
                lineColTile: [],
                lineData: [],
                payData: []
            }
        },
        mounted() {
        },
        created() {
            let param = {
                pageNum: 1,
                pageSize: 2147483647
            }
            let shopItems = []
            this.colTitle = []
            this.chartsData = []
            Pages(param).then(r => {
                let d = r.data.data.records
                d.forEach(item => {
                    shopItems.push({
                        id: item.id,
                        name: item.name
                    })
                    this.colTitle.push(item.name)
                    this.chartsData.push({
                        value: item.quantity,
                        label: {
                            show: true,
                            position: 'top',
                            color: '#000'
                        },
                        itemStyle: {
                            color: Random.color()
                        }
                    })
                })
            })
            getShoppingTendency().then(r => {
                this.pieData = r.data.data
            })
            ordersPages(param).then(r => {
                let d = r.data.data.records
                let map = new Map()
                d.forEach(item => {
                    if (item.isPay == 1) {
                        if (!map.has(moment(item.time).format('YYYY-MM-DD')))
                            map.set(moment(item.time).format('YYYY-MM-DD'), 1)
                        else {
                            let c = map.get(moment(item.time).format('YYYY-MM-DD'))
                            c++
                            map.set(moment(item.time).format('YYYY-MM-DD'), c)
                        }
                    }
                })
                map.forEach((v, k) => {
                    this.lineColTile.push(k)
                    this.lineData.push(v)
                })
            })

            getUserPay().then(r =>{
                this.payData = r.data
            })
        }
    }
</script>

<style scoped>

</style>
