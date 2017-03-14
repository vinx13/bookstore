<template>
    <section>
        <section class="content-header">
            <h1>Orders</h1>
        </section>
        <section class="content">
            <div class="box">
                <div class="box">
                    <div class="box-header">
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <data-table :tableOption="tableOption""></data-table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </section>
        <!-- /.content -->
    </section>
</template>

<script>
  import DataTable from "./DataTable.vue"

  export default{
    'name': 'Orders',
    data() {
      return {
        tableOption: {
          name: 'Orders',
          colTitles: ['Id', 'Book Name', 'User Name', 'Unit Price', "Quantity", "Time"],
          fields: ['id', 'book.name', 'user.name', 'price', 'quantity', 'created_at']
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