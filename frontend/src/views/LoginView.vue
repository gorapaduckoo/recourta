<template>
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">
    <h1 class="my-10 text-center font-bold">로그인 페이지</h1>
    <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    <form class="pt-10">
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="email" name="floating_email" id="floating_email" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
      </div>
      <div class="relative z-0 mb-6 mr-auto ml-auto w-3/4 group"> 
        <input type="password" name="floating_password" id="floating_password" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">비밀번호</label>
      </div>
      <div class="flex w-3/4 mr-auto ml-auto mb-6">
        <div class="flex items-center h-5">
          <input id="remember" type="checkbox" value="" class="w-4 h-4 bg-[#ffffff] rounded border border-gray-300 focus:ring-3 focus:ring-blue-300 dark:bg-[#444444] dark:border-[#ffffff] dark:focus:ring-blue-600 dark:ring-offset-gray-800">
        </div>
        <label for="remember" class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Remember me</label>
      </div>
      <div class="text-center w-3/4 mr-auto ml-auto mb-6">
        <button type="submit" class="text-white font-bold bg-[#2c5172] focus:outline-none font-medium rounded-lg text-sm w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">로그인</button>
      </div>
    </form>
  </div>
</template>

<script>
import DarkmodeButton from '@/components/DarkmodeButton'

export default {
  name: 'loginView',
  components: {
    DarkmodeButton,
  },
}
</script>

<style>
</style>