<template>
  <DarkmodeButton />
  <CustomNavbar />
  <div class="pt-[60px] w-1/3 min-w-[480px] ml-auto mr-auto border">
    <img class="mt-6 mb-12 w-20 h-20 ml-auto mr-auto" src="../assets/placeholder2.png" alt="">
    <div class="ml-[1.2em] mb-6 text-2xl font-bold dark:font-semibold text-center tracking-[1.2em]">김우석</div>
    <div class="mb-12 text-lg font-medium text-center">terrykim96@naver.com</div>
    <div class="mb-12 w-[384px] h-[288px] ml-auto mr-auto bg-neutral-400"></div>
    <div class="text-center w-3/4 mr-auto ml-auto mb-3">
      <button type="button" class="text-white text-lg font-semibold bg-[#fe5358] hover:bg-[#fe343b] focus:outline-none rounded-lg w-full px-5 py-2.5 text-center" data-bs-toggle="modal" data-bs-target="#signoutModal">회원 탈퇴하기</button>
    </div>
  </div>

  <!-- 로그아웃 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="signoutModal" tabindex="-1" aria-labelledby="signoutModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body pt-0 pb-4 text-center">
          <p class="font-medium">정말 탈퇴하시겠습니까?<br/>(탈퇴 시, 모든 정보가 삭제됩니다.)</p>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-4 pb-4 rounded-b-md space-x-3">
          <router-link to="/">
            <button type="button" class="text-white bg-[#fe5358] w-[62.3px] border border-[#fe5358] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#fe343b]" data-bs-dismiss="modal">예</button>
          </router-link>
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">아니오</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
</script>

<style>

</style>