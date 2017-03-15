<template>
    <div id="app" class="wrapper" style="height: auto;">
        <nav-bar></nav-bar>
        <side-bar></side-bar>
        <content-container></content-container>
    </div>
</template>

<script>
  import Vue from 'vue'
  import VueRouter from 'vue-router'
  import VueResource from 'vue-resource'
  import NavBar from './layout/NavBar.vue'
  import SideBar from './layout/SideBar.vue'
  import Books from './Books.vue'
  import Users from './Users.vue'
  import Orders from './Orders.vue'
  import ContentContainer from './layout/Content.vue'

  Vue.use(VueRouter)
  Vue.use(VueResource);
  Vue.filter('priceFilter',function(price) {
      const s = price.toString();
      return '$ ' + s.slice(0, -2) + '.' + s.slice(-2);
    });

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
</style>
