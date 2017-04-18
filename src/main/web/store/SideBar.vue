<template>
  <div class="mdl-layout__drawer">
    <a class="mdl-layout-title" href="/">Bookstore</a>
    <nav class="mdl-navigation">
      <span class="mdl-navigation__link-divider"></span>
      <a v-if="!isLogin" class="mdl-navigation__link" href="/login">
        <i class="material-icons mdl-navigation__icon">face</i>
        Sign In
      </a>
      <a v-if="!isLogin" class="mdl-navigation__link" href="/registration">
        <i class="material-icons mdl-navigation__icon">directions_up</i>
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

<style>

</style>
