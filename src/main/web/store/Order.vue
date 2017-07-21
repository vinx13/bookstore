<template>
  <main v-if="item" class="mdl-layout__content">
    <div class="mdl-grid mdl-grid--no-fullwidth">
      <div>Order ID: {{item.id}}</div>
      <div>Status: {{item.status}}</div>
    </div>
    <div class="mdl-grid mdl-grid--no-fullwidth">
      <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
        <thead>
        <tr>
          <th class="mdl-data-table__cell--non-numeric">Book</th>
          <th>Quantity</th>
          <th>Unit price</th>
          <th>Amount</th>
        </tr>
        </thead>

        <tbody>

        <tr class="mdl-list__item" v-for="entry in orderEntries">
          <td  class="mdl-data-table__cell--non-numeric">
            {{entry.book.name}}
          </td>

          <td>{{entry.quantity}}</td>
          <td>{{entry.unitPrice | currency}}</td>
          <td>{{ (entry.unitPrice * entry.quantity) | currency}}</td>
        </tr>

        </tbody>
      </table>

    </div>

    <div class="mdl-grid mdl-grid--no-fullwidth" v-if="item && item.status=='UnPaid'">
      <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" v-on:click="pay">
        Pay Now
      </button>
      <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" v-on:click="cancel">
        Cancel Order
      </button>
    </div>
  </main>
</template>
<script>
  export default{
    name: 'Order',
    data() {
      return {
        item: null
      }
    },
    created () {
      this.loadData()
    },
    watch: {
      '$route': 'loadData'
    },
    methods: {
      loadData() {
        console.log('id:' + this.itemId)
        this.$resource('/api/orders{/id}').get({id: this.itemId}).then(response => {
          this.item = response.data
        }, err => window.alert('Invalid order'))
      },
      pay() {
        this.$http.post('/api/checkout/' + this.itemId + '/pay').then(response => {
          this.loadData()
        })
      },
      cancel(){
        this.$http.post('/api/checkout/' + this.itemId + '/cancel').then(response => {
          this.loadData()
        })
      }
    },
    computed: {
      itemId(){
        const params = this.$route.params
        return params ? params.id : null
      },
      orderEntries() {
        return this.item ? this.item.content[0].value : null
      }

    }
  }
</script>
<style scoped>
  b:after {
    content: "\00a0";
  }

  .mdl-typography--headline {
    margin: 20px
  }
</style>