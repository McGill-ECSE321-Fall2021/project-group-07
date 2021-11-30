import Vue from 'vue'
import Router from 'vue-router'
import LogIn from '@/components/LogIn.vue'
import AccountInfo from '@/components/AccountInfo.vue'
import Reserve from '@/components/Reserve.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/info',
      name: 'Info',
      component: AccountInfo
    },
    {
      path: '/browse',
      name: 'Browse',
      component: Reserve
    },
    {
      path: '/',
      name: 'LogIn',
      component: LogIn
    }
  ]
})
