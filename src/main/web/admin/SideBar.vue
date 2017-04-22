<template>
  <aside class="main-sidebar">
    <!-- Inner sidebar -->
    <section class="sidebar">
      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <router-link activeClass="active" :to="item.link" tag="li" v-for="item in items" :key="item.name">
          <a>
            <i class="fa" v-bind:class="item.icon"></i><span>{{item.name}}</span>
          </a>
        </router-link>
      </ul><!-- /.sidebar-menu -->

    </section><!-- /.sidebar -->
  </aside><!-- /.main-sidebar -->
</template>

<script>
  export default {
    'name': 'SideBar',
    data () {
      return {
        'items': [
          {'name': 'Books', 'icon': 'fa-book', 'link': '/books'},
          {'name': 'Users', 'icon': 'fa-user', 'link': '/users'},
          {'name': 'Orders', 'icon': 'fa-archive', 'link': '/orders'}
        ]
      }
    }
  }
</script>

<style>

</style>