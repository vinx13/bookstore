<template>
  <section>
    <div class="mdl-layout mdl-js-layout mdl-layout--overlay-drawer-button mdl-layout--fixed-header">
      <nav-bar></nav-bar>
      <side-bar></side-bar>
      <router-view @addToCart="addToCart"></router-view>
    </div>
    <cart v-if='isLogin' :items="cartItems"></cart>
    <div>
      <vue-toast ref='toast'></vue-toast>
    </div>
  </section>
</template>

<script>
  import Vue from 'vue'
  import VueRouter from 'vue-router'
  import Vuex, {mapState} from 'vuex'
  import VueResource from 'vue-resource'
  import VueToast from 'vue-toast'
  import Vue2Filters from 'vue2-filters'

  Vue.use(VueRouter)
  Vue.use(Vue2Filters)
  Vue.use(VueResource);
  Vue.use(Vuex)


  import NavBar from './NavBar.vue'
  import SideBar from './SideBar.vue'
  import Cart from './Cart.vue'
  import List from './List.vue'
  import router from './router'

  const store = new Vuex.Store({
    state: {
      isLogin: false,
      cartItems: []
    },
    mutations: {
      login(state){
        state.isLogin = true
      },
      logout(state){
        state.isLogin = false
      },
      updateCart(state, items) {
        state.cartItems = items
      }
    }
  })

  export default {
    name: 'App',
    store,
    router,
    components: {
      NavBar,
      SideBar,
      Cart,
      List,
      VueToast
    },
    mounted() {
      this.loadUserData()
      this.$refs.toast.setOptions({position: 'top right'})
    },
    updated(){
      componentHandler.upgradeAllRegistered();
    },
    methods: {
      loadUserData() {
        this.$http.get('/api/user').then(response => {
          console.log(response)
          this.$store.commit('login')
          this.loadCart();
        }, err => {

        })
      },
      loadCart() {
        this.$http.get('/api/user/cart').then(response => {
          this.$store.commit('updateCart', response.data)
        })
      },
      addToCart(book){
        console.log(book)
        if (!this.isLogin) {
          this.$refs.toast.showToast('Please login', {theme: 'error', timeLife: 3000})
        } else {
          this.$resource('/api/user/cart{/id}').get({id: book.id}).then(response => {
            this.$store.commit('updateCart', response.data)
          })
        }
      }

    },
    computed: mapState(['isLogin', 'cartItems'])
  }
</script>

<style lang="scss">

  @import "~vue-toast/dist/vue-toast.min.css";

  @media screen and (min-width: 840px) {
    .mdl-grid--no-fullwidth {
      width: 1170px;
      max-width: 90%
    }
  }

</style>
