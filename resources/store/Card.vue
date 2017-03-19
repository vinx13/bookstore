<template>
  <div class="mdl-card mdl-shadow--2dp">
    <div class="mdl-card__media">
      <a><img class="item-image" :src="item.image"></a>
    </div>
    <div class="mdl-card__title">
      <h3 class="mdl-card__title-text">
        <a class="mdl-typography--title mdl-typography--font-light">{{item.name}}</a>
      </h3>
    </div>
    <div class="mdl-card__actions mdl-card--border">
      <strong class="mdl-typography--subhead">
        <ins><span>{{item.price | priceFilter}}</span></ins>
      </strong>
      <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
        <i class="material-icons">add_shopping_cart</i>
        <span class="mdl-button__ripple-container"><span class="mdl-ripple"></span></span>
      </button>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Card',
    props: {
      item: {
        type: Object,
        required: true
      }
    }
  }
</script>

<style lang="scss" scoped>
  .mdl-card {
    width: 90%;
    transition: all .3s;
    overflow: hidden;
    margin: 0 auto;
    .mdl-card__media {
      background-color: #fafafa;
    }
  }

  .item-image {
    margin: 0 auto;
    display: block;
    z-index: 1;
    height: 220px;
    max-width: 100%;
    position: relative
  }

  button {
    overflow: visible;
    transition: color .2s ease-in .2s;
  }

  .mdl-card__actions {
    //height: 30px;
    display: -webkit-flex;
    display: flex;
    box-sizing: border-box;
    -webkit-align-items: center;
    align-items: center;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    .mdl-typography--headline {
      display: block;
    }
  }

  .mdl-card__title {
    display: block;
    width: 100%;

    .mdl-card__title-text {
      //height: 28px;
      width: 100%;
      font-size: 18px;
      line-height: 24px;
      a {
        display: block;
        width: 100%;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        color: inherit;
        text-decoration: none
      }
    }
  }

  .mdl-card__media {
    overflow: hidden;
    position: relative
  }


</style>