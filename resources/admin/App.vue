
//  import "jquery/dist/jquery.js"
 // import "bootstrap/dist/js/bootstrap.js"
//  import Dt from "datatables.net"
  var $ = require('jquery')
  require("imports-loader?jQuery=$!admin-lte/dist/js/app.js")
  import Vue from 'vue'
  import VueRouter from 'vue-router'
  import NavBar from './layout/NavBar.vue'
  import SideBar from './layout/SideBar.vue'
  import Books from './Books.vue'
  import Users from './Users.vue'
  import Orders from './Users.vue'
  import ContentContainer from './layout/Content.vue'

  Vue.use(VueRouter)

  const routes = [
    {path: '/books', component: Books},
    {path: '/users', component: Users},
    {path: '/orders', component: Orders}
  ];
  const router = new VueRouter({routes});

  Vue.component('App',{
    router,
    components: {
      NavBar,
      SideBar,
      ContentContainer,
      Books,
      Users,
      Orders
    },
    template:'    <div id="app" class="wrapper" style="height: auto;">
    <nav-bar></nav-bar>
    <side-bar></side-bar>
    <content-container></content-container>
    </div>'
  })

