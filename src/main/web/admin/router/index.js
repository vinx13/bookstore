import VueRouter from 'vue-router'
import BookRouter from './books'
import UserRouter from './users'
import OrderRouter from './orders'

const routes = [BookRouter, UserRouter, OrderRouter]
const router = new VueRouter({routes})

export default router
