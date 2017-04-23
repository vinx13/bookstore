<template>

  <div class="box">
    <div  v-if="!bookSource" class="box-header col-md-12">
      <router-link to="/books/new" class="btn btn-sm btn-success">New</router-link>
    </div>
    <!-- /.box-header -->

    <div class="box-body">
      <table class="table table-filter table-bordered table-hover">
        <thead>
        <tr>
          <td>Book Name</td>
          <td>Description</td>
          <td>ISBN</td>
          <td>Price</td>
          <td>Inventory</td>
          <td></td>
        </tr>
        </thead>
        <tbody>
        <tr class="table-fade" v-for="item in items">
          <td>{{item.name}}</td>
          <td>{{item.description}}</td>
          <td>{{item.isbn}}</td>
          <td>{{item.price}}</td>
          <td>{{item.inventory}}</td>
          <td>
            <router-link :to="'/books/' + item.id" class="btn btn-sm btn-info">Edit</router-link>
            <a v-on:click="deleteItem(item)" class="btn btn-sm btn-danger">Delete</a>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="box-footer">
      <pagination :pagination="pagination" :callback="loadData"></pagination>
    </div>
  </div>

</template>

<script>
  import Pagination from '../Pagination.vue'

  export default{
    name: 'BookList',
    mounted() {
      this.loadData();
    },
    data() {
      return {
        items: [],
        pagination: {
          total: 0,
          per_page: 15,    // required
          current_page: 0, // required
          last_page: 0,    // required
        }
      };
    },
    computed: {
      bookUrl() {
        return this.bookSource ? this.bookSource : '/api/books'
      }
    },
    props: {
      bookSource: {
        type: String
      }
    },
    methods: {
      loadData()
      {
        const params = {
          size: this.pagination.per_page,
          page: this.pagination.current_page,
        }

        const resource = this.$resource(this.bookUrl, params);
        resource.get().then(response => {
          this.items = response.data.content;
          this.pagination.total = response.data.page.totalPages;
          this.pagination.per_page = response.data.page.size;
          this.pagination.last_page = response.data.page.totalPages - 1;
        });
      },
      deleteItem(item)
      {
        this.$resource('/api/books{/id}').delete({id: item.id}).then(response => {
          this.items.splice(this.items.indexOf(item), 1);
        }, err => {
          window.alert("Broken foreign key constraint")
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