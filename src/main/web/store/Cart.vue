<template>
  <div>
    <div class="mdl-layout__drawer mdl-layout__drawer--right" v-bind:class="{'is-visible': active}">
      <span class="mdl-layout-title">
        <span>Cart</span>
        <a v-on:click="toggleCart()"><i class="material-icons">close</i></a>
      </span>

      <div class="divider"></div>

      <div class="cart-body">
        <ul>
          <li class="item" v-for="item in items">
            <a class="item-image">
              <img :src="item.book.image">
            </a>

            <div class="item-text">
              <a class="item-title">{{ item.book.name}}</a>
              <span class="item-subtitle">{{ item.book.price | currency}} x{{item.quantity}}</span>
            </div>

            <div>
              <a href="#" class="mdl-button mdl-js-button mdl-button--accent mdl-button--icon"
                 v-on:click="removeOne(item)">
                <i class="material-icons">close</i>
              </a>
            </div>

          </li>
        </ul>

        <div class="cart-actions">

          <div class="divider"></div>
          <h3 class="total">Total: {{ total | currency }}</h3>
          <div class="divider"></div>

          <div class="mdl-grid">

            <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--primary" v-on:click="checkout">
              Checkout
              <span class="mdl-button__ripple-container"><span class="mdl-ripple"></span></span>
            </a>
          </div>

        </div>
      </div>
    </div>

    <button v-on:click="toggleCart()"
            class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--fab mdl-button--colored mdl-badge"
            data-badge="0">
      <i class="material-icons">shopping_cart</i>
      <span class="mdl-button__ripple-container">
        <span class="mdl-ripple"></span>
      </span>
    </button>
  </div>
</template>

<script>
  export default{
    name: 'Cart',
    props: {
      items: Array
    },
    computed: {
      total() {
        return (this.items || [])
          .map(item => item.book.price * item.quantity)
          .reduce(function (acc, val) {
            return acc + val;
          }, 0.0);
      },
    },
    data(){
      return {
        'active': false
      }
    },
    methods: {
      toggleCart() {
        this.active = !this.active
      },
      removeOne(item) {
        this.$resource('/api/user/cart{/id}').remove({id: item.book.id}).then(response => {
          this.$state.commit('updateCart', response.data)
        })
      },
      checkout(){
        this.$http.post('/api/orders');
      }
    }
  }
</script>

<style scoped lang="scss">

  .mdl-layout__drawer--right {
    left: 100%;
    position: fixed;
    transform: translateX(0);
    z-index: 101
  }

  .mdl-layout__drawer--right .mdl-layout-title {
    padding: 0 16px;
    display: -webkit-flex;
    display: flex;
    justify-content: space-between;
  }

  .mdl-layout__drawer--right.is-visible {
    transform: translateX(-240px)
  }

  .divider {
    border-bottom: 1px solid rgba(0, 0, 0, .1)
  }

  .cart-body {
    flex: 1 0;
    display: flex;
    flex-direction: column;
    justify-content: space-between
  }

  ul {
    display: block;
    padding: 8px 0;
    margin: 0;
    overflow: auto
  }

  .item {
    padding: 0 16px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    min-height: 48px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis
  }

  .item-text {
    flex: 1;
    padding: 16px 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis
  }

  .item-image {
    display: block;
    width: 40px;
    height: 40px;
    margin-top: 8px;
    margin-bottom: 8px;
    margin-right: 16px;
    box-sizing: content-box;
    box-shadow: 0 0 2px 0 #d8d8d8;
    img {
      max-width: 100%;
    }
  }

  .item-title {
    display: block;
    font-size: 14px;
    line-height: 24px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    justify-content: space-between
  }

  .item-subtitle {
    display: block;
    font-size: 14px;
    font-weight: 400;
    line-height: 20px;
    text-decoration: none
  }

  .total {
    font-size: 16px;
    text-align: center;
    margin-top: 8px;
    margin-bottom: 8px
  }

  .is-visible {
    transform: translateX(0);
  }

  .mdl-button--fab {
    position: fixed;
    bottom: 16px;
    right: 16px;
    z-index: 100
  }

  .mdl-grid {
    a {
      margin: 0 auto;
    }
  }
</style>
