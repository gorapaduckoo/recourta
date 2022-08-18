import { createStore } from 'vuex'
import axios from 'axios'

import createPersistedState from 'vuex-persistedstate'

import { lecture } from './modules/lecture'
import { lectureinfo } from './modules/lectureinfo'
import { login } from './modules/login'
import { user } from './modules/user'
import { classroom } from './modules/classroom'


export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    lecture,
    lectureinfo,
    login,
    user,
    classroom,
  },
  plugins: [
    createPersistedState(),
  ],
})
