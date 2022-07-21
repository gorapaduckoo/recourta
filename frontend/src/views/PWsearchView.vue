<template>
  <DarkmodeButton />
  <div class="pt-10 w-1/3 min-w-[480px] ml-auto mr-auto border">
    <h1 class="my-10 text-center font-bold">비밀번호 찾기</h1>
    <img class="ml-auto mr-auto" src="../assets/placeholder.png" alt="">
    <form class="pt-10">
      <div class="mt-16 relative z-0 mb-6 mr-auto ml-auto w-3/4 group">
        <input type="email" name="floating_email" id="floating_email" class="block py-2.5 px-0 w-full text-sm bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-[#6c9cc6] focus:outline-none focus:ring-0 focus:border-[#2c5172] peer" placeholder=" " required />
        <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#2c5172] dark:peer-focus:text-[#6c9cc6] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">이메일</label>
      </div>
      <div class="mt-16 text-center w-3/4 mr-auto ml-auto mb-6">
        <button data-modal-toggle="EmailModal" class="text-white font-bold bg-[#2c5172] focus:outline-none font-medium rounded-lg text-sm w-full px-5 py-2.5 text-center dark:bg-[#2c5172]">비밀번호 재설정 이메일 발송</button>
      </div>

    </form>
  </div>
    <!-- Main modal -->
    <div id="EmailModal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden ml-auto mr-auto fixed z-50 w-1/2 md:inset-0 h-modal md:h-full">
      <div class="relative p-4 w-full max-w-2xl h-full md:h-auto">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
          <!-- Modal header -->
          <div class="flex justify-between items-start rounded-t dark:border-gray-600">
            <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="EmailModal">
                <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                <span class="sr-only">Close modal</span>
            </button>
          </div>
          <!-- Modal body -->
          <div class="py-4 text-center">
              <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
                비밀번호 재설정 메일을 보내시겠습니까?
              </p>
          </div>
          <!-- Modal footer -->
          <div class="flex items-center justify-center p-6 space-x-24 rounded-b border-t border-gray-200 dark:border-gray-600">
              <button data-modal-toggle="EmailModal" type="button" class="text-white bg-[#2c5172] focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-[#2c5172] dark:hover:bg-blue-700 dark:focus:ring-blue-800">I accept</button>
              <button data-modal-toggle="EmailModal" type="button" class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">Decline</button>
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
}
</script>

<style>
</style>