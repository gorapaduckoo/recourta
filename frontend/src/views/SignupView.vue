<template>
  <!-- 다크모드 -->
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">

    <!-- 뒤로가기 -->
    <router-link to="/" class="absolute top-3">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
      </svg>
    </router-link>

    <!--  -->
    <h1 class="my-10 text-center font-bold">회원가입 페이지</h1>

    <!-- 로고 - 로그인페이지로 이동 -->
    <router-link to="/">
      <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    </router-link>

    <!-- 입력form -->
    <form class="pt-10" @submit.prevent="signupSubmit">

      <!-- 이름 입력 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="text" name="floating_name" id="floating_name" v-model.trim="floating_name" class="block py-0.5 px-1 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " />
        <label for="floating_name" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-5 scale-75 top-1 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">이름</label>
      </div>

      <!-- 이메일 입력 -->
      <div class="flex items-center justify-between relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="email" name="floating_email" id="floating_email" v-model.trim="floating_email" class="block py-0.5 px-1 w-3/4 text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-5 scale-75 top-1 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">이메일</label>
        <button type="button" @click="sendemailtoserver" class="w-[76.4px] text-[#2c5172] dark:text-gray-300 text-sm border-2 border-[#2c5172] dark:border-gray-300 focus:outline-none rounded-md px-3 py-0.5 text-center hover:bg-gray-100 dark:hover:bg-[#555555]">
          <span>메일발송</span>
        </button>
      </div>

      <!-- 인증번호 입력 -->
      <div class="flex items-center justify-between relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="text" name="floating_verify" id="floating_verify" v-model.trim="floating_verify" class="block py-0.5 px-1 w-3/4 text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " />
        <label for="floating_verify" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-5 scale-75 top-1 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">인증번호</label>
        <button type="button" id="submitverifybtn" @click="sendverifytoserver" class="w-[76.4px] text-[#2c5172] dark:text-gray-300 text-sm border-2 border-[#2c5172] dark:border-gray-300 focus:outline-none rounded-md px-3 py-0.5 text-center hover:bg-gray-100 dark:hover:bg-[#555555]" disabled="true">
          <span>확인</span>
        </button>
      </div>

      <!-- 비밀번호 입력 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" id="floating_password" v-model.trim="floating_password" class="block py-0.5 px-1 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " />
        <label for="floating_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-5 scale-75 top-1 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">비밀번호</label>
      </div>

      <!-- 비밀번호 확인 -->
      <div class="relative z-0 mb-4 mr-auto ml-auto w-3/4 group">
        <input type="password" name="floating_repeat_password" v-model.trim="floating_repeat_password" id="floating_repeat_password" class="block py-0.5 px-1 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " />
        <label for="floating_repeat_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-5 scale-75 top-1 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-5">비밀번호 확인</label>
      </div>

      <!-- 분류  -->
      <div class="flex justify-between items-center w-3/4 mr-auto ml-auto mb-4">
        <div class="text-sm text-gray-500 dark:text-gray-400">분류</div>
        <div class="flex justify-between">

          <!-- 수강생 -->
          <div class="mr-4">
            <input type="radio" id="student" name="role" v-model="isStudent" value="1" class="hidden peer" checked>
            <label for="student" class="flex items-center py-1 px-8 w-full text-gray-500 rounded-lg border-2 border-gray-200 cursor-pointer dark:hover:text-gray-300 dark:border-gray-600 dark:peer-checked:text-gray-200 dark:peer-checked:bg-[#666666] peer-checked:border-[#2c5172] dark:peer-checked:border-gray-300 peer-checked:border-[3px] dark:peer-checked:border-[3px] peer-checked:text-[#2c5172] hover:text-gray-600 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-[#555555]">
              <div class="text-sm mr-auto ml-auto text-center font-semibold">수강생</div>
            </label>
          </div>

          <!-- 강의자 -->
          <div>
            <input type="radio" id="lecturer" name="role" v-model="isStudent" value="0" class="hidden peer">
            <label for="lecturer" class="flex items-center py-1 px-8 w-full text-gray-500 rounded-lg border-2 border-gray-200 cursor-pointer dark:hover:text-gray-300 dark:border-gray-600 dark:peer-checked:text-gray-200 dark:peer-checked:bg-[#666666] peer-checked:border-[#2c5172] dark:peer-checked:border-gray-300 peer-checked:border-[3px] dark:peer-checked:border-[3px] peer-checked:text-[#2c5172] hover:text-gray-600 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-[#555555]">
              <div class="text-sm mr-auto ml-auto text-center font-semibold">강의자</div>
            </label>
          </div>
        </div>
      </div>

      <!-- 웹캠 캡쳐 -->
      
      <div class="flex justify-between items-center w-3/4 mr-auto ml-auto">
        <div class="flex items-end relative">
          <div class="text-sm mr-0.5 text-gray-500 dark:text-gray-400">사진</div>
          <button id="camexp" type="button" @mouseenter="tooltiptoggle" @mouseleave="tooltiptoggle" class="text-center">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 fill-[#2c5172] dark:fill-gray-400" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-8-3a1 1 0 00-.867.5 1 1 0 11-1.731-1A3 3 0 0113 8a3.001 3.001 0 01-2 2.83V11a1 1 0 11-2 0v-1a1 1 0 011-1 1 1 0 100-2zm0 8a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd" />
            </svg>
          </button>
          <div id="camexptooltip" v-if="state.istooltip" class="inline-block absolute whitespace-nowrap text-center z-10 -top-4 left-12 py-2 px-3 text-[12px] text-white dark:text-gray-200 bg-[#444444] rounded-lg dark:bg-[#222222]">
            출석 인증과 자리 비움 여부를 판단하기 위해서<br/>
            웹 카메라를 통한 얼굴 캡쳐가 필요합니다.
          </div>
        </div>

        <!-- 카메라 버튼 -->
        <button v-if="!state.isCameraOpen" type="button" class="text-[#2c5172] dark:text-gray-300 text-sm border-2 border-[#2c5172] dark:border-gray-300 focus:outline-none rounded-md px-3 py-0.5 text-center hover:bg-gray-100 dark:hover:bg-[#555555]" @click="toggleCamera">
          <span>사진등록</span>
        </button>

        <button v-if="state.isCameraOpen && !state.isLoading" type="button" :class="{'bg-[#faa405] hover:bg-[#fbb026] border-[#faa405] hover:border-[#fbb026] font-semibold':state.isPhotoTaken && state.isCameraOpen,'bg-[#4fb054] hover:bg-[#66bb6a] border-[#4fb054] hover:border-[#66bb6a]':!state.isPhotoTaken || !state.isCameraOpen}" class="text-white dark:text-gray-100 text-sm border-2 focus:outline-none rounded-md px-3 py-0.5 text-center" @click="takePhoto">
          <span v-if="state.isPhotoTaken && state.isCameraOpen">다시찍기</span>
          <span v-else>사진촬영</span>
        </button>
      </div>

      <!-- 카메라 로딩 -->
      <div v-show="state.isCameraOpen && state.isLoading" class="flex justify-center items-center mr-auto ml-auto w-[384px] h-[288px] bg-black mt-2">
        <svg aria-hidden="true" role="status" class="w-[80px] h-[80px] text-white animate-spin" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="#2c5172"/>
          <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="#92a8d1"/>
        </svg>
      </div>
      
      <!-- 사진등록을 위한 캠/사진 화면 -->
      <div v-if="state.isCameraOpen" v-show="!state.isLoading" class="mt-2" :class="{ 'opacity-0' : state.isShotPhoto }">
        
        <div :class="{'opacity-0' : state.isShotPhoto}"></div>
        <!-- 웹캠 -->
        <video width="384" height="288" class="mr-auto ml-auto" v-show="!state.isPhotoTaken" ref="camera" autoplay></video>
        <!-- 캡쳐 -->
        <canvas width="384" height="288" class="mr-auto ml-auto" v-show="state.isPhotoTaken" id="photoTaken" ref="canvas"></canvas>

      </div>
      
      
      
      <!-- <div v-if="isPhotoTaken && isCameraOpen" class="camera-download">
        <a id="downloadPhoto" download="my-photo.jpg" class="button" role="button" @click="downloadImage">
          Download
        </a>
      </div> -->
    
      <div class="text-center w-3/4 mr-auto ml-auto mt-4 mb-6">
        <button type="submit" class="text-white font-bold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">회원가입</button>
      </div>
    </form>
  </div>
</template>

<script setup>

import DarkmodeButton from '../components/DarkmodeButton.vue'
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'

const route = useRouter()

const state = reactive({
  isCameraOpen: false,
  isPhotoTaken: false,
  isShotPhoto: false,
  isLoading: false,
  istooltip: false,
  isname: true,
  isemail: true,
  isverify: true,
  ispassword: true,
  isrepeat: true,
  isphoto: true,
  isemailsend: false,
  isemailverified: false,
  wrongemail:''
})

const camera = ref(null)
const canvas = ref(null)

let email = ""

let floating_name = ref(null)
let floating_email = ref(null)
let floating_verify = ref(null)
let floating_password = ref(null)
let floating_repeat_password = ref(null)
let isStudent = ref(null)

const tooltiptoggle = () => {
  state.istooltip = !state.istooltip;
}

const toggleCamera = () => {
  if(state.isCameraOpen) {
    state.isCameraOpen = false;
    state.isPhotoTaken = false;
    state.isShotPhoto = false;
    stopCameraStream();
  } else {
    state.isCameraOpen = true;
    createCameraElement();
  }
}

const createCameraElement = () => {
  state.isLoading = true;
  
  const constraints = (window.constraints = {
    audio: false,
    video: true
  });
  navigator.mediaDevices
  .getUserMedia(constraints)
  .then(stream => {
    state.isLoading = false;
    camera.value.srcObject = stream;
  })
  .catch(error => {
    state.isLoading = false;
    alert("May the browser didn't support or there is some errors.");
    console.log(error)
  });
}

const stopCameraStream = () => {
  let tracks = camera.value.srcObject.getTracks();
  tracks.forEach(track => {
    track.stop();
  });
}

const takePhoto = () => {
  if(!state.isPhotoTaken) {
    state.isShotPhoto = true;
    const FLASH_TIMEOUT = 50;
    setTimeout(() => {
      state.isShotPhoto = false;
    }, FLASH_TIMEOUT);
  }
  state.isPhotoTaken = !state.isPhotoTaken;
  const context = canvas.value.getContext('2d');
  context.drawImage(camera.value, 0, 0, 384, 288);
}

const downloadImage = () => {
  const download = document.getElementById("downloadPhoto");
  const canvas = document.getElementById("photoTaken").toDataURL("image/jpeg")
  .replace("image/jpeg", "image/octet-stream");
  download.setAttribute("href", canvas);
}

const sendemailtoserver = () => {
  if(floating_email.value==="test@gmail.com") return 0
  return 1
}

const checkemail = () => {
  let email_regex = new RegExp(/[A-Za-z0-9\._-]+@([A-Za-z0-9]+\.)+([A-Za-z0-9])/)
  if(email_regex.test(floating_email.value)){
    if(sendemailtoserver()) {
      document.getElementById('submitverifybtn').setAttribute('disabled','false')
      state.isemailsend = true
      email = floating_email.value
    }
    else{
      state.wrongemail='이미 가입된 이메일입니다'
      state.isemail=false
    }
  }
  else {
    state.wrongemail='올바른 이메일을 입력하세요'
    state.isemail=false
  }
}

const sendverifytoserver = () => {
  if(flaoting_verify==="123456") return 1
  return 0
}

const checkverify = () => {
  if(sendverifytoserver()){
    document.getElementById('submitverifybtn').setAttribute('disabled','true')
    console.log("이메일이 인증되었습니다")
    state.isemailverified=true
  }
  else{
    state.isverify=false
  }
}

const signupSubmit = () => {
  console.log(floating_name.value)
  console.log(floating_email.value)
  console.log(floating_verify.value)
  console.log(floating_password.value)
  console.log(floating_repeat_password.value)
  console.log(isStudent.value)

  if(floating_name==="") state.isname = false
  
  let pw_regex = new RegExp(/(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*\?])(?=.{8,})/)
      // let pw_regex = new RegExp()
  console.log(floating_email.value)
  console.log(floating_password.value)
  if(!isemailverified) state.isverify=false
  if(!pw_regex.test(floating_password.value)) state.ispassword=false
  if(floating_password.value!==floating_repeat_password.value) state.isrepeat=false
  if(!isPhotoTaken) state.isphoto=false

  if(isemailverified&&state.ispassword&&state.isrepeat&&state.isphoto) route.replace({path:"/"})
}
</script>

<style>

</style>