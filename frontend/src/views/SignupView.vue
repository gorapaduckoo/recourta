<template>
  <!-- 다크모드 -->
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">

    <!-- 뒤로가기 -->
    <router-link to="/" class="fixed top-3">
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
    <form class="pt-10">

      <!-- 이름 입력 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="text" name="floating_name" id="floating_name" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_name" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이름</label>
      </div>

      <!-- 이메일 입력 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="email" name="floating_email" id="floating_email" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
      </div>

      <!-- 비밀번호 입력 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" id="floating_password" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호</label>
      </div>

      <!-- 비밀번호 확인 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="password" name="repeat_password" id="floating_repeat_password" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_repeat_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호 확인</label>
      </div>

      <!-- 분류  -->
      <div class="flex justify-between items-center w-3/4 mr-auto ml-auto mb-6">
        <div class="text-sm text-gray-500 dark:text-gray-400">분류</div>
        <div class="flex justify-between">

          <!-- 수강생 -->
          <div class="mr-4">
            <input type="radio" id="student" name="role" value="student" class="hidden peer" required checked>
            <label for="student" class="flex items-center py-2 px-10 w-full text-gray-500 rounded-lg border-2 border-gray-200 cursor-pointer dark:hover:text-gray-300 dark:border-gray-600 dark:peer-checked:text-gray-200 dark:peer-checked:bg-[#666666] peer-checked:border-[#2c5172] dark:peer-checked:border-gray-300 peer-checked:border-[3px] dark:peer-checked:border-[3px] peer-checked:text-[#2c5172] hover:text-gray-600 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-[#555555]">
              <div class="text-sm mr-auto ml-auto text-center font-semibold">수강생</div>
            </label>
          </div>

          <!-- 강의자 -->
          <div>
            <input type="radio" id="lecturer" name="role" value="lecturer" class="hidden peer">
            <label for="lecturer" class="flex items-center py-2 px-10 w-full text-gray-500 rounded-lg border-2 border-gray-200 cursor-pointer dark:hover:text-gray-300 dark:border-gray-600 dark:peer-checked:text-gray-200 dark:peer-checked:bg-[#666666] peer-checked:border-[#2c5172] dark:peer-checked:border-gray-300 peer-checked:border-[3px] dark:peer-checked:border-[3px] peer-checked:text-[#2c5172] hover:text-gray-600 hover:bg-gray-100 dark:text-gray-400 dark:hover:bg-[#555555]">
              <div class="text-sm mr-auto ml-auto text-center font-semibold">강의자</div>
            </label>
          </div>
        </div>
      </div>

      <!-- 웹캠 캡쳐 -->
      
      <div class="flex justify-between items-center w-3/4 mr-auto ml-auto mb-3">
        <div class="text-sm text-gray-500 dark:text-gray-400">사진</div>

        <!-- 카메라 버튼 -->
        <button v-if="!isCameraOpen" type="button" class="text-[#2c5172] dark:text-gray-300 text-sm border-2 border-[#2c5172] dark:border-gray-300 focus:outline-none rounded-md px-3 py-.5 text-center" @click="toggleCamera">
          <span>사진등록</span>
        </button>

        <button v-if="isCameraOpen && !isLoading" type="button" :class="{'bg-[#fbb026] border-[#fbb026] font-semibold':isPhotoTaken && isCameraOpen,'bg-[#66bb6a] border-[#66bb6a]':!isPhotoTaken || !isCameraOpen}" class="text-white dark:text-gray-100 text-sm border-2 focus:outline-none rounded-md px-3 py-.5 text-center" @click="takePhoto">
          <span v-if="isPhotoTaken && isCameraOpen">다시찍기</span>
          <span v-else>사진촬영</span>
        </button>
      </div>

      <!-- 카메라 로딩 -->
      <div v-show="isCameraOpen && isLoading" class="mr-auto ml-auto bg-black mb-6" :width="450" :height="337.5">
      <!-- <div v-show="isCameraOpen" class="mr-auto ml-auto bg-black" :width="450" :height="337.5"> -->

        <svg aria-hidden="true" role="status" class="block mr-auto ml-auto w-[337.5px] h-[337.5px] text-white animate-spin" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="#FF0000"/>
          <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentColor"/>
        </svg>
      </div>
      
      <div v-if="isCameraOpen" v-show="!isLoading" class="mb-6" :class="{ 'opacity-0' : isShotPhoto }">
        
        <div :class="{'opacity-0' : isShotPhoto}"></div>
          
        <video class="mr-auto ml-auto" v-show="!isPhotoTaken" ref="camera" :width="450" :height="337.5" autoplay></video>
        
        <canvas class="mr-auto ml-auto" v-show="isPhotoTaken" id="photoTaken" ref="canvas" :width="450" :height="337.5"></canvas>
      </div>
      
      
      
      <div v-if="isPhotoTaken && isCameraOpen" class="camera-download">
        <a id="downloadPhoto" download="my-photo.jpg" class="button" role="button" @click="downloadImage">
          Download
        </a>
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