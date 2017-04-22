<template>
  <main class="mdl-layout__content">
    <div class="mdl-grid mdl-grid--no-fullwidth" style="margin-top: 20px;margin-bottom: 20px;">
      <!-- breakcrumb  -->
      <!-- filter -->
    </div>

    <div class="mdl-grid mdl-grid--no-fullwidth">
      <div v-for="item in items"
           class="mdl-cell mdl-cell--4-col mdl-cell--4-col-tablet mdl-cell--4-col-phone item-card-container">
        <card @addToCart="addToCart" :item="item"></card>
      </div>
    </div>

    <pagination :pagination="pagination" :callback="loadData"></pagination>

    <site-footer></site-footer>
  </main>
</template>

<script>
  import {mapState} from 'vuex'
  import Card from './Card.vue'
  import Pagination from './Pagination.vue'
  import SiteFooter from "./Footer.vue"

  export default{
    name: 'List',
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
    computed: mapState(['isLogin']),
    components: {
      Card, Pagination,SiteFooter
    },
    mounted(){
      this.loadData()
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
      },
      addToCart(book){
        this.$emit('addToCart', book)
      }
    }
  }

</script>
