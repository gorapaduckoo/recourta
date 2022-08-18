<template>
  <form class="lg:pt-10 w-4/5 lg:w-3/4 min-w-[450px] ml-auto mr-auto" @submit.prevent="editClassSubmit">

    <!-- 강의 기간 -->
    <div class="form-group mb-10 flex justify-between">
      <div class="flex flex-col justify-center">강의기간</div>
      <div class="flex justify-between w-4/5">
        <input type="date" :value="store.state.lecture.lecInfo.startDate" id="editing_lecture_duration_start" class="form-control text-center block w-2/5 px-3 py-2 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
        <div class="flex flex-col justify-center">-</div>
        <input type="date" :value="store.state.lecture.lecInfo.endDate" id="editing_lecture_duration_end" class="form-control text-center block w-2/5 px-3 py-2 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
      </div>
    </div>

    <!-- 강의 시간 -->
    <div class="form-group mb-2 flex justify-between">
      <div class="flex flex-col justify-center">강의 시간</div>
      <div class="flex justify-between w-4/5">
        <select name="editing_lecture_weekday" id="editing_lecture_weekday" class="form-control block w-2/7 px-3 py-2 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
          <option value="1">월요일</option>
          <option value="2">화요일</option>
          <option value="3">수요일</option>
          <option value="4">목요일</option>
          <option value="5">금요일</option>
          <option value="6">토요일</option>
          <option value="7">일요일</option>
        </select>
        <input type="time" step="900" v-model.trim="editing_lecture_start_time" id="editing_lecture_start_time" class="timepicker block w-1/3  px-3 py-2 text-xs text-center font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
        <div class="flex flex-col justify-center">-</div>
        <input type="time" v-model.trim="editing_lecture_end_time" id="editing_lecture_end_time" class="form-control block w-1/3  px-3 py-2 text-xs text-center tracking-widest font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
        <button type="button" class="disabled:cursor-not-allowed" @click="addLectureTime" :disabled="store.state.lecture.lectureTimeList.length > 4">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-[#0066eb] dark:text-[#1a7dff]" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
        </button>
      </div>
    </div>

    <!-- 선택된 강의 시간 -->
    <div class="flex justify-end mb-10">
      <div class="w-4/5">
        <LectureTime
          v-for="editLecTime in store.state.lecture.lectureTimeList"
          :key="editLecTime"
          :lecTime="editLecTime"
        />
      </div>
    </div>

    <!-- 강의 설명 -->
    <div class="form-group mb-10 flex justify-between">
      <div >강의 설명</div>
      <textarea rows="3" :value="store.state.lecture.lecInfo.content" id="editing_lecture_info" name="editing_lecture_info" class="form-control block w-4/5 px-3 py-2 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required></textarea>
    </div>

    <!-- 강의 썸네일 -->
    <div class="form-group mb-10 flex justify-between">
      <div>강의 썸네일</div>
      <input type="file" @change="onInputImage()" ref="editing_lecture_image" name="editing_lecture_image" id="editing_lecture_image" accept="image/gif, image/jpeg, image/png" class="form-control block w-4/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700">
    </div>

    <!-- 강의 정보 수정 버튼 -->
    <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end mb-10 pb-4 rounded-b-md">
      <button type="submit" class="text-white bg-[#4fb054] w-[120px] border border-[#4fb054] font-medium rounded-lg text-sm px-3 py-2 text-center hover:bg-[#66bb6a]">저장</button>
    </div>
  </form>

  <!-- 수정 완료 Modal -->
  <div class="modal fade bg-black bg-opacity-50 fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="infoEditModal" tabindex="-1" aria-hidden="true" @click.self="modalClose">
    <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close" @click="modalClose">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body py-4 text-center">
            <p class="font-medium">
              성공적으로 정보가 변경되었습니다.
            </p>
          </div>
          <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end p-4 rounded-b-md space-x-3">
            <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" @click="modalClose">닫기</button>
          </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import LectureTime from '../components/LectureTime.vue'

import { ref, reactive } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'
import { useStore } from 'vuex'

const store = useStore()
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
    lectimeObj['startHour'] = splitStarttime[0].padStart(2, '0')
    lectimeObj['startMinute'] = splitStarttime[1].padStart(2, '0')
    lectimeObj['endHour'] = splitEndtime[0].padStart(2, '0')
    lectimeObj['endMinute'] = splitEndtime[1].padStart(2, '0')
    store.state.lecture.lectureTimeList.push(lectimeObj)
  }
}

firstLectimeList()

let editing_lecture_start_time = ref("00:00")
let editing_lecture_end_time = ref("00:00")
let editing_lecture_image = ref("")

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
  if (state.image) {
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
      console.log('이미지 보내는 거 성공')
      modalOpen()
    })
    .catch(err => {
      console.log('이미지 보내는 거 실패')
      console.log(err)
    })
  } else {
    let editfd = new FormData()
    const data = {
      content : document.getElementById('editing_lecture_info').value,
      startDate : document.getElementById('editing_lecture_duration_start').value,
      endDate : document.getElementById('editing_lecture_duration_end').value,
      lectureTime : store.state.lecture.lectureTimeList,
    }
    const json = JSON.stringify(data)
    const datablob = new Blob([json],{type:"application/json"})
    editfd.append("request",datablob)
    await axios.put(rct.lecture.lectureinfo(Number(rout.params.lecId)),editfd,{
      headers: {
        Authorization: store.state.user.accessToken,
        'Context-Type' : 'multipart/form-data',
      }
    })
    .then(res => {
      console.log('이미지 안 보내는 거 성공')
      modalOpen()
    })
    .catch(err => {
      console.log('이미지 안 보내는 거 실패')
      console.log(err)
    })
  }
  
  
}

const onInputImage = () => {
  state.image = window.URL.createObjectURL(editing_lecture_image.value.files[0])
}

const UrltoBlob = async (dataURL) => {
  const res = await fetch(dataURL)
  const blob = await res.blob()
  return blob
}

const modalOpen = () => {
  document.getElementById('infoEditModal').classList.replace('hidden', 'show')
}

const modalClose = () => {
  document.getElementById('infoEditModal').classList.replace('show', 'hidden')
  location.reload()
}
</script>

<style scoped>

</style>