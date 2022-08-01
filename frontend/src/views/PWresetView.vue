<template>
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">
       
    <router-link to="/">
      <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    </router-link>
    <div class="w-3/4 ml-auto mr-auto dark:text-gray-300">
      <h1 class="mt-10 mb-6 font-bold">비밀번호 재설정</h1>
    </div>
    <form class="pt-10" @submit.prevent="PWresetSubmit">
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" v-model.trim="floating_password" id="floating_password" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.pw_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.pw_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none  focus:outline-none focus:ring-0 peer" placeholder=" "/>
        <label for="floating_password" :class="{'text-[#fe5358] dark:text-[#fe5358] peer-focus:text-[#fe5358] dark:peer-focus:text-[#fe5358]':!state.pw_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.pw_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">새 비밀번호</label>
        <label v-if="!state.pw_check" for="floating_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">비밀번호는 8자 이상이고 영문, 숫자, 특수문자를 포함해야 합니다</label>
      </div>
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="password" name="repeat_password" v-model.trim="repeat_password" id="floating_repeat_password" :class="{'border-[#fe5358] focus:border-[#fe5358] dark:border-[#fe5358] dark:focus:border-[#fe5358]':!state.repeat_check,'border-gray-300 focus:border-[#2c5172] dark:border-gray-600 dark:focus:border-[#6c9cc6]':state.repeat_check,}" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 appearance-none  focus:outline-none focus:ring-0 peer" placeholder=" "/>
        <label for="floating_repeat_password" :class="{'text-[#fe5358] dark:text-[#fe5358] peer-focus:text-[#fe5358] dark:peer-focus:text-[#fe5358]':!state.repeat_check,'text-gray-500 dark:text-gray-400 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6]':state.repeat_check,}" class="peer-focus:font-medium absolute text-sm duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">새 비밀번호 확인</label>
        <label v-if="!state.repeat_check" for="floating_repeat_password" class="absolute text-[4px] text-[#fe5358] dark:text-[#fe5358] -bottom-3.5 right-0">비밀번호와 비밀번호 확인이 일치하지 않습니다</label>
      </div>
      <div class="mt-10 text-center w-3/4 mr-auto ml-auto mb-6">
        <button type="submit" class="text-white font-bold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">비밀번호 재설정</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import { ref, reactive } from 'vue'
import {useRouter} from 'vue-router'

const route = useRouter()

const state = reactive({
  pw_check : true,
  repeat_check : true,
})


let floating_password = ref("")
let repeat_password = ref("")

const PWresetSubmit = () => {
      
  let pw_regex = new RegExp(/(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*\?])(?=.{8,})/)
      // let pw_regex = new RegExp()
    console.log(floating_password.value)
    console.log(repeat_password.value)
    if(pw_regex.test(floating_password.value)) {
      state.pw_check=true
    } else {
      console.log('올바른 pw를 입력하세요.')
      state.pw_check=false
    }

    if(state.pw_check) {
      if(floating_password.value===repeat_password.value) route.replace({path:'/'})
      else {
        console.log('비밀번호확인과 일치하지 않습니다')
        state.repeat_check = false
      }
    }
  }

</script>

<style>
</style>