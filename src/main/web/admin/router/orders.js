import Orders from '../orders/Orders.vue'
import OrderList from '../orders/OrderList.vue'
import OrderDetail from '../orders/OrderDetail.vue'

export default {
  path: '/orders',
  component: Orders,
  children: [
    {
      path: '',
      component: OrderList
    },
    {
      path: ':id',
      component: OrderDetail
    }
  ]
}
