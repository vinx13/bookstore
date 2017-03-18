<template>
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
          <card :item="item"></card>
        </div>
      </div>
      <pagination :pagination="pagination" :callback="loadData"
                  :options="paginationOptions"></pagination>
      <site-footer></site-footer>
    </main>
  </div>
</template>

<script>
  import NavBar from './NavBar.vue'
  import SideBar from './SideBar.vue'
  import Card from './Card.vue'
  import Vue from 'vue'
  import VueResource from 'vue-resource'
  import PriceFilter from '../price-filter.js'
  import SiteFooter from "./Footer.vue"
  import Pagination from './Pagination.vue'

  Vue.use(VueResource);
  Vue.filter('priceFilter', PriceFilter);

  export default {
    name: 'App',
    data() {
      return {
        items: [],
        pagination: {
          total: 0,
          per_page: 16,    // required
          current_page: 1, // required
          last_page: 1,    // required
        },
        paginationOptions: {
          offset: 4,
          previousText: 'Prev',
          nextText: 'Next',
          alwaysShowPrevNext: true
        }
      }
    },
    components: {
      SiteFooter, NavBar,
      SideBar,
      Card,
      Pagination
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
          per_page: this.pagination.per_page,
          page: this.pagination.current_page,
        }

        const resource = this.$resource('/api/books{/id}', params);
        resource.get().then(response => {
          this.items = response.data.data;
          this.pagination.total = response.data.total;
          this.pagination.per_page = response.data.per_page;
          this.pagination.page = response.data.current_page;
          this.pagination.last_page = response.data.last_page;
        });
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
