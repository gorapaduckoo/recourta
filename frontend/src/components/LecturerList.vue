<template>
  <div class="flex justify-end mr-4">
    <button type="button" class="text-white font-bold w-1/8 bg-[#4fb054] border border-[#4fb054] hover:bg-[#66bb6a] focus:outline-none rounded-lg px-6 py-1.5 text-center" @click="modalOpen">
      강의 생성
    </button>
  </div>

	<div class="flex items-center pb-4">
		<!-- The left line -->
		<div class="flex-grow h-px bg-neutral-400"></div> 

		<!-- Your text here -->
		<div class="flex-shrink text-2xl font-bold px-4">강의중인 강의 목록</div>

		<!-- The right line -->
		<div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
	</div>

	<!-- 강의중인 강의가 없을 때 -->
	<div v-if="(props.currentTeaching)?!props.currentTeaching.length:true" class="w-3/4 ml-auto mr-auto mt-20 mb-16 text-lg lg:text-xl text-center font-semibold">현재 강의중인 강의가 없습니다.</div>
	
	<!-- Reactive Desktop -->
	<div class="hidden lg:grid lg:pt-8 lg:grid-cols-2 xl:grid-cols-3">
    <LecturerCard
      v-for="lecture in props.currentTeaching"
      :key="lecture.lectureId"
      :lecture="lecture"
    />
	</div>

	<!-- Reactive Mobile -->
	<div class="lg:hidden flex flex-col my-8">
		<mLecturerCard
		v-for="lecture in props.currentTeaching"
		:key="lecture.lectureId"
    :lecture="lecture"
		/>
	</div>

	<!-- 강의 만들기 Modal -->
  <div class="modal fade bg-black bg-opacity-50 fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="classMakeModal" tabindex="-1" aria-labelledby="classMakeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg relative w-[600px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-6 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close" @click="modalClose">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body mt-4 pt-2 pb-4 px-10 text-center">
          <!-- 본문 -->
          <form class="pt-10" @submit.prevent="makeClassSubmit">

            <!-- 이름 입력 -->
            <div class="form-group mb-6 flex justify-between">
              <div class="flex flex-col justify-center">강의명</div>
              <input type="text" v-model.trim="floating_lecture_name" id="floating_lecture_name" class="form-control block w-4/5 px-3 py-1.5 text-base font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
            </div>

            <!-- 강의 기간 -->
            <div class="form-group mb-6 flex justify-between">
              <div class="flex flex-col justify-center">강의기간</div>
              <div class="flex justify-between w-4/5">
                <input type="date" v-model.trim="floating_lecture_duration_start" id="floating_lecture_duration_start" class="form-control text-center block w-2/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
                <div class="flex flex-col justify-center">-</div>
                <input type="date" v-model.trim="floating_lecture_duration_end" id="floating_lecture_duration_end" class="form-control text-center block w-2/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
              </div>
            </div>
            
            <!-- 시간 추가 / 제거 -->
            <div class="form-group mb-2 flex justify-between">
              <div class="">강의 시간</div>
                <div class="w-4/5">
                  <LectureTime
                    v-for="lecTime in store.state.lecture.lectureTimeList"
                    :key="lecTime"
                    :lecTime="lecTime"
                  />
                <div v-if="!state.isempty" class="flex flex-col justify-center w-full border border-solid border-neutral-300 rounded h-[30px] text-sm text-neutral-400">
                  <div>강의 시간을 추가해주세요</div>
                </div>
              </div>
            </div>
            
            <!-- 강의 시간 -->
            <div class="form-group mb-5 flex justify-end">
              <div class="flex justify-between w-4/5">
                <select name="floating_lecture_weekday" id="floating_lecture_weekday" class="form-control block w-2/7 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
                  <option value="1">월요일</option>
                  <option value="2">화요일</option>
                  <option value="3">수요일</option>
                  <option value="4">목요일</option>
                  <option value="5">금요일</option>
                  <option value="6">토요일</option>
                  <option value="7">일요일</option>
                </select>
                <input type="time" step="900" v-model.trim="floating_lecture_start_time" id="floating_lecture_start_time" class="timepicker block w-1/3  px-3 py-1.5 text-xs text-center font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
                <div class="flex flex-col justify-center">-</div>
                <input type="time" v-model.trim="floating_lecture_end_time" id="floating_lecture_end_time" class="form-control block w-1/3  px-3 py-1.5 text-xs text-center tracking-widest font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
                <button type="button" class="disabled:cursor-not-allowed" @click="addLectureTime" :disabled="store.state.lecture.lectureTimeList.length > 4">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-[#0066eb] dark:text-[#1a7dff]" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                </button>
              </div>
            </div>

            
            <!-- 강의 설명 -->
            <div class="form-group mb-6 flex justify-between">
              <div >강의 설명</div>
              <textarea rows="3" name="floating_lecture_info" v-model.trim="floating_lecture_info" class="form-control block w-4/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required></textarea>
            </div>

            <!-- 강의 썸네일 -->
            <div class="form-group mb-6 flex justify-between">
              <div>강의 썸네일</div>
              <input type="file" @change="onInputImage()" ref="floating_lecture_image" name="floating_lecture_image" id="floating_lecture_image" accept="image/gif, image/jpeg, image/png" class="form-control block w-4/5 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none dark:bg-neutral-700" required>
            </div>

            <!-- 등록하기 버튼 -->
            <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-center px-8 pb-4 rounded-b-md space-x-3">
              <button type="submit" class="text-white bg-[#4fb054] w-[120px] border border-[#4fb054] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#66bb6a]">강의 생성</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import LecturerCard from './LecturerCard.vue'
import mLecturerCard from './mLecturerCard.vue'
import LectureTime from '../components/LectureTime.vue'

import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'
import { useStore } from 'vuex'

const store = useStore()
const route = useRouter()

const props = defineProps({
  currentTeaching : {
    type: Array,
  }
})

const state = reactive({
  image : '',
  isempty : computed(() => store.state.lecture.lectureTimeList.length),
})

let floating_lecture_name = ref("")
let floating_lecture_start_time = ref("00:00")
let floating_lecture_end_time = ref("00:00")
let floating_lecture_duration_start = ref(new Date().toISOString().substring(0, 10))
let floating_lecture_duration_end = ref(new Date().toISOString().substring(0, 10))
let floating_lecture_info = ref("")
let floating_lecture_image = ref("")

const addLectureTime = () => {
  let lectureTime = {}
  lectureTime['weekDay'] = floating_lecture_weekday.value
  const startTime = floating_lecture_start_time.value.split(':')
  lectureTime['startHour'] = startTime[0]
  lectureTime['startMinute'] = startTime[1]
  const endTime = floating_lecture_end_time.value.split(':')
  lectureTime['endHour'] = endTime[0]
  lectureTime['endMinute'] = endTime[1]
  store.state.lecture.lectureTimeList.push(lectureTime)
  store.state.lecture.lectureTimeList.sort(function(a, b) {
    return a["weekDay"] - b["weekDay"]
  })
}

const makeClassSubmit = async () => {
  if (!store.state.lecture.lectureTimeList.length) {
    alert('강의 시간을 추가해주세요.')
  } else {
    let classblob = await UrltoBlob(state.image)
    let classfd = new FormData()
    
    const data = {
      userId : store.state.user.userId,
      title : floating_lecture_name.value,
      content : floating_lecture_info.value,
      startDate : floating_lecture_duration_start.value,
      endDate : floating_lecture_duration_end.value,
      lectureTime : store.state.lecture.lectureTimeList,
    }
    const json = JSON.stringify(data)
    const datablob = new Blob([json],{type:"application/json"})
    classfd.append("lectureImg",classblob)
    classfd.append("request",datablob)
    await axios.post(rct.lecture.lecturecreate(),classfd,{
      headers: {
        Authorization: store.state.user.accessToken,
        'Context-Type' : 'multipart/form-data',
      }
    })
    .then(res => {
      modalClose()
      route.push({path:"/main"})
    })
    .catch(err => {
      console.log(err)
    })
  }
}

const onInputImage = () => {
  state.image = window.URL.createObjectURL(floating_lecture_image.value.files[0])
}

const UrltoBlob = async (dataURL) => {
  const res = await fetch(dataURL)
  const blob = await res.blob()
  return blob
}

const modalOpen = () => {
  document.getElementById('classMakeModal').classList.replace('hidden', 'show')
  floating_lecture_name.value = ""
  floating_lecture_start_time.value = "00:00"
  floating_lecture_end_time.value = "00:00"
  floating_lecture_duration_start.value = new Date().toISOString().substring(0, 10)
  floating_lecture_duration_end.value = new Date().toISOString().substring(0, 10)
  floating_lecture_info.value = ""
  floating_lecture_image.value = ""
  store.state.lecture.lectureTimeList = []
}

const modalClose = () => {
  document.getElementById('classMakeModal').classList.replace('show', 'hidden')
}
</script>

<style scoped>
  input {
    -webkit-appearance: none;
       -moz-appearance: none;
            appearance: none;
  }
</style>