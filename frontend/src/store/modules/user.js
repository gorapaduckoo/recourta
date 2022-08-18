import router from '../../router.js'
import axios from 'axios'
import rct from '../../api/rct'

export const user = {
  state: {
	  accessToken: localStorage.getItem('accessToken') || '' ,
		refreshToken: localStorage.getItem('refreshToken') || '' ,
    refreshtime: localStorage.getItem('refreshtime') || null ,
    islogin: localStorage.getItem('islogin') || false ,
		userImgFirstUrl: 'http://localhost:8081/recourta/uploads/img/user/',
		userId: null,
		isStudent: null,
	},

	getters: {
	},

	mutations: {
		SET_ACCESS_TOKEN: (state, accessToken) => state.accessToken = accessToken,
		SET_REFRESH_TOKEN: (state, refreshToken) => state.refreshToken = refreshToken,
    SET_REFRESH_TIME: (state, refreshtime) => state.refreshtime = refreshtime,
    SET_IS_LOGIN: (state, islogin) => state.islogin = islogin,
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

    updateRefreshTime({ commit }, refreshtime) {
			commit('SET_REFRESH_TIME', refreshtime)
			localStorage.setItem('refreshtime', refreshtime)
		},

    updateIsLogin({ commit }, islogin) {
			commit('SET_IS_LOGIN', islogin)
			localStorage.setItem('islogin', islogin)
		},
	},
}