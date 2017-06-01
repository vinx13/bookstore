<template>
  <main class="mdl-layout__content">
    <div class="mdl-grid mdl-grid--no-fullwidth" style="margin-top: 20px;margin-bottom: 20px;">
      <!-- breakcrumb  -->
      <!-- filter -->
      <select v-if="genres.length > 0" v-model="genreSelected">
        <option :value="-1">
          All
        </option>
        <option v-for="option in genres" :value="option.id">
          {{ option.name }}
        </option>
      </select>

    </div>

    <div class="mdl-grid mdl-grid--no-fullwidth">
      <div v-if="items.length > 0" v-for="item in items"
           class="mdl-cell mdl-cell--4-col mdl-cell--4-col-tablet mdl-cell--4-col-phone item-card-container">
        <card @addToCart="addToCart" :item="item"></card>
      </div>
      <div v-if="items.length == 0">
        <b>No items available</b>
      </div>
    </div>

    <pagination v-if="pagination" :pagination="pagination" :callback="loadData"></pagination>

    <site-footer></site-footer>
  </main>
</template>

<script>
  import {mapState} from 'vuex'
  import Card from './Card.vue'
  import Pagination from './Pagination.vue'
  import SiteFooter from "./Footer.vue"

  export default {
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
        cartItems: [],
        genres: [],
        genreSelected: -1
      }
    },
    components: {
      Card, Pagination, SiteFooter
    },
    mounted() {
      this.$resource('/api/genres').get().then(response=>{
        this.genres = response.data.content
      })
      this.loadData()
    },
    computed: {
      bookUrl() {
        const bookName = this.$route.query.name
        if(this.genreSelected != -1)
          return '/api/genres/' + this.genreSelected + '/books'
        else if (bookName && bookName != '')
          return '/api/books/search/findByNameStartsWith?name=' + bookName
        return '/api/books'
      },
      ...mapState(['isLogin'])
    },
    methods: {
      loadData() {

        const params = this.pagination ? {
          size: this.pagination.per_page,
          page: this.pagination.current_page,
        } : null

        this.$resource(this.bookUrl, params).get()
          .then(response => {
            if (response.data.content.length < 1 || response.data.content[0].id == undefined)
              this.items = []
            else
              this.items = response.data.content

            if (response.data.page) {
              this.pagination = {
                total: response.data.page.totalPages,
                per_page: 12,
                per_page: response.data.page.size,
                last_page: response.data.page.totalPages - 1
              }
            } else {
              this.pagination = null
            }
          });
      },
      addToCart(book) {
        this.$emit('addToCart', book)
      }
    },
    watch: {
      '$route': 'loadData',
      'genreSelected': 'loadData'
    },
  }

</script>
