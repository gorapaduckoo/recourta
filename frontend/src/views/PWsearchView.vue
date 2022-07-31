<template>
  <DarkmodeButton />
  <!-- <div modal-backdrop class="bg-[#444444] bg-opacity-50 fixed inset-0 z-40"></div> -->
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">
    <router-link to="/" class="absolute top-3">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
      </svg>
    </router-link>
    
    <router-link to="/">
      <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    </router-link>
    <!-- 설명문장 -->
    <div class="w-3/4 ml-auto mr-auto dark:text-gray-300">
      <h1 class="mt-10 mb-6 font-bold">비밀번호 재설정</h1>
      <p class="text-sm">계정의 이메일을 입력해주세요.</p>
      <p class="text-sm">비밀번호 재설정 링크가 포함된 메일이 발송됩니다.</p>
    </div>
    <!-- 이메일 입력 -->
    <form id="email_input" @submit.prevent="PWsearchSubmit">
      <div class="mt-16 relative z-0 mb-10 mr-auto ml-auto w-3/4 group">
        <input type="text" v-model.trim="floating_email" id="floating_email" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" "/>
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
        <label v-if="!state.email_check" for="floating_email" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">올바른 이메일을 입력하세요</label>
      </div>
    </form>

    <!-- 이메일 발송 버튼 -->
    <div class="mt-10 text-center w-3/4 mr-auto ml-auto mb-6">
      <button type="button" class="text-white font-bold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]" data-bs-toggle="modal" data-bs-target="#emailSendModal">
        이메일 발송
      </button>
    </div>

    <!-- Modal -->
    <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="emailSendModal" tabindex="-1" aria-labelledby="emailSendModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
        <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
          <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
            <button type="button" data-bs-dismiss="modal" aria-label="Close">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>
          <div class="modal-body py-4 text-center">
              <p class="font-medium">
                비밀번호 재설정 메일을 보내시겠습니까?
              </p>
            </div>
            <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end p-4 rounded-b-md space-x-3">
              <button type="submit" form="email_input" class="text-white bg-[#2c5172] w-[62.3px] border border-[#2c5172] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#325c81]" data-bs-dismiss="modal">예</button>
              <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">아니오</button>
            </div>
        </div>
      </div>
    </div>    
  </div>
  <router-link class="mx-3" to="/pwreset">비밀번호 재설정</router-link>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import {useRouter} from 'vue-router'
import { ref, reactive } from 'vue'

const route = useRouter()

const state = reactive({
  email_check : true
})
let floating_email = ref("")

const test_email = (id) => {
  if(id==='test@gmail.com') return 1
  return 0
}

const PWsearchSubmit = () => {
  let email_regex = new RegExp(/[A-Za-z0-9\._-]+@([A-Za-z0-9]+\.)+([A-Za-z0-9])/)
  
  // let pw_regex = new RegExp()
  console.log(floating_email.value)
  
  if(email_regex.test(floating_email.value)) {
    state.email_check=true
  } else {
    console.log('올바른 email을 입력하세요')
    state.email_check=false
  }

  if(state.email_check) {
    if(test_email(floating_email.value)) route.replace({path:'/'})
    else console.log('일치하는 회원이 없습니다.')
  }
}

</script>

<style>
</style>