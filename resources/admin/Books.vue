<template>
    <section>
        <section class="content-header">
            <h1>Books</h1>
        </section>
        <section class="content">
            <div class="box">
                <div class="box">
                    <div class="box-header">
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <data-table :tableOption="tableOption"></data-table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </section>
        <!-- /.content -->
    </section>
</template>

<script>
  import DataTable from './DataTable.vue'
  export default{
    name: 'Books',
    data(){
      return {
        tableOption: {
          name: 'Books',
          colTitles: ['Id', 'Name', 'Description', 'ISBN', 'Price', 'Quantity'],
          fields: ['id', 'name', 'description', 'isbn', 'price', 'quantity']
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