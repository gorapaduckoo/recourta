import router from '../../router.js'
import axios from 'axios'
import rct from '../../api/rct'

export const user = {
    state: {
      token: localStorage.getItem('token') || '' ,
      currentUser: {},
      authError: null,
    },

    getters: {
			isLoggedIn: state => !!state.token,
    	currentUser: state => state.currentUser,
			authError: state => state.authError,
			authHeader: state => ({ Authorization: `Token ${state.token}`})
    },

    mutations: {
			SET_TOKEN: (state, token) => state.token = token,
			SET_CURRENT_USER: (state, user) => state.currentUser = user,
			SET_AUTH_ERROR: (state, error) => state.authError = error
    },

    actions: {
			saveToken({ commit }, token) {
				commit('SET_TOKEN', token)
				localStorage.setItem('token', token)
			},

			removeToken({ commit }) {
				commit('SET_TOKEN', '')
				localStorage.setItem('token', '')
			},

			login({ commit, dispatch }, credentials) {
				axios({
					url: rct.login.login(),
					method: 'post',
					data: credentials
				})
					.then(res => {
						const token = res.data.token
						dispatch('saveToken', token)
						dispatch('fetchCurrentUser')
						router.push({ name: 'main' })
					})
					.catch(err => {
						commit('SET_AUTH_ERROR', err.response.data)
					})
			},

			signup({ commit, dispatch }, credentials) {
				axios({
					url: rct.user.signup(),
					method: 'post',
					data: credentials
				})
					.then(res => {
						const token = res.data.token
						dispatch('saveToken', token)
						dispatch('fetchCurrentUser')
						router.push({ name: 'login' })
					})
					.catch(err => {
						commit('SET_AUTH_ERROR', err.response.data)
					})
			},

			initialization({ commit }) {
				commit('SET_AUTH_ERROR', null)
			},

			logout({ getters, dispatch }) {
				axios({
					url: rct.login.logout(),
					method: 'post',
					headers: getters.authHeader,
				})
					.then(() => {
						dispatch('removeToken')
						router.push({ name: 'login' })
					})
					.catch(err => {
						console.error(err.response)
					})
			},

			fetchCurrentUser({ commit, getters, dispatch }) {
				if (getters.isLoggedIn) {
					axios({
						url: drf.accounts.currentUserInfo(),
						method: 'get',
						headers: getters.authHeader,
					})
						.then(res => commit('SET_CURRENT_USER', res.data))
						.catch(err => {
							if (err.response.status === 401) {
								dispatch('removeToken')
								router.push({ name: 'login' })
							}
						})
				}
			},
    },
}