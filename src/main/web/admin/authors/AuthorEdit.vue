<template>
  <div class="box">
    <div class="box-body">
      <form class="form-horizontal">
        <div class="form-group">
          <label for="inputName" class="col-sm-2 control-label">Name</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="inputName" placeholder="Name" v-model="target.name">
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-sm btn-success" v-on:click="submit()">Submit</button>
            <button class="btn btn-sm btn-danger" v-on:click="back()">Cancel</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  export default {
    name: 'authorEdit',
    data(){
      return {
        target: {name: ''}
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
        this.$router.push("/authors")
      },
      fetchData() {
        const id = this.itemId
        if (id)
          this.$resource('/api/authors{/id}').get({id: id}).then(response => {
            this.target = response.data
          })
      },
      submit() {
        const id = this.itemId
        if (id) {
          this.$resource('/api/authors{/id}').update({id: id}, this.target).then(response => this.back())
        } else {
          this.$http.post('/api/authors', this.target).then(response => this.back())
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