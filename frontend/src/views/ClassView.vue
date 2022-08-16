<template>
<div class="bg-[#444444] text-white">
  <div :class="{'lg:pr-[360px]':state.isside}" class="flex flex-col overflow-y-auto h-[100vh] lg:py-2 justify-between items-center font-bold text-4xl">
    <ClassList v-if="state.session && state.issublist" :publisher="state.publisher" :userAll="state.userAll" class="hidden lg:flex flex-1"/>
    <button @click="toggleSublist" :class="{'mt-0':!state.issublist}" class="hidden lg:flex my-2 px-2 hover:text-[#b8b8b8] text-neutral-300 rounded-full hover:bg-[#4e4e4e]">
      <svg :class="{'rotate-180':!state.issublist}" class="h-5 w-5"  width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">  <path stroke="none" d="M0 0h24v24H0z"/>  <line x1="12" y1="4" x2="12" y2="14" />  <line x1="12" y1="4" x2="16" y2="8" />  <line x1="12" y1="4" x2="8" y2="8" />  <line x1="4" y1="20" x2="20" y2="20" /></svg>
    </button>
    <ClassMain class="mainscreen" v-if="state.session" :mainStreamManager="state.mainStreamManager"/>
    <div v-if="state.issubtitle" class="w-[480px] lg:w-[640px] flex-none text-center mt-2 text-lg">
      {{state.texts}}
    </div>
    <div v-else class="h-[44px]"></div>
    <ClassToolbar class="flex-none mt-2" :isshare="state.isshare" :ismic="state.ismic" :iscam="state.iscam" :isLecturer="state.isLecturer" :issubtitle="state.issubtitle" :isAuth="state.isAuth" @tryleave="leaveClass" @toggleshare="toggleshare" @togglecam="togglecam" @togglemic="togglemic" @toggleSubtitle="toggleSubtitle"/>
    <ClassSidebar @closeList="toggleside" @submitMsg="sendMsg" @submitAuth="sendauth" @submitCam="sendcam" @submitMic="sendmic" @submitBan="sendban" :publisher="state.publisher" :subscribers="state.subscribers" :msglist="state.msgs" :myID="(state.publisher)?state.publisher.stream.connection.connectionId:null" :sidebarTitle="state.sidebarTitle" :classAttList="state.classAttList" :classAbsList="state.classAbsList" :isLecturer="state.isLecturer" class="lg:hidden flex-1 max-h-[70vh] mt-2 width-full border-t-[1px] border-neutral-400"/>
  </div>
  <ClassSidebar @closeList="toggleside" @submitMsg="sendMsg" @submitAuth="sendauth" @submitCam="sendcam" @submitMic="sendmic" @submitBan="sendban" :publisher="state.publisher" :subscribers="state.subscribers" :msglist="state.msgs" :myID="(state.publisher)?state.publisher.stream.connection.connectionId:null" :sidebarTitle="state.sidebarTitle" :classAttList="state.classAttList" :classAbsList="state.classAbsList" :isLecturer="state.isLecturer" v-if="state.isside" class="hidden lg:flex absolute top-0 right-0 h-full width-[360px] border-l-[1px] border-neutral-400"/>
</div>
 
<button @click="toggleside" :class="{'right-4 top-3':!state.isside,'right-[308px] top-2':state.isside}" class="hidden lg:flex hover:text-neutral-200 text-neutral-400 absolute">
  <svg v-if="!state.isside" class="h-14 w-14"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8h2a2 2 0 012 2v6a2 2 0 01-2 2h-2v4l-4-4H9a1.994 1.994 0 01-1.414-.586m0 0L11 14h4a2 2 0 002-2V6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2v4l.586-.586z"/>
  </svg>
</button>
</template>

<script setup>
import ClassList from '../components/ClassList.vue'
import ClassMain from '../components/ClassMain.vue'
import ClassToolbar from '../components/ClassToolbar.vue'
import ClassSidebar from '../components/ClassSidebar.vue'
import axios from 'axios'
import rct from '../api/rct'
import { OpenVidu } from 'openvidu-browser'
import { reactive } from 'vue'
import { useStore } from 'vuex'
import * as faceapi from 'face-api.js'
import { FaceMatch, FaceMatcher } from 'face-api.js'


const store = useStore()

axios.defaults.headers.post['Content-Type'] = 'application/json'

const state = reactive({
  OV: undefined,
  session: undefined,
  mainStreamManager: undefined,
  publisher: undefined,
  temppublisher: undefined,
  subscribers: [],

  mySessionId: store.getters.currentMySessionId,
  myUserId: store.state.user.userId,
  sidebarTitle: store.getters.currentSidebarTitle,

  classRegiList: [['lecturer',store.getters.currentLecturerName]],
  classAttList: [],
  classAbsList: [],
  userAll: [],

  isshare:false,
  isside:false,
  msgs:[],
  iscam:true,
  ismic:false,
  issubtitle:false,
  issublist:true,
  texts:"",
  
  isLecturer:store.getters.currentIsLecturer,
  isAuth:false,
})

const toggleside = () => {
  state.isside=!state.isside
}

const toggleshare = () => {
  state.isshare=!state.isshare
  if(state.isshare)startsharing()
  else stopsharing()
}

const togglecam = () => {
  state.iscam=!state.iscam
  if(state.iscam) state.publisher.publishVideo(true)
  else state.publisher.publishVideo(false)
}

const togglemic = () => {
  state.ismic=!state.ismic
  if(state.ismic) {
    state.publisher.publishAudio(true)
    recognition.start()
  }
  else {
    state.publisher.publishAudio(false)
    recognition.stop()
    cnt=0
  }
}

const toggleSubtitle = () => {
  state.issubtitle=!state.issubtitle
}

const toggleSublist = () => {
  state.issublist=!state.issublist
}

const reactiveAttList = () => {
  const tmpAttList = []
  const tmpAbsList = []

  // for (let usr of state.classRegiList) {
  //   if(state.publisher && JSON.parse(state.publisher.stream.connection.data).clientData === usr[0]) {
  //     tmpAttList.push([usr[0], usr[1], state.publisher.stream.connection.connectionId, state.publisher.stream.connection])
  //   }
    
  //   else {
  //     const sub = state.subscribers.find(sub => JSON.parse(sub.stream.connection.data).clientData === usr[0])
  //     if(sub) {
  //       tmpAttList.push([usr[0], usr[1], sub.stream.connection.connectionId, sub.stream.connection])
  //     } else {
  //       tmpAbsList.push([usr[0], usr[1]])
  //     }
  //   }
  // }

  if (state.publisher) {
    const tmpUserId = state.isLecturer? 'lecturer': state.myUserId
    const tmpId = state.classRegiList.find(student => student[0] === tmpUserId)
    tmpAttList.push([tmpId[0], tmpId[1], state.publisher.stream.connection.connectionId, state.publisher.stream.connection,state.publisher])
  }

  for (let sub of state.subscribers) {
    const tmpId = state.classRegiList.find(student => student[0] === JSON.parse(sub.stream.connection.data).clientData)
    tmpAttList.push([tmpId[0], tmpId[1], sub.stream.connection.connectionId, sub.stream.connection,sub])
  }

  for (let student of state.classRegiList) {
    const tmpId = state.classAttList.find(att => att[0] === student[0])
    if (!tmpId && student[0] !== 'lecturer') tmpAbsList.push(student)
  }

  state.classAttList = tmpAttList
  state.classAbsList = tmpAbsList

  const tmpUser = []

  if (state.publisher) tmpUser.push(state.publisher)

  tmpUser.push(...state.subscribers)

  state.userAll = tmpUser
}


const getRegiList = async () => {
  await axios({
    url: rct.regist.currentstudentlist(state.mySessionId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  }).then(res=>{

    for(let usr of res.data.userList){
      state.classRegiList.push([usr.userId,usr.name])
    }
    const tmpRegiList = [['0', '김싸피'], ['1', '이싸피'], ['2', '박싸피'], ['3', '안싸피']]

    for(let usr of tmpRegiList) {
      state.classRegiList.push([usr[0], usr[1]])
    }
  })
}



const joinSession = () => {
  getRegiList()
  
  state.OV = new OpenVidu();


  // --- Init a session ---
  state.session = state.OV.initSession();

  // --- Specify the actions when events take place in the session ---

  // On every new Stream received...
  state.session.on('streamCreated', ({ stream }) => {
    const subscriber = state.session.subscribe(stream);
    state.subscribers.push(subscriber);
    reactiveAttList()
    if(state.isshare&&(subscriber.stream.typeOfVideo==="SCREEN")) state.mainStreamManager = subscriber
    // state.mainStreamManager=subscriber
    // updateMainVideoStreamManager(subscriber)
  });

  // On every Stream destroyed...
  state.session.on('streamDestroyed', ({ stream }) => {
    const index = state.subscribers.indexOf(stream.streamManager, 0);
    if (index >= 0) {
      state.subscribers.splice(index, 1);
    }
    reactiveAttList()
  });

  // On every asynchronous exception...
  state.session.on('exception', ({ exception }) => {
    // console.warn(exception);
  });

  state.session.on('signal:my-chat', (event) => {
    state.msgs.push([event.data,event.from.connectionId])
  });

  state.session.on('signal:screenshare',(event) => {
    if(event.data==="ON"){
      state.isshare=true
    }
    else{
      state.isshare=false
      state.mainStreamManager = state.publisher
    }
  })

  state.session.on('signal:Auth',(event) => {
    state.isAuth=!state.isAuth
    if(state.isshare){
      stopsharing()
      state.isshare=false
    }
  })

  state.session.on('signal:Cam',(event) => {
    if(state.iscam) togglecam()
  })

  state.session.on('signal:Mic',(event) => {
    if(state.ismic) togglemic()
  })

  state.session.on('signal:Ban',(event) => {
    leaveClass()
  })

  state.session.on('publisherStartSpeaking', (event) => {
    console.log('User ' + event.connection.connectionId + ' start speaking');
  });

  state.session.on('publisherStopSpeaking', (event) => {
    console.log('User ' + event.connection.connectionId + ' stop speaking');
  });

  // --- Connect to the session with a valid user token ---

  // 'getToken' method is simulating what your server-side should do.
  // 'token' parameter should be retrieved and returned by your own backend
  getToken(state.mySessionId).then(token => {
    const tmpClientData = state.isLecturer? 'lecturer': state.myUserId
    state.session.connect(token, { clientData: tmpClientData })
    .then(() => {

      // --- Get your own camera stream with the desired properties ---

      let publisher = state.OV.initPublisher(undefined, {
        audioSource: undefined, // The source of audio. If undefined default microphone
        videoSource: undefined, // The source of video. If undefined default webcam
        publishAudio: false,	// Whether you want to start publishing with your audio unmuted or not
        publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
        resolution: '640x480',  // The resolution of your video
        frameRate: 30,			// The frame rate of your video
        insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
        mirror: false       	// Whether to mirror your local video or not
      });

      state.mainStreamManager = publisher;
      state.publisher = publisher

      // --- Publish your stream ---
      state.session.publish(state.publisher);
      reactiveAttList()
    })
    .catch(error => {
    });
    window.addEventListener('beforeunload', leaveClass);
  });

  
}

const getToken = async mySessionId => {
  return createSession(mySessionId).then(sessionId => createToken(sessionId));
}

const createSession = (sessionId) => {
  return new Promise((resolve, reject) => {
    axios
      .post(rct.webrtc.openvd_url()+'/openvidu/api/sessions', JSON.stringify({
        customSessionId: sessionId,
      }), {
        auth: {
          username: 'OPENVIDUAPP',
          password: rct.webrtc.openvd_secret(),
        },
      })
      .then(response => response.data)
      .then(data => resolve(data.id))
      .catch(error => {
        if (error.response.status === 409) {
          resolve(sessionId);
        } else {
          console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${rct.webrtc.openvd_url()}`);
          if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${rct.webrtc.openvd_url()}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${rct.webrtc.openvd_url()}"`)) {
            location.assign(`${rct.webrtc.openvd_url()}/accept-certificate`);
          }
          reject(error.response);
        }
      });
  });
}

// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
const createToken = (sessionId) => {
  return new Promise((resolve, reject) => {
    axios
      .post(`${rct.webrtc.openvd_url()}/openvidu/api/sessions/${sessionId}/connection`, {}, {
        auth: {
          username: 'OPENVIDUAPP',
          password: rct.webrtc.openvd_secret(),
        },
      })
      .then(response => response.data)
      .then(data => resolve(data.token))
      .catch(error => reject(error.response));
  });
}

const updateMainVideoStreamManager = (stream) => {
  if (state.mainStreamManager === stream) return;
  state.mainStreamManager = stream;
}

const leaveClass = () => {
  // --- Leave the session by calling 'disconnect' method over the Session object ---
  if (state.session) state.session.disconnect();

  // Select whether to save the lecture script
  let issave = confirm("강의록을 저장하시겠습니까?")

  if(issave) {
    let blob = new Blob([scripts], {type: 'text/plain'})
    link.href = window.URL.createObjectURL(blob)
    link.download = state.sidebarTitle + '_강의록.txt'
    link.click()
  }

  state.session = undefined
  state.mainStreamManager = undefined
  state.publisher = undefined
  state.temppublisher = undefined
  state.subscribers = []
  state.OV = undefined
  
  state.ismic=false
  state.iscam=false
  state.isshare=false
  state.isside=false
  state.msgs=[]

  state.mySessionId = ""
  state.myUserId = ""
  state.sidebarTitle = ""

  state.classRegiList = []
  state.classAttList = []
  state.classAbsList = []
  state.userAll = []

  state.issubtitle = false
  state.issublist = false
  state.texts=""
  state.isAuth=false

  state.isLecturer = false
  

  store.commit("SET_MySessionId",'')
  store.commit("SET_MyUserName",'')

  window.removeEventListener('beforeunload', leaveClass);
  location.href="/main"
}

const sendMsg = (data,reciever) => {
  state.session.signal({
    data: data,  // Any string (optional)
    to: reciever,                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'my-chat'             // The type of message (optional)
  })
  .then(() => {
  })
  .catch(error => {
    console.error(error)
  })
}

const sendauth = (reciever) => {
  state.session.signal({
    data: "auth",  // Any string (optional)
    to: reciever,                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'Auth'             // The type of message (optional)
  })
  .then(() => {
  })
  .catch(error => {
    console.error(error)
  })
}

const sendcam = (reciever) => {
  state.session.signal({
    data: "cam",  // Any string (optional)
    to: reciever,                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'Cam'             // The type of message (optional)
  })
  .then(() => {
  })
  .catch(error => {
    console.error(error)
  })
}

const sendmic = (reciever) => {
  state.session.signal({
    data: "mic",  // Any string (optional)
    to: reciever,                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'Mic'             // The type of message (optional)
  })
  .then(() => {
  })
  .catch(error => {
    console.error(error)
  })
}

const sendban = (reciever) => {
  state.session.signal({
    data: "ban",  // Any string (optional)
    to: reciever,                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'Ban'             // The type of message (optional)
  })
  .then(() => {
  })
  .catch(error => {
    console.error(error)
  })
}

const startsharing = () => {
  
  let newPublisher = state.OV.initPublisher(
    undefined,
    {
      audioSource: undefined, // The source of audio. If undefined default microphone
      videoSource: "screen",
      publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
      publishVideo: true, // Whether you want to start publishing with your video enabled or not
      resolution: "1920x1080", // The resolution of your video
      frameRate: 30, // The frame rate of your video
      insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
      mirror: false // Whether to mirror your local video or not
    },
    error => {
      // Function to be executed when the method finishes
      if (error) {
        console.error(
          "Error while initializing publisher",
          error
        );
        state.publisher = state.temppublisher;
        state.session.publish(state.publisher);
      } else {
      }
    }
  );
  newPublisher.once("accessAllowed", event => {
    newPublisher.stream
      .getMediaStream()
      .getVideoTracks()[0]
      .addEventListener("ended", () => {
        stopsharing();
      });
  });
  newPublisher.once("accessAllowed", () => {
    try {
      newPublisher.stream
        .getMediaStream()
        .getVideoTracks()[0]
        .applyConstraints({
          width: '1920',
          height: '1080',
        });
    } catch (error) {
      console.error("Error applying constraints: ", error);
    }
  });
  state.session.unpublish(state.publisher)
  state.temppublisher = state.publisher
  state.publisher = newPublisher
  state.session.publish(state.publisher)
  sss('ON')
  state.mainStreamManager=state.publisher
}

const stopsharing = () => {
  state.session.unpublish(state.publisher);
  state.publisher = state.temppublisher;
  state.session.publish(state.publisher);
  sss('OFF')
  state.mainStreamManager = state.publisher
}

// sendscreenshare => sss
const sss = (data) => {
  state.session.signal({
    data: data,  // Any string (optional)
    to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'screenshare'             // The type of message (optional)
  })
  .then(() => {
  })
  .catch(error => {
    console.error(error)
  })
}

joinSession()


///////////////////////////// Speech Recognition Part ////////////////////////////

 window.SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
  
  // Create New SpeechRecognition Object
  const recognition = new SpeechRecognition();
  recognition.interimResults = false;
  recognition.continuous = true;
  recognition.lang = 'ko-KR';

  

  // Create <p> element to insert on view
  let p = document.createElement('p');
  let link = document.createElement('a');
  let scripts = ""; // lecture script text


  let cnt = 0;
  // When the Speech Recognition Server returns the result, concat '.' on result
  // if you want concat current result and results to be returned later, chain .join('') after map() function
  recognition.onresult = function(e) {
    state.texts = e.results[cnt][0].transcript;
    // texts = Array.from(e.results).map(result => result[0])
    // .map(result => (result.transcript));
    scripts += state.texts;
    scripts +='\n';
    cnt++;

  // print {texts} on console
  }






</script>

<style scoped>
.mainscreen{
  flex:none;
}
@media (min-width: 1024px){
  .mainscreen{
    flex: 5 1 0%;
  }
}
</style>