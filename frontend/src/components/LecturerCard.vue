<template>
  <div class="relative ml-auto mr-auto mb-10">
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
            <router-link to="/classsetting" class="text-xl text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full text-center dark:bg-[#2c5172] py-2 px-10">
              <button>강의 관리</button>
            </router-link>
            <button @click="enterClass" class="text-xl text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full text-center dark:bg-[#2c5172] py-2 px-10">
              강의 시작
            </button>
            
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script setup>
import { reactive } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const state = reactive({
  // prop서 가져온 lectureid를 String()하여 이용
  mySessionId: 'SessionA',
  // store에 있는 이름 정보를 가져오기
  myUserName: '김싸피'
})

const enterClass = () => {
  
  store.commit("SET_MySessionId",state.mySessionId)
  store.commit("SET_MyUserName",state.myUserName)

  location.href="/class"
}

</script>

<style>

</style>