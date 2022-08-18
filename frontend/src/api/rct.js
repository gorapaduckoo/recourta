const HOST = 'https://recourta.ga'
const HOST_BACK = 'https://recourta.ga/recourta'

const USER = '/user'
const LECTURE = '/lecture'
const REGIST = '/registration'
const CHECK = '/check'

const openvidu_port = ":4443"
const openvidu_server_secret = "MY_SECRET"

export default {
  user: {
    signup: () => HOST_BACK + USER,
    userauth: () => HOST_BACK + '/auth/regist',
    usercode: () => HOST_BACK + '/auth/code',
    tokenrefresh: () => HOST_BACK + '/auth/refresh',
    userinfo: userid => HOST_BACK + USER + '/' + userid,
    userimgmod: () => HOST_BACK + USER + '/img',
    userpwmod: () => HOST_BACK + USER + '/pw',
      
  },
  lecture: {
    lecturecreate: () => HOST_BACK + LECTURE,
    lectureinfo: lectureid => HOST_BACK + LECTURE + '/' + lectureid,
    availablelist: userid => HOST_BACK + LECTURE + '/' + userid + '/availableList',
    currentteaching: userid => HOST_BACK + LECTURE + '/' + userid + '/currentTeachingList',
    currentlearning: userid => HOST_BACK + LECTURE + '/' + userid + '/currentLectureList',
  },
  regist: {
    regist: () => HOST_BACK + REGIST,
    registinfo: regiid => HOST_BACK + REGIST + '/' + regiid,
    registlearning: userid => HOST_BACK + REGIST + USER + '/current/' + userid,
    registlearned: userid => HOST_BACK + REGIST + USER + '/previous/' + userid,
    currentstudentlist: lectureid => HOST_BACK + REGIST + '/lecture/' + lectureid,
  },
  login: {
    login: () => HOST_BACK + '/login',
    pwsearch: () => HOST_BACK + '/reset',
    pwreset: () => HOST_BACK + '/reset/pw',
    logout: () => HOST_BACK + '/logout',
  },
  webrtc: {
    openvd_url:() => "https://"+location.hostname+openvidu_port,
    openvd_secret:() => openvidu_server_secret,
    checkin:() => HOST_BACK + CHECK + "/in",
    checkout:() => HOST_BACK + CHECK + "/out",
  }
}
