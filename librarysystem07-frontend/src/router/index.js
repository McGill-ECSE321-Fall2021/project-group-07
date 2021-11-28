import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import LibrarySystem07 from '@/components/LibrarySystem07'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/app',
      name: 'LibrarySystem07',
      component: LibrarySystem07
    },
    {
      path: '/event',
      name: 'Event',
      component: Event
    },
  ]
})