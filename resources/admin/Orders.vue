<template>
  <section>
    <section class="content-header">
      <h1>Orders</h1>
    </section>
    <section class="content">
      <div class="box">
        <div class="box-header">
        </div>
        <!-- /.box-header -->

        <div class="box-body">
          <table class="table table-filter table-bordered table-hover">
            <thead>
            <tr>
              <td>ID</td>
              <td>Book Name</td>
              <td>User Name </td>
              <td>Unit Price</td>
              <td>Quantity</td>
              <td>Time</td>
            </tr>
            </thead>
            <tbody>
            <tr class="table-fade" v-for="item in items">
              <td>{{item.id}}</td>
              <td>{{item.book.name}}</td>
              <td>{{item.user.name}}</td>
              <td>{{item.price | priceFilter}}</td>
              <td>{{item.quantity}}</td>
              <td>{{item.created_at}}</td>
            </tr>
            </tbody>
          </table>
        </div>
        <div class="box-footer">
          <pagination :pagination="pagination" :callback="loadData"
                      :options="paginationOptions"></pagination>
        </div>
      </div>
    </section>
  </section>
</template>

<script>
  import Pagination from './Pagination.vue'

  export default{
    'name': 'Orders',
    mounted() {
      this.loadData();
    },
    data() {
      return {
        items: [],
        pagination: {
          total: 0,
          per_page: 15,    // required
          current_page: 1, // required
          last_page: 1,    // required
        },
        paginationOptions: {
          offset: 4,
          previousText: 'Prev',
          nextText: 'Next',
          alwaysShowPrevNext: true
        }
      }
    },
    methods: {
      loadData () {
        const params = {
          per_page: this.pagination.per_page,
          page: this.pagination.current_page,
        }

        const resource = this.$resource('/api/orders{/id}', params);
        resource.get().then(response => {
          this.items = response.data.data;
          this.pagination.total = response.data.total;
          this.pagination.per_page = response.data.per_page;
          this.pagination.page = response.data.current_page;
          this.pagination.last_page = response.data.last_page;
        });
      }
    },
    components: {
      Pagination
    }
  }
</script>
<style>

</style>