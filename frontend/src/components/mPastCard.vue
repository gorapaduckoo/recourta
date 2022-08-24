<template>
<div class="flex justify-center mb-3 w-[450px] sm:w-[600px] h-[170px] ml-auto mr-auto">
  <div class="flex flex-col border w-full rounded-lg justify-between p-6 bg-[#fffbf8] dark:bg-[#4c4c4c]">
    <div class="text-2xl font-bold mb-2">{{ pastClass.title }}</div>
    <div class="flex justify-between items-end mt-5">
      <div class="text-xl font-semibold text-end">{{ pastClass.teacher }}</div>
      <div>
        <p>시작 : {{ pastClass.startDate }}</p>
        <p>종료 : {{ pastClass.endDate }}</p>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
const props = defineProps({
  pastClass : {
    type: Object,
  }
})
</script>

<style>

</style>