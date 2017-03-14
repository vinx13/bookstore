<template>
    <section>
        <section class="content-header">
            <h1>Users</h1>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header">

                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <data-table :tableOption="tableOption"></data-table>
                </div>
                <!-- /.box-body -->
            </div>
        </section>
    </section>
    <!-- /.content -->
</template>
<script>
  import DataTable from "./DataTable.vue"

  export default{
    'name': 'Users',
    data() {
      return {
        tableOption: {
          name: 'Users',
          colTitles: ['Id', 'Name', 'Email'],
          fields: ['id', 'name', 'email']
        }
      }
    },
    components: {
      DataTable
    }
  }
</script>
<style>

</style>