<template>
  <section>
    <section class="content-header">
      <h1>Users</h1>
    </section>
    <section class="content">
      <div class="box">
        <div class="box-header col-md-12">
          <a v-on:click="showNewModal" class="btn btn-sm btn-success">New</a>
        </div>
        <!-- /.box-header -->

        <div class="box-body">
          <table class="table table-filter table-bordered table-hover">
            <thead>
            <tr>
              <td>ID</td>
              <td>Name</td>
              <td>Email</td>
            </tr>
            </thead>
            <tbody>
            <tr class="table-fade" v-for="item in items">
              <td>{{item.id}}</td>
              <td>{{item.name}}</td>
              <td>{{item.email}}</td>
              <td><a v-on:click="showEditModal(item)" class="btn btn-sm btn-info">Edit</a>
                <a v-on:click="deleteItem(item)" class="btn btn-sm btn-danger">Delete</a></td>
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
    <user-edit :option="modalOption" ref="modal"></user-edit>
  </section>
</template>
<script>
  import UserEdit from './UserEdit.vue'
  import Pagination from './Pagination.vue'


  export default{
    'name': 'Users',
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
        },
        modalOption: {
          action: 'New',
          item: {
            'name': '',
            'email': '',
            'password': ''
          },
          callback: null
        }
      }
    },
    methods: {
      loadData()
      {
        const params = {
          per_page: this.pagination.per_page,
          page: this.pagination.current_page,
        }

        const resource = this.$resource('/api/users{/id}', params);
        resource.get().then(response => {
          this.items = response.data.data;
          this.pagination.total = response.data.total;
          this.pagination.per_page = response.data.per_page;
          this.pagination.page = response.data.current_page;
          this.pagination.last_page = response.data.last_page;
        });
      },
      showNewModal()
      {
        this.modalOption = {
          action: 'New',
          item: {
            'name': '',
            'email': '',
            'password': ''
          },
          callback: this.newItemCallback
        };
        this.$refs.modal.show();
      },
      newItemCallback(item) {
        this.$http.post('/api/users', item).then(response => {
          this.$refs.modal.hide();
        });
      },
      showEditModal(item){
        this.modalOption = {
          action: 'Edit',
          item,
          callback: this.editItemCallback
        };
        this.$refs.modal.show();
      },
      editItemCallback(item) {
        this.$http.put('/api/users{/id}', item, {params: {id: item.id}}).then(response => {
          this.$refs.modal.hide();
        });
      },
      deleteItem(item) {
        this.$http.delete('/api/users{/id}', {params: {id: item.id}}).then(response => {
          this.items.splice(this.items.indexOf(item), 1);
        });
      }
    },
    components: {
      UserEdit,
      Pagination
    }
  }
</script>
<style>

</style>