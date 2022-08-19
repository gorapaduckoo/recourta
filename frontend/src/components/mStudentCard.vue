<template>

	<div class="flex justify-center mb-3 w-[450px] sm:w-[600px] h-[170px] ml-auto mr-auto">
		<div class="flex flex-col border w-full rounded-lg justify-between p-6 bg-[#fffbf8] dark:bg-[#4c4c4c]">
			<div class="text-2xl font-bold mb-2">{{ lecture.title }}</div>
			<div class="flex justify-between mt-5">
				<div class="text-xl font-semibold text-end">{{ lecture.teacher }}</div>
				<div class="space-x-5">
					<button class="py-1.5 px-3 text-white rounded-md bg-[#2c5172]" @click="attModalOpen">출결 확인</button>
					<button @click="enterClass" type="button" class="py-1.5 px-3 text-white rounded-md bg-[#2c5172]">강의 입장</button>
					<button type="button" class="py-1.5 px-3 text-white rounded-md bg-[#fe5358]" @click="outModalOpen">수강 철회</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 출결 Modal -->
  <div class="modal fade bg-black bg-opacity-50 fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" :id="mAttModalId" tabindex="-1" aria-labelledby="attendanceModalLabel" aria-hidden="true" @click.self="attModalClose">
    <div class="modal-dialog modal-dialog-centered relative w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-6 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close" @click="attModalClose">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body pt-2 pb-4 px-10 text-center">
          <p class="mb-8 font-bold text-2xl">출결 확인</p>
          <!-- Progress Bar -->
          <div class="w-full h-8 mb-8 bg-neutral-200 dark:bg-[#666666] rounded-lg">
            <div class="h-8 bg-[#2c5172] dark:bg-[#325c81] p-0.5 leading-none rounded-lg" :style="{width: `${state.percentAtt}%`}">
              <p class="text-base font-medium text-white text-center">{{ state.percentAtt }}%</p>
            </div>
          </div>

          <!-- 본문 -->
          <div class="flex flex-wrap w-full mb-6">
            <div class="w-[110px] ml-[10px] mb-6 text-start text-xl font-medium border-r border-neutral-400 dark:border-neutral-500">출석</div>
            <div class="w-[110px] mb-6 text-end text-xl font-semibold">{{ state.attLateAbs[1] }}</div>
            <div class="w-[50px] text-end text-lg font-medium"> / {{ state.totalClass }}</div>
            <div class="w-[110px] ml-[10px] mb-6 text-start text-xl font-medium border-r border-neutral-400 dark:border-neutral-500">지각</div>
            <div class="w-[110px] mb-6 text-end text-xl font-semibold">{{ state.attLateAbs[2] }}</div>
            <div class="w-[50px] mb-6 text-end text-lg font-medium"> / {{ state.totalClass }}</div>
            <div class="w-[110px] ml-[10px] text-start text-xl font-medium border-r border-neutral-400 dark:border-neutral-500">결석</div>
            <div class="w-[110px] text-end text-xl font-semibold">{{ state.attLateAbs[3] }}</div>
            <div class="w-[50px] text-end text-lg font-medium"> / {{ state.totalClass }}</div>
          </div>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-6 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" @click="attModalClose">닫기</button>
        </div>
      </div>
    </div>
  </div>

   <!-- 수강 철회 Modal -->
  <div class="modal fade bg-black bg-opacity-50 fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" :id="outModalId" tabindex="-1" aria-labelledby="mClassoutModalLabel" aria-hidden="true" @click.self="outModalClose">
    <div class="modal-dialog modal-dialog-centered relative w-[440px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-6 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close" @click="outModalClose">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body mt-4 pt-2 pb-4 px-10 text-center">
          <!-- 본문 -->
          <div class="flex flex-wrap w-full mb-6">
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의명</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{ lecture.title }}</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의자</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{ lecture.teacher }}</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 기간</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{ lecture.startDate }} ~ {{ lecture.endDate }}</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 시간</div>
            <div class="w-[260px] mb-6 flex flex-col">
              <div class="text-start text-lg font-medium" v-for="lecturetime in lecture.lectureTime" :key="lecturetime">
                <p>{{lecturetime}}</p>
              </div>
            </div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 설명</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{ lecture.content }}</div>
          </div>

          <p class="mb-4 font-bold dark:font-semibold text-2xl">해당 강의를 취소 하시겠습니까?</p>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-center px-4 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-white bg-[#fe5358] w-[62.3px] border border-[#fe5358] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#fe343b]" @click="outRegistration">예</button>
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" @click="outModalClose">아니오</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'

const route = useRouter()
const store = useStore()

const props = defineProps({
  lecture : {
    type: Object,
  }
})

const state = reactive({
  lectureAttendance : [],
  userAttendance: [],
  userAtt: [],
  attLateAbs: computed(() => {
    if (state.userAtt) {
      let tempala = [0, 0, 0, 0]
      for (let att of state.userAtt) {
        tempala[att]++
      }
      return tempala
    } else {
      return null
    }
  }),
  totalClass: computed(() => {
    if (state.attLateAbs) {
      return state.attLateAbs.reduce((a, b) => a + b, 0)
    } else {
      return null
    }
  }),
  percentAtt: computed(() => {
    if (state.attLateAbs && state.totalClass) {
      return state.attLateAbs[1]/state.totalClass*100
    } else {
      return null
    }
  }),
})

const enterClass = () => {
  store.commit("SET_MySessionId",String(props.lecture.sessionId))
  store.commit("SET_MyLectureId",String(props.lecture.lectureId))
  store.commit("SET_LecturerName",props.lecture.teacher)
  store.commit("SET_SidebarTitle", props.lecture.title)
  store.commit("SET_IsLecturer", false)
  route.push({path:"/class"})
}

// 출결 확인 관련 함수
const mAttModalId = 'mAtt'+props.lecture.lectureId

const attModalOpen = () => {
  document.getElementById(mAttModalId).classList.replace('hidden', 'show')
}

const attModalClose = () => {
  document.getElementById(mAttModalId).classList.replace('show', 'hidden')
}

const getLectureAttendance = async () => {
  await axios({
    url: rct.attendance.lectureAllAttendance(props.lecture.lectureId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    state.lectureAttendance = res.data.lectureAttendance
    if (state.lectureAttendance.length) {
      state.lectureAttendance.map(sessionAtt => {
        state.userAttendance.push(sessionAtt.sessionAttendance.find(sessionUserAtt => {
          return sessionUserAtt.userId == store.state.user.userId
        }).attType)
      })
      state.userAtt = Object.values(state.userAttendance)
    }
  })
  .catch(err => {
    console.log(err)
  })
}

getLectureAttendance()

// 수강 철회 관련 함수
const outModalId = 'mOut'+props.lecture.lectureId

const outModalOpen = () => {
  document.getElementById(outModalId).classList.replace('hidden', 'show')
}

const outModalClose = () => {
  document.getElementById(outModalId).classList.replace('show', 'hidden')
}

const outRegistration = async () => {
  await axios({
    url: rct.regist.regist(),
    method: 'delete',
    headers: {
      Authorization: store.state.user.accessToken,
    },
    data: {
      userId : store.state.user.userId,
      lectureId : props.lecture.lectureId,
    }
  })
  .then(res => {
    route.replace({path:"/main"})
  })
  .catch(err => {
    console.log(err)
  })
}
</script>

<style>

</style>