const HOST = 'https://recourta.ga/recourta'

const USER = '/user'
const LECTURE = '/lecture'
const REGIST = '/registration'
const CHECK = '/check'
const ATTENDANCE = '/attendance'

const openvidu_port = ":4443"
const openvidu_server_secret = "MY_SECRET"

export default {
  user: {
    signup: () => HOST + USER,
    userauth: () => HOST + '/auth/regist',
    usercode: () => HOST + '/auth/code',
    tokenrefresh: () => HOST + '/auth/refresh',
    userinfo: userid => HOST + USER + '/' + userid,
    userimgmod: () => HOST + USER + '/img',
    userpwmod: () => HOST + USER + '/pw',
    facemodel: () => HOST + '/uploads/model/',
  },
  lecture: {
    lecturecreate: () => HOST + LECTURE,
    lectureinfo: lectureid => HOST + LECTURE + '/' + lectureid,
    lecturedelete: () => HOST + LECTURE,
    availablelist: userid => HOST + LECTURE + '/' + userid + '/availableList',
    currentteaching: userid => HOST + LECTURE + '/' + userid + '/currentTeachingList',
    currentlearning: userid => HOST + LECTURE + '/' + userid + '/currentLectureList',
  },
  regist: {
    regist: () => HOST + REGIST,
    registinfo: regiid => HOST + REGIST + '/' + regiid,
    registlearning: userid => HOST + REGIST + USER + '/current/' + userid,
    registlearned: userid => HOST + REGIST + USER + '/previous/' + userid,
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
    exitclass: sessionid => HOST + ATTENDANCE + "/autorecord/" + sessionid,
    checkin:() => HOST + CHECK + "/in",
    checkout:() => HOST + CHECK + "/out",
  },
  attendance: {
    sessionAttendance: sessionId => HOST + ATTENDANCE + '/session/' + sessionId,
    lectureAllAttendance: lectureId => HOST + ATTENDANCE + LECTURE + '/' + lectureId,
    changeAttendance: () => HOST + ATTENDANCE + '/change',
    sessionstarttime: sessionId => HOST + '/session/starttime/' + sessionId,
  },
}
