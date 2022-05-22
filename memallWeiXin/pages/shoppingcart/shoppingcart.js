// pages/shoppingcart/shoppingcart.js
import axios from '../../utils/axios'

Page({

    /**
     * 页面的初始数据
     */
    data: {
        result: [],
        isAll: false,
        number: 0,
        userShoppingCart: [],
        price: 0
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {},

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        axios.post('/user/shoppingCart/select')
            .then(r => {
                this.setData({
                    userShoppingCart: r.data
                })
            }).catch(r => {
                wx.showModal({
                    title: '是否进行登录？',
                    success(res) {
                        if (res.confirm)
                            wx.navigateTo({
                                url: '/pages/login/login',
                            })
                    }
                })
            })
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

    onChange(event) {
        this.setData({
            result: event.detail,
        })
        if (this.data.result.length == 0) {
            this.setData({
                price: 0,
                number: 0
            })
        } else {
            if (this.data.result.length == this.data.userShoppingCart.length)
                this.setData({
                    isAll: true
                })
            else
                this.setData({
                    isAll: false
                })
            this.setData({
                price: 0,
                number: 0
            })
            this.data.userShoppingCart.forEach(item => {
                let r = this.data.result.toString().split(';')
                if (r.includes(item.name))
                    this.setData({
                        price: this.data.price += item.quantity * item.price * 100,
                        number: ++ this.data.number
                    })
            })
        }
    },
    onSelectAll(event) {
        let r = []
        this.setData({
            isAll: event.detail
        })
        if (this.data.isAll) {
            this.data.userShoppingCart.forEach(item => {
                r.push(item.shoppingCartID.toString()+';'+item.name+';'+item.type+';'+item.quantity+';'+item.price)
                this.setData({
                    price: this.data.price += item.quantity * item.price * 100,
                    number: ++ this.data.number
                })
            })
            this.setData({
                result: r
            })
        } else
            this.setData({
                result: [],
                price: 0,
                number: 0
            })
    },
    onNumberChange(event) {
        let i = -1
        axios.post('/user/shoppingCart/update', {
            id: event.target.dataset.shoppingcartid,
            commodityID: event.target.id,
            quantity: event.detail
        }).then(r => {
            this.onShow()
        })
    },
    insertOrders(){
        if(this.data.result.length != 0)
            wx.navigateTo({
              url: `/pages/orders/orders?name=${this.data.result}`,
            })
    }
})