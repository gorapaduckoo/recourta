<template>
  <div class="lg:pt-4 w-3/4 ml-auto mr-auto">
    <div class="flex justify-between items-end">
      <div class="text-xl font-semibold">보낸 알림 목록</div>
      <button type="button" class="text-white font-bold w-1/8 bg-[#4fb054] border border-[#4fb054] hover:bg-[#66bb6a] focus:outline-none rounded-lg px-6 py-1.5 text-center">
        알림 보내기
      </button>
    </div>
    <hr class="my-4"/>
    <SendNoticeList />
  </div>
</template>

<script setup>
import SendNoticeList from './SendNoticeList.vue'
</script>

<style scoped>

</style>