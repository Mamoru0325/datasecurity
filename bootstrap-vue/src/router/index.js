import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/game',
    name: 'game',
    component: () => import('../views/GameView.vue')
  },
  {
    path: '/score',
    name: 'score',
    component: () => import('../views/ScoreView.vue')
  },
  {
    path: '/cizar',
    name: 'cizar',
    component: () => import('../views/CizarView.vue')
  },
  {
    path: '/vigenere',
    name: 'vigenere',
    component: () => import('../views/VigenereView.vue')
  },
  {
    path: '/permutation',
    name: 'permutation',
    component: () => import('../views/PermutationView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
