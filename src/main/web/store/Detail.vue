<template>
  <main v-if="item" class="mdl-layout__content">
    <div class="mdl-typography--headline">{{item.name}}</div>

    <div class="mdl-grid mdl-grid--no-fullwidth">
      <ul class="mdl-list">
        <li class="mdl-list__item">
          <span class="mdl-list__item-primary-content">
            <b>ISBN</b> {{item.isbn}}
          </span>
        </li>
        <li class="mdl-list__item">
          <span class="mdl-list__item-primary-content">
            <b>Price</b>{{item.price | currency }}
          </span>
        </li>
        <li class="mdl-list__item">
          <span class="mdl-list__item-primary-content">
            <b>Author</b>{{authorNameList}}
          </span>
        </li>

        <li class="mdl-list__item ">
          <span class="mdl-list__item-primary-content">
            <b>Genre</b>{{meta.genre.name}}
          </span>
        </li>

        <li class="mdl-list__item ">
          <span class="mdl-list__item-primary-content">
            <b>Description</b>{{item.description}}
          </span>
        </li>
      </ul>

    </div>
    <div class="mdl-grid mdl-grid--no-fullwidth">
      <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" v-on:click="addToCart">
        Add to Cart



      </button>
    </div>
  </main>
</template>
<script>
  export default{
    name: 'Detail',
    data() {
      return {
        item: null
      }
    },
    created () {
      this.loadData()
    },
    watch: {
      '$route': 'loadData'
    },
    methods: {
      loadData() {
        this.$resource('/api/books{/id}').get({id: this.itemId}).then(response => {
          this.item = response.data
        })
      },
      addToCart() {
        this.$emit('addToCart', this.item)
      }
    },
    computed: {
      itemId(){
        const params = this.$route.params
        return params ? params.id : null
      },
      meta() {
        return this.item ?
          this.item.content.reduce((obj, pair) => {
            obj[pair.rel] = pair.value
            return obj
          }, {})
          : {authors: [], genre: {}}
      },

      authorNameList() {
        return this.meta.authors.map(author => author.name).join(' / ')
      }
    }
  }
</script>
<style scoped>
  b:after {
    content: "\00a0";
  }

  .mdl-typography--headline {
    margin: 20px
  }
</style>