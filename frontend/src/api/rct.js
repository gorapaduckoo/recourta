const HOST = 'http://localhost:8081/recourta'

const USER = '/user'
const LECTURE = '/lecture'
const REGIST = '/registration'

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
    currentteaching: userid => HOST + LECTURE + '/' + userid + '/currentTeachingList'
  },
  regist: {
    regist: () => HOST + REGIST,
    registinfo: regiid => HOST + REGIST + '/' + regiid, 
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
  }
}