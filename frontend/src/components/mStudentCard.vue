<template>

	<div class="flex justify-center mb-3 h-[170px]">
		<div class="flex flex-col border rounded-lg justify-between p-6 bg-[#fffbf8] dark:bg-[#4c4c4c]">
			<div class="text-2xl font-bold mb-2">데이터로 표현한 세상을 보고있는 우리와 나와 바다 놀러가</div>
			<div class="flex justify-between mt-5">
				<div class="text-xl font-semibold text-end">김우석</div>
				<div class="space-x-5">
					<button class="py-1.5 px-3 text-white rounded-md bg-[#2c5172]" data-bs-toggle="modal" data-bs-target="#mAttendanceModal">출결 확인</button>
					<router-link to="/class">
						<button type="button" class="py-1.5 px-3 text-white rounded-md bg-[#2c5172]">강의 정보</button>
					</router-link>
					<router-link to="/class">
						<button type="button" class="py-1.5 px-3 text-white rounded-md bg-[#fe5358]">강의 입장</button>
					</router-link>
				</div>
			</div>
		</div>
	</div>


	<!-- 출결 Modal -->
	<div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="mAttendanceModal" tabindex="-1" aria-labelledby="mAttendanceModalLabel" aria-hidden="true">
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
</template>

<script>
export default {

}
</script>

<style>

</style>