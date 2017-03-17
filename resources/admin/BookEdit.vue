<template>
  <div id="book-edit" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span>&times;</span></button>
          <h4 class="modal-title">{{option.action}} User</h4>
        </div>
        <div class="modal-body">
          <form class="form-horizontal">
            <div class="form-group">
              <label for="inputName" class="col-sm-2 control-label">Name</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="inputName" placeholder="Name" v-model="option.item.name">
              </div>
            </div>
            <div class="form-group">
              <label for="inputDescription" class="col-sm-2 control-label">Description</label>
              <div class="col-sm-10">
                <textarea type="text" class="form-control" id="inputDescription" v-model="option.item.description"
                       placeholder="Description">
                </textarea>
              </div>
            </div>
            <div class="form-group">
              <label for="inputISBN" class="col-sm-2 control-label">ISBN</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="inputISBN" v-model="option.item.isbn" placeholder="ISBN">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPrice" class="col-sm-2 control-label">Price</label>
              <div class="col-sm-10">
                <input type="number" class="form-control" id="inputPrice" v-model="price" placeholder="Price">
              </div>
            </div>
            <div class="form-group">
              <label for="inputQuantity" class="col-sm-2 control-label">Quantity</label>
              <div class="col-sm-10">
                <input type="number" class="form-control" id="inputQuantity" v-model="option.item.quantity"
                       placeholder="Quantity">
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" v-on:click="save" class="btn btn-primary">Save</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
</template>

<script>
  export default {
    name: 'BookEdit',
    data(){
      return {
        price: 0.0
      };
    },
    watch:{
      'option' (newVal, oldVal) {
        this.price = newVal.item.price / 100.0;
      }
    },
    props: {
      option: {
        type: Object,
        required: true
      },
    },
    methods: {
      save(){
        this.option.item.price = Math.round(this.price * 100);
        this.option.callback(this.option.item);
      },
      show() {
        $('#book-edit').modal();
      },
      hide() {
        $('#book-edit').modal('hide');
      }
    }
  }

</script>

<style></style>