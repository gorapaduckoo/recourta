<template>
  <DarkmodeButton />
  <CustomNavbar />
  <div class="w-60 flex flex-col items-center h-full pt-[70px] absolute border-r-[1px] border-neutral-300 dark:border-neutral-500" id="sidenavSecExample">
    <div>강의제목</div>
    
    <button @click="onClickInfo" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isinfo,'bg-[#ffffff] dark:bg-[#444444]':!state.isinfo}" class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path d="M12 14l9-5-9-5-9 5 9 5z" />
        <path d="M12 14l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14z" />
        <path stroke-linecap="round" stroke-linejoin="round" d="M12 14l9-5-9-5-9 5 9 5zm0 0l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14zm-4 6v-7.5l4-2.222" />
      </svg>
      <span>강의 정보</span>
    </button>
  
    <button @click="onClickAtten" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isatten,'bg-[#ffffff] dark:bg-[#444444]':!state.isatten}" class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
      </svg>
      <span>출석부</span>
    </button>
  
    <button @click="onClickRegi" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isregi,'bg-[#ffffff] dark:bg-[#444444]':!state.isregi}" class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z" />
      </svg>
      <span>신청자 명단</span>
    </button>
  
    <button @click="onClickDM" :class="{'bg-[#ededed] dark:bg-[#545454]':state.isdm,'bg-[#ffffff] dark:bg-[#444444]':!state.isdm}" class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded hover:bg-neutral-200 dark:hover:bg-[#5f5f5f]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
      </svg>
      <span>알림 내역</span>
    </button>
      
    <div class="my-[3px] w-full h-[1px] bg-neutral-300 dark:bg-neutral-500"></div>
    
    <button class="flex items-center w-[14.5rem] text-sm my-[3px] py-4 px-4 h-10 overflow-hidden text-ellipsis whitespace-nowrap rounded text-[#fe5358] hover:text-white hover:bg-[#fe5358]">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
      </svg>
      <span>강의 폐쇄</span>
    </button>
  
  </div>

  <div class="absolute left-[15rem] h-full w-full pt-[70px]">

  </div>
  
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import { ref, reactive } from 'vue'

const state = reactive({
  isinfo:true,
  isatten:false,
  isregi:false,
  isdm:false,
})

const setfalse = () => {
  state.isinfo = false
  state.isatten = false
  state.isregi = false
  state.isdm = false
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

const onClickRegi = () => {
  if(!state.isregi){
    setfalse()
    state.isregi = true
  }
}

const onClickDM = () => {
  if(!state.isdm){
    setfalse()
    state.isdm = true
  }
}

</script>

<style>

</style>