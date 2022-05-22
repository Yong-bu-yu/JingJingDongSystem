import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import './plugins/element.js'
import './mock/index'

// import 'pattern.css'
import installElementPlus from './plugins/element'
import installCharts from './plugins/charts.js'

const app = createApp(App)

installElementPlus(app)
installCharts(app)

app.use(router)
    .use(store)
    .mount('#app')
