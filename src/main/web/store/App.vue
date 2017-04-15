<template>
  <section>
    <div class="mdl-layout mdl-js-layout mdl-layout--overlay-drawer-button mdl-layout--fixed-header">
      <nav-bar></nav-bar>
      <side-bar></side-bar>
      <main class="mdl-layout__content">
        <div class="mdl-grid mdl-grid--no-fullwidth" style="margin-top: 40px;margin-bottom: 30px;">
          <!-- breakcrumb  -->
          <!-- filter -->
        </div>
        <div class="mdl-grid mdl-grid--no-fullwidth">
          <div v-for="item in items"
               class="mdl-cell mdl-cell--4-col mdl-cell--4-col-tablet mdl-cell--4-col-phone item-card-container">
            <card :item="item" @add_to_cart="add_to_cart"></card>
          </div>
        </div>
        <pagination :pagination="pagination" :callback="loadData"></pagination>
        <site-footer></site-footer>
      </main>
    </div>
    <cart :items="cartItems"></cart>
  </section>
</template>

<script>
  import NavBar from './NavBar.vue'
  import SideBar from './SideBar.vue'
  import Card from './Card.vue'
  import Vue from 'vue'
  import Vuex from 'vuex'
  import VueResource from 'vue-resource'
  import SiteFooter from "./Footer.vue"
  import Pagination from './Pagination.vue'
  import Cart from './Cart.vue'


  Vue.use(VueResource);
  Vue.use(Vuex)

  const store = new Vuex.Store({
    state: {
      isLogin: false,
      cartItems: []
    }, mutations: {
      login(state){
        state.isLogin = true
      },
      logout(state){
        state.isLogin = false
      },
    }
  })

  export default {
    name: 'App',
    store,
    data() {
      return {
        items: [],
        pagination: {
          total: 0,
          per_page: 12,    // required
          current_page: 0, // required
          last_page: 0,    // required
        },
        cartItems: []
      }
    },
    components: {
      SiteFooter,
      NavBar,
      SideBar,
      Card,
      Pagination,
      Cart
    },
    mounted() {
      this.loadData();
    },
    updated(){
      componentHandler.upgradeAllRegistered();
    },
    methods: {
      loadData(){
        const params = {
          size: this.pagination.per_page,
          page: this.pagination.current_page,
        }

        this.$resource('/api/books{/id}', params).get()
          .then(response => {
            this.items = response.data.content;
            this.pagination.total = response.data.page.totalPages;
            this.pagination.per_page = response.data.page.size;
            this.pagination.last_page = response.data.page.totalPages - 1;
          });

        this.$http.get('/api/user').then(response => this.loadCart())
      },
      loadCart() {
        this.$http.get('/api/user/cart').then(response => {
          this.cartItems = response.data
        })
      },
      add_to_cart(item){
        this.$resource('/api/user/cart{/id}').get({id: item.id}).then(response => {
          this.cartItems = response.data
        })
      }
    }
  }
</script>

<style lang="scss">
  @media screen and (min-width: 840px) {
    .mdl-grid--no-fullwidth {
      width: 1170px;
      max-width: 85%
    }
  }
</style>
