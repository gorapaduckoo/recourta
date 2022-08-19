<template>
  <DarkmodeButton />
  <CustomFooter/>
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[60px] w-1/3 min-w-[480px] ml-auto mr-auto">
    <div class="pt-20 ml-[1.2em] mb-6 text-2xl font-bold dark:font-semibold text-center tracking-[1.2em]">{{ state.name }}</div>
    <div class="mb-16 text-lg font-medium text-center">{{ state.email }}</div>
    <!-- 저장되어 있는 사진 -->
    <img v-if="!state.isCamOpen" :src="state.takenImg" alt="" class="my-2 ml-auto mr-auto w-[384px] h-[288px]">
    <!-- 카메라 로딩 -->
    <div v-show="state.isCamOpen && state.isLoad" class="flex justify-center items-center mr-auto ml-auto w-[384px] h-[288px] bg-black mt-2">
      <svg aria-hidden="true" role="status" class="w-[80px] h-[80px] text-white animate-spin" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="#2c5172"/>
        <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="#92a8d1"/>
      </svg>
    </div>
    <!-- 사진등록을 위한 캠/사진 화면 -->
    <div v-if="state.isCamOpen" v-show="!state.isLoad" class="my-2" :class="{ 'opacity-0' : state.isShot }">
      <div :class="{'opacity-0' : state.isShot}"></div>
      <div v-if="!state.isFace" class="mb-1 text-[15px] text-center font-bold dark:font-semibold text-[#fe5358] dark:text-[#fe5358]">얼굴이 잘 보이게 사진을 찍어주세요.</div>
      <div v-if="!state.isSingle" class="mb-1 text-[15px] text-center font-bold dark:font-semibold text-[#fe5358] dark:text-[#fe5358]">한 사람만 나오게 사진을 찍어주세요.</div>
      <div v-if="state.isFace&&state.isSingle&&!state.isPhotoTake" class="mb-1 text-[15px] text-center font-bold dark:font-semibold text-[#fe5358] dark:text-[#fe5358]">얼굴 인식까지 다소 시간이 걸릴 수 있으니 잠시 기다려주세요.</div>
      <!-- 웹캠 -->
      <video width="384" height="288" class="mr-auto ml-auto" v-show="!state.isPhotoTake" ref="profileCamera" autoplay></video>
      <!-- 캡쳐 -->
      <canvas width="384" height="288" class="mr-auto ml-auto" v-show="state.isPhotoTake" id="profilePhotoTaken" ref="profileCanvas"></canvas>
    </div>
    <!-- 웹캠 버튼 -->
    <div class="flex justify-center mb-12">
      <button v-if="!state.isCamOpen" type="button" class="text-[#2c5172] dark:text-gray-300 text-sm border-2 border-[#2c5172] dark:border-gray-300 focus:outline-none rounded-md px-3 py-0.5 text-center hover:bg-gray-100 dark:hover:bg-[#555555]" @click="toggleCam">
        <span>사진변경</span>
      </button>
      <button v-if="state.isCamOpen && !state.isLoad" type="button" :class="{'bg-[#faa405] hover:bg-[#fbb026] border-[#faa405] hover:border-[#fbb026] font-semibold':state.isPhotoTake && state.isCamOpen,'bg-[#4fb054] hover:bg-[#66bb6a] border-[#4fb054] hover:border-[#66bb6a]':!state.isPhotoTake || !state.isCamOpen}" class="text-white dark:text-gray-100 text-sm border-2 focus:outline-none rounded-md px-3 py-0.5 text-center" @click="takePhotoShot">
        <span v-if="state.isPhotoTake && state.isCamOpen">다시찍기</span>
        <span v-else>사진촬영</span>
      </button>
      <button v-if="state.isCamOpen && !state.isLoad && state.isPhotoTake && state.isCamOpen" type="button" class="bg-[#4fb054] hover:bg-[#66bb6a] border-[#4fb054] hover:border-[#66bb6a] text-white dark:text-gray-100 text-sm border-2 focus:outline-none rounded-md ml-2 px-3 py-0.5 text-center" @click="btnClick">저장</button>
    </div>

    <!-- 비밀번호 변경 Button -->
    <div class="text-center w-3/4 mr-auto ml-auto mb-8">
      <button type="button" class="text-white text-lg font-semibold bg-[#faa405] hover:bg-[#fbb026] border-[#faa405] hover:border-[#fbb026] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center" @click="modalOpen">비밀번호 변경</button>
    </div>

    <!-- 회원 탈퇴 Button -->
    <div class="text-center w-3/4 mr-auto ml-auto mb-12">
      <button type="button" class="text-white text-lg font-semibold bg-[#fe5358] hover:bg-[#fe343b] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center" data-bs-toggle="modal" data-bs-target="#signoutModal">회원 탈퇴하기</button>
    </div>
  </div>

  <!-- 비밀번호 변경 Modal -->
  <div class="modal fade bg-black bg-opacity-50 fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="pwChangeModal" tabindex="-1" aria-labelledby="pwChangeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close" @click="modalClose">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body pt-0 pb-4">
          <form id="pwChange_input" class="pt-10" @submit.prevent="pwChange">
            <!-- 현재 비밀번호 입력 -->
            <div class="relative z-0 mb-8 mr-auto ml-auto w-3/4 group"> 
              <input type="password" name="floating_current_password" id="floating_current_password" v-model.trim="floating_current_password" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " @click="onCurrentPasswordClick" required />
              <label for="floating_current_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-5 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">현재 비밀번호</label>
              <label v-if="!state.current_pw_check" for="floating_current_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">{{ state.new_pw_err_msg }}</label>
            </div>

            <!-- 새 비밀번호 입력 -->
            <div class="relative z-0 mb-8 mr-auto ml-auto w-3/4 group"> 
              <input type="password" id="floating_new_password" name="floating_new_password" v-model.trim="floating_new_password" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.new_pw_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.new_pw_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none focus:outline-none focus:ring-0 peer" placeholder=" " @click="onNewPasswordClick"/>
              <label for="floating_new_password" :class="{'text-[#fe5358] dark:text-[#fe5358] peer-focus:text-[#fe5358] dark:peer-focus:text-[#fe5358]':!state.new_pw_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.new_pw_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">새 비밀번호</label>
              <label v-if="state.new_pw_check" for="floating_new_password" class="absolute text-[4px] text-gray-500 dark:text-gray-400 -bottom-3.5 right-0">영문, 숫자, 특수문자 포함 8 - 20자</label>
              <label v-if="!state.new_pw_check" for="floating_new_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">{{ state.new_pw_err_msg }}</label>
            </div>

            <!-- 새 비밀번호 확인 -->
            <div class="relative z-0 mb-4 mr-auto ml-auto w-3/4 group">
              <input type="password" name="floating_repeat_new_password" v-model.trim="floating_repeat_new_password" id="floating_repeat_new_password" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.new_repeat_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.new_repeat_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none  focus:outline-none focus:ring-0 peer" placeholder=" " @Click="onNewRepeatClick"/>
              <label for="floating_repeat_new_password" :class="{'text-[#fe5358] dark:text-[#fe5358] peer-focus:text-[#fe5358] dark:peer-focus:text-[#fe5358]':!state.new_repeat_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.new_repeat_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">새 비밀번호 확인</label>
              <label v-if="!state.new_repeat_check" for="floating_repeat_new_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">비밀번호와 일치하지 않습니다</label>
            </div>
          </form>
        </div>
        <div class="modal-footer text-center w-3/4 mr-auto ml-auto mt-4 mb-6">
          <button form="pwChange_input" type="submit" class="text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">비밀번호 변경하기</button>
        </div>
      </div>
    </div> 
  </div>

  <!-- 회원 탈퇴 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="signoutModal" tabindex="-1" aria-labelledby="signoutModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body pt-0 pb-4 text-center">
          <p class="font-medium">정말 탈퇴하시겠습니까?<br/>(탈퇴 시, 모든 정보가 삭제됩니다.)</p>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-4 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-white bg-[#fe5358] w-[62.3px] border border-[#fe5358] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#fe343b]" @click="deleteUser" data-bs-dismiss="modal">예</button>
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">아니오</button>
        </div>
      </div>
    </div> 
  </div>
</template>

<script setup>
import CustomFooter from '../components/CustomFooter.vue'
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import { ref, reactive } from 'vue'
import axios from 'axios'
import rct from '../api/rct'
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import * as faceapi from '@vladmandic/face-api'

const store = useStore()
const route = useRouter()

Promise.all([
    faceapi.nets.ssdMobilenetv1.loadFromUri(rct.user.facemodel()),
])

const getProfile = async () => {
  await axios({
    url: rct.user.userinfo(store.state.user.userId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    state.userId = res.data.userId
    state.name = res.data.name
    state.email = res.data.email
    state.takenImg = store.state.user.userImgFirstUrl+res.data.userImg
    console.log(store.state.user.userImgFirstUrl)
  })
  .catch(err => {
    console.log(err)
  })
}

getProfile()

const state = reactive({
  isCamOpen: false,
  isPhotoTake: false,
  isFace: true,
  isSingle: true,
  isShot: false,
  isLoad: false,
  current_pw_check : true,
  new_pw_check : true,
  new_pw_err_msg : '',
  new_repeat_check : true,
  curpage : "profile",
  userId : null,
  name : "",
  email : "",
  takenImg : "",
})

let floating_current_password = ref("")
let floating_new_password = ref("")
let floating_repeat_new_password = ref("")

const profileCamera = ref(null)
const profileCanvas = ref(null)

const toggleCam = () => {
  if(state.isCamOpen) {
    state.isCamOpen = false;
    state.isPhotoTake = false;
    state.isShot = false;
    stopCamStream();
  } else {
    state.isCamOpen = true;
    createCamera();
  }
}

const createCamera = () => {
  state.isLoad = true;
  
  const constraints = (window.constraints = {
    audio: false,
    video: true
  });


  navigator.mediaDevices
  .getUserMedia(constraints)
  .then(stream => {
    state.isLoad = false;
    profileCamera.value.srcObject = stream;
  })
  .catch(error => {
    state.isLoad = false;
    alert("May the browser didn't support or there is some errors.");
    console.log(error)
  });
}

const stopCamStream = () => {
  let tracks = profileCamera.value.srcObject.getTracks();
  tracks.forEach(track => {
    track.stop();
  });
}

const takePhotoShot = async () => {
  state.isFace = true;
  state.isSingle = true;
  if(!state.isPhotoTake) {
    state.isShot = true;

    const FLASH_TIMEOUT = 50;

    setTimeout(() => {
      state.isShot = false;
    }, FLASH_TIMEOUT);
  }
  
  state.isPhotoTake = !state.isPhotoTake;
  
  const context = profileCanvas.value.getContext('2d');
  context.drawImage(profileCamera.value, 0, 0, 384, 288);
  const detections = await faceapi.detectAllFaces(profileCamera.value)

  if(detections.length==0) {
    state.isFace=false
    state.isSingle=true
  } else if (detections.length==1){
    state.isFace=true
    state.isSingle=true
  } else {
    state.isFace=true
    state.isSingle=false
  }
}

// 사진 변경 함수
const UrltoBlob = async (dataURL) => {
  const res = await fetch(dataURL)
  const blob = await res.blob()
  return blob
}

const btnClick = () => {
  if (state.isFace && state.isSingle) {
    changePhoto()
  }
}

const changePhoto = async () => {
  state.isCamOpen = false;
  state.isPhotoTake = false;
  state.isShot = false;
  stopCamStream();
  const procamimgurl = document.getElementById("profilePhotoTaken").toDataURL("image/jpeg");
  let problob = await UrltoBlob(procamimgurl)
  let profd = new FormData()
  const data = {
    userId : store.state.user.userId,
  }
  const json = JSON.stringify(data)
  const datablob = new Blob([json],{type:"application/json"})
  profd.append("userImg",problob)
  profd.append("request",datablob)
  await axios.put(rct.user.userimgmod(),profd,{
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    route.replace({path:'/profile'})
    getProfile()
  })
  .catch(err => {
  })
}

// 비밀번호 변경 함수
const sendChangetoServer = async () => {
  const res = await axios({
    url: rct.user.userpwmod(),
    method: 'put',
    headers: {
      Authorization: store.state.user.accessToken,
    },
    data: {
      userId : store.state.user.userId,
      nowPw : floating_current_password.value,
      newPw : floating_new_password.value,
    }
  })
  .then(res => {
    if (res.data==="success") {
      modalClose()
      getProfile()
    }
  })
  .catch(err => {
    state.new_pw_err_msg = '현재 비밀번호가 일치하지 않습니다'
    state.current_pw_check = false
  })
}

const pwChange = async () => {
      
  let pw_reg = new RegExp(/(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*\?])(?=.{8,20})/)

  if(!pw_reg.test(floating_new_password.value)) {
    state.new_pw_err_msg = '비밀번호는 영문, 숫자 특수문자를 포함하고 8자 이상 20자 이하이어야 합니다'
    state.new_pw_check = false
  }
  if (floating_new_password.value!==floating_repeat_new_password.value) {
    state.new_pw_err_msg = '새 비밀번호가 일치하지 않습니다'
    state.new_repeat_check = false
  }
  if (state.new_pw_check&&state.new_repeat_check) {
    sendChangetoServer()
  }
}

const onCurrentPasswordClick = () => {
  state.current_pw_check = true
}

const onNewPasswordClick = () => {
  state.new_pw_check = true
}

const onNewRepeatClick = () => {
  state.new_repeat_check = true
}

const modalOpen = () => {
  document.getElementById('pwChangeModal').classList.replace('hidden', 'show')
  floating_current_password.value = ''
  floating_new_password.value = ''
  floating_repeat_new_password.value = ''
}

const modalClose = () => {
  document.getElementById('pwChangeModal').classList.replace('show', 'hidden')
  route.replace({path:'/profile'})
}

// 회원 탈퇴 함수
const deleteUser = async () => {
  await axios({
    url: rct.user.userinfo(store.state.user.userId),
    method: 'delete',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    route.replace({path:'/'})
  })
  .catch(err => {
    console.log(err)
  })
}

</script>

<style>

</style>
