// pages/index/index.js

import axios from '../../utils/axios'

var tempData = new Map()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        commodityList: null,
        records: []
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
        wx.request({
            url: 'http://localhost:8080/commodityList',
            success: res => {
                let commodityInfo = res.data
                this.setData({
                    commodityList: commodityInfo.data,
                    records: commodityInfo.data.records
                })
            }
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

    commodityLoad() {
        let current = this.data.commodityList.current
        current++
        let pageSize = 10
        let tempRecords = this.data.records
        wx.request({
            url: `http://localhost:8080/commodityList?pageNum=${current}&pageSize=${pageSize}`,
            method: 'GET',
            success: res => {
                let commodityInfo = res.data
                if (commodityInfo.data.records.length != 0) {
                    // tempRecords.push.apply(tempRecords, commodityInfo.data.records)
                    tempRecords.forEach(item => tempData.set(item.id, item))
                    commodityInfo.data.records.forEach(item => tempData.set(item.id, item))
                    tempRecords = []
                    tempData.forEach(item => tempRecords.push(item))
                    tempData = new Map()
                    this.setData({
                        records: tempRecords,
                        commodityList: commodityInfo.data
                    })
                }
            }
        })
    },

    onSearch(event) {
        let name = event.detail
        let current = 1
        let pageSize = 10
        wx.request({
            url: `http://localhost:8080/commodityList?name=${name}&pageNum=${current}&pageSize=${pageSize}`,
            method: 'GET',
            success: res => {
                let commodityInfo = res.data
                this.setData({
                    records: commodityInfo.data.records,
                    commodityList: commodityInfo.data
                })
            }
        })
    },

    insertShoppingCart(event) {
        console.log(event.target.id);
        axios.post('/user/shoppingCart/insert', {
                commodityID: event.target.id,
                quantity: 1
            })
            .then(r => {
                if (r.statusCode == 500)
                    wx.showToast({
                        title: '已经有该商品了',
                    })
                else
                    wx.showToast({
                        title: '添加成功',
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
    }
})