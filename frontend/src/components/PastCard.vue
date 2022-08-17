<template>
  <div class="flex justify-center mb-10">
    <div class="w-[400px] h-[500px] border-2 rounded-lg border-[#2c5172] dark:border-neutral-400 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <img class="rounded-t-lg w-[400px] h-[225px]" :src="pastClassThumbnail" alt=""/>
      <div class="p-6 flex flex-col justify-between h-[275px] dark:text-neutral-100">
        <div>
          <div class="text-3xl text-justify font-bold dark:font-semibold mb-6 h-[72px]">{{ pastClass.title }}</div>
          <div class="text-xl font-semibold mt-4 text-end">{{ pastClass.teacher }}</div>
        </div>
        <div class="flex justify-end">
          <div class="text-lg text-start">
            <p>시작 : {{ pastClass.startDate }}</p>
            <p>종료 : {{ pastClass.endDate }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'

const store = useStore()

const props = defineProps({
  pastClass : {
    type: Object,
  }
})

const pastClassThumbnail = store.state.lecture.lectureImgFirstUrl+props.pastClass.lectureImg

</script>

<style>

</style>