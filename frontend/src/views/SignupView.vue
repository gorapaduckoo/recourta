<template>
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">
    <router-link to="/" class="fixed top-3">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
      </svg>
    </router-link>
    <h1 class="my-10 text-center font-bold">회원가입 페이지</h1>
    <router-link to="/">
      <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    </router-link>
    <form class="pt-10">
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="text" name="floating_name" id="floating_name" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_name" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이름</label>
      </div>
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="email" name="floating_email" id="floating_email" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
      </div>
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" id="floating_password" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호</label>
      </div>
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="password" name="repeat_password" id="floating_repeat_password" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_repeat_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호 확인</label>
      </div>
      <div class="flex justify-between items-center w-3/4 mr-auto ml-auto mb-6">
        <div class="text-sm text-gray-500 dark:text-gray-400">분류</div>
        <div class="flex justify-between">
          <div class="mr-4">
            <input type="radio" id="student" name="role" value="student" class="hidden peer" required checked>
            <label for="student" class="flex items-center py-2 px-10 w-full text-gray-500 rounded-lg border-2 border-gray-200 cursor-pointer dark:hover:text-gray-300 dark:border-gray-600 dark:peer-checked:text-gray-200 dark:peer-checked:bg-[#666666] peer-checked:border-[#2c5172] dark:peer-checked:border-gray-300 peer-checked:border-[3px] dark:peer-checked:border-[3px] peer-checked:text-[#2c5172] hover:text-gray-600 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-[#555555]">
              <div class="text-sm mr-auto ml-auto text-center font-semibold">수강생</div>
            </label>
          </div>
          <div>
            <input type="radio" id="lecturer" name="role" value="lecturer" class="hidden peer">
            <label for="lecturer" class="flex items-center py-2 px-10 w-full text-gray-500 rounded-lg border-2 border-gray-200 cursor-pointer dark:hover:text-gray-300 dark:border-gray-600 dark:peer-checked:text-gray-200 dark:peer-checked:bg-[#666666] peer-checked:border-[#2c5172] dark:peer-checked:border-gray-300 peer-checked:border-[3px] dark:peer-checked:border-[3px] peer-checked:text-[#2c5172] hover:text-gray-600 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-[#555555]">
              <div class="text-sm mr-auto ml-auto text-center font-semibold">강의자</div>
            </label>
          </div>
        </div>
      </div>
      <div id="webrtc" class="web-camera-container">
        <div class="camera-button">
            <button type="button" class="button is-rounded" :class="{ 'is-primary' : !isCameraOpen, 'is-danger' : isCameraOpen}" @click="toggleCamera">
              <span v-if="!isCameraOpen">Open Camera</span>
              <span v-else>Close Camera</span>
          </button>
        </div>
        
        <div v-show="isCameraOpen && isLoading" class="camera-loading">
          <ul class="loader-circle">
            <li></li>
            <li></li>
            <li></li>
          </ul>
        </div>
        
        <div v-if="isCameraOpen" v-show="!isLoading" class="camera-box" :class="{ 'flash' : isShotPhoto }">
          
          <div class="camera-shutter" :class="{'flash' : isShotPhoto}"></div>
            
          <video v-show="!isPhotoTaken" ref="camera" :width="450" :height="337.5" autoplay></video>
          
          <canvas v-show="isPhotoTaken" id="photoTaken" ref="canvas" :width="450" :height="337.5"></canvas>
        </div>
        
        <div v-if="isCameraOpen && !isLoading" class="camera-shoot">
          <button type="button" class="button" @click="takePhoto">
            <img src="https://img.icons8.com/material-outlined/50/000000/camera--v2.png">
          </button>
        </div>
        
        <div v-if="isPhotoTaken && isCameraOpen" class="camera-download">
          <a id="downloadPhoto" download="my-photo.jpg" class="button" role="button" @click="downloadImage">
            Download
          </a>
        </div>
      </div>
      <div class="text-center w-3/4 mr-auto ml-auto mb-6">
        <button type="submit" class="text-white font-bold bg-[#2c5172] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">회원가입</button>
      </div>
    </form>
  </div>
</template>

<script>

import DarkmodeButton from '../components/DarkmodeButton.vue'

export default {
  
  name: 'SignupView',

  el: '#webrtc',

  components:{
    DarkmodeButton,
  },
  data() {
    return {
      isCameraOpen: false,
      isPhotoTaken: false,
      isShotPhoto: false,
      isLoading: false,
      link: '#'
    }
  },
  methods: {
    toggleCamera() {
      if(this.isCameraOpen) {
        this.isCameraOpen = false;
        this.isPhotoTaken = false;
        this.isShotPhoto = false;
        this.stopCameraStream();
      } else {
        this.isCameraOpen = true;
        this.createCameraElement();
      }
    },
    
    createCameraElement() {
      this.isLoading = true;
      
      const constraints = (window.constraints = {
				audio: false,
				video: true
			});


			navigator.mediaDevices
				.getUserMedia(constraints)
				.then(stream => {
          this.isLoading = false;
					this.$refs.camera.srcObject = stream;
				})
				.catch(error => {
          this.isLoading = false;
					alert("May the browser didn't support or there is some errors.");
          console.log(error)
				});
    },
    
    stopCameraStream() {
      let tracks = this.$refs.camera.srcObject.getTracks();

			tracks.forEach(track => {
				track.stop();
			});
    },
    
    takePhoto() {
      if(!this.isPhotoTaken) {
        this.isShotPhoto = true;

        const FLASH_TIMEOUT = 50;

        setTimeout(() => {
          this.isShotPhoto = false;
        }, FLASH_TIMEOUT);
      }
      
      this.isPhotoTaken = !this.isPhotoTaken;
      
      const context = this.$refs.canvas.getContext('2d');
      context.drawImage(this.$refs.camera, 0, 0, 450, 337.5);
    },
    
    downloadImage() {
      const download = document.getElementById("downloadPhoto");
      const canvas = document.getElementById("photoTaken").toDataURL("image/jpeg")
    .replace("image/jpeg", "image/octet-stream");
      download.setAttribute("href", canvas);
    }
  },
}
</script>

<style>

</style>