<template>
  <section>
    <div class="box">
      <div class="box-header">
        <div class="box-title">Order Info</div>
      </div>
      <div class="box-body">
        <div>Order Id: {{itemId}}</div>
        <div>Date: {{target.createdAt | dateFilter}}</div>
      </div>
    </div>

    <div class="box">
      <div class="box-header">
        <div class="box-title">User info</div>
      </div>
      <div class="box-body">
        <div>User Id: {{user.id}}</div>
        <div>Username {{user.username}}</div>
        <div>Email {{user.email}}</div>
      </div>
    </div>

    <div class="box">
      <div class="box-header">
        <div class="box-title">Entries</div>
      </div>
      <div class="box-body">
        <table class="table table-filter table-bordered table-hover">
          <thead>
          <tr>
            <td>Book Name</td>
            <td>Unit Price</td>
            <td>Quantity</td>
            <td>Amount</td>
            <td></td>
          </tr>
          </thead>
          <tbody>
          <tr class="table-fade" v-for="entry in orderEntries">
            <td>{{entry.book.name}}</td>
            <td>{{entry.unitPrice | currency}}</td>
            <td>{{entry.quantity}}</td>
            <td>{{ (entry.unitPrice * entry.quantity) | currency}}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</template>

<script>
  import {mapState} from 'vuex'
  export default {
    name: 'OrderDetail',
    data(){
      return {
        target: null,
        user: {id: null, username: null, email: null}
      }
    },
    created () {
      this.fetchData()
    },
    watch: {
      '$route': 'fetchData'
    },
    methods: {
      back() {
        this.$router.push("/orders")
      },
      fetchData() {
        const id = this.itemId
        if (id)
          this.$resource('/api/orders{/id}').get({id: id}).then(response => {
            this.target = response.data
          })
          this.$resource('/api/orders{/id}/user').get({id: id}).then(response => {
            this.user = response.data
          })
      },
      submit() {
        const id = this.itemId
        if (id) {
          $http.$resource('/api/orders{/id}').put({id: id}, target).then(response => this.back())
        } else {
          this.$http.post('/api/orders', this.target).then(response => this.back())
        }
      }
    },
    filters: {
      dateFilter(date) {
        return date.slice(0, 10) + ' ' + date.slice(11, 19)
      }
    },
    computed: {
      itemId(){
        const params = this.$route.params
        return params ? params.id : null
      },
      orderEntries() {
        return this.target ? this.target.content[0].value : null
      }
    }
  }

</script>

<style lang="scss" scoped>
  img {
    margin-top: 5px;
    margin-left: auto;
    margin-right: auto;
  }
</style>