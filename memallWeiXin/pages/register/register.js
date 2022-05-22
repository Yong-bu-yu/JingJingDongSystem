// index.js
// 获取应用实例
const app = getApp()

Page({
  data: {
    user: {
      username: '',
      password: ''
    },
    animationData: {},
    textValue: '',
    id: '',
    color: ''
  },
  Register(res) {
    //console.log(res.detail.value);
    if (res.detail.value.userPassword.length < 6){
      wx.showToast({
        title: '密码长度太短',
        icon: 'error'
      })
      return
    }
    let that = this;
    this.setData({
      user: {
        username: res.detail.value.userName,
        password: res.detail.value.userPassword
      }
    })
    wx.request({
      url: `http://localhost:8080/user/register?username=${this.data.user.username}&password=${this.data.user.password}`,
      method: 'POST',
      success(r) {
        if (r.statusCode == '200') {
          wx.setStorage({
            key: 'user',
            data: that.data.user
          })
          wx.showToast({
            title: '注册成功',
            success: r => {
              wx.navigateTo({
                url: '/pages/login/login',
              })
            }
          })
        } else
          wx.showModal({
            title: '注册失败,已存在用户名了'
          })
      }
    })
  },
  Login() {
    wx.navigateBack({
      url: '/pages/login/login',
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
      color: '#987698'
    })
  },
  onBlur(r) {
    let animation = wx.createAnimation({
      delay: 30,
      timingFunction: 'ease'
    })
    this.animation = animation;
    animation.translateY(0).scale(1).step();
    if (r.detail.value == '') {
      this.setData({
        animationData: animation,
        textValue: '',
        id: r.target.id,
        color: '#fff'
      })
    }
  },
  onLoad() {
    let that = this;
    wx.getStorage({
      key: 'user',
      success(res) {
        console.log(res.data);
        that.setData({
          user: res.data
        })
      }
    })
  }
})