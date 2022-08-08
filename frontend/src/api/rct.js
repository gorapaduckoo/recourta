const HOST = 'http://localhost:8081/recourta'

const USER = '/user'
const LECTURE = '/lecture'
const REGIST = '/registration'

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
        lecturecreate: () => HOST,
        lectureinfo: lectureid => HOST + LECTURE + '/' + lectureid,
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
    }
}