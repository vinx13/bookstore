<template>
  <form class="form-horizontal">
    <div class="form-group">
      <label for="inputUsername" class="col-sm-2 control-label">Name</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="inputUsername" placeholder="Username" v-model="target.username">
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail" class="col-sm-2 control-label">Email</label>
      <div class="col-sm-10">
        <input type="email" class="form-control" id="inputEmail" v-model="target.email"
               placeholder="Email">
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button class="btn btn-sm btn-success" v-on:click="submit()">Submit</button>
        <button class="btn btn-sm btn-danger" v-on:click="back()">Cancel</button>
      </div>
    </div>
  </form>
</template>

<script>
  export default {
    name: 'UserEdit',
    created () {
      this.fetchData()
    },
    watch: {
      '$route': 'fetchData'
    },
    data(){
      return {
        target: {username: '', email: ''}
      }
    },
    methods: {
      back() {
        this.$router.push("/users/")
      },
      fetchData() {
        const id = this.itemId
        if (id)
          this.$resource('/api/users{/id}').get({id: id}).then(response => {
            this.target = response.data
          })
      },
      submit() {
        const id = this.itemId
        if (id) {
          this.$http.$resource('/api/users{/id}').put({id: id}, target).then(response => {
            this.back()
          })
        } else {
          this.$http.post('/api/users', this.target).then(
            response => {
              this.back()
            }
          )
        }
      }
    },
    computed: {
      itemId(){
        const params = this.$route.params
        return params ? params.id : null
      }
    }
  }

</script>

<style></style>