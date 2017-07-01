import VueRouter from 'vue-router'
import BookRouter from './books'
import UserRouter from './users'
import OrderRouter from './orders'
import AuthorRouter from './authors'
import GenreRouter from './genres'
import StatRouter from './stat'

const routes = [BookRouter, UserRouter, OrderRouter, AuthorRouter, GenreRouter, StatRouter]
const router = new VueRouter({routes})

export default router
