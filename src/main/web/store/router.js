import VueRouter from 'vue-router'
import List from './List.vue'
import Detail from './Detail.vue'
import Order from './Order.vue'
import Profile from './Profile.vue'

const routes = [
  {
    path: '/',
    component: List
  },
  {
    path: '/detail/:id',
    component: Detail
  },
  {
    path: '/order/:id',
    component: Order
  },
  {
    path: '/profile',
    component: Profile
  }
]
const router = new VueRouter({routes})

export default router