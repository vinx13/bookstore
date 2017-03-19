<template>
  <div id="user-edit" class="modal fade" role="dialog">
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
              <label for="inputEmail" class="col-sm-2 control-label">Email</label>
              <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail" v-model="option.item.email"
                       placeholder="Email">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword" class="col-sm-2 control-label">Password</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" v-model="option.item.password"
                       placeholder="Password">
              </div>
            </div>
            <div class="form-group">
              <label for="inputAvatar" class="col-sm-2 control-label">Avatar</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="inputAvatar" v-model="option.item.avatar"
                       placeholder="Password">
              </div>
            </div>
            <div class="form-group"  v-if="option.item.avatar" >
              <div class="col-sm-10 col-sm-offset-2">
                <img :src="option.item.avatar">
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
    name: 'UserEdit',
    props: {
      option: {
        type: Object,
        required: true
      }
    },
    methods: {
      save(){
        this.option.callback(this.option.item);
      },
      show() {
        $('#user-edit').modal();
      },
      hide() {
        $('#user-edit').modal('hide');
      }
    }
  }

</script>

<style></style>