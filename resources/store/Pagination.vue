<template>
  <div class="mdl-grid">
    <div class="mdl-cell mdl-cell--12-col">
      <ul class="pagination" v-if="pagination.last_page > 0">
        <li v-if="showPrevious()" :class="{ disabled : pagination.current_page <= 1 }">
                <span v-if="pagination.current_page <= 1" class="mdl-button pagination-link">«</span>


          <a href="#" v-if="pagination.current_page > 1"
             @click.prevent="changePage(pagination.current_page - 1)"
             class="mdl-button mdl-js-button mdl-js-ripple-effect pagination-link">«
          </a>
        </li>
        <li v-for="num in array">
          <a href="#" @click.prevent="changePage(num)"
             :class="{ active: num === pagination.current_page }" class="mdl-button mdl-js-button mdl-js-ripple-effect pagination-link">{{ num }}</a>
        </li>
        <li v-if="showNext()"
            :class="{ disabled: pagination.current_page === pagination.last_page || pagination.last_page === 0 }">
          <span v-if="pagination.current_page === pagination.last_page || pagination.last_page === 0" class="mdl-button pagination-link">
            »
          </span>

          <a href="#" v-if="pagination.current_page < pagination.last_page"
             @click.prevent="changePage(pagination.current_page + 1)"
             class="mdl-button mdl-js-button mdl-js-ripple-effect pagination-link">»
          </a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Pagination',

    props: {
      pagination: {
        type: Object,
        required: true
      },
      callback: {
        type: Function,
        required: true
      },
      options: {
        type: Object
      },
      size: {
        type: String
      }
    },
    computed: {
      array () {
        if (this.pagination.last_page <= 0) {
          return [];
        }

        let from = this.pagination.current_page - this.config.offset; // FIXME
        from = from > 1 ? from : 1;

        let to = from + (this.config.offset * 2);
        to = to < this.pagination.last_page ? to : this.pagination.last_page;

        let arr = [];
        while (from <= to) {
          arr.push(from);
          from++;
        }

        return arr;
      },
      config () {
        return Object.assign({
          offset: 3,
          alwaysShowPrevNext: false
        }, this.options);
      }
    },
    watch: {
      'pagination.per_page' (newVal, oldVal) {
        if (+newVal !== +oldVal) {
          this.callback();
        }
      }
    },
    methods: {
      showPrevious () {
        return this.config.alwaysShowPrevNext || this.pagination.current_page > 1;
      },
      showNext () {
        return this.config.alwaysShowPrevNext || this.pagination.current_page < this.pagination.last_page;
      },
      changePage (page) {
        if (this.pagination.current_page === page) {
          return;
        }

        this.$set(this.pagination, 'current_page', page);
        this.callback();
      }
    }
  }
</script>
<style lang="scss">
  li {
    list-style-type: none;
    display: inline-block;
  }
  .pagination {
    margin: 0;
    padding: 0;
    text-align: center
  }
  .pagination-link {
    width: 40px;
    height: 40px;
    line-height: 40px;
    min-width: 40px;
    border-radius: 50%;
    box-sizing: border-box;
    opacity: .4
  }
  .active{
    opacity: 1
  }
</style>
