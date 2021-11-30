import Vue from 'vue'
import Router from 'vue-router'
import LogIn from '@/components/LogIn.vue'
import AccountInfo from '@/components/AccountInfo.vue'
import Librarian from '@/components/LibrarianInfo.vue'

import Reserve from '@/components/Reserve.vue'
import ReserveLibrarian from '@/components/ReserveLibrarian.vue'
import Event from '@/components/Event.vue'

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
      path: '/info-librarian',
      name: 'InfoLibrarian',
      component: Librarian
    },
     {
      path: '/event',
      name: 'Event',
      component: Event
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
