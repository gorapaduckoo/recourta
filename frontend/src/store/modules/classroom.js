import router from '../../router.js'
import axios from 'axios'
import rct from '../../api/rct'

export const classroom = {
  state: {
		mySessionId: '',
		myUserName: '',
	},

	getters: {
		currentMySessionId: state => state.mySessionId,
		currentMyUserName: state => state.myUserName,
	},

	mutations: {
		SET_MySessionId:(state,newSessionId) => state.mySessionId = newSessionId,
		SET_MyUserName: (state,newUserName) => state.myUserName = newUserName,
	},

	actions: {
		

	},
}