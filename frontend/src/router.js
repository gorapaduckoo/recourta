import { createRouter, createWebHistory } from 'vue-router'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('./views/LoginView.vue'),
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('./views/SignupView.vue'),
    },
    {
      path: '/pwsearch',
      name: 'pwsearch',
      component: () => import('./views/PWsearchView.vue'),
    },
    {
      path: '/pwreset',
      name: 'pwreset',
      component: () => import('./views/PWresetView.vue'),
    },
  ],
})
