<template>
  <div class="relative">
    <!-- 보이는 부분 -->
    <div class="w-[400px] h-[500px] z-0 border-2 rounded-lg border-[#2c5172] dark:border-neutral-500 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <img class="rounded-t-lg w-[400px] h-[225px]" src="https://mdbootstrap.com/img/new/standard/nature/184.jpg" alt=""/>
      <div class="p-6 flex flex-col h-[275px] dark:text-neutral-100">
        <div>
          <div class="text-3xl text-justify font-bold dark:font-semibold mb-6 h-[72px]">데이터로 표현한 세상을 보고있는 우리와 나와 바다 놀러가</div>
        </div>
        <div class="flex justify-around h-full">
          <div class="flex flex-col-reverse">
            <div class="text-lg text-start">
              <p>월 13:00 ~ 15:00</p>
              <p>목 10:00 ~ 12:00</p>
              <p>금 09:00 ~ 12:00</p>
            </div>
          </div>
          <div class="flex flex-col items-end justify-end space-y-5">
            <router-link to="/main" class="text-xl text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full text-center dark:bg-[#2c5172] py-2 px-10">
              <button class="">강의 관리</button>
            </router-link>
            <router-link to="/class" class="text-xl text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full text-center dark:bg-[#2c5172] py-2 px-10">
              <button>강의 시작</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <!-- Hover시 보이는 부분 -->
    <div v-if="state.isMouseIn" class="absolute top-0 z-10 px-6 flex flex-col w-[400px] h-[500px] bg-black bg-opacity-80 rounded-lg border-2 border-black">
      <div class="h-[225px] flex items-center">
        <div class="text-3xl text-justify font-bold text-white">데이터로 표현한 세상을 보고있는 우리와 나와 바다 놀러가</div>
      </div>
      <div class="h-[275px] flex flex-col justify-around items-center py-4">
        <router-link to="/" class="w-[175px]">
          <button class="w-full py-2.5 text-white rounded-md cursor-pointer bg-black hover:bg-neutral-700 border-gray-300 border-[3px]">강의 관리</button>
        </router-link>
        <router-link to="/class" class="w-[175px]">
          <button class="w-full py-2.5 text-white rounded-md cursor-pointer bg-black hover:bg-neutral-700 border-gray-300 border-[3px]">강의 시작</button>
        </router-link>
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