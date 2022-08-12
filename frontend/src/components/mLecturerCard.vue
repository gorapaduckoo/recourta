<template>
  <div class="flex justify-center mb-3 w-[450px] sm:w-[600px] h-[170px] ml-auto mr-auto">
    <div class="flex flex-col border w-full rounded-lg justify-between p-6 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <div class="text-2xl font-bold mb-2">{{ props.lecture.title }}</div>
      <div class="flex justify-between mt-5">
        <div class="text-xl font-semibold text-end">{{ props.lecture.teacher }}</div>
        <div class="space-x-5">
          <router-link to="/classsetting">
            <button class="py-1.5 px-3 text-white rounded-md bg-[#2c5172]">강의 관리</button>
          </router-link>
          <button type="button" @click="enterClass" class="py-1.5 px-3 text-white rounded-md bg-[#2c5172]">강의 시작</button>
        </div>
      </div>
  </div>
  </div>
</template>


<script setup>
import { useStore } from 'vuex'

const store = useStore()

const props = defineProps({
  lecture : {
    type: Object,
  }
})

const enterClass = () => {
  store.commit("SET_MySessionId",props.lecture.lectureId)
  store.commit("SET_MyUserName",props.lecture.teacher)
  location.href="/class"
}
</script>

<style>

</style>