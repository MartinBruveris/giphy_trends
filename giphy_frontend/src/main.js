import Vue from 'vue'
import App from './App.vue'
import VueMaterial from 'vue-material'
import vuetify from '@/plugins/vuetify'

Vue.config.productionTip = false

Vue.use(VueMaterial)

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app')
