import * as echarts from 'echarts';

export default (app) => {
    app.config.globalProperties.$echarts = echarts
}
