<template>
  <div @mouseenter="cardToggle" @mouseleave="cardToggle" class="relative">
    <!-- 보이는 부분 -->
    <div class="w-[400px] h-[500px] z-0 border-2 rounded-lg border-[#2c5172] dark:border-neutral-400 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <img class="rounded-t-lg w-[400px] h-[225px]" src="https://mdbootstrap.com/img/new/standard/nature/184.jpg" alt=""/>
      <div class="p-6 flex flex-col justify-between h-[275px] dark:text-neutral-100">
        <div>
          <div class="text-3xl text-justify font-bold dark:font-semibold mb-6 h-[72px]">데이터로 표현한 세상을 보고있는 우리와 나와 바다 놀러가</div>
          <div class="text-xl font-semibold mt-4 text-end">김우석</div>
        </div>
        <div class="text-base text-end">금 09:00 - 12:00</div>
      </div>
    </div>

    <!-- Hover시 보이는 부분 -->
    <div v-if="state.isMouseIn" class="absolute top-0 z-10 px-6 flex flex-col w-[400px] h-[500px] bg-black bg-opacity-80 rounded-lg border-2 border-black">
      <div class="h-[225px] flex items-center">
        <div class="text-3xl text-justify font-bold text-white">데이터로 표현한 세상을 보고있는 우리와 나</div>
      </div>
      <div class="h-[275px] flex flex-col justify-around items-center py-4">
        <router-link to="/class" class="w-[175px]">
          <button class="w-full py-2.5 text-white rounded-md cursor-pointer bg-black border-gray-300 border-[3px]">강의실 입장</button>
        </router-link>
        <button class="w-[175px] py-2.5 text-white rounded-md cursor-pointer bg-black border-gray-300 border-[3px]">출결 확인</button>
        <button class="w-[175px] py-2.5 text-white rounded-md cursor-pointer bg-black border-gray-300 border-[3px]">수강 철회</button>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'

export default {
  setup(){
    const state = reactive({
      isMouseIn : false
    })
    const cardToggle = () => {
      state.isMouseIn = !state.isMouseIn
    }
    return {
      state,
      cardToggle,
    }
  },
}
</script>

<style>

</style>