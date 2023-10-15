import { createApp } from 'vue'
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css'
import bootstrap from 'bootstrap/dist/js/bootstrap.js'
import router from './router'
import 'bootstrap-vue/dist/bootstrap-vue.css'

createApp(App).use(router).use(bootstrap).mount('#app')
