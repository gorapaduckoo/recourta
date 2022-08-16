import router from '../../router.js'
import axios from 'axios'
import rct from '../../api/rct'

export const classroom = {
  state: {
		mySessionId: '',
		myLectureId: '',
		LecturerName: '',
		sidebarTitle: '',
		isLecturer: false,
	},

	getters: {
		currentMySessionId: state => state.mySessionId,
		currentMyLectureId: state => state.myLectureId,
		currentLecturerName: state => state.LecturerName,
		currentSidebarTitle: state => state.sidebarTitle,
		currentIsLecturer: state => state.isLecturer,
	},

	mutations: {
		SET_MySessionId:(state,newSessionId) => state.mySessionId = newSessionId,
		SET_MyLectureId:(state,newLectureId) => state.myLectureId = newLectureId,
		SET_LecturerName: (state,newLecturerName) => state.LecturerName = newLecturerName,
		SET_SidebarTitle: (state,newSidebarTitle) => state.sidebarTitle = newSidebarTitle,
		SET_IsLecturer: (state,newIsLecturer) => state.isLecturer = newIsLecturer,
		
	},

	actions: {
		

	},
}