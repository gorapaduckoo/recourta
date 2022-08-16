<template>
  <div class="relative ml-auto mr-auto mb-10">
    <!-- 보이는 부분 -->
    <div class="w-[400px] h-[500px] z-0 border-2 rounded-lg border-[#2c5172] dark:border-neutral-500 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <img class="rounded-t-lg w-[400px] h-[225px]" :src="lectureThumbnail" alt=""/>
      <div class="p-6 flex flex-col h-[275px] dark:text-neutral-100">
        <div>
          <div class="text-3xl text-justify font-bold dark:font-semibold mb-6 h-[72px]">{{ props.lecture.title }}</div>
        </div>
        <div class="flex justify-between h-full">
          <div class="flex flex-col-reverse">
            <div class="text-lg text-start" v-for="lecturetime in lectime" :key="lecturetime">
              <p>{{lecturetime}}</p>
            </div>
          </div>
          <div class="flex flex-col items-end justify-end space-y-5">
            <router-link :to="{ name: 'classsetting', params: { lecId: props.lecture.lectureId } }" class="text-xl text-white font-semibold bg-[#2c5172] hover:bg-[#325c81] dark:hover:bg-[#325c81] focus:outline-none rounded-lg w-full text-center dark:bg-[#2c5172] py-2 px-10">
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
import { useStore } from 'vuex'

const store = useStore()

const props = defineProps({
  lecture : {
    type: Object,
  }
})

const lectime = [...props.lecture.lectureTime].reverse()
const lectureThumbnail = store.state.lecture.lectureImgFirstUrl+props.lecture.lectureImg

const enterClass = () => {
  // store.commit("SET_MySessionId", 'SessionA')
  // store.commit("SET_MyUserName", '김싸피')
  store.commit("SET_MySessionId",String(props.lecture.lectureId))
  store.commit("SET_LecturerName",props.lecture.teacher)
  store.commit("SET_SidebarTitle", props.lecture.title)
  store.commit("SET_IsLecturer", true)
  location.href="/class"
}


</script>

<style>

</style>