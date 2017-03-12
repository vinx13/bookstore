<template>
    <div id="app">
        <nav-bar></nav-bar>
        <side-bar></side-bar>
        <book></book>
    </div>
</template>

<script>
  import "jquery/dist/jquery.js"
  import "bootstrap/dist/js/bootstrap.js"
  import "admin-lte/dist/js/app.js"
  import NavBar from './NavBar.vue'
  import SideBar from './SideBar.vue'
  import Book from './Book.vue'

  export default {
    name: 'app',
    components: {
      NavBar,
      SideBar,
      Book
    }
  }
</script>

<style lang="sass">
    @import "~bootstrap/dist/css/bootstrap.css"
    @import "~admin-lte/dist/css/AdminLTE.css"
    @import "~admin-lte/dist/css/skins/skin-blue.css"
    @import "~font-awesome/css/font-awesome.css"
</style>
