<template>
    <div id="app" class="wrapper" style="height: auto;">
        <nav-bar></nav-bar>
        <side-bar></side-bar>
        <content-container></content-container>
    </div>
</template>

<script>
  import "jquery/dist/jquery.js"
  import "bootstrap/dist/js/bootstrap.js"
  import "admin-lte/dist/js/app.js"
  import Vue from 'vue'
  import VueRouter from 'vue-router'
  import NavBar from './NavBar.vue'
  import SideBar from './SideBar.vue'
  import Books from './Books.vue'
  import Users from './Users.vue'
  import Orders from './Users.vue'
  import ContentContainer from './Content.vue'


  Vue.use(VueRouter)

  const routes = [
    {path: '/books', component: Books},
    {path: '/users', component: Users},
    {path: '/orders', component: Orders}
  ];
  const router = new VueRouter({routes});

  export default {
    name: 'app',
    router,
    components: {
      NavBar,
      SideBar,
      ContentContainer,
      Books,
      Users,
      Orders
    }
  }

</script>

<style lang="sass">
    @import "~bootstrap/dist/css/bootstrap.css"
    @import "~admin-lte/dist/css/AdminLTE.css"
    @import "~admin-lte/dist/css/skins/skin-blue.css"
    @import "~font-awesome/css/font-awesome.css"
</style>
