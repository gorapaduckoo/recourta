<template>
  <DarkmodeButton/>
  <CustomNavbar :curpage="state.curpage"/>
  
  <div class="flex justify-end pt-[80px] mr-16 lg:mr-20 2xl:mr-32">
    <button type="submit" class="text-white font-bold w-1/8 bg-[#4fb054] border border-[#4fb054] hover:bg-[#66bb6a] focus:outline-none rounded-lg px-6 py-1.5 text-center" data-bs-toggle="modal" data-bs-target="#classMakeModalLabel">
      강의 등록하기
    </button>
  </div>
  
  <div class="flex items-center mb-8">
		<!-- The left line -->
		<div class="flex-grow h-px bg-neutral-400"></div> 

		<!-- Your text here -->
		<div class="flex-shrink text-2xl font-bold px-4">강의 목록</div>

		<!-- The right line -->
		<div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
	</div>

  <SearchForm />

  

  <SearchList />

  <!-- 강의 만들기 Modal -->
  <div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="classMakeModalLabel" tabindex="-1" aria-labelledby="classMakeModalLabel" aria-hidden="true">
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
          <form class="pt-10" @submit.prevent="signupSubmit">

            <!-- 이름 입력 -->
            <div class="form-group mb-6 flex justify-between">
              <div class="flex flex-col justify-center">강의명 : </div>
              <input type="text" v-model.trim="floating_lecture_name" id="floating_lecture_name" class="form-control block w-3/4 px-3 py-1.5 text-base font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none" placeholder="Name">
            </div>

            <!-- 강의 기간 -->
            <div class="form-group mb-6 flex justify-between">
              <div class="flex flex-col justify-center">강의기간 : </div>
              <input type="date" v-model.trim="floating_lecture_duration_start" id="floating_lecture_duration_start" class="form-control block w-1/3 ml-4 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none">
              <div class="flex flex-col justify-center">-</div>
              <input type="date" v-model.trim="floating_lecture_duration_end" id="floating_lecture_duration_end" class="form-control block w-1/3 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none">
            </div>

            <!-- 강의 시간 -->
            <div class="form-group mb-6 flex justify-between">
              <div class="flex flex-col justify-center">강의 시간 : </div>
              <input type="text" v-model.trim="floating_lecture_name" id="floating_lecture_name" class="form-control block w-3/4 px-3 py-1.5 text-base font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none">
            </div>

            <!-- 강의 설명 -->
            <div class="form-group mb-6 flex justify-between">
              <div >강의 설명 : </div>
              <textarea rows="3" name="floating_lecture_info" v-model.trim="floating_lecture_info" class="form-control block w-3/4 ml-4 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none" placeholder=" "></textarea>
            </div>

            <!-- 강의 썸네일 -->
            <div class="form-group mb-6 flex justify-between">
              <div>썸네일 : </div>
              <input type="file" name="floating_lecture_image" id="floating_lecture_image" class="form-control block w-3/4 ml-4 px-3 py-1.5 text-xs font-normal bg-clip-padding border border-solid border-neutral-300 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none" placeholder=" ">
            </div>

            <!-- 등록하기 버튼 -->
            <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-center px-8 pb-4 rounded-b-md space-x-3">
              <button type="submit" class="text-white bg-[#4fb054] w-[120px] border border-[#4fb054] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#66bb6a]" data-bs-dismiss="modal">강의 만들기</button>
            </div>
          </form>

        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import SearchList from '../components/SearchList.vue'
import SearchForm from '../components/SearchForm.vue'

import { reactive } from 'vue'
 

const state = reactive({
  curpage : "classList",
})

</script>

<style>

</style>