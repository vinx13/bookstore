import Books from '../books/Books.vue'
import BookList from '../books/BookList.vue'
import BookEdit from '../books/BookEdit.vue'

export default {
  path: '/books',
  component: Books,
  children: [
    {
      path: '',
      component: BookList
    },
    {
      path: 'new',
      component: BookEdit
    },
    {
      path: ':id',
      component: BookEdit
    }
  ]
}
