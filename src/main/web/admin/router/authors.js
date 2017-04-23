import Authors from '../authors/Authors.vue'
import AuthorList from '../authors/AuthorList.vue'
import AuthorEdit from '../authors/AuthorEdit.vue'
import AuthorDetail from '../authors/AuthorDetail.vue'

export default {
  path: '/authors',
  component: Authors,
  children: [
    {
      path: '',
      component: AuthorList
    },
    {
      path: 'new',
      component: AuthorEdit
    },
    {
      path: ':id',
      component: AuthorEdit
    },
    {
      path: 'detail/:id',
      component: AuthorDetail
    }
  ]
}
