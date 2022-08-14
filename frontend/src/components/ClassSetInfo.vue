<template>
  <div class="px-3">class information</div>
  <div>{{store.state.lecture.lecInfo}}</div>
  <form class="pt-10 w-4/5 lg:w-3/4 min-w-[450px] ml-auto mr-auto" @submit.prevent="editClassSubmit">

    <!-- 강의 기간 -->
    <div class="form-group mb-6 flex justify-between">
      <div class="flex flex-col justify-center">강의기간</div>
      <div class="flex justify-between w-4/5">
        <input type="date" :value="store.state.lecture.lecInfo.startDate" id="editing_lecture_duration_start" class="form-control text-center block w-2/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
        <div class="flex flex-col justify-center">-</div>
        <input type="date" :value="store.state.lecture.lecInfo.endDate" id="editing_lecture_duration_end" class="form-control text-center block w-2/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
      </div>
    </div>

    <!-- 강의 시간 -->
    <div class="form-group mb-2 flex justify-between">
      <div class="flex flex-col justify-center">강의 시간</div>
      <div class="flex justify-between w-4/5">
        <select name="editing_lecture_weekday" id="editing_lecture_weekday" class="form-control block w-2/7 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
          <option value="1">월요일</option>
          <option value="2">화요일</option>
          <option value="3">수요일</option>
          <option value="4">목요일</option>
          <option value="5">금요일</option>
          <option value="6">토요일</option>
          <option value="7">일요일</option>
        </select>
        <input type="time" step="900" v-model.trim="editing_lecture_start_time" id="editing_lecture_start_time" class="timepicker block w-1/3  px-3 py-1.5 text-xs text-center font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
        <div class="flex flex-col justify-center">-</div>
        <input type="time" v-model.trim="editing_lecture_end_time" id="editing_lecture_end_time" class="form-control block w-1/3  px-3 py-1.5 text-xs text-center tracking-widest font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
        <button type="button" class="disabled:cursor-not-allowed" @click="addLectureTime" :disabled="store.state.lecture.lectureTimeList.length > 4">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-[#0066eb] dark:text-[#1a7dff]" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
        </button>
      </div>
    </div>

    <!-- 선택된 강의 시간 -->
    <div class="flex justify-end mb-6">
      <div class="w-4/5">
        <LectureTime
          v-for="editLecTime in store.state.lecture.lectureTimeList"
          :key="editLecTime"
          :lecTime="editLecTime"
        />
      </div>
    </div>

    <!-- 강의 설명 -->
    <div class="form-group mb-6 flex justify-between">
      <div >강의 설명</div>
      <textarea rows="3" :value="store.state.lecture.lecInfo.content" id="editing_lecture_info" name="editing_lecture_info" class="form-control block w-4/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required></textarea>
    </div>

    <!-- 강의 썸네일 -->
    <div class="form-group mb-6 flex justify-between">
      <div>강의 썸네일</div>
      <input type="file" @change="onInputImage()" ref="editing_lecture_image" name="editing_lecture_image" id="editing_lecture_image" accept="image/gif, image/jpeg, image/png" class="form-control block w-4/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
    </div>

    <!-- 강의 정보 수정 버튼 -->
    <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end pb-4 rounded-b-md">
      <button type="submit" class="text-white bg-[#4fb054] w-[120px] border border-[#4fb054] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#66bb6a]">저장</button>
    </div>
  </form>
</template>

<script setup>
import LectureTime from '../components/LectureTime.vue'

import { ref, reactive } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'
import { useStore } from 'vuex'

const store = useStore()
const route = useRouter()
const rout = useRoute()

const props = defineProps({
  lecturetime : {
    type: Array,
  }
})


const state = reactive({
  image: '',
  weekDays : ['', '월', '화', '수', '목', '금', '토', '일'],
})

const firstLectimeList = () => {
  store.state.lecture.lectureTimeList = []
  for (let lecTime of props.lecturetime) {
    let lectimeObj = {}
    const splitLectime = lecTime.split(' ')
    const splitStarttime = splitLectime[1].split(':')
    const splitEndtime = splitLectime[3].split(':')
    lectimeObj['weekDay'] = state.weekDays.indexOf(splitLectime[0])
    lectimeObj['startHour'] = splitStarttime[0]
    lectimeObj['startMinute'] = splitStarttime[1]
    lectimeObj['endHour'] = splitEndtime[0]
    lectimeObj['endMinute'] = splitEndtime[1]
    store.state.lecture.lectureTimeList.push(lectimeObj)
  }
}

firstLectimeList()

let editing_lecture_start_time = ref("00:00")
let editing_lecture_end_time = ref("00:00")
let editing_lecture_image = ref("")
// document.getElementById('name').value

const addLectureTime = () => {
  let lectureTime = {}
  lectureTime['weekDay'] = editing_lecture_weekday.value
  const startTime = editing_lecture_start_time.value.split(':')
  lectureTime['startHour'] = startTime[0]
  lectureTime['startMinute'] = startTime[1]
  const endTime = editing_lecture_end_time.value.split(':')
  lectureTime['endHour'] = endTime[0]
  lectureTime['endMinute'] = endTime[1]
  store.state.lecture.lectureTimeList.push(lectureTime)
  store.state.lecture.lectureTimeList.sort(function(a, b) {
    return a["weekDay"] - b["weekDay"]
  })
}

const editClassSubmit = async () => {
  let editblob = await UrltoBlob(state.image)
  let editfd = new FormData()
  const data = {
    content : document.getElementById('editing_lecture_info').value,
    startDate : document.getElementById('editing_lecture_duration_start').value,
    endDate : document.getElementById('editing_lecture_duration_end').value,
    lectureTime : store.state.lecture.lectureTimeList,
  }
  const json = JSON.stringify(data)
  const datablob = new Blob([json],{type:"application/json"})
  editfd.append("lectureImg",editblob)
  editfd.append("request",datablob)
  await axios.put(rct.lecture.lectureinfo(Number(rout.params.lecId)),editfd,{
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    console.log('성공')
    console.log(res.data)
  })
  .catch(err => {
    console.log('실패')
    console.log(err)
  })
}

const onInputImage = () => {
  state.image = window.URL.createObjectURL(editing_lecture_image.value.files[0])
}

const UrltoBlob = async (dataURL) => {
  const res = await fetch(dataURL)
  const blob = await res.blob()
  return blob
}
</script>

<style scoped>

</style>