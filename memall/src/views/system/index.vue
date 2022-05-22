<template>
    <el-container style="height: 100%">
        <el-header style="padding: 0;">
            <Header></Header>
        </el-header>
        <el-main style="padding: 0;">
            <div style="display: flex;height: 100%">
                <el-menu mode="vertical" background-color="#6f8799" text-color="#fff" active-text-color="#ffd596"
                         style="text-align: left;" :router="true" :collapse="collapse" default-active="-1">
                    <el-menu-item style="text-align: right;">
                        <i :class="collapse_class" style="color: white;font-size: 21px;" @click="isCollapse"
                           v-popover:popover></i>
                        <slot name="tt">
                            <el-popover
                                    ref="popover"
                                    placement="right"
                                    width="200"
                                    trigger="hover"
                                    :content="content">
                            </el-popover>
                        </slot>
                        <template v-slot:tt>
                        </template>
                    </el-menu-item>
                    <el-menu-item index="-1" route="/system/home">
                        <i class="el-icon-s-home" style="color: white"></i>
                        <span>首页</span>
                    </el-menu-item>
                    <el-submenu index="1">
                        <template v-slot:title>
                            <i class="el-icon-s-goods" style="color: white"></i>
                            <span>商品管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="1-1" route="/404">商品的添加</el-menu-item>
                            <el-menu-item index="1-2" route="/404">商品的删除</el-menu-item>
                            <el-menu-item index="1-3" route="/404">商品的修改</el-menu-item>
                            <el-menu-item index="1-4" route="/404">商品的查询</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2">
                        <template v-slot:title>
                            <i class="el-icon-s-order" style="color: white"></i>
                            <span>订单管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="2-1" route="/404">订单的添加</el-menu-item>
                            <el-menu-item index="2-2" route="/404">订单的删除</el-menu-item>
                            <el-menu-item index="2-3" route="/404">订单的修改</el-menu-item>
                            <el-menu-item index="2-4" route="/404">订单的查询</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3">
                        <template v-slot:title>
                            <i class="el-icon-s-goods" style="color: white"></i>
                            <span>购物车管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="3-1" route="/404">购物车的添加</el-menu-item>
                            <el-menu-item index="3-2" route="/404">购物车的删除</el-menu-item>
                            <el-menu-item index="3-3" route="/404">购物车的修改</el-menu-item>
                            <el-menu-item index="3-4" route="/404">购物车的查询</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="4">
                        <template v-slot:title>
                            <i class="el-icon-user-solid" style="color: white"></i>
                            <span>用户管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="4-1" route="/system/user/insert">用户的添加</el-menu-item>
                            <el-menu-item index="4-2" route="/system/user/management">用户的管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
                <router-view></router-view>
            </div>
        </el-main>
        <Footer></Footer>
    </el-container>
</template>

<script>
    import Footer from '../../components/footer/index.vue'
    import Header from '../../components/header/index.vue'

    export default {
        name: "System",
        components: {
            Header,
            Footer
        },
        data() {
            return {
                collapse: true,
                collapse_class: 'el-icon-caret-right',
                content: '展开'
            }
        },
        methods: {
            isCollapse() {
                this.collapse = !this.collapse
                if (this.collapse) {
                    this.content = '展开'
                    this.collapse_class = 'el-icon-caret-right'
                } else {
                    this.content = '收起'
                    this.collapse_class = 'el-icon-caret-left'
                }
            }
        }
    }
</script>
<style scoped>
</style>
