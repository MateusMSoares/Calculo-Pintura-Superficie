import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CriarEquipamento from '../views/CriarEquipamento.vue'
import Equipamentos from '../views/EquipamentosManager.vue'
import CalculoView from '../views/CalculoView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/criar-equipamento', 
    name: 'criarEquipamento',
    component: CriarEquipamento  
  },
  {
    path: '/equipamentos',
    name: 'equipamentos',
    component: Equipamentos
  },
  { path: '/calculo',
    name: 'calculo',
    component: CalculoView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
