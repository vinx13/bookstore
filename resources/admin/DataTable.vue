<template>
    <table :id="tableId" class="table table-bordered table-hover">
        <thead>
        <tr>
            <th v-for="col in tableOption.colTitles">{{col}}</th>
        </tr>
        </thead>
    </table>
</template>

<script>

  function initTable(component) {
    console.log(component.columns)
    const selector = '#' + component.tableId
    $(selector).DataTable({
      "ajax": component.api,
      "columns": component.columns,
      "serverSide": false,
      "paging": true,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  }

  export default {
    name: "DataTable",
    props: {
      tableOption: {
        type: Object,
        required: true
      }
    },
    computed: {
      api(){
        return '/api/' + this.tableOption.name.toLowerCase()
      },
      tableId() {
        return 'datatable' + this._uid;
      },
      columns() {
        return this.tableOption.fields.map(col => ({
          data: col
        }));
      }
    },
    mounted(){
      initTable(this)
    }
  }
</script>