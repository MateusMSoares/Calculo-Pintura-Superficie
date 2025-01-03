import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CriarEquipamento from '../views/CriarEquipamento.vue'
import Equipamentos from '../views/EquipamentosManager.vue'

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
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
