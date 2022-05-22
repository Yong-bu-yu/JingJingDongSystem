import axios from "../../utils/axios"
import formatTime from "../../utils/util"
// pages/user/user.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        show: {
            userInfo: false,
            resetPassword: false,
            orders: false
        },
        user: {
            username: '',
            type: '',
            oldpassword: '',
            newpassword: ''
        },
        orders: [],
        time: formatTime
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

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
        axios.get('/user/info')
            .then(r => {
                if (r.statusCode == 200)
                    this.setData({
                        user: {
                            username: r.data.data.principal,
                            type: r.data.data.authorities,
                            oldpassword: '',
                            newpassword: ''
                        }
                    })

            })
            .catch(r => {
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
    userInfo() {
        this.setData({
            show: {
                userInfo: true
            }
        })
        this.onShow()
    },
    resetPassword() {
        this.setData({
            show: {
                resetPassword: true
            }
        })
        if (this.data.user.username.length != 0 && this.data.user.oldpassword.length == 0 && this.data.user.newpassword.length == 0)
            axios.post('/user/resetPassword', {
                username: this.data.user.username
            }).then(r => {
                wx.showToast({
                    title: '更新成功',
                })
            }).catch(r => [
                wx.showToast({
                    title: '更新失败',
                })
            ])
        else {
            axios.post('/user/resetPassword', {
                oldpassword: this.data.user.oldpassword,
                newpassword: this.data.user.newpassword
            }).then(r => {
                wx.showToast({
                    title: '更新成功',
                })
            }).catch(r => [
                wx.showToast({
                    title: '更新失败',
                })
            ])
        }

    },
    orders() {
        this.setData({
            show: {
                orders: true
            }
        })
        axios.post('/user/orders/select')
            .then(r => {
                this.setData({
                    orders: r.data
                })
            }).catch(r => {
                wx.showModal({
                    title: '请重新进行登录',
                    success(res) {
                        if (res.confirm)
                            wx.navigateTo({
                                url: '/pages/login/login',
                            })
                    }
                })
            })
    },
    onClose() {
        this.setData({
            show: {
                userInfo: false,
                resetPassword: false,
                orders: false
            }
        })
    },
    logout() {
        let that = this
        wx.showModal({
            title: '是否退出登录？',
            success(res) {
                if (res.confirm) {
                    axios.post('/user/logout')
                    wx.showToast({
                        title: '退出成功',
                    })
                    setTimeout(() => {
                        that.onShow()
                    }, 1000)
                }
            }
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
                        that.orders()
                    }).catch(r => {
                        that.orders()
                    })
            }
        })
    }
})