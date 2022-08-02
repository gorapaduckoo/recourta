<template>
  <form class="flex justify-center" @submit.prevent="onSubmit">
    <div class="flex w-3/4 lg:w-5/6">
      <div class="dropdown relative">
        <button class="dropdown-toggle px-4 py-3 bg-neutral-300 dark:bg-neutral-600 font-medium text-sm leading-tight uppercase rounded transition ease-in-out flex items-center whitespace-nowrap" type="button" id="SearchDropdown" data-bs-toggle="dropdown" aria-expanded="false">
          전체
          <svg aria-hidden="true" focusable="false" data-prefix="false" data-icon="caret-down" class="w-2 ml-2" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512">
            <path fill="currentColor" d="M31.3 192h257.3c17.8 0 26.7 21.5 14.1 34.1L174.1 354.8c-7.8 7.8-20.5 7.8-28.3 0L17.2 226.1C4.6 213.5 13.5 192 31.3 192z"></path>
          </svg>
        </button>
        <ul class="dropdown-menu min-w-max absolute bg-neutral-50 dark:bg-neutral-600 text-base px-0.5 z-50 float-left list-none text-left rounded-lg shadow-lg mt-1 hidden m-0 bg-clip-padding border-none" aria-labelledby="SearchDropdown">
          <li>
            <a class="dropdown-item text-sm py-2 px-4 font-normal block w-full whitespace-nowrap bg-transparent hover:bg-neutral-100 dark:hover:bg-[#5f5f5f]" href="#">전체</a>
          </li>
          <li>
            <a class="dropdown-item text-sm py-2 px-4 font-normal block w-full whitespace-nowrap bg-transparent hover:bg-neutral-100 dark:hover:bg-[#5f5f5f]" href="#">강의명</a>
          </li>
          <li>
            <a class="dropdown-item text-sm py-2 px-4 font-normal block w-full whitespace-nowrap bg-transparent hover:bg-neutral-100 dark:hover:bg-[#5f5f5f]" href="#">강의자</a>
          </li>
        </ul>
      </div>
      <div class="relative w-full">
          <input v-model="searchContent" type="text" id="search-dropdown" class="block p-2.5 w-full z-20 text-sm text-neutral-900 bg-neutral-50 rounded-r-lg border-l-neutral-50 border-l-2 border border-neutral-300 focus:border-[#2c5172] dark:bg-[#3a3a3a] dark:border-l-neutral-700  dark:border-neutral-600 dark:placeholder-neutral-400 dark:text-white" placeholder="강의명, 강의자로 검색하세요" required>
          <button type="submit" class="absolute top-0 right-0 p-2.5 text-sm font-medium text-white bg-[#2c5172] rounded-r-lg border border-[#2c5172] hover:bg-[#325c81]">
              <svg aria-hidden="true" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
              <span class="sr-only">Search</span>
          </button>
      </div>
    </div>
  </form>
</template>

<script setup>
import { reactive } from 'vue'
 

const state = reactive({
  searchContent : "",
})

// const onSubmit = () => {
//       // const searchMovie = state.searchContent
//       // state.$store.dispatch('fetchSearches', searchMovie) 
//       state.searchContent = ''
//       // if (searchMovie) {
//       //   this.$router.push({ name: '' })
//       // }
//   },

</script>

<style>

</style>