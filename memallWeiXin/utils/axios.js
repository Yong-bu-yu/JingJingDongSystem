export class Axios {
  get(url, data) {
    if (data == undefined)
      data = ''
    const baseUrl = 'http://localhost:8080'
    const header = {
      Authentication: null
    }
    if (wx.getStorageSync('token')) {
      header.Authentication = wx.getStorageSync('token') //登录后拿到token值
    }
    return new Promise((resolve, reject) => {
      wx.showLoading({
        title: '加载中',
      })
      wx.request({
        url: `${baseUrl+url+data}`, // 兼容baseUrl不同的情况，可以通过填写完整路径
        method: 'GET',
        header: header,
        success: res => {
          resolve(res)
        },
        fail: err => {
          reject(err)
        },
        complete: () => {
          wx.hideLoading()
        }
      })
    })
  }
  // delete<T = any, R = AxiosResponse<T>, D = any>(url: string, config?: AxiosRequestConfig<D>): Promise<R>;
  // head<T = any, R = AxiosResponse<T>, D = any>(url: string, config?: AxiosRequestConfig<D>): Promise<R>;
  // options<T = any, R = AxiosResponse<T>, D = any>(url: string, config?: AxiosRequestConfig<D>): Promise<R>;
  post(url, data, config) {
    const header = {
      Authentication: null
    }

    const obj2strUrl = (obj) => {
      let str = "";
      for (let key in obj) {
        str = `${str}${key}=${obj[key]}&`
      };
      str = str.substring(0, str.length - 1);
      return str;
    }
    data = obj2strUrl(data)
    if (data == undefined) {
      data = ''
    }
    if (wx.getStorageSync('token')) {
      header.Authentication = wx.getStorageSync('token')
    }
    return new Promise((resolve, reject) => {
      wx.showLoading({
        title: '加载中',
      })
      wx.request({
        url: `http://localhost:8080${url}?${data}`, // 兼容baseUrl不同的情况，可以通过填写完整路径
        method: 'POST',
        // data: data,
        header: header,
        success: res => {
          resolve(res)
        },
        fail: err => {
          reject(err)
        },
        complete: () => {
          wx.hideLoading()
        }
      })
    })
  }
  // put<T = any, R = AxiosResponse<T>, D = any>(url: string, data?: D, config?: AxiosRequestConfig<D>): Promise<R>;
  // patch<T = any, R = AxiosResponse<T>, D = any>(url: string, data?: D, config?: AxiosRequestConfig<D>): Promise<R>;
}

const axios = new Axios();

export default axios;