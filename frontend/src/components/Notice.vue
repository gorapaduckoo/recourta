<template>
<button class="w-4/5 ml-auto mr-auto flex justify-center mb-3" data-bs-toggle="modal" data-bs-target="#noticeModal">
  <div class="w-full min-w-[400px] h-[150px] flex flex-col border rounded-lg justify-between px-8 py-6 bg-[#fffbf8] dark:bg-[#4c4c4c]">
    <div :class="{'text-[#808080] dark:text-[#dcdcdc]': isread}" class="flex justify-between">
      <div class="text-2xl font-bold">휴강 안내</div>
      <!-- 읽었을 때 이미지 -->
      <svg v-if="isread" xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M3 19v-8.93a2 2 0 01.89-1.664l7-4.666a2 2 0 012.22 0l7 4.666A2 2 0 0121 10.07V19M3 19a2 2 0 002 2h14a2 2 0 002-2M3 19l6.75-4.5M21 19l-6.75-4.5M3 10l6.75 4.5M21 10l-6.75 4.5m0 0l-1.14.76a2 2 0 01-2.22 0l-1.14-.76" />
      </svg>
      <!-- 안 읽었을 때 이미지 -->
      <svg v-else xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
      </svg>
    </div>
    <div :class="{'text-[#808080] dark:text-[#dcdcdc]': isread}" class="flex justify-between items-end mt-5">
      <div class="text-xl font-semibold text-end">알고리즘</div>
      <div>
        <p>2022-09-01</p>
      </div>
    </div>
  </div>
</button>

<!-- 알림 Modal -->
<div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="noticeModal" tabindex="-1" aria-labelledby="noticeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
    <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
      <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
        <button type="button" data-bs-dismiss="modal" aria-label="Close">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>
      <div class="modal-body mb-8 pt-2 pb-4 px-6 text-center">
        <p class="text-xl font-bold dark:font-semibold mb-8">과제 제출 안내</p>
        <div class="flex flex-col items-end mb-8 px-3">
          <p class="font-medium">프로그래밍 언어</p>
          <p class="text-xs">2022-08-02 17:45:38</p>
        </div>
        <p class="text-justify px-3">과제 2는 리눅스 서버로 제출하세요. 제발 말 좀 들으세요. 오죽하면 이렇게 알림까지 보내겠습니까. 부탁드릴게요.</p>
      </div>
      <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-4 pb-4 rounded-b-md space-x-3">
        <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
const props = defineProps({
  isread : {
    type: Boolean,
  }
})

</script>

<style>

</style>