<template>
  <section>
    <section class="content-header">
      <h1>Sales Statistics</h1>
    </section>
    <section class="content">
      <div class="box">
        <!-- /.box-header -->


        <div class="box-body">
          <form class="form-horizontal">
            <div class="form-group">
              <label for="inputUser" class="col-sm-2 control-label">User</label>
              <div class="col-sm-10">
                <v-select id="inputUser"
                          :debounce="250"
                          :on-search="getUsers"
                          :options="users"
                          placeholder="Search users..."
                          label="username"
                          v-model="selectedUser">
                </v-select>
              </div>
            </div>

            <div class="form-group">
              <label for="inputGenre" class="col-sm-2 control-label">Genre</label>
              <div class="col-sm-10">
                <v-select id="inputGenre"
                          :debounce="250"
                          :on-search="getGenres"
                          :options="genres"
                          placeholder="Search genres..."
                          label="name"
                          v-model="selectedGenre">
                </v-select>
              </div>
            </div>

            <div class="form-group">
              <label for="inputBook" class="col-sm-2 control-label">Book</label>
              <div class="col-sm-10">
                <v-select id="inputBook"
                          :debounce="250"
                          :on-search="getBooks"
                          :options="books"
                          placeholder="Search users..."
                          label="name"
                          v-model="selectedBook">
                </v-select>
              </div>
            </div>

            <div class="form-group">
              <label for="inputStart" class="col-sm-2 control-label">Start Date</label>
              <div id="inputStart" class="col-sm-10">
                <input type="datetime-local" v-model="startDate">
              </div>
            </div>


            <div class="form-group">
              <label for="inputEnd" class="col-sm-2 control-label">Start Date</label>
              <div id="inputEnd" class="col-sm-10">
                <input type="datetime-local" v-model="endDate">
              </div>
            </div>

            <div class="form-group">
              <button class="col-sm-offset-2 btn btn-success" @click="getStat">Query</button>
            </div>
          </form>

        </div>
      </div>

      <div v-if="stat" class="box">
        <h4 class="box-header">Query Result</h4>
        <div class="box-body">
          <span> <b> Sale Count</b> {{stat.saleCount}} </span> <br/>
          <span> <b> Sale Amount</b>{{stat.saleAmount | currency}}</span>
        </div>
      </div>
    </section>
  </section>
</template>
<script>
  import vSelect from "vue-select"

  export default {
    components: {vSelect},

    data() {
      return {
        selectedUser: null,
        selectedBook: null,
        selectedGenre: null,

        users: [],
        books: [],
        genres: [],

        startDate: null,
        endDate: null,

        stat: null
      }
    },

    methods: {
      getGenres(search, loading) {
        loading(true)
        this.$resource('/api/genres/search/findByNameStartsWith').get({'name': search}).then(
            response => {
              loading(false)
              this.genres = response.data.content
            }
        )
      },

      getBooks(search, loading) {
        loading(true)
        this.$resource('/api/books/search/findByNameStartsWith').get({'name': search}).then(
            response => {
              loading(false)
              this.books = response.data.content
            }
        )
      },

      getUsers(search, loading) {
        loading(true)
        this.$resource('/api/users/search/findByUsernameStartsWith').get({'name': search}).then(
            response => {
              loading(false)
              console.log(this.users)
              this.users = response.data.content
            }
        )
      },

      getStat() {

        this.$resource('/api/stat').get({
          'genreId': this.selectedGenre ? this.selectedGenre.id : null,
          'userId': this.selectedUser ? this.selectedUser.id : null,
          'bookId': this.selectedBook ? this.selectedBook.id : null,
          'startDate': this.startDate,
          'endDate': this.endDate
        }).then(response => {
          this.stat = response.data
        })

      }
    }
  }
</script>