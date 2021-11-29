import Vue from 'vue'
import Router from 'vue-router'
import LogIn from '@/components/LogIn.vue'
import AccountInfo from '@/components/AccountInfo.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/info',
      name: 'Info',
      component: AccountInfo
    },
  
    {
      path: '/',
      name: 'LogIn',
      component: LogIn
    }
  ]
})
