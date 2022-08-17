import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import './index.css'
import 'tw-elements'


createApp(App).use(store).use(router).mount('#app')
