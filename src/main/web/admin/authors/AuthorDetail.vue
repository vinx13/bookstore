<template>
  <section>
    <BookList v-if="itemId" :bookSource="'/api/authors/'+this.itemId+'/books'"></BookList>
  </section>
</template>

<script>
  import {mapState} from 'vuex'
  import BookList from '../books/BookList.vue'

  export default {
    name: 'AuthorDetail',
    data(){
      return {
        target: null
      }
    },
    created () {
      this.fetchData()
    },
    watch: {
      '$route': 'fetchData'
    },
    computed: {
      itemId(){
        const params = this.$route.params
        return params ? params.id : null
      }
    },
    components:{
      BookList
    }
  }

</script>

<style lang="scss" scoped>
  img {
    margin-top: 5px;
    margin-left: auto;
    margin-right: auto;
  }
</style>