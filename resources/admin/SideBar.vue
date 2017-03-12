<template>
    <aside class="main-sidebar">
        <!-- Inner sidebar -->
        <section class="sidebar">
            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="active"  v-for="item in items">
                    <a href="#"><i class="fa" v-bind:class="item.icon"></i><span>{{item.name}}</span></a>
                </li>
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
          {'name': 'Books','icon':'fa-book'},
          {'name': 'Users','icon':'fa-user'},
          {'name': 'Orders','icon':'fa-archive'}
        ]
      }
    }
  }
</script>

<style>

</style>