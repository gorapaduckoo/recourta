<template>
<div class="bg-[#444444] text-white">
  <div :class="{'pr-[360px]':state.isside}" class="flex flex-col h-full py-2 justify-between items-center font-bold text-4xl">
    <ClassList v-if="state.session" :publisher="state.publisher" :subscribers="state.subscribers"/>
    <ClassMain v-if="state.session" :mainStreamManager="state.mainStreamManager"/>
    <ClassToolbar :isshare="state.isshare" :ismic="state.ismic" :iscam="state.iscam" @tryleave="leaveClass" @toggleshare="toggleshare" @togglecam="togglecam" @togglemic="togglemic"/>
  </div>
  <ClassSidebar @closeList="toggleside" @submitMsg="sendMsg" :publisher="state.publisher" :subscribers="state.subscribers" :msglist="state.msgs" :myID="(state.publisher)?state.publisher.stream.connection.connectionId:null" v-if="state.isside" class="absolute top-0 right-0 h-screen width-[360px] border-l-[1px] border-neutral-400"/>
</div>
<button @click="toggleside" :class="{'right-4 top-3':!state.isside,'right-[308px] top-2':state.isside}" class="hover:text-neutral-200 text-neutral-400 absolute">
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
import { reactive, computed, watch } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

axios.defaults.headers.post['Content-Type'] = 'application/json'

const state = reactive({
  OV: undefined,
  session: undefined,
  mainStreamManager: undefined,
  publisher: undefined,
  temppublisher: undefined,
  subscribers: [],

  mySessionId: computed(()=>store.getters.currentMySessionId),
  myUserName: computed(()=>store.getters.currentMyUserName),

  isshare:false,
  streamId:"",
  isside:false,
  msgs:[],
  iscam:true,
  ismic:false,
})

// watch(()=>state.streamId,(newId,oldId)=>{
//   console.log("change streamId")
//   const main = state.subscribers.find(sub=>sub.stream.connection.connectionId!==state.streamId)
//   console.log(main)
//   if(main) state.mainStreamManager = main
//   state.mainStreamManager = state.publisher
// })

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
  if(state.iscam) state.publisher.publishAudio(true)
  else state.publisher.publishAudio(false)
}

const joinSession = () => {
  state.OV = new OpenVidu();

  // --- Init a session ---
  state.session = state.OV.initSession();

  // --- Specify the actions when events take place in the session ---

  // On every new Stream received...
  state.session.on('streamCreated', ({ stream }) => {
    const subscriber = state.session.subscribe(stream);
    state.subscribers.push(subscriber);
    console.log(subscriber.stream.typeOfVideo)
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
  });

  // On every asynchronous exception...
  state.session.on('exception', ({ exception }) => {
    console.warn(exception);
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

  // --- Connect to the session with a valid user token ---

  // 'getToken' method is simulating what your server-side should do.
  // 'token' parameter should be retrieved and returned by your own backend
  getToken(state.mySessionId).then(token => {
    state.session.connect(token, { clientData: state.myUserName })
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
        // state.streamId = publisher.stream.connection.connectionId
        state.publisher = publisher

        // --- Publish your stream ---
        state.session.publish(state.publisher);
      })
      .catch(error => {
        console.log('There was an error connecting to the session:', error.code, error.message);
      });
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
          console.log(error)
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

  state.session = undefined
  state.streamId = ""
  state.mainStreamManager = undefined
  state.publisher = undefined
  state.temppublisher = undefined
  state.subscribers = []
  state.OV = undefined
  
  state.ismic=false
  state.iscam=false
  state.isshare=false
  state.isside=false
  msgs=[]

  store.commit("SET_MySessionId",'')
  store.commit("SET_MyUserName",'')

  // window.removeEventListener('beforeunload', leaveClass);
  location.href="/main"
}

const sendMsg = (data,reciever) => {
  state.session.signal({
    data: data,  // Any string (optional)
    to: reciever,                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'my-chat'             // The type of message (optional)
  })
  .then(() => {
    // console.log('Message successfully sent')
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
          "Error while initializing publisher: =====================================뉴퍼블리셔 게시하는도중에 에러가 발생했다",
          error
        );
        state.publisher = state.temppublisher;
        state.session.publish(state.publisher);
      } else {
        console.log("Publisher successfully initialized");
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
  // console.log(state.publisher)
  state.temppublisher = state.publisher
  state.publisher = newPublisher
  // console.log(state.publisher)
  state.session.publish(state.publisher)
  sss('ON')
  // state.streamId = state.publisher.stream.connection.connectionId
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
  console.log("sharing:",data)
  state.session.signal({
    data: data,  // Any string (optional)
    to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
    type: 'screenshare'             // The type of message (optional)
  })
  .then(() => {
    // console.log('Message successfully sent')
  })
  .catch(error => {
    console.error(error)
  })
}

joinSession()

</script>

<style>

</style>