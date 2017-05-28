<template>
  <header class="mdl-layout__header mdl-layout__header--waterfall">
    <div class="mdl-layout__header-row">
      <router-link to="/" class="mdl-layout-title" href="/"><b>Bookstore</b></router-link>
      <div class="mdl-layout-spacer"></div>

      <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
          mdl-textfield--floating-label mdl-textfield--align-right">
        <label class="mdl-button mdl-js-button mdl-button--icon" for="waterfall-exp">
          <i class="material-icons">search</i>
        </label>
        <div class="mdl-textfield__expandable-holder">
          <input class="mdl-textfield__input" type="text"
                 id="waterfall-exp" v-model="searchText" input v-on:keyup.enter="search">
        </div>
      </div>
    </div>
    <!--
    <div class="mdl-layout__header-row">
      <div class="mdl-layout-spacer"></div>
      <nav class="mdl-navigation mdl-layout--large-screen-only">
        <a class="mdl-navigation__link">CART</a>
        <a class="mdl-navigation__link">CHECKOUT</a>
      </nav>
    </div>
    -->
  </header>
</template>

<script>
  export default {
    name: 'NavBar',
    data() {
      return {
        searchText:''
      }
    },
    methods:{
      search(){
        this.$router.push({ path: '/', query: { name: this.searchText }})
      }
    }
  }
</script>

<style scoped>
.mdl-layout-title{
  color: white;
  text-decoration-line: none;
}
</style>
