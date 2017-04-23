import Genres from '../genres/Genres.vue'
import GenreList from '../genres/GenreList.vue'
import GenreEdit from '../genres/GenreEdit.vue'
import GenreDetail from '../genres/GenreDetail.vue'

export default {
  path: '/genres',
  component: Genres,
  children: [
    {
      path: '',
      component: GenreList
    },
    {
      path: 'new',
      component: GenreEdit
    },
    {
      path: ':id',
      component: GenreEdit
    },
    {
      path: 'detail/:id',
      component: GenreDetail
    }
  ]
}
