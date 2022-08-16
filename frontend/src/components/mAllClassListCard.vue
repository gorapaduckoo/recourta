<template>

	<div class="flex justify-center mb-3 w-[450px] sm:w-[600px] h-[170px] ml-auto mr-auto">
		<div class="flex flex-col border w-full rounded-lg justify-between p-6 odd:bg-[#fffbf8]">
			<div class="text-gray-900 text-2xl font-bold mb-2">{{ lecpreview.title }}</div>
			<div class="flex justify-between mt-5">
				<div class="text-xl font-semibold text-end">{{ lecpreview.teacher }}</div>
				<div class="space-x-5">
					<button class="py-1.5 px-3 text-white rounded-md bg-[#2c5172]" data-bs-toggle="modal" data-bs-target="#mClassinModalLabel">강의신청</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 수강 신청 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="mClassinModalLabel" tabindex="-1" aria-labelledby="mClassinModalLabel" aria-hidden="true">
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
import { useStore } from 'vuex'

const store = useStore()

// { "lectureId": 18, "title": "ggg", "teacher": "유지슬", "lectureImg": "b785ba11-e5ee-468a-b2cd-b2cf16684201.png", "lectureTime": [ "월 0:00 ~ 4:00", "토 0:00 ~ 4:00", "일 0:00 ~ 4:00" ] }
const props = defineProps({
  lecpreview : {
    type: Object,
  }
})

const lecpreviewThumbnail = store.state.lecture.lectureImgFirstUrl+props.lecpreview.lectureImg

</script>

<style>

</style>