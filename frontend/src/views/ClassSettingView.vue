<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="hidden w-60 lg:flex lg:flex-col items-center h-full pt-[68px] absolute border-r-[1px] border-neutral-300 dark:border-neutral-500">
    <img class="rounded-md w-[224px] h-[126px] mb-[4px]" :src="state.lecImgUrl" alt=""/>
    <div class="text-xl text-justify overflow-hidden px-2 w-full font-bold dark:font-semibold h-[56px]">{{ store.state.lecture.lecInfo.title }}</div>
    
    <button @click="onClickAtten" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isatten,'bg-[#ffffff] dark:bg-[#444444]':!state.isatten}" class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
      </svg>
      <span>출석부</span>
    </button>

    <button @click="onClickInfo" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isinfo,'bg-[#ffffff] dark:bg-[#444444]':!state.isinfo}" class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path d="M12 14l9-5-9-5-9 5 9 5z" />
        <path d="M12 14l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14z" />
        <path stroke-linecap="round" stroke-linejoin="round" d="M12 14l9-5-9-5-9 5 9 5zm0 0l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14zm-4 6v-7.5l4-2.222" />
      </svg>
      <span>강의 정보</span>
    </button>
  
    <button @click="onClickDM" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isdm,'bg-[#ffffff] dark:bg-[#444444]':!state.isdm}" class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
      </svg>
      <span>알림 내역</span>
    </button>
      
    <div class="my-[3px] w-full h-[1px] bg-neutral-300 dark:bg-neutral-500"></div>
    
    <button class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded text-[#fe5358] hover:text-white hover:bg-[#fe5358]" data-bs-toggle="modal" data-bs-target="#closeLectureModal">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
      </svg>
      <span>강의 폐쇄</span>
    </button>
  
  </div>

  <div class="fixed flex w-full flex-col bg-white dark:bg-[#444444] mt-[68px] lg:hidden">
    <div class="flex justify-center space-x-2">
      <button @click="onClickAtten" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isatten,'bg-[#ffffff] dark:bg-[#444444]':!state.isatten}" class="flex justify-center items-center text-sm my-[3px] py-2 px-6 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f] border border-neutral-300 dark:border-neutral-500">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path stroke-linecap="round" stroke-linejoin="round" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
        </svg>
      </button>
    
      <button @click="onClickInfo" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isinfo,'bg-[#ffffff] dark:bg-[#444444]':!state.isinfo}" class="flex justify-center items-center text-sm my-[3px] py-2 px-6 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f] border border-neutral-300 dark:border-neutral-500">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path d="M12 14l9-5-9-5-9 5 9 5z" />
          <path d="M12 14l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14z" />
          <path stroke-linecap="round" stroke-linejoin="round" d="M12 14l9-5-9-5-9 5 9 5zm0 0l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14zm-4 6v-7.5l4-2.222" />
        </svg>
      </button>
    
      <button @click="onClickDM" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isdm,'bg-[#ffffff] dark:bg-[#444444]':!state.isdm}" class="flex justify-center items-center text-sm my-[3px] py-2 px-6 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f] border border-neutral-300 dark:border-neutral-500">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path stroke-linecap="round" stroke-linejoin="round" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
        </svg>
      </button>
      
      <button class="flex justify-center items-center text-sm my-[3px] py-2 px-6 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded text-[#fe5358] hover:text-white hover:bg-[#fe5358] border border-neutral-300 dark:border-neutral-500" data-bs-toggle="modal" data-bs-target="#closeLectureModal">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
      </button>
    </div>
    <div class="py-1 text-center text-lg font-bold dark:font-semibold border-b">{{ store.state.lecture.lecInfo.title }}</div>
  </div>


  <div class="left-[15rem] lg:pl-60 w-full pt-0 lg:pt-[70px]">
    <ClassSetAtt v-if="state.isatten" :studentList="state.studentList.userList"/>
    <ClassSetInfo v-if="state.isinfo" :lecturetime="state.lecturetime"/>
    <ClassSetRegi v-if="state.isregi"/>
    <ClassSetDM v-if="state.isdm"/>
  </div>

  <!-- 강의 폐쇄 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="closeLectureModal" tabindex="-1" aria-labelledby="closeLectureModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body pt-0 pb-4 text-center">
          <p class="font-medium">해당 강의를 폐쇄시키겠습니까?<br/>(폐쇄 시, 해당 강의 관련 모든 정보가 삭제됩니다.)</p>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-4 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-white bg-[#fe5358] w-[62.3px] border border-[#fe5358] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#fe343b]" @click="deleteLecture" data-bs-dismiss="modal">예</button>
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">아니오</button>
        </div>
      </div>
    </div> 
  </div>
  
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import ClassSetInfo from '../components/ClassSetInfo.vue'
import ClassSetAtt from '../components/ClassSetAtt.vue'
import ClassSetRegi from '../components/ClassSetRegi.vue'
import ClassSetDM from '../components/ClassSetDM.vue'
import { ref, reactive } from 'vue'
import { useStore } from "vuex"
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'

const store = useStore()
const route = useRouter()
const rout = useRoute()

// 강의 정보 조회
const getClassInfo = async () => {
  await axios({
    url: rct.lecture.lectureinfo(Number(rout.params.lecId)),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    store.state.lecture.lecInfo = res.data
    state.lecturetime = res.data.lectureTime
    state.lecImgUrl = store.state.lecture.lectureImgFirstUrl+res.data.lectureImg
  })
  .catch(err => {
    console.log(err)
  })
}

// 강의 수강생 조회
const getStudentList = async () => {
  await axios({
    url: rct.regist.currentstudentlist(Number(rout.params.lecId)),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    state.studentList = res.data
  })
  .catch(err => {
    console.log(err)
  })
}

getClassInfo()
getStudentList()

const state = reactive({
  lecturetime: [],
  studentList: {},
  lecImgUrl: "",
  isatten: true,
  isinfo: false,
  isdm: false,
  isham: false,
  curpage:"main",
})

// 강의 폐쇄 함수
const deleteLecture = async () => {
  await axios({
    url: rct.lecture.lectureinfo(Number(rout.params.lecId)),
    method: 'delete',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    route.replace({path:'/main'})
  })
  .catch(err => {
    console.log(err)
  })
}

const setfalse = () => {
  state.isinfo = false
  state.isatten = false
  state.isdm = false
  state.isham = false
}

const onClickInfo = () => {
  if(!state.isinfo){
    setfalse()
    state.isinfo = true
  }
}

const onClickAtten = () => {
  if(!state.isatten){
    setfalse()
    state.isatten = true
  }
}

const onClickDM = () => {
  if(!state.isdm){
    setfalse()
    state.isdm = true
  }
}

const onClickHam = () => {
  state.isham = !state.isham
}

</script>

<style>

</style>