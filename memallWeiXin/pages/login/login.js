// index.js
// 获取应用实例

const app = getApp()

Page({
  data: {
    user: {
      username:'',
      password:''
    },
    animationData: {},
    id: '',
    color: ''
  },
  Login(res) {
    console.log(res.detail.value);
    let that = this;
    this.setData({
      user: {
        username: res.detail.value.userName,
        password: res.detail.value.userPassword
      }
    })
    wx.request({
      url: `http://localhost:8080/user/login?username=${this.data.user.username}&password=${this.data.user.password}`,
      method: 'POST',
      success(r) {
        if (r.statusCode == '200') {
          that.setData({
            user:{
              username:that.data.user.username,
              password:undefined
            }
          })
          wx.setStorage({
            key: 'user',
            data: that.data.user
          })
          wx.setStorage({
            key: 'token',
            data: r.data.data.Authentication
          })
          wx.showToast({
            title: '登录成功',
            success: r =>{
              wx.switchTab({
                url: '/pages/index/index',
              })
            }
          })
        } else
          wx.showModal({
            title: '登录失败'
          })
        console.log(r);
      }
    })
  },
  Register() {
    wx.navigateTo({
      url: '/pages/register/register',
    })
  },
  onFocus(r) {
    let animation = wx.createAnimation({
      delay: 30,
      timingFunction: 'ease'
    })
    this.animation = animation;
    animation.translateY(-30).scale(1.5).step();
    this.setData({
      animationData: animation,
      id: r.target.id,
      color: '#F9E2F8'
    })
  },
  onBlur(r) {
    let animation = wx.createAnimation({
      delay: 30,
      timingFunction: 'ease'
    })
    this.animation = animation;
    animation.translateY(0).scale(1).step();
    if (r.detail.value == '')
      this.setData({
        animationData: animation,
        id: r.target.id,
        color: '#fff'
      })
  },
  onLoad() {
    let that = this;
    wx.getStorage({
      key: 'user',
      success(res) {
        // console.log(res.data);
        that.setData({
          user: res.data
        })
      }
    })
    setTimeout(()=>{
      let animation = wx.createAnimation({
        delay: 30,
        timingFunction: 'ease'
      })
      this.animation = animation;
      animation.translateY(-30).scale(1.5).step();
      if (that.data.user != '')
        this.setData({
          animationData: animation,
          id:'userName',
          color: '#fff'
        })
    })
  }
})