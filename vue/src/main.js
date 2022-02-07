import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VModal from 'vue-js-modal'

Vue.config.productionTip = false
Vue.use(VModal)

export const bus = new Vue();

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')