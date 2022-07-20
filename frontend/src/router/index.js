import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import SignupView from '../views/SignupView.vue'
import PWsearchView from '../views/PWsearchView.vue'
import PWresetView from '../views/PWresetView.vue'

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignupView
  },
  {
    path: '/pwsearch',
    name: 'pwsearch',
    component: PWsearchView
  },
  {
    path: '/pwreset',
    name: 'pwreset',
    component: PWresetView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
