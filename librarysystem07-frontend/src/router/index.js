import Vue from 'vue'
import Router from 'vue-router'
import LogIn from '@/components/LogIn.vue'
import AccountInfo from '@/components/AccountInfo.vue'
import Reserve from '@/components/Reserve.vue'
import ReserveLibrarian from '@/components/ReserveLibrarian.vue'

//import Audio from '@/components/Club%20Penguin%20-%20Pizza%20Parlor%20Theme%20(Charlie%27s%20Here)%20%5BFULL%20High%20Quality%5D.mp3'


Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/audio',
    //   name: 'Audio',
    //   Audio: Audio
    // },
    {
      path: '/info',
      name: 'Info',
      component: AccountInfo
    },
    {
      path: '/browse-librarian',
      name: 'BrowseLibrarian',
      component: ReserveLibrarian
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
