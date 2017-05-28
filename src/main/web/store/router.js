import VueRouter from 'vue-router'
import List from './List.vue'
import Detail from './Detail.vue'
import Order from './Order.vue'

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
  }
]
const router = new VueRouter({routes})

export default router