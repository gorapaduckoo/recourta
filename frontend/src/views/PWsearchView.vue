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
    <form id="email_input">
      <div class="mt-16 relative z-0 mb-10 mr-auto ml-auto w-3/4 group">
        <input type="email" name="floating_email" id="floating_email" class="block pt-2.5 pb-1 px-2 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-2.5 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
      </div>
    </form>
    <!-- modal button -->
    <div class="mt-10 text-center w-3/4 mr-auto ml-auto mb-6">
      <button data-modal-toggle="EmailModal" class="text-white font-bold bg-[#2c5172] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">이메일 발송</button>
    </div>  
    <!-- Main modal -->
    <div id="EmailModal" tabindex="-1" aria-hidden="true" class="hidden ml-auto mr-auto fixed z-50 w-1/3 min-w-[480px] inset-0 h-full h-modal">
      <div class="relative p-5 w-full h-auto">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg p-2.5 dark:bg-[#444444]">
          <!-- Modal header -->
          <div class="flex justify-between items-start dark:border-gray-600">
            <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-[#353535] rounded-lg text-sm ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="EmailModal">
              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
              <span class="sr-only">Close modal</span>
            </button>
          </div>
          <!-- Modal body -->
          <div class="pt-4 pb-8 text-center">
            <p class="font-medium">
              비밀번호 재설정 메일을 보내시겠습니까?
            </p>
          </div>
          <!-- Modal footer -->
          <div class="flex items-center justify-end space-x-3">
            <button data-modal-toggle="EmailModal" type="button" class="text-white bg-[#2c5172] w-[62.3px] border border-[#2c5172] font-medium rounded-lg text-sm px-3 py-1.5 text-center">예</button>
            <button data-modal-toggle="EmailModal" type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500">아니오</button>
          </div>
        </div>
      </div>
    </div>
  
  </div>

    

  <router-link class="mx-3" to="/pwreset">비밀번호 재설정</router-link>
</template>

<script>
import DarkmodeButton from '@/components/DarkmodeButton'

export default {
  name: 'loginView',
  components: {
    DarkmodeButton,
  },
  setup(){

  }
  
}


</script>

<style>
</style>