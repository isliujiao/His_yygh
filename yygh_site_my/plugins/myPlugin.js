import Vue from 'vue'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
Vue.use(ElementUI) //使用elementUI
//使用微信支付功能二维码
import VueQriously from 'vue-qriously'
Vue.use(VueQriously)