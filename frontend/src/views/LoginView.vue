<template>
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">
    <h1 class="my-10 text-center font-bold">로그인 페이지</h1>
    <router-link to="/">
      <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    </router-link>
    <form @submit.prevent="loginSubmit" class="pt-10">
      <!-- 이메일 입력 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="text" name="floating_email" v-model.trim="floating_email" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
      </div>
      <!-- 비밀번호 입력 -->
      <div class="relative z-0 mb-3 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" v-model.trim="floating_password" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" "/>
        <label for="floating_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호</label>
      </div>
      <div class="flex items-center w-3/4 mr-auto ml-auto mb-6 form-chec">
        <div class="flex items-center h-5 form-check">
          <input id="remember" type="checkbox" class="form-check-input w-4 h-4 accent-[#2c5172] dark:accent-[#2c5172] bg-[#ffffff] focus:ring-[#ffffff] dark:focus:ring-[#444444] dark:focus:ring-offset-[#444444] rounded border border-gray-300 dark:border-[#888888] dark:bg-[#444444]">
        </div>
        <label for="remember" class="ml-2 text-xs text-gray-500 dark:text-gray-400">이메일 저장</label>
      </div>
      <div class="text-center w-3/4 mr-auto ml-auto mb-3">
        <button type="submit" class="text-white font-bold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">로 그 인</button>
      </div>
    </form>
    <div class="w-1/2 mr-auto ml-auto mb-6 flex justify-center text-xs text-gray-500 dark:text-gray-400">
      <router-link class="w-20 text-right hover:text-gray-700 dark:hover:text-gray-300" to="/signup">회원가입</router-link>
      <div class="mx-4">|</div>
      <router-link class="w-20 text-left hover:text-gray-700 dark:hover:text-gray-300" to="/pwsearch">비밀번호 찾기</router-link>
    </div>
    <!-- 임시 -->
    <router-link to="/main">임시 링크</router-link>
  </div>
 
</template>

<script setup>

import DarkmodeButton from '../components/DarkmodeButton.vue'
import { ref, reactive } from 'vue'
import {useRouter} from 'vue-router'

const route = useRouter()

let email_check=true
let pw_check=true

let floating_email = ref("")
let floating_password = ref("")

const test_id_pw = (id,pw) => {
  if(id==="test@gmail.com"&&pw==="Test123@") return 1
  return 0
}

const loginSubmit = () => {
      
      // 영문숫자로만이뤄진id(_-.이아이디포함x)@----.2-3글자(com)
  let email_regex = new RegExp(/[A-Za-z0-9\._-]+@([A-Za-z0-9]+\.)+([A-Za-z0-9])/)
  let pw_regex = new RegExp(/(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*\?])(?=.{8,})/)
      // let pw_regex = new RegExp()
    console.log(floating_email.value)
    console.log(floating_password.value)
    if(email_regex.test(floating_email.value)) {
      email_check=true
    } else {
      console.log('옳바른 email을 입력하세요')
      email_check=false
    }
    if(pw_regex.test(floating_password.value)) {
      pw_check=true
    } else {
      console.log('옳바른 pw를 입력하세요.')
      pw_check=false
    }

    if(email_check&&pw_check) {
      if(test_id_pw(floating_email.value,floating_password.value)) route.replace({path:'/main'})
      else console.log('일치하는 회원이 없습니다.')
    }
    
  }
</script>

<style>
</style>