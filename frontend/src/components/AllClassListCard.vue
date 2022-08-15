<template>
  <div>{{lecpreview}}</div>
  <div @mouseenter="cardToggle" @mouseleave="cardToggle" class="relative ml-auto mr-auto mb-10">
    <!-- 보이는 부분 -->
    <div class="w-[400px] h-[500px] z-0 border-2 rounded-lg border-[#2c5172] dark:border-neutral-500 bg-[#fffbf8] dark:bg-[#4c4c4c]">
      <img class="rounded-t-lg w-[400px] h-[225px]" :src="lecpreviewThumbnail" alt=""/>
      <div class="p-6 flex flex-col justify-between h-[275px] dark:text-neutral-100">
        <div>
          <div class="text-3xl text-justify font-bold dark:font-semibold mb-6 h-[72px]">{{ lecpreview.title }}</div>
          <div class="text-xl font-semibold mt-4 text-end">{{ lecpreview.teacher }}</div>
        </div>
        <div class="flex flex-col">
          <div class="text-lg text-end" v-for="lecturetime in lecpreview.lectureTime" :key="lecturetime">
            <p>{{lecturetime}}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Hover시 보이는 부분 -->
    <div v-if="state.isMouseIn" class="absolute top-0 z-10 px-6 flex flex-col w-[400px] h-[500px] bg-black bg-opacity-80 rounded-lg border-2 border-black">
      <div class="h-[225px] flex justify-center items-center">
        <div class="text-3xl font-bold text-white">{{ lecpreview.title }}</div>
      </div>
      <div class="h-[275px] flex flex-col justify-around items-center py-4">
        <button class="w-[175px] py-3 text-white rounded-md cursor-pointer text-xl font-semibold bg-black hover:bg-neutral-700 border-gray-300 border-[3px]" data-bs-toggle="modal" data-bs-target="#classinModalLabel">수강 신청</button>
      </div>
    </div>
  </div>

  <!-- 수강 신청 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="classinModalLabel" tabindex="-1" aria-labelledby="classinModalLabel" aria-hidden="true">
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
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{  }}</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의자</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{  }}</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 기간</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{  }} ~ {{  }}</div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 시간</div>
            <div class="w-[260px] mb-6 flex flex-col">
              <div class="text-start text-lg font-medium" v-for="lecturetime in lecpreview.lectureTime" :key="lecturetime">
                <p>{{lecturetime}}</p>
              </div>
            </div>
            <div class="w-[80px] mr-[20px] mb-6 text-start text-lg font-medium border-r border-neutral-400 dark:border-neutral-500">강의 설명</div>
            <div class="w-[260px] mb-6 text-justify text-lg font-medium">{{  }}</div>
          </div>

          <p class="mb-4 font-bold dark:font-semibold text-2xl">해당 강의를 신청 하시겠습니까?</p>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-center px-4 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-white bg-[#4fb054] w-[62.3px] border border-[#4fb054] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#66bb6a]" data-bs-dismiss="modal">예</button>
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">아니오</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const props = defineProps({
  lecpreview : {
    type: Object,
  }
})

const state = reactive({
  isMouseIn: false,
})

const lecpreviewThumbnail = store.state.lecture.lectureImgFirstUrl+props.lecpreview.lectureImg

const cardToggle = () => {
  state.isMouseIn = !state.isMouseIn
}

</script>

<style>

</style>