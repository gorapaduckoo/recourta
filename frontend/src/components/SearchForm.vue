<template>
  <form class="flex justify-center" @submit.prevent="onSearch">
    <div class="flex w-3/4 lg:w-5/6">
      <select name="searching_classification" id="searching_classification" class="form-control block w-2/7 px-3 py-1.5 dark:bg-neutral-600 text-sm font-medium bg-clip-padding border border-solid border-neutral-300 dark:border-neutral-500 focus:border-[#2c5172] focus:border-2 focus:ring-0 rounded transition ease-in-out m-0 focus:outline-none" required>
        <option value="1">전체</option>
        <option value="2">강의명</option>
        <option value="3">강의자</option>
      </select>
      <div class="relative w-full">
        <input type="text" id="searching_text" class="block p-2.5 w-full z-20 text-sm text-neutral-900 bg-neutral-50 rounded-r-lg border-l-neutral-50 border-l-2 border border-neutral-300 focus:border-[#2c5172] dark:bg-[#3a3a3a] dark:border-l-neutral-700  dark:border-neutral-600 dark:placeholder-neutral-400 dark:text-white" placeholder="강의명, 강의자로 검색하세요">
        <button type="submit" class="absolute top-0 right-0 p-2.5 text-sm font-medium text-white bg-[#2c5172] rounded-r-lg border border-[#2c5172] hover:bg-[#325c81]">
          <svg aria-hidden="true" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
          <span class="sr-only">Search</span>
        </button>
      </div>
    </div>
  </form>
</template>

<script setup>
import { useStore } from "vuex"
import { useRouter } from 'vue-router'

const store = useStore()
const route = useRouter()

const props = defineProps({
  allClassList : {
    type: Array,
  }
})

const onSearch = () => {
  const classification = document.getElementById('searching_classification').value
  const searchText = document.getElementById('searching_text').value
  store.commit("UpdateClassification",classification)
  store.commit("UpdateSearchText",searchText)
  // 걸러서 searchList 설정
  if (classification === '1') {
    let tempList = []
    for (let lecture of props.allClassList) {
      if (lecture.title.indexOf(searchText) !== -1 || lecture.teacher.indexOf(searchText) !== -1) {
        tempList.push(lecture)
      }
    }
    store.commit("UpdateSearchList",tempList)
  } else if (classification === '2') {
    let tempList = []
    for (let lecture of props.allClassList) {
      if (lecture.title.indexOf(searchText) !== -1) {
        tempList.push(lecture)
      }
    }
    store.commit("UpdateSearchList",tempList)
  } else if (classification === '3') {
    let tempList = []
    for (let lecture of props.allClassList) {
      if (lecture.teacher.indexOf(searchText) !== -1) {
        tempList.push(lecture)
      }
    }
    store.commit("UpdateSearchList",tempList)
  }
  document.getElementById('searching_classification').value = '1'
  document.getElementById('searching_text').value = ""
  route.push({ name: 'search', params: { searchText: searchText } })
}

</script>

<style>

</style>