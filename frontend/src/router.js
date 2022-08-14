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
      path: '/recourta/reset',
      name: 'pwreset',
      component: () => import('./views/PWresetView.vue'),
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('./views/MainView.vue'),
    },
    {
      path: '/classlist',
      name: 'classlist',
      component: () => import('./views/ClassListView.vue'),
    },
    {
      path: '/pastclass',
      name: 'pastclass',
      component: () => import('./views/PastClassView.vue'),
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('./views/ProfileView.vue'),
    },
    {
      path: '/class',
      name: 'class',
      component: () => import('./views/ClassView.vue'),
    },
    {
      path: '/classsetting/:lecId',
      name: 'classsetting',
      component: () => import('./views/ClassSettingView.vue'),
    },
    {
      path: '/notice',
      name: 'notice',
      component: () => import('./views/NoticeListView.vue'),
    },
  ],
})
