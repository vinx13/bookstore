import Users from '../users/Users.vue'
import UserList from '../users/UserList.vue'
import UserEdit from '../users/UserEdit.vue'


export default {
  path: '/users',
  component: Users,
  children: [
    {
      path: '',
      component: UserList
    },
    {
      path: 'new',
      component: UserEdit
    },
    {
      path: ':id',
      component: UserEdit
    }
  ]
}
