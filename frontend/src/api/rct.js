const HOST = 'http://localhost:8081/recourta'

const USER = '/user'
const LECTURE = '/lecture'
const REGIST = '/registration'
const CHECK = '/check'

const openvidu_port = ":4443"
const openvidu_server_secret = "MY_SECRET"

export default {
  user: {
    signup: () => HOST + USER,
    userauth: () => HOST + '/auth/regist',
    usercode: () => HOST + '/auth/code',
    userinfo: userid => HOST + USER + '/' + userid,
    userimgmod: () => HOST + USER + '/img',
    userpwmod: () => HOST + USER + '/pw',
      
  },
  lecture: {
    lecturecreate: () => HOST + LECTURE,
    lectureinfo: lectureid => HOST + LECTURE + '/' + lectureid,
    currentteaching: userid => HOST + LECTURE + '/' + userid + '/currentTeachingList',
    currentlearning: userid => HOST + LECTURE + '/' + userid + '/currentLectureList'
  },
  regist: {
    regist: () => HOST + REGIST,
    registinfo: regiid => HOST + REGIST + '/' + regiid,
    registlearning: userid => HOST + REGIST + USER + '/current/' + userid,
    currentstudentlist: lectureid => HOST + REGIST + '/lecture/' + lectureid,
  },
  login: {
    login: () => HOST + '/login',
    pwsearch: () => HOST + '/reset',
    pwreset: () => HOST + '/reset/pw',
    logout: () => HOST + '/logout',
  },
  webrtc: {
    openvd_url:() => "https://"+location.hostname+openvidu_port,
    openvd_secret:() => openvidu_server_secret,
    checkin:() => HOST + CHECK + "/in",
    checkout:() => HOST + CHECK + "/out",
  }
}