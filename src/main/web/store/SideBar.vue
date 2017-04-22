<template>
  <div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Bookstore</span>
    <span class="mdl-navigation__link-divider"></span>


    <nav class="mdl-navigation">
      <a v-if="!isLogin" class="mdl-navigation__link" href="/login">
        <i class="material-icons mdl-navigation__icon">face</i>
        Sign In
      </a>
      <a v-if="!isLogin" class="mdl-navigation__link" href="/registration">
        <i class="material-icons mdl-navigation__icon">directions_walk</i>
        Sign Up
      </a>
      <a v-if="isLogin" class="mdl-navigation__link" href="/logout">
        <i class="material-icons mdl-navigation__icon">accessibility</i>
        Logout
      </a>
    </nav>
  </div>

</template>

<script>
  import {mapState} from 'vuex'
  export default {
    name: 'SideBar',
    computed: mapState(['isLogin'])
  }
</script>

<style scoped>
</style>
