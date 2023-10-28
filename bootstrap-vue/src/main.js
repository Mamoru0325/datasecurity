import { createApp } from 'vue'
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css'
import bootstrap from 'bootstrap/dist/js/bootstrap.js'
import router from './router'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import store from "./store/index";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'
import 'bootstrap/dist/js/bootstrap.bundle.js'
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'

createApp(App).use(bootstrap).use(store).use(router).component("font-awesome-icon", FontAwesomeIcon).mount('#app')

