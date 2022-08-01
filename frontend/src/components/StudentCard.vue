<template>
  <div @mouseenter="cardToggle" @mouseleave="cardToggle" class="relative">
    <!-- 보이는 부분 -->
    <div class="w-[400px] h-[500px] z-0 border-2 rounded-lg border-[#2c5172] dark:border-neutral-500 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <img class="rounded-t-lg w-[400px] h-[225px]" src="https://mdbootstrap.com/img/new/standard/nature/184.jpg" alt=""/>
      <div class="p-6 flex flex-col justify-between h-[275px] dark:text-neutral-100">
        <div>
          <div class="text-3xl text-justify font-bold dark:font-semibold mb-6 h-[72px]">데이터로 표현한 세상을 보고있는 우리와 나와 바다 놀러가</div>
          <div class="text-xl font-semibold mt-4 text-end">김우석</div>
        </div>
        <div class="flex justify-end">
          <div class="text-lg text-start">
            <p>월 13:00 ~ 15:00</p>
            <p>목 10:00 ~ 12:00</p>
            <p>금 09:00 ~ 12:00</p>
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
        <router-link to="/class" class="w-[175px]">
          <button class="w-full py-2.5 text-white rounded-md cursor-pointer bg-black hover:bg-neutral-700 border-gray-300 border-[3px]">강의실 입장</button>
        </router-link>
        <button class="w-[175px] py-2.5 text-white rounded-md cursor-pointer bg-black hover:bg-neutral-700 border-gray-300 border-[3px]" data-bs-toggle="modal" data-bs-target="#attendanceModal">출결 확인</button>
        <button class="w-[175px] py-2.5 text-white rounded-md cursor-pointer bg-black hover:bg-neutral-700 border-gray-300 border-[3px]" data-bs-toggle="modal" data-bs-target="#classoutModal">수강 철회</button>
      </div>
    </div>
  </div>

  <!-- 출결 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="attendanceModal" tabindex="-1" aria-labelledby="attendanceModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered relative w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-6 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body pt-2 pb-4 px-10 text-center">
          <p class="mb-8 font-bold text-2xl">출결 확인</p>
          <!-- Progress Bar -->
          <div class="w-full h-8 mb-8 bg-neutral-200 dark:bg-[#666666] rounded-lg">
            <div class="h-8 bg-[#2c5172] dark:bg-[#325c81] p-0.5 leading-none rounded-lg" style="width: 75%">
              <p class="text-base font-medium text-white text-center">75%</p>
            </div>
          </div>

          <!-- 본문 -->
          <div class="flex flex-wrap w-full mb-6">
            <div class="w-[110px] ml-[30px] mb-6 text-start text-xl font-medium border-r border-neutral-400 dark:border-neutral-500">수업 일수</div>
            <div class="w-[80px] mb-6 text-end text-xl font-semibold">20</div>
            <div class="w-[30px] mr-[30px] mb-6 text-end text-xl font-medium">일</div>
            <div class="w-[110px] ml-[30px] mb-6 text-start text-xl font-medium border-r border-neutral-400 dark:border-neutral-500">정상 출석</div>
            <div class="w-[80px] mb-6 text-end text-xl font-semibold">15</div>
            <div class="w-[30px] mr-[30px] mb-6 text-end text-xl font-medium">일</div>
            <div class="w-[110px] ml-[30px] text-start text-xl font-medium border-r border-neutral-400 dark:border-neutral-500">결석</div>
            <div class="w-[80px] text-end text-xl font-semibold">5</div>
            <div class="w-[30px] mr-[30px] text-end text-xl font-medium">일</div>
          </div>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-6 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 수강 철회 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="classoutModal" tabindex="-1" aria-labelledby="classoutModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered relative w-[440px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-6 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body mt-4 pt-2 pb-4 px-10 text-center">
          <!-- 본문 -->
          <div class="flex flex-wrap w-full mb-6">
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의명</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">데이터로 표현한 세상을 보고있는 우리와 나와 바다 놀러가</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의자</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">김우석</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 기간</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">2022-09-01 ~ 2022-09-30</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 시간</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">월 13:00 ~ 15:00<br/>목 10:00 ~ 12:00<br/>금 09:00 ~ 12:00</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 설명</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">집가고싶다</div>
          </div>

          <p class="mb-4 font-bold dark:font-semibold text-2xl">해당 강의를 취소 하시겠습니까?</p>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-center px-4 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-white bg-[#fe5358] w-[62.3px] border border-[#fe5358] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#fe343b]" data-bs-dismiss="modal">예</button>
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">아니오</button>
        </div>
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