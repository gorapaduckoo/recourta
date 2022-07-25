<template>
  <CustomNavbar />
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">
    <h1 class="my-10 text-center font-bold">로그인 페이지</h1>
    <router-link to="/">
      <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    </router-link>
    <form class="pt-10">
      <!-- 이메일 입력 -->
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="email" name="floating_email" id="floating_email" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
      </div>
      <!-- 비밀번호 입력 -->
      <div class="relative z-0 mb-3 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" id="floating_password" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호</label>
      </div>
      <div class="flex items-center w-3/4 mr-auto ml-auto mb-6">
        <div class="flex items-center h-5">
          <input id="remember" type="checkbox" value="" class="w-4 h-4 bg-[#ffffff] checked:bg-[#2c5172] dark:checked:bg-[#2c5172] focus:ring-[#ffffff] dark:focus:ring-[#444444] dark:focus:ring-offset-[#444444] rounded border border-gray-300 dark:border-[#888888] dark:bg-[#444444]">
        </div>
        <label for="remember" class="ml-2 text-xs text-gray-500 dark:text-gray-400">이메일 저장</label>
      </div>
      <div class="text-center w-3/4 mr-auto ml-auto mb-3">
        <button type="submit" class="text-white font-bold bg-[#2c5172] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">로 그 인</button>
      </div>
    </form>
    <div class="w-1/2 mr-auto ml-auto mb-6 flex justify-center text-xs text-gray-500 dark:text-gray-400">
      <router-link class="w-20 text-right" to="/signup">회원가입</router-link>
      <div class="mx-4">|</div>
      <router-link class="w-20 text-left" to="/pwsearch">비밀번호 찾기</router-link>
    </div>
  </div>
</template>

<script>
import CustomNavbar from '../components/CustomNavbar.vue'
import DarkmodeButton from '../components/DarkmodeButton.vue'

export default {
  name: 'loginView',
  components: {
    DarkmodeButton,
    CustomNavbar,
  },
}
</script>

<style>
</style>