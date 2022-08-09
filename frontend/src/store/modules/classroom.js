import router from '../../router.js'
import axios from 'axios'
import rct from '../../api/rct'

export const classroom = {
  state: {
	  OV: undefined ,
		session: undefined,
		mainStreamManager: undefined,
		publisher: undefined,
		subscribers:[],
	},

	getters: {
		currentOV: state => state.OV,
		currentSession: state => state.session,
		currentMainStreamManager: state => state.mainStreamManager,
		currentPublisher: state => state.publisher,
		currentSubscribers: state => state.subscribers,
	},

	mutations: {
		SET_OV:(state,newOV) => state.OV = newOV,
		SET_Session:(state,newSession) => state.session = newSession,
		SET_MainStreamManager:(state,newMainStreamManager) => state.mainStreamManager = newMainStreamManager,
		SET_Publisher:(state,newPublisher) => state.publisher = newPublisher,
		Set_Subscribers:(state,newSubs) => state.subscribers = newSubs,
	},

	actions: {
		

	},
}