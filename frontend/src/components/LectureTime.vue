<template>
  <div class="flex rounded mb-1 justify-between">
    <div class="block w-2/7 px-6 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700">{{ state.weekDayList[props.lecTime.weekDay] }}</div>
    <div class="block w-1/3  px-3 py-1.5 text-xs text-center font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700">{{ props.lecTime.startHour }} : {{ props.lecTime.startMinute }}</div>
    <div class="flex flex-col justify-center">-</div>
    <div class="block w-1/3  px-3 py-1.5 text-xs text-center font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700">{{ props.lecTime.endHour }} : {{ props.lecTime.endMinute }}</div>
    <button type="button" @click="deleteLectureTime">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-[#fe343b] dark:text-[#fe4d53]" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
      </svg>
    </button>
    
  </div>
  <!-- { "weekDay": "1", "startHour": "00", "startMinute": "00", "endHour": "00", "endMinute": "05" } -->
</template>

<script setup>
import { reactive } from 'vue'
import {useStore} from 'vuex'

const store = useStore()


const props = defineProps({
  lecTime : {
    type: Object,
  }
})

const state = reactive({
  weekDayList : ['', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
})

const deleteLectureTime = () => {
  const idx = store.state.lecture.lectureTimeList.indexOf(props.lecTime)
  store.state.lecture.lectureTimeList.splice(idx, 1)
  
}

</script>

<style>

</style>