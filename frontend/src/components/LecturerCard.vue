<template>
  <div class="relative ml-auto mr-auto mb-10">
    <!-- 보이는 부분 -->
    <div class="w-[400px] h-[500px] z-0 border-2 rounded-lg border-[#2c5172] dark:border-neutral-500 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <img class="rounded-t-lg w-[400px] h-[225px]" src="https://mdbootstrap.com/img/new/standard/nature/184.jpg" alt=""/>
      <div class="p-6 flex flex-col h-[275px] dark:text-neutral-100">
        <div>
          <div class="text-3xl text-justify font-bold dark:font-semibold mb-6 h-[72px]">데이터로 표현한 세상을 보고있는 우리와 나와 바다 놀러가</div>
        </div>
        <div class="flex justify-around h-full">
          <div class="flex flex-col-reverse">
            <div class="text-lg text-start">
              <p>월 13:00 ~ 15:00</p>
              <p>목 10:00 ~ 12:00</p>
              <p>금 09:00 ~ 12:00</p>
            </div>
          </div>
          <div class="flex flex-col items-end justify-end space-y-5">
            <router-link to="/classsetting" class="text-xl text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full text-center dark:bg-[#2c5172] py-2 px-10">
              <button>강의 관리</button>
            </router-link>
            <button @click="joinSession" class="text-xl text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full text-center dark:bg-[#2c5172] py-2 px-10">
              강의 시작
            </button>
            
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script setup>
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser';
import rct from '../api/rct'
import { reactive } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

axios.defaults.headers.post['Content-Type'] = 'application/json'

const state = reactive({
  OV: undefined,
  session: undefined,
  mainStreamManager: undefined,
  publisher: undefined,
  subscribers: [],
  // prop서 가져온 lectureid를 String()하여 이용
  mySessionId: 'SessionA',
  // store에 있는 이름 정보를 가져오기
  myUserName: '김싸피'
})

const joinSession = () => {
  state.OV = new OpenVidu();

  // --- Init a session ---
  state.session = state.OV.initSession();

  // --- Specify the actions when events take place in the session ---

  // On every new Stream received...
  state.session.on('streamCreated', ({ stream }) => {
    const subscriber = state.session.subscribe(stream);
    state.subscribers.push(subscriber);
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
          publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
          publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
          resolution: '640x480',  // The resolution of your video
          frameRate: 30,			// The frame rate of your video
          insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
          mirror: false       	// Whether to mirror your local video or not
        });

        state.mainStreamManager = publisher;
        state.publisher = publisher;

        // --- Publish your stream ---

        state.session.publish(state.publisher);
      })
      .catch(error => {
        console.log('There was an error connecting to the session:', error.code, error.message);
      });
  });

  console.log(state.session)
  // store.commit("SET_OV",state.OV)
  // store.commit("SET_Session",state.session)
  store.commit("SET_MainStreamManager",state.mainStreamManager)
  store.commit("SET_Publisher",state.publisher)
  store.commit("SET_Subscribers",state.subscribers)

  location.href="/class"
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


</script>

<style>

</style>