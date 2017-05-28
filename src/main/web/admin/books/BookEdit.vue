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
          <label for="inputISBN" class="col-sm-2 control-label">ISBN</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="inputISBN" v-model="target.isbn" placeholder="ISBN">
          </div>
        </div>
        <div class="form-group">
          <label for="inputPrice" class="col-sm-2 control-label">Price</label>
          <div class="col-sm-10">
            <input type="number" step="0.01" class="form-control" id="inputPrice" v-model="target.price"
                   placeholder="Price">
          </div>
        </div>
        <div class="form-group">
          <label for="inputInventory" class="col-sm-2 control-label">Inventory</label>
          <div class="col-sm-10">
            <input type="number" class="form-control" id="inputInventory" v-model="target.inventory"
                   placeholder="Inventory">
          </div>
        </div>
        <div class="form-group">
          <label for="inputImage" class="col-sm-2 control-label">Image </label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="inputImage" v-model="target.image" placeholder="Image">
          </div>
        </div>
        <div class="form-group">
          <label for="inputFile" class="col-sm-2 control-label">Choose file</label>
          <div class="col-sm-10">
            <input id="inputFile" type="file" @change="uploadFile($event)">
          </div>
        </div>
        <div class="form-group" v-if="target.image">
          <div class="col-sm-10 col-sm-offset-2">
            <img :src="target.image">
          </div>
        </div>

        <div id="summernote">Hello Summernote</div>

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
    name: 'BookEdit',
    data(){
      return {
        target: {name: '', description: '', price: '', inventory: '', image: ''}
      }
    },
    created () {
      this.fetchData()
    },
    mounted() {
      $(function () {
        $('#summernote').summernote({
          callbacks: {
            onImageUpload: function (files) {
              // upload image to server and create imgNode...
              var form = new FormData()
              form.append('file', files[0])

              $.ajax({
                url: '/files',
                data: form,
                method:'POST',
                success: response => {
                  console.log(response)
                  const node = document.createElement('img');
                  const link = '/files/' + response.link
                  node.setAttribute('src', link)
                  $('#summernote').summernote('insertNode', node);
                },
                contentType: false,
                enctype: 'multipart/form-data',
                processData: false
              })
            }
          }
        })
      })
    },
    watch: {
      '$route': 'fetchData'
    },
    methods: {
      back() {
        this.$router.push("/books")
      },
      fetchData() {
        const id = this.itemId
        if (id)
          this.$resource('/api/books{/id}').get({id: id}).then(response => {
            this.target = response.data
            $('#summernote').summernote('code', this.target.description)
          })
      },
      submit() {
        const id = this.itemId
        const markupStr = $('#summernote').summernote('code');
        this.target.description = markupStr
        if (id) {
          this.$resource('/api/books{/id}').update({id: id}, this.target).then(response => this.back())
        } else {
          this.$http.post('/api/books', this.target).then(response => this.back())
        }
      },
      uploadFile(event) {
        var form = new FormData()
        form.append('file', event.target.files[0])

        $.ajax({
          url: '/files',
          data: form,
          method:'POST',
          success: response => {
            const node = document.createElement('img');
            const link = '/files/' + response.link
            console.log(link)
            this.target.image = link
          },
          contentType: false,
          enctype: 'multipart/form-data',
          processData: false
        })
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

<style lang="scss" scoped>
  img {
    margin-top: 5px;
    margin-left: auto;
    margin-right: auto;
  }
</style>