import router from '../../router.js'
import axios from 'axios'
import rct from '../../api/rct'

export const user = {
  state: {
	  accessToken: localStorage.getItem('accessToken') || '' ,
		refreshToken: localStorage.getItem('refreshToken') || '' ,
		currentUser: {},
		authError: null,
		userId: null,
		isStudent: null,
	},

	getters: {
		isLoggedIn: state => !!state.token,
		currentUser: state => state.currentUser,
		authError: state => state.authError,
		authHeader: state => ({ Authorization: `Token ${state.token}`})
	},

	mutations: {
		SET_ACCESS_TOKEN: (state, accessToken) => state.accessToken = accessToken,
		SET_REFRESH_TOKEN: (state, refreshToken) => state.refreshToken = refreshToken,
		SET_CURRENT_USER: (state, user) => state.currentUser = user,
		SET_AUTH_ERROR: (state, error) => state.authError = error,
		Set_userId: (state,userId) => state.userId = userId,
		Set_isStudent: (state,isStudent) => state.isStudent = isStudent,
	},

	actions: {
		saveAccessToken({ commit }, accessToken) {
			commit('SET_ACCESS_TOKEN', accessToken)
			localStorage.setItem('accessToken', accessToken)
		},
		
		saveRefreshToken({ commit }, refreshToken) {
			commit('SET_REFRESH_TOKEN', refreshToken)
			localStorage.setItem('refreshToken', refreshToken)
		},

		removeAccessToken({ commit }) {
			commit('SET_ACCESS_TOKEN', '')
			localStorage.setItem('accessToken', '')
		},

		removeAccessToken({ commit }) {
			commit('SET_REFRESH_TOKEN', '')
			localStorage.setItem('refreshToken', '')
		},

		login({ commit, dispatch }, credentials) {
			axios({
				url: rct.login.login(),
				method: 'post',
				data: credentials
			})
			.then(res => {
				dispatch('saveToken', res.data.token)
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