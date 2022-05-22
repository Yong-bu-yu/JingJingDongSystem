// pages/orders/orders.js
import axios from "../../utils/axios"

Page({

    /**
     * 页面的初始数据
     */
    data: {
        text: '<',
        orders: []
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let temp = []
        let name = options.name
        let orders = name.split(',')
        orders.forEach(item => {
            let o = item.split(';')
            temp.push({
                shoppingCartID: o[0],
                name: o[1],
                type: o[2],
                quantity: o[3],
                sum: o[3] * o[4],
                is_pay: 0
            })
            axios.post('/user/orders/insert',{
                id: o[0]
            }).then(r=>{
                console.log('订单生成成功');
            }).catch(r=>{
                wx.showToast({
                  title: '订单生成失败',
                })
            })
        })
        this.setData({
            orders: temp
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },

    toBack() {
        wx.switchTab({
            url: '/pages/index/index',
        })
    },
    pay(event) {
        let that = this
        wx.showModal({
            title: '是否支付？',
            success(res) {
                if (res.confirm)
                    axios.post('/user/orders/update', {
                        shoppingCarID: event.target.id
                    }).then(r => {
                        let arr = that.data.orders
                        let temp = arr.map((value, index, array) => {
                            if (value.shoppingCartID == event.target.id){
                                value.is_pay = 1
                                return value
                            }
                            return value
                        })
                        that.setData({
                            orders: temp
                        })
                    })
            }
        })
    },
    cancelPay(event) {
        console.log(event.target.id);
        let o = Array.from(this.data.orders)
        let a = o.filter((value, index, array) => index != event.target.id)
        this.setData({
            orders: a
        })
        if (this.data.orders.length == 0)
            wx.switchTab({
                url: '/pages/shoppingcart/shoppingcart',
            })
    }
})