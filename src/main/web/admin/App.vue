<template>
  <div id="app" class="wrapper" style="height: auto;">
    <nav-bar></nav-bar>
    <side-bar></side-bar>
    <div class="content-wrapper" style="min-height: 320px;">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue'
  import VueRouter from 'vue-router'
  import VueResource from 'vue-resource'
  import NavBar from './NavBar.vue'
  import SideBar from './SideBar.vue'
  import Vue2Filters from 'vue2-filters'
 // import Books from '../tmp/Books.vue'

  Vue.use(VueRouter)
  Vue.use(VueResource);
  Vue.use(Vue2Filters)

  import router from './router'

  export default {
    name: 'app',
    router,
    mounted() {
      //router.push('/books');
    },
    components: {
      NavBar,
      SideBar
    }
  }

</script>