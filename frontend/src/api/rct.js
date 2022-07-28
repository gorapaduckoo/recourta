const HOST = 'http://localhost:8081/recourta/'

const USER = 'user/'
const LECTURE = 'lecture/'
const REGIST = 'registration/'

export default {
    user: {
        signup: () => HOST + USER,
        userauth: () => HOST + USER + 'auth/',
        usercode: () => HOST + USER + 'code/',
        userinfo: userid => HOST + USER + userid,
        userimgmod: () => HOST + USER + 'img/',
        userpwmod: () => HOST + USER + 'pw/',
        
    },
    lecture: {
        lecturecreate: () => HOST,
        lectureinfo: lectureid => HOST + LECTURE + lectureid,
    },
    regist: {
        regist: () => HOST + REGIST,
        registinfo: regiid => HOST + REGIST + regiid, 
    },
    login: {
        login: () => HOST + 'login/',
        pwreset: () => HOST + 'reset/',
        logout: () => HOST + 'logout/',
    }
}