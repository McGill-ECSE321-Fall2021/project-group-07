import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello.vue'
import LogIn from '@/components/LogIn.vue'
import AccountInfo from '@/components/AccountInfo.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/info',
      name: 'Info',
      component: AccountInfo
    },
    {
      path: '/login',
      name: 'LogIn',
      component: LogIn
    }
  ]
})
