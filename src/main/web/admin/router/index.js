import VueRouter from 'vue-router'
import BookRouter from './books'
import UserRouter from './users'
import OrderRouter from './orders'
import AuthorRouter from './authors'
import GenreRouter from './genres'

const routes = [BookRouter, UserRouter, OrderRouter, AuthorRouter, GenreRouter]
const router = new VueRouter({routes})

export default router
