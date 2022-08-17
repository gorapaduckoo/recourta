<template>
<button class="w-full ml-auto mr-auto flex justify-center mb-3"  @click="modalOpen">
  <div class="w-full min-w-[400px] h-[80px] flex flex-col border rounded-lg justify-between px-8 py-6 bg-[#fffbf8] dark:bg-[#4c4c4c]">
    <div class="flex justify-between items-center">
      <div class="text-xl font-semibold">휴강 안내</div>
      <div>
        <p>2022-09-01</p>
      </div>
    </div>
  </div>
</button>

<!-- 알림 Modal -->
<div class="modal fade bg-black bg-opacity-50 fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" :id="sendNoticeModal" tabindex="-1" aria-labelledby="noticeModalLabel" aria-hidden="true" @click.self="modalClose">
  <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
    <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
      <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
        <button type="button" data-bs-dismiss="modal" aria-label="Close" @click="modalClose">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>
      <div class="modal-body mb-8 pt-2 pb-4 px-6 text-center">
        <p class="text-xl font-bold dark:font-semibold mb-8">과제 제출 안내</p>
        <div class="flex flex-col items-end mb-8 px-3">
          <p class="font-medium">김그작 외 3명</p>
          <p class="text-xs">2022-08-02 17:45:38</p>
        </div>
        <p class="text-justify px-3">과제 2는 리눅스 서버로 제출하세요. 제발 말 좀 들으세요. 오죽하면 이렇게 알림까지 보내겠습니까. 부탁드릴게요.</p>
      </div>
      <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-4 pb-4 rounded-b-md space-x-3">
        <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" @click="modalClose">닫기</button>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
const props = defineProps({
  num : {
    type: Number,
  }
})

const sendNoticeModal = 'sendNotice'+props.num

const modalOpen = () => {
  document.getElementById(sendNoticeModal).classList.replace('hidden', 'show')
}

const modalClose = () => {
  document.getElementById(sendNoticeModal).classList.replace('show', 'hidden')
}


</script>

<style>

</style>