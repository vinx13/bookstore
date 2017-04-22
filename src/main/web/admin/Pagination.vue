<template>
  <nav>
    <ul class="pagination" v-if="pagination.last_page > 0">
      <li v-if="showPrevious()" :class="{ disabled : pagination.current_page <= 1 }">
                <span v-if="pagination.current_page <= 0">
                    <span>«</span>
                </span>

        <a href="#" v-if="pagination.current_page > 0"
           @click.prevent="changePage(pagination.current_page - 1)">
          <span>«</span>
        </a>
      </li>
      <li v-for="num in array" :class="{ active: num === pagination.current_page }">
        <a href="#" @click.prevent="changePage(num)">{{ num + 1 }}</a>
      </li>
      <li v-if="showNext()"
          :class="{ disabled: pagination.current_page === pagination.last_page || pagination.last_page === 0 }">
                <span v-if="pagination.current_page === pagination.last_page || pagination.last_page === 0">
                    <span aria-hidden="true">»</span>
                </span>

        <a href="#" v-if="pagination.current_page < pagination.last_page"
           @click.prevent="changePage(pagination.current_page + 1)">
          <span aria-hidden="true">»</span>
        </a>
      </li>
    </ul>
  </nav>
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
        from = from > 0 ? from : 0;

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
          previousText: '«',
          nextText: '»',
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
<style></style>
