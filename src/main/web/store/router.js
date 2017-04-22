import VueRouter from 'vue-router'
import List from './List.vue'
import Detail from './Detail.vue'

const routes = [
  {
    path: '/',
    component: List
  },
  {
    path: '/detail/:id',
    component: Detail
  }
]
const router = new VueRouter({routes})

export default router