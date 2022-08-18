<template>
  <DarkmodeButton />
  <CustomFooter/>
  
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto">
    <router-link to="/">
      <img class="mt-14 ml-auto mr-auto w-4/5" src="../assets/logo.png" alt="">
    </router-link>
    <h1 class="mt-4 mb-20 text-center font-semibold text-neutral-600 dark:text-neutral-300">Record | Course | Ta-da!</h1>
    <form class="pt-10" @submit.prevent="PWresetSubmit">
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group"> 
        <input type="password" id="floating_password" name="floating_password" v-model.trim="floating_password" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.pw_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.pw_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none focus:outline-none focus:ring-0 peer placeholder-opacity-100" placeholder="영문, 숫자, 특수문자 포함 8 - 20자" @click="onPasswordClick"/>
        <label for="floating_password" :class="{'text-[#fe5358] dark:text-[#fe5358] peer-focus:text-[#fe5358] dark:peer-focus:text-[#fe5358]':!state.pw_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.pw_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">새 비밀번호</label>
        <label v-if="!state.pw_check" for="floating_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">{{ state.pw_err_msg }}</label>
      </div>
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="password" name="repeat_password" v-model.trim="repeat_password" id="floating_repeat_password" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.repeat_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.repeat_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none  focus:outline-none focus:ring-0 peer" placeholder=" " @Click="onRepeatClick"/>
        <label for="floating_repeat_password" :class="{'text-[#fe5358] dark:text-[#fe5358] peer-focus:text-[#fe5358] dark:peer-focus:text-[#fe5358]':!state.repeat_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.repeat_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">새 비밀번호 확인</label>
        <label v-if="!state.repeat_check" for="floating_repeat_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">비밀번호와 일치하지 않습니다</label>
      </div>
      <div class="mt-10 text-center w-3/4 mr-auto ml-auto mb-6">
        <button type="submit" class="text-white font-bold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">비밀번호 재설정</button>
      </div>
    </form>
  </div>
  
</template>

<script setup>
import CustomFooter from './components/CustomFooter.vue'
import DarkmodeButton from '../components/DarkmodeButton.vue'
import { ref, reactive } from 'vue'
import {useRouter} from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'

const route = useRouter()

const state = reactive({
  pw_check : true,
  pw_err_msg : '',
  repeat_check : true,
})


let floating_password = ref("")
let repeat_password = ref("")

const sendresettoserver = async () => {
  const url = new URL(window.location.href)
  const code = url.search.substr(6)
  const res = await axios({
    url: rct.login.pwreset(),
    method: 'put',
    data: {
      code : code,
      newPw : floating_password.value,
    }
  })
  .then(res => {
    if (res.data==="success") {
      route.replace({path:'/'})
    }
    else if (res.data==="fail") {
      state.pw_err_msg = '비밀번호 재설정을 실패하셨습니다'
      state.pw_check = false
    } else {
      state.pw_err_msg = '재설정 유효시간이 만료되었습니다. 비밀번호찾기를 다시 시도해주세요'
      state.pw_check = false
    }
  })
  .catch(err => {
    console.log(err)
  })
}
const PWresetSubmit = async () => {
      
  let pw_regex = new RegExp(/(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*\?])(?=.{8,20})/)

  if(!pw_regex.test(floating_password.value)) {
    state.pw_err_msg = '비밀번호는 영문, 숫자 특수문자를 포함하고 8자 이상 20자 이하이어야 합니다'
    state.pw_check = false
  }
  if(floating_password.value!==repeat_password.value) state.repeat_check = false

  if(state.pw_check&&state.repeat_check) {
    sendresettoserver()
  }

}

const onPasswordClick = () => {
  state.pw_check = true
}

const onRepeatClick = () => {
  state.repeat_check = true
}

</script>

<style scoped>
#floating_password::placeholder{
  text-align:right;
  font-size:11px;
}
</style>