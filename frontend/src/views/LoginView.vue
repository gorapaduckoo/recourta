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
        <input type="text" name="floating_email" v-model.trim="floating_email" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.email_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.email_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none  focus:outline-none focus:ring-0 peer" placeholder=" " @click="onEmailClick"/>
        <label for="floating_email" :class="{'text-[#fe5358] dark:text-[#fe5358]':!state.email_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.email_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
        <label v-if="!state.email_check" for="floating_email" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">{{ state.email_err_msg }}</label>
      </div>
      <!-- 비밀번호 입력 -->
      <div class="relative z-0 mb-3 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" v-model.trim="floating_password" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.pw_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.pw_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none  focus:outline-none focus:ring-0 peer" placeholder=" " @click="onPasswordClick"/>
        <label for="floating_password"  :class="{'text-[#fe5358] dark:text-[#fe5358] peer-focus:text-[#fe5358] dark:peer-focus:text-[#fe5358]':!state.pw_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.pw_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호</label>
        <label v-if="!state.pw_check" for="floating_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">{{ state.pw_err_msg }}</label>
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
import axios from 'axios'
import rct from '../api/rct'
import {useStore} from 'vuex'

const route = useRouter()
const store = useStore()

const state = reactive({
  email_check:true,
  email_err_msg:'',
  pw_check:true,
  pw_err_msg:'',
})

let floating_email = ref("")
let floating_password = ref("")

const login = async () => {
  await axios({
    url: rct.login.login(),
    method: 'post',
    data: {
      email : floating_email.value,
      password : floating_password.value,
    }
  })
  .then(res => {
    store.dispatch('user/saveToken', res.data.token)
    store.commit("user/Set_userId",res.data.userId)
    store.commit("user/Set_isStudent",res.data.isStudent)
    route.replace({path:'/main'})
  })
  .catch(err => {
    state.email_err_msg = "가입된 이메일이 아니거나"
    state.email_check=false
    state.pw_err_msg = "잘못된 비밀번호를 입력하였습니다"
    state.pw_check=false
    store.commit('SET_AUTH_ERROR', err.response.data)
  })
}


const test_id_pw = (id,pw) => {
  if(id==="test@gmail.com"&&pw==="Test123@") return 1
  return 0
}

const loginSubmit = () => {
      
  if(floating_email.value!=="") {
    state.email_check=true
  } else {
    state.email_err_msg = '이메일을 입력해 주세요'
    state.email_check=false
  }
  if(floating_password.value!=="") {
    state.pw_check=true
  } else {
    state.pw_err_msg = '비밀번호를 입력해 주세요'
    state.pw_check=false
  }

  if(state.email_check&&state.pw_check) {
    login()
  }
    
}

const onEmailClick = () => {
  state.email_check = true
}

const onPasswordClick = () => {
  state.pw_check = true
}
</script>

<style>
</style>